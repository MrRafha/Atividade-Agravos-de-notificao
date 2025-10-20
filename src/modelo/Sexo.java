package modelo;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    IGNORADO("Ignorado");

    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
