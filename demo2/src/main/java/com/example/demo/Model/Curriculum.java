package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String resumo;
    private LocalDate dataNascimento;
    private String endereco;
    private String telefone;
    private String email;
    private String linkedin;
    private String github;
    private String objetivo;
    private String habilidades;
}

