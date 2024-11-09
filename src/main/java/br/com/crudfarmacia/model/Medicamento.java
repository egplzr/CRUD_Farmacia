package br.com.crudfarmacia.model;

import br.com.crudfarmacia.model.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "laboratorio", nullable = false)
    private String laboratorio;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Farmaco> principioAtivo = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Medicamento(String nome, String laboratorio, Categoria categoria) {
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.categoria = categoria;
    }

    public void adicionarFarmaco(Farmaco farmaco) {
        farmaco.setMedicamento(this);
        this.principioAtivo.add(farmaco);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("""
                ------------------------------------------
                MEDICAMENTO: %s
                LABORATÓRIO: %s
                CATEGORIA: %s
                FÁRMACOS: %s
                ------------------------------------------
                """.formatted(this.getNome(), this.getLaboratorio(),
                this.getCategoria(), this.principioAtivo.toString()));

        String descricaoMedicamento = String.valueOf(s);
        return descricaoMedicamento;
    }
}