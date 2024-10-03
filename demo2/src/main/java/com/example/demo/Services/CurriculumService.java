package com.example.demo.Services;


import com.example.demo.CurriculumRepositories.CurriculumRepository;
import com.example.demo.Exception.ObjectNotFound;
import com.example.demo.Model.Curriculum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CurriculumService {

    private final CurriculumRepository curriculumRepository;

    @Autowired
    public CurriculumService(CurriculumRepository curriculumRepository) {
        this.curriculumRepository = curriculumRepository;
    }

    public Curriculum add(Curriculum curriculum) {
        curriculumRepository.save(curriculum);
        return curriculum;
    }

    public Optional<Curriculum> getCurriculumById(Long id) {
        return Optional.ofNullable(curriculumRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Curriculum not found")));
    }
    public List<Curriculum> getAllCurriculum() {
        return curriculumRepository.findAll();
    }

    public void deleteCurriculum(Long id) {
        curriculumRepository.deleteById(id);
    }
}
