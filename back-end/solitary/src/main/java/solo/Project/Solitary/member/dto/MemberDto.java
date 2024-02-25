package solo.Project.Solitary.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
        private String memberName;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    @Data
    public static class MemberPatchDto {
        @Positive
        private long memberId;

        @NotBlank(message = "수정하실 이름을 입력해주세요.")
        private String memberName;

        @NotBlank(message = "수정하실 비밀번호를 입력해주세요.")
        private String password;
    }

    @Builder
    @Data
    public static class MemberResponseDto {
        private String email;
        private String memberName;
    }
}
