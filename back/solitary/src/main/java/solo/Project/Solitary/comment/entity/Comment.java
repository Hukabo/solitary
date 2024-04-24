package solo.Project.Solitary.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import solo.Project.Solitary.member.entity.Member;
import solo.Project.Solitary.recipe.entity.Recipe;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPE_ID")
    @Setter(value = AccessLevel.NONE) // setter 비활성화
    @JsonIgnore
    private Recipe recipe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    @Setter(value = AccessLevel.NONE)
    @JsonIgnore
    private Member member;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipe.getComments().add(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }
}
