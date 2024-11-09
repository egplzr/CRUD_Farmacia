package br.com.crudfarmacia.dao;

import java.util.List;

public interface IDao <T>{
    public void inserir(T objeto) throws Exception;
    public void alterar(T objeto) throws Exception;
    public T buscar(long id) throws Exception;
    public void excluir(long id) throws Exception;
    public List<T> listar() throws Exception;
}