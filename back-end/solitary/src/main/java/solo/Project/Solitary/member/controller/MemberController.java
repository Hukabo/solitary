package solo.Project.Solitary.member.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private MemberService memberService;
    private MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> postMember(@Valid @RequestBody MemberPostDto requestBody) {
        Member member = mapper.memberPostDtoToMember(requestBody);
        memberService.createMember(member);

        return new ResponseEntity<>(mapper.memberToResponseDto(member), HttpStatus.CREATED);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity<?> getMember(@PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToResponseDto(member), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> patchMember(@RequestBody MemberPatchDto memberPatchDto) {
        Member member = memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(mapper.memberToResponseDto(member), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
