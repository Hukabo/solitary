package solo.Project.Solitary.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import solo.Project.Solitary.exception.BusinessLogicException;
import solo.Project.Solitary.exception.ExceptionCode;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.member.repository.MemberRepository;
import solo.Project.Solitary.recipe.RecipeRepository.RecipeRepository;
import solo.Project.Solitary.recipe.entity.Recipe;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;

    public Recipe createRecipe(Recipe recipe, Long memberId) {

        Member findMember = memberRepository.findById(memberId).orElseThrow();

        recipe.setMember(findMember);

        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) {

        return null;
    }

    public Recipe findRecipe(long recipeId) {

        return findVerifiedRecipe(recipeId);
    }

    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> findAllRecipesByField(String field) {
        return recipeRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Recipe> findRecipesWithPagination(int offset, int pageSize) {
        Page<Recipe> recipesPage = recipeRepository.findAll(PageRequest.of(offset, pageSize));
        return recipesPage;
    }

    public Page<Recipe> findRecipesWithPaginationByField(int offset, int pageSize, String field) {
        Page<Recipe> recipesPage = recipeRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return recipesPage;
    }

    public void deleteRecipe(Long recipeId) {

        recipeRepository.deleteById(recipeId);
    }

    private Recipe findVerifiedRecipe(long recipeId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        return optionalRecipe.orElseThrow(() -> new BusinessLogicException(ExceptionCode.RECIPE_NOT_FOUND));
    }
}
