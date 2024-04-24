package solo.Project.Solitary.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solo.Project.Solitary.comment.entity.Comment;
import solo.Project.Solitary.comment.mapper.CommentMapper;
import solo.Project.Solitary.comment.service.CommentService;

import java.util.List;

import static solo.Project.Solitary.comment.dto.CommentDto.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper mapper;

    @PostMapping("/{member-id}/{recipe-id}")
    public ResponseEntity<?> postComment(@RequestBody PostCommentDto postCommentDto,
                                         @PathVariable("member-id") long memberId,
                                         @PathVariable("recipe-id") long recipeId) {
        Comment comment = mapper.postCommentDtoToComment(postCommentDto);
        Comment savedComment = commentService.createComment(comment, memberId, recipeId);

        ResponseCommentDto response = mapper.commentToResponseCommentDto(savedComment);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{recipe-id}")
    public ResponseEntity<?> getComments(@PathVariable("recipe-id") long recipeId) {

        List<Comment> comments = commentService.findAllComment(recipeId);

        ResponseCommentsDto responseCommentsDto = mapper.commentsToResponseCommentsDto(comments);

        return ResponseEntity.ok(responseCommentsDto);
    }
}
