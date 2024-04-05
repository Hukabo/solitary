package solo.Project.Solitary.recipe.dto;

import lombok.Builder;
import lombok.Data;


public class RecipeDto {
    @Data
    public static class RecipePostDto {
        private Long memberId;
        private String title;
        private String description;
        private String imagePath;
        private String category;
    }

    @Data
    public static class RecipePatchDto {
        private String title;
        private String description;
        private String imagePath;
        private String category;
    }

    @Builder
    @Data
    public static class RecipeResponseDto {
        private Long recipeId;
        private String title;
        private String description;
        private String imageName;
        private String category;
    }
}
