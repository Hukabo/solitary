package solo.Project.Solitary.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.Project.Solitary.comment.entity.Comment;
import solo.Project.Solitary.comment.repository.CommentRepository;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.member.sevice.MemberService;
import solo.Project.Solitary.recipe.entity.Recipe;
import solo.Project.Solitary.recipe.service.RecipeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final RecipeService recipeService;
    private final MemberService memberService;


    public Comment createComment(Comment comment, Long memberId, Long recipeId) {
        Recipe findRecipe = recipeService.findVerifiedRecipe(recipeId);
        comment.setRecipe(findRecipe);

        Member findMember = memberService.findVerifiedMember(memberId);
        comment.setMember(findMember);

        return commentRepository.save(comment);
    }

    public List<Comment> findAllComment(long recipeId) {
        Recipe findRecipe = recipeService.findVerifiedRecipe(recipeId);
        List<Comment> comments = commentRepository.findAllByRecipe(findRecipe);

        return comments;
    }
}
