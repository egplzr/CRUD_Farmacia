package br.com.crudfarmacia.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "medicamentos")
public class Medicamento {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome", nullable = false)
    String nome;

    @Column(name = "laboratorio", nullable = false)
    String laboratorio;

    @OneToMany(mappedBy = "medicamento")
    List<Farmaco> principioAtivo;

    @Enumerated(EnumType.STRING)
    Categoria categoria;

    public Medicamento(String nome, String laboratorio, List<Farmaco> principioAtivo, Categoria categoria) {
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.principioAtivo = principioAtivo;
        this.categoria = categoria;
    }
}
