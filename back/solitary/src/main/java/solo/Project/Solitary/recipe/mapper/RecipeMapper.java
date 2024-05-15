package solo.Project.Solitary.recipe.mapper;

import org.mapstruct.Mapper;
import solo.Project.Solitary.recipe.entity.Recipe;

import static solo.Project.Solitary.recipe.dto.RecipeDto.*;
@Mapper(componentModel = "spring")
public interface RecipeMapper {
    default Recipe recipePostDtoToRecipe(String title,
                                         String description,
                                         String category,
                                         String imageName) {

        Recipe recipe = Recipe.builder()
                .title(title)
                .description(description)
                .imageName(imageName)
                .build();

        switch (category) {
            case "korean":
                recipe.setCategory(Recipe.Category.KOREAN);
                break;
            case "western":
                recipe.setCategory(Recipe.Category.WESTERN);
                break;
            case "chinese":
                recipe.setCategory(Recipe.Category.CHINESE);
                break;
            case "japanese":
                recipe.setCategory(Recipe.Category.JAPANESE);
                break;
            default:
                recipe.setCategory(Recipe.Category.OTHER);
        }

        return recipe;
    }

    default Recipe recipePatchDtoToRecipe(String title,
                                                  String description,
                                                  String category) {
        Recipe recipe = Recipe.builder()
                .title(title)
                .description(description)
                .build();

        switch (category) {
            case "korean":
                recipe.setCategory(Recipe.Category.KOREAN);
                break;
            case "western":
                recipe.setCategory(Recipe.Category.WESTERN);
                break;
            case "chinese":
                recipe.setCategory(Recipe.Category.CHINESE);
                break;
            case "japanese":
                recipe.setCategory(Recipe.Category.JAPANESE);
                break;
            default:
                recipe.setCategory(Recipe.Category.OTHER);
        }

        return recipe;
    }

    default RecipeResponseDto recipeToRecipeResponseDto(Recipe recipe) {

        if (recipe == null) {
            return null;
        }

        return RecipeResponseDto.builder()
                .recipeId(recipe.getRecipeId())
                .title(recipe.getTitle())
                .description(recipe.getDescription())
                .category(recipe.getCategory().getCategory())
                .imageName(recipe.getImageName())
                .memberName(recipe.getMember().getMemberName())
                .createdAt(recipe.getCreatedAt())
                .build();
    }
}
