package modelo;

public enum TipoAgravo {
    HANSENIASE("Hanseníase"),
    TUBERCULOSE("Tuberculose"),
    MALARIA("Malária");

    private final String descricao;

    TipoAgravo(String descricao) {
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
