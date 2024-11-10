package br.com.crudfarmacia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "farmacos")
public class Farmaco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "peso", nullable = false)
    private String peso;

    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    public Farmaco(String nome, String peso, Medicamento medicamento) {
        this.nome = nome;
        this.peso = peso;
        this.medicamento = medicamento;
    }

    public String toString() {
        String descricaoFarmaco = "%s: %s".formatted(this.getNome(), this.getPeso());
        return descricaoFarmaco;
    }
}