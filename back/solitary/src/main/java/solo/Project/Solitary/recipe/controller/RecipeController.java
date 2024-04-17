package solo.Project.Solitary.recipe.controller;


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
                                        @RequestParam("image") MultipartFile image,
                                        @PathVariable("member-id") long memberId) throws IOException {

        imageDataService.uploadImageToFileSystem(image);

        Recipe recipe = mapper.recipePostDtoToRecipe(title, description, category, image.getOriginalFilename());
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
}
