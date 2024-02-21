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
        @Email
        private String email;

        @NotBlank(message = "공백은 불가능합니다.")
        private String memberName;

        @NotBlank(message = "공백은 불가능합니다.")
        private String password;
    }

    @Data
    public static class MemberPatchDto {
        @Positive
        private long memberId;

        @NotBlank(message = "공백은 불가능합니다.")
        private String memberName;

        @NotBlank(message = "공백은 불가능합니다.")
        private String password;
    }

    @Builder
    @Data
    public static class MemberResponseDto {
        private String email;
        private String memberName;
    }
}
