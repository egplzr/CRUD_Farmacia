package br.com.crudfarmacia.model;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medicamentos")
public class Medicamento {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome", nullable = false)
    String nome;

    @Column(name = "laboratorio", nullable = false)
    String laboratorio;

    @ManyToMany
    @JoinTable(name = "medicamentos_farmacos",
            joinColumns = @JoinColumn(name = "id_medicamento"),
            inverseJoinColumns = @JoinColumn(name = "id_farmaco"))
    List<Farmaco> principioAtivo;

    @Enumerated(EnumType.STRING)
    Categoria categoria;

    public Medicamento() {
    }

    //Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public List<Farmaco> getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(List<Farmaco> principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
