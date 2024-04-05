package solo.Project.Solitary.recipe.controller;


import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import solo.Project.Solitary.image.service.ImageDataService;
import solo.Project.Solitary.recipe.entity.Recipe;
import solo.Project.Solitary.recipe.mapper.RecipeMapper;
import solo.Project.Solitary.recipe.service.RecipeService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        RecipeResponseDto response = mapper.RecipeToRecipeResponseDto(recipe);

        return ResponseEntity.ok().body(response);
    }
}
