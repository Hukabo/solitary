package solo.Project.Solitary.member.mapper;

import org.mapstruct.Mapper;
import solo.Project.Solitary.member.dto.MemberDto;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.member.role.Role;

import static solo.Project.Solitary.member.dto.MemberDto.*;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    default Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        if (memberPostDto == null) {
            return null;
        }

        return Member.builder()
                .email(memberPostDto.getEmail())
                .memberName(memberPostDto.getMemberName())
                .password(memberPostDto.getPassword())
                .role(Role.USER)
                .build();
    }

    default Member memberLoginDtoToMember(MemberLoginDto memberLoginDto) {

        if (memberLoginDto == null) {
            return null;
        }

        return Member.builder()
                .email(memberLoginDto.getEmail())
                .password(memberLoginDto.getPassword())
                .build();
    }

    default Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {

        if (memberPatchDto == null) {
            return null;
        }

        return Member.builder()
                .memberId(memberPatchDto.getMemberId())
                .memberName(memberPatchDto.getMemberName())
                .password(memberPatchDto.getPassword())
                .build();
    }
    default MemberResponseDto memberToResponseDto(Member member) {
        if (member == null) {
            return null;
        }

        return MemberResponseDto.builder()
                .email(member.getEmail())
                .memberName(member.getMemberName())
                .build();
    }
}
