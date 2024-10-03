package com.example.demo.Controller;

import com.example.demo.Model.Education;
import com.example.demo.Services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/curriculums/{curriculumId}/educations")
@CrossOrigin(origins = "*")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping
    public ResponseEntity<Education> addEducation(@PathVariable Long curriculumId, @RequestBody Education education) {
        Education createdEducation = educationService.addEducationToCurriculum(curriculumId, education);
        return ResponseEntity.ok(createdEducation);
    }

    @GetMapping
    public ResponseEntity<List<Education>> getAllEducations(@PathVariable Long curriculumId) {
        List<Education> educations = educationService.getEducationsByCurriculumId(curriculumId);
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable Long curriculumId, @PathVariable Long id) {
        Education education = educationService.getEducationById(id);
        if (!education.getCurriculum().getId().equals(curriculumId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(education);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long curriculumId, @PathVariable Long id, @RequestBody Education educationDetails) {
        Education education = educationService.getEducationById(id);
        if (!education.getCurriculum().getId().equals(curriculumId)) {
            return ResponseEntity.badRequest().build();
        }
        Education updatedEducation = educationService.updateEducation(id, educationDetails);
        return ResponseEntity.ok(updatedEducation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long curriculumId, @PathVariable Long id) {
        Education education = educationService.getEducationById(id);
        if (!education.getCurriculum().getId().equals(curriculumId)) {
            return ResponseEntity.badRequest().build();
        }
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }
}
