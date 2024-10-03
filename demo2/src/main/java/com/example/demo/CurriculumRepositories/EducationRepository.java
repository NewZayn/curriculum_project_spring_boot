package com.example.demo.CurriculumRepositories;
import com.example.demo.Model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByCurriculumId(Long curriculumId);
}
