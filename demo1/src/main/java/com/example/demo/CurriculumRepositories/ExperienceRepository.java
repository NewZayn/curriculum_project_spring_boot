package com.example.demo.CurriculumRepositories;
import com.example.demo.Model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByCurriculumId(Long curriculumId);
}
