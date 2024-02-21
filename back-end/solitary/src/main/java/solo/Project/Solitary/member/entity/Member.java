package solo.Project.Solitary.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String memberName;

    @Column
    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private MemberRating memberRating = MemberRating.BRONZE;

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(length = 20)
    private LocalDateTime modifiedAt;

    @Getter
    @AllArgsConstructor
    public enum MemberRating {
        BRONZE("초보 요리사"),
        SILVER("보통 요리사"),
        GOLD("고수 요리사"),
        DIAMOND("요리의 신");

        private String title;
    }
}
