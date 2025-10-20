package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Notificacao {
    private static int contadorId = 1;
    private int id;
    private LocalDate dataNotificacao;
    private Paciente paciente;
    private TipoAgravo tipoAgravo;
    private String unidadeSaude;

    public Notificacao(LocalDate dataNotificacao, Paciente paciente, TipoAgravo tipoAgravo, String unidadeSaude) {
        this.id = contadorId++;
        this.dataNotificacao = dataNotificacao;
        this.paciente = paciente;
        this.tipoAgravo = tipoAgravo;
        this.unidadeSaude = unidadeSaude;
    }

    public Notificacao(int id, LocalDate dataNotificacao, Paciente paciente, TipoAgravo tipoAgravo, String unidadeSaude) {
        this.id = id;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
        this.dataNotificacao = dataNotificacao;
        this.paciente = paciente;
        this.tipoAgravo = tipoAgravo;
        this.unidadeSaude = unidadeSaude;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(LocalDate dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public TipoAgravo getTipoAgravo() {
        return tipoAgravo;
    }

    public void setTipoAgravo(TipoAgravo tipoAgravo) {
        this.tipoAgravo = tipoAgravo;
    }

    public String getUnidadeSaude() {
        return unidadeSaude;
    }

    public void setUnidadeSaude(String unidadeSaude) {
        this.unidadeSaude = unidadeSaude;
    }

    public String getMesAno() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return dataNotificacao.format(formatter);
    }

    public abstract String toArquivo();
    
    public abstract String getDetalhes();

    protected String toArquivoBase() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return id + "|" + dataNotificacao.format(formatter) + "|" + tipoAgravo.name() + "|" + 
               unidadeSaude + "|" + paciente.toArquivo();
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Data: " + dataNotificacao + " | Agravo: " + tipoAgravo + 
               " | Paciente: " + paciente.getNome() + " | Bairro: " + paciente.getEndereco().getBairro();
    }
}
