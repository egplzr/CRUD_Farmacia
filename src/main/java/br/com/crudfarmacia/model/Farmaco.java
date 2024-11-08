package br.com.crudfarmacia.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "farmacos")
public class Farmaco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome", nullable = false)
    String nome;

    @Column(name = "peso", nullable = false)
    String peso;

    @ManyToMany
    Medicamento medicamento;

    public Farmaco(String nome, String peso) {
        this.nome = nome;
        this.peso = peso;
    }
}
