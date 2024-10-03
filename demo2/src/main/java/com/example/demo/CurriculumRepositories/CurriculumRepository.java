package com.example.demo.CurriculumRepositories;

import com.example.demo.Model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    @Query("SELECT c FROM Curriculum c WHERE c.nome =: name")
    List<Curriculum> findCurriculumByName(String name);
}
