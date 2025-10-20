package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotificacaoTuberculose extends Notificacao {
    private String formaClinica;
    private boolean baciloscopia;
    private boolean raioXTorax;
    private String tipoEntrada;

    public NotificacaoTuberculose(LocalDate dataNotificacao, Paciente paciente, String unidadeSaude,
                                  String formaClinica, boolean baciloscopia, boolean raioXTorax,
                                  String tipoEntrada) {
        super(dataNotificacao, paciente, TipoAgravo.TUBERCULOSE, unidadeSaude);
        this.formaClinica = formaClinica;
        this.baciloscopia = baciloscopia;
        this.raioXTorax = raioXTorax;
        this.tipoEntrada = tipoEntrada;
    }

    public NotificacaoTuberculose(int id, LocalDate dataNotificacao, Paciente paciente, String unidadeSaude,
                                  String formaClinica, boolean baciloscopia, boolean raioXTorax,
                                  String tipoEntrada) {
        super(id, dataNotificacao, paciente, TipoAgravo.TUBERCULOSE, unidadeSaude);
        this.formaClinica = formaClinica;
        this.baciloscopia = baciloscopia;
        this.raioXTorax = raioXTorax;
        this.tipoEntrada = tipoEntrada;
    }

    public String getFormaClinica() {
        return formaClinica;
    }

    public void setFormaClinica(String formaClinica) {
        this.formaClinica = formaClinica;
    }

    public boolean isBaciloscopia() {
        return baciloscopia;
    }

    public void setBaciloscopia(boolean baciloscopia) {
        this.baciloscopia = baciloscopia;
    }

    public boolean isRaioXTorax() {
        return raioXTorax;
    }

    public void setRaioXTorax(boolean raioXTorax) {
        this.raioXTorax = raioXTorax;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    @Override
    public String toArquivo() {
        return toArquivoBase() + "|" + formaClinica + "|" + baciloscopia + "|" + 
               raioXTorax + "|" + tipoEntrada;
    }

    @Override
    public String getDetalhes() {
        return toString() + "\n" +
               "  Forma Clínica: " + formaClinica + "\n" +
               "  Baciloscopia: " + (baciloscopia ? "Positiva" : "Negativa") + "\n" +
               "  Raio-X de Tórax: " + (raioXTorax ? "Realizado" : "Não Realizado") + "\n" +
               "  Tipo de Entrada: " + tipoEntrada;
    }

    public static NotificacaoTuberculose fromArquivo(String linha) {
        String[] partes = linha.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int id = Integer.parseInt(partes[0]);
        LocalDate dataNotif = LocalDate.parse(partes[1], formatter);
        String unidade = partes[3];
        
        Paciente paciente = Paciente.fromArquivo(String.join("|", 
            java.util.Arrays.copyOfRange(partes, 4, 16)));
        
        String formaClinica = partes[16];
        boolean baciloscopia = Boolean.parseBoolean(partes[17]);
        boolean raioX = Boolean.parseBoolean(partes[18]);
        String tipoEntrada = partes[19];
        
        return new NotificacaoTuberculose(id, dataNotif, paciente, unidade, formaClinica, 
                                         baciloscopia, raioX, tipoEntrada);
    }
}
