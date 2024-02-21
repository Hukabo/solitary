package solo.Project.Solitary.member.sevice;

import org.springframework.stereotype.Service;
import solo.Project.Solitary.member.dto.MemberDto;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.exception.BusinessLogicException;
import solo.Project.Solitary.exception.ExceptionCode;
import solo.Project.Solitary.member.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        verifyExistMember(member.getEmail());

        return memberRepository.save(member);
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
