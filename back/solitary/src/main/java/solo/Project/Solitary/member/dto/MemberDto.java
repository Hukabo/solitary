package solo.Project.Solitary.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class MemberDto {
    @Data
    public static class MemberPostDto {
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "올바른 이메일을 입력해주세요.")
        private String email;

        @NotBlank(message = "이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 가능하십니다.")
        private String memberName;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=])(?!.*\\s).{8,}$",
                message = "영문,숫자,특수문자를 조합하여 8글자를 만들어주세요.")
        private String password;
    }

    @Data
    @Builder
    public static class AuthenticationResponse {
        private String token;
    }

    @Data
    public static class MemberLoginDto {
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "올바른 이메일을 입력해주세요.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=])(?!.*\\s).{8,}$",
                message = "영문,숫자,특수문자를 조합하여 8글자를 만들어주세요.")
        private String password;
    }

    @Data
    public static class MemberPatchDto {
        @Positive
        private long memberId;

        @NotBlank(message = "이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 가능하십니다.")
        private String memberName;

        @NotBlank(message = "수정하실 비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=])(?!.*\\s).{8,}$",
                message = "영문,숫자,특수문자를 조합하여 8글자를 만들어주세요.")
        private String password;
    }

    @Builder
    @Data
    public static class MemberResponseDto {
        private String email;
        private String memberName;
    }

    @Builder
    @Data
    public static class LoginMemberResponseDto {
        private Long memberId;
        private String memberName;
    }

    @Builder
    @Data
    public static class MemberRecipeResponseDto {
        private Long recipeId;
        private String title;
        private String createdAt;
    }
}
