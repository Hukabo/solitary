package solo.Project.Solitary.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class CommentDto {
    @Data
    public static class PostCommentDto {
        private String content;
    }

    @Builder
    @Data
    public static class ResponseCommentDto {
        private String content;
        private String author;
        private Long memberId;
        private Long recipeId;
    }

    @Data
    public static class ResponseCommentsDto {
        private List<ResponseCommentDto> comments = new ArrayList<>();
    }
}