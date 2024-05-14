package solo.Project.Solitary.member.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import solo.Project.Solitary.member.dto.MemberDto;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.member.mapper.MemberMapper;
import solo.Project.Solitary.member.sevice.MemberService;

import static solo.Project.Solitary.member.dto.MemberDto.*;

@RestController
@RequestMapping("/member")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> postMember(@Valid @RequestBody MemberPostDto requestBody) {

        Member member = memberService.createMember(mapper.memberPostDtoToMember(requestBody));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + member.getToken()); // 추후에 제거. 이유는 로그인 절차에서는 유효한 회원인지만 검증하고 성공 후 로그인 할 때 토큰 지급

        return ResponseEntity.ok().headers(headers).body(mapper.memberToResponseDto(member));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginDto memberLoginDto) {
        Member member = mapper.memberLoginDtoToMember(memberLoginDto);

        Member loginMember = memberService.joinMember(member);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + loginMember.getToken());

        LoginMemberResponseDto response = mapper.memberToLoginMemberResponseDto(loginMember);
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity<?> getMember(@PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToResponseDto(member), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> patchMember(@Valid @RequestBody MemberPatchDto memberPatchDto) {
        Member member = memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(mapper.memberToResponseDto(member), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
