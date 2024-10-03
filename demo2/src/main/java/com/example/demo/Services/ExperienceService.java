package com.example.demo.Services;
import com.example.demo.Exception.ObjectNotFound;
import com.example.demo.Model.Curriculum;
import com.example.demo.Model.Experience;
import com.example.demo.CurriculumRepositories.CurriculumRepository;
import com.example.demo.CurriculumRepositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private CurriculumRepository curriculumRepository;

    public Experience addExperienceToCurriculum(Long curriculumId, Experience experience) {
        Curriculum curriculum = curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new ObjectNotFound("Currículo não encontrado com id: " + curriculumId));
        experience.setCurriculum(curriculum);
        return experienceRepository.save(experience);
    }

    public Experience getExperienceById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Experiência não encontrada com id: " + id));
    }

    public List<Experience> getExperiencesByCurriculumId(Long curriculumId) {
        return experienceRepository.findByCurriculumId(curriculumId);
    }

    public Experience updateExperience(Long id, Experience experienceDetails) {
        Experience experience = getExperienceById(id);
        experience.setCompanyName(experienceDetails.getCompanyName());
        experience.setPosition(experienceDetails.getPosition());
        experience.setStartDate(experienceDetails.getStartDate());
        experience.setEndDate(experienceDetails.getEndDate());
        experience.setDescription(experienceDetails.getDescription());
        return experienceRepository.save(experience);
    }

    public void deleteExperience(Long id) {
        Experience experience = getExperienceById(id);
        experienceRepository.delete(experience);
    }
}
