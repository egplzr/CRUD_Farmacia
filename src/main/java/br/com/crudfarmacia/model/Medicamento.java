package br.com.crudfarmacia.model;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medicamento")
public class Medicamento {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    List<Farmaco> principioAtivo;
    Categoria categoria;

    public Medicamento() {
    }
}
