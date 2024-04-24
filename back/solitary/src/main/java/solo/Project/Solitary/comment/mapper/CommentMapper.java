package solo.Project.Solitary.comment.mapper;

import org.mapstruct.Mapper;
import solo.Project.Solitary.comment.dto.CommentDto;
import solo.Project.Solitary.comment.entity.Comment;

import java.util.List;

import static solo.Project.Solitary.comment.dto.CommentDto.*;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment postCommentDtoToComment(PostCommentDto postCommentDto) {
        if (postCommentDto == null) {
            return null;
        }

        return Comment.builder()
                .content(postCommentDto.getContent())
                .build();
    }

    default ResponseCommentDto commentToResponseCommentDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        return ResponseCommentDto.builder()
                .content(comment.getContent())
                .author(comment.getMember().getMemberName())
                .memberId(comment.getMember().getMemberId())
                .recipeId(comment.getRecipe().getRecipeId())
                .build();
    }

    default ResponseCommentsDto commentsToResponseCommentsDto(List<Comment> comments) {
        if (comments.isEmpty()) {
            return null;
        }

        ResponseCommentsDto responseCommentsDto = new ResponseCommentsDto();

        for (Comment comment : comments) {

            ResponseCommentDto responseCommentDto = commentToResponseCommentDto(comment);

            responseCommentsDto.getComments().add(responseCommentDto);
        }

        return responseCommentsDto;
    }
}
