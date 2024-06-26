package solo.Project.Solitary.recipe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import solo.Project.Solitary.comment.entity.Comment;
import solo.Project.Solitary.member.entity.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(length = 20)
    private LocalDateTime modifiedAt;

    @Getter
    @AllArgsConstructor
    public enum Category {
        KOREAN("한식"),
        WESTERN("양식"),
        CHINESE("중식"),
        JAPANESE("일식"),
        OTHER("기타");

        private final String category;

        public String getCategory() {
            return this.category;
        }
    }

    public void setComment(Comment comment) {
        this.comments.add(comment);
        comment.setRecipe(this);
    }
}
