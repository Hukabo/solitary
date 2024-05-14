package solo.Project.Solitary.member.mapper;

import org.mapstruct.Mapper;
import solo.Project.Solitary.member.dto.MemberDto;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.member.role.Role;
import solo.Project.Solitary.recipe.entity.Recipe;
import solo.Project.Solitary.response.PageResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    default LoginMemberResponseDto memberToLoginMemberResponseDto(Member member) {
        if (member == null) {
            return null;
        }

        return LoginMemberResponseDto.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .build();
    }

    default MemberRecipeResponseDto recipeToMemberRecipeDto(Recipe recipe) {
        if(recipe == null) {
            return null;
        }

        LocalDateTime createdAt = recipe.getCreatedAt();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = createdAt.format(formatter);

        return MemberRecipeResponseDto.builder()
                .recipeId(recipe.getRecipeId())
                .title(recipe.getTitle())
                .createdAt(date)
                .build();
    }

    default PageResponse<?> memberRecipePage(List<Recipe> recipes) {
        if (recipes == null) {
            return null;
        }

        List<MemberRecipeResponseDto> list = new ArrayList<>();

        for(Recipe recipe : recipes) {
            MemberRecipeResponseDto memberRecipeResponseDto = recipeToMemberRecipeDto(recipe);
            list.add(memberRecipeResponseDto);
        }

        return new PageResponse<>(recipes.size(), list);
    }
}
