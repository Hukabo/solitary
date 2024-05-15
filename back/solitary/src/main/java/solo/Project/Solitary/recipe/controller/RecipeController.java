package solo.Project.Solitary.recipe.controller;


import jakarta.annotation.Nullable;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import solo.Project.Solitary.image.service.ImageDataService;
import solo.Project.Solitary.recipe.entity.Recipe;
import solo.Project.Solitary.recipe.mapper.RecipeMapper;
import solo.Project.Solitary.recipe.service.RecipeService;
import solo.Project.Solitary.response.PageResponse;

import java.io.IOException;
import java.util.List;

import static solo.Project.Solitary.recipe.dto.RecipeDto.*;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final ImageDataService imageDataService;
    private final RecipeMapper mapper;

    @PostMapping("/{member-id}")
    public ResponseEntity<?> postRecipe(@RequestParam("title") String title,
                                        @RequestParam("description") String description,
                                        @RequestParam("category") String category,
                                        @RequestParam("image") @Nullable MultipartFile image,
                                        @PathVariable("member-id") long memberId) throws IOException {
        // 이미지 없을 경우 고려하여 로직 짜기
        String imageName = "";

        if(image != null) {
            imageDataService.uploadImageToFileSystem(image);
            imageName = image.getOriginalFilename();
        }

        Recipe recipe = mapper.recipePostDtoToRecipe(title, description, category, imageName);
        recipeService.createRecipe(recipe, memberId);

        RecipeResponseDto response = mapper.recipeToRecipeResponseDto(recipe);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<?> getRecipe(@PathVariable long recipeId) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        RecipeResponseDto response = mapper.recipeToRecipeResponseDto(recipe);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/recipes")
    private PageResponse<List<Recipe>> getRecipes() {
        List<Recipe> allRecipes = recipeService.findAllRecipes();

        return new PageResponse<>(allRecipes.size(), allRecipes);
    }

    @GetMapping("/recipes/{field}")
    private PageResponse<List<Recipe>> getRecipesByField(@PathVariable String field) {
        List<Recipe> allRecipesByField = recipeService.findAllRecipesByField(field);

        return new PageResponse<>(allRecipesByField.size(), allRecipesByField);
    }

    @GetMapping("/recipes/page/{offset}/{pageSize}")
    private PageResponse<Page<Recipe>> getRecipesWithPage(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Recipe> page = recipeService.findRecipesWithPagination(offset, pageSize);

        return new PageResponse<>(page.getSize(), page);
    }

    @GetMapping("/recipes/page/{offset}/{pageSize}/{field}")
    private PageResponse<Page<Recipe>> getRecipesWithPageByField(@PathVariable int offset, @PathVariable int pageSize,
                                                                 @PathVariable String field) {
        Page<Recipe> page = recipeService.findRecipesWithPaginationByField(offset, pageSize, field);

        return new PageResponse<>(page.getSize(), page);
    }

    @PatchMapping("/{recipe-id}")
    public ResponseEntity<?> patchRecipe(@RequestParam("title") String title,
                                        @RequestParam("description") String description,
                                        @RequestParam("category") String category,
                                        @PathVariable("recipe-id") long recipeId) {

        Recipe recipe = mapper.recipePatchDtoToRecipe(title, description, category);
        recipeService.updateRecipe(recipe, recipeId);

        return ResponseEntity.ok().body("레시피가 수정되었습니다.");
    }

    @DeleteMapping("/{recipe-id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("recipe-id") Long recipeId) {

        recipeService.deleteRecipe(recipeId);

        return ResponseEntity.ok("레시피가 삭제되었습니다.");
    }
}
