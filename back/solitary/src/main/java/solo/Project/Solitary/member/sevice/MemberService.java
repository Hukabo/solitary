package solo.Project.Solitary.member.sevice;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solo.Project.Solitary.config.JwtService;
import solo.Project.Solitary.member.dto.MemberDto;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.exception.BusinessLogicException;
import solo.Project.Solitary.exception.ExceptionCode;
import solo.Project.Solitary.member.repository.MemberRepository;

import java.util.HashMap;
import java.util.Optional;

import static solo.Project.Solitary.member.dto.MemberDto.*;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public Member createMember(Member member) {
        verifyExistMember(member.getEmail());

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        String token = jwtService.generateToken(member);
        member.setToken(token);

        return memberRepository.save(member);
    }

    public Member joinMember(Member member) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword()));

        Member findMember = memberRepository.findByEmail(authenticate.getName()).orElseThrow();

        String token = jwtService.generateToken(findMember);

        findMember.setToken(token);

        return findMember;
    }

    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getMemberName()).ifPresent(findMember::setMemberName);
        Optional.ofNullable(member.getPassword()).ifPresent(findMember::setPassword);

        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId) {

        return findVerifiedMember(memberId);
    }

    public void deleteMember(long memberId) {

        memberRepository.deleteById(memberId);
    }

    public void verifyExistMember(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXIST);
        }
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        return optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}
