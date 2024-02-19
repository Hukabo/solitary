package solo.Project.Solitary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solo.Project.Solitary.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
