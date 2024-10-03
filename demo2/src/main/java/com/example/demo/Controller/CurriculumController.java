package com.example.demo.Controller;


import com.example.demo.Model.Curriculum;
import com.example.demo.Services.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/curriculums")
public class CurriculumController {

    private final CurriculumService curriculumServices;

    @Autowired
    public CurriculumController(CurriculumService curriculumServices) {
        this.curriculumServices = curriculumServices;
    }

    @GetMapping
    public List<Curriculum> getAllCurriculums() {
        return curriculumServices.getAllCurriculum();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Curriculum>> getCurriculumById(@PathVariable Long id) {
        Optional<Curriculum> curriculum = curriculumServices.getCurriculumById(id);
        if (curriculum.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curriculum);
    }

    @PostMapping
    public Curriculum createCurriculum(@RequestBody Curriculum curriculum) {
        return curriculumServices.add(curriculum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curriculum> updateCurriculum(@PathVariable Long id, @RequestBody Curriculum curriculumDetails) {
        Curriculum curriculum = curriculumServices.getCurriculumById(id).orElseThrow(() -> new RuntimeException("Curriculum not found"));
        if (curriculum == null) {
            return ResponseEntity.notFound().build();
        }
        curriculum.setNome(curriculumDetails.getNome());
        curriculum.setResumo(curriculumDetails.getResumo());
        Curriculum updatedCurriculum = curriculumServices.add(curriculum);
        return ResponseEntity.ok(updatedCurriculum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurriculum(@PathVariable Long id) {
        curriculumServices.deleteCurriculum(id);
        return ResponseEntity.noContent().build();
    }
}
