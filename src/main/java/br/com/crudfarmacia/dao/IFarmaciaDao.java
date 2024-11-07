package br.com.crudfarmacia.dao;

import br.com.crudfarmacia.model.Medicamento;

import java.util.List;
import java.util.Optional;

public interface IFarmaciaDao {
    Medicamento save(Medicamento medicamento);
    Medicamento update(Medicamento medicamento);
    void delete(Medicamento medicamento);
    List<Medicamento> findAll();
    Optional<Medicamento> findByName();

}
