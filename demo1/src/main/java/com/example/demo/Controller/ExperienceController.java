package com.example.demo.Controller;
import com.example.demo.Model.Experience;
import com.example.demo.Services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/curriculums/{curriculumId}/experiences")
@CrossOrigin(origins = "*")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @PostMapping()
    public ResponseEntity<Experience> addExperience(@PathVariable Long curriculumId, @RequestBody Experience experience) {
        Experience createdExperience = experienceService.addExperienceToCurriculum(curriculumId, experience);
        return ResponseEntity.ok(createdExperience);
    }

    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences(@PathVariable Long curriculumId) {
        List<Experience> experiences = experienceService.getExperiencesByCurriculumId(curriculumId);
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long curriculumId, @PathVariable Long id) {
        Experience experience = experienceService.getExperienceById(id);
        if (!experience.getCurriculum().getId().equals(curriculumId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(experience);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long curriculumId, @PathVariable Long id, @RequestBody Experience experienceDetails) {
        Experience experience = experienceService.getExperienceById(id);
        if (!experience.getCurriculum().getId().equals(curriculumId)) {
            return ResponseEntity.badRequest().build();
        }
        Experience updatedExperience = experienceService.updateExperience(id, experienceDetails);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long curriculumId, @PathVariable Long id) {
        Experience experience = experienceService.getExperienceById(id);
        if (!experience.getCurriculum().getId().equals(curriculumId)) {
            return ResponseEntity.badRequest().build();
        }
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}
