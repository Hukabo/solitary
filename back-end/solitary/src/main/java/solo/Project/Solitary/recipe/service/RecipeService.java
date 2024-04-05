package solo.Project.Solitary.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.member.repository.MemberRepository;
import solo.Project.Solitary.recipe.RecipeRepository.RecipeRepository;
import solo.Project.Solitary.recipe.entity.Recipe;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;

    public Recipe createRecipe(Recipe recipe, Long memberId) {

        Member findMember = memberRepository.findById(memberId).orElseThrow();

        findMember.getRecipes().add(recipe);
        recipe.setMember(findMember);

        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) {

        return null;
    }

    public Recipe findRecipes(Pageable pageable) {

        return null;
    }

    public void deleteRecipe(Long recipeId) {

        recipeRepository.deleteById(recipeId);
    }
}
