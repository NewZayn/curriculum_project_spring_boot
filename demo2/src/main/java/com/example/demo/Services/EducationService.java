package com.example.demo.Services;
import com.example.demo.Exception.ObjectNotFound;
import com.example.demo.Model.Curriculum;
import com.example.demo.Model.Education;
import com.example.demo.CurriculumRepositories.CurriculumRepository;
import com.example.demo.CurriculumRepositories.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private CurriculumRepository curriculumRepository;

    public Education addEducationToCurriculum(Long curriculumId, Education education) {
        Curriculum curriculum = curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new ObjectNotFound("Currículo não encontrado com id: " + curriculumId));
        education.setCurriculum(curriculum);
        return educationRepository.save(education);
    }

    public Education getEducationById(Long id) {
        return educationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Educação não encontrada com id: " + id));
    }

    public List<Education> getEducationsByCurriculumId(Long curriculumId) {
        return educationRepository.findByCurriculumId(curriculumId);
    }

    public Education updateEducation(Long id, Education educationDetails) {
        Education education = getEducationById(id);
        education.setInstitution(educationDetails.getInstitution());
        education.setDegree(educationDetails.getDegree());
        education.setStartDate(educationDetails.getStartDate());
        education.setEndDate(educationDetails.getEndDate());
        return educationRepository.save(education);
    }

    public void deleteEducation(Long id) {
        Education education = getEducationById(id);
        educationRepository.delete(education);
    }
}
