package solo.Project.Solitary.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    MEMBER_EXIST(404, "member is already exist"),
    MEMBER_NOT_FOUND(404,"member not found"),
    IMAGE_NOT_FOUNT(404, "image not found");


    private int status;
    private String message;
    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
