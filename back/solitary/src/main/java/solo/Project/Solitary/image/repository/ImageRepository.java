package solo.Project.Solitary.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solo.Project.Solitary.image.entity.ImageData;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByImageName(String fileName);
}
