package solo.Project.Solitary.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solo.Project.Solitary.comment.entity.Comment;
import solo.Project.Solitary.recipe.entity.Recipe;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByRecipe(Recipe recipe);
}
