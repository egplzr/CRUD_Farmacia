package br.com.crudfarmacia.model;

public enum Categoria {
    COMPRIMIDO("Comprimido"),
    CAPSULA("Capsula"),
    XAROPE("Xarope"),
    POMADA("Pomada");

    private final String descricao;

    Categoria(String descricao){
        this.descricao =descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
