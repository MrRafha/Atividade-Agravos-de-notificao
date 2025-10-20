package modelo;

public enum Raca {
    BRANCA("Branca"),
    PRETA("Preta"),
    AMARELA("Amarela"),
    PARDA("Parda"),
    INDIGENA("Indígena"),
    IGNORADO("Ignorado");

    private final String descricao;

    Raca(String descricao) {
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
