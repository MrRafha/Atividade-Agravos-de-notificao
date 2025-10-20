package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotificacaoMalaria extends Notificacao {
    private String tipoLamina;
    private String especieParasitaria;
    private String localProvavelInfeccao;
    private boolean autoctone;

    public NotificacaoMalaria(LocalDate dataNotificacao, Paciente paciente, String unidadeSaude,
                              String tipoLamina, String especieParasitaria, String localProvavelInfeccao,
                              boolean autoctone) {
        super(dataNotificacao, paciente, TipoAgravo.MALARIA, unidadeSaude);
        this.tipoLamina = tipoLamina;
        this.especieParasitaria = especieParasitaria;
        this.localProvavelInfeccao = localProvavelInfeccao;
        this.autoctone = autoctone;
    }

    public NotificacaoMalaria(int id, LocalDate dataNotificacao, Paciente paciente, String unidadeSaude,
                              String tipoLamina, String especieParasitaria, String localProvavelInfeccao,
                              boolean autoctone) {
        super(id, dataNotificacao, paciente, TipoAgravo.MALARIA, unidadeSaude);
        this.tipoLamina = tipoLamina;
        this.especieParasitaria = especieParasitaria;
        this.localProvavelInfeccao = localProvavelInfeccao;
        this.autoctone = autoctone;
    }

    public String getTipoLamina() {
        return tipoLamina;
    }

    public void setTipoLamina(String tipoLamina) {
        this.tipoLamina = tipoLamina;
    }

    public String getEspecieParasitaria() {
        return especieParasitaria;
    }

    public void setEspecieParasitaria(String especieParasitaria) {
        this.especieParasitaria = especieParasitaria;
    }

    public String getLocalProvavelInfeccao() {
        return localProvavelInfeccao;
    }

    public void setLocalProvavelInfeccao(String localProvavelInfeccao) {
        this.localProvavelInfeccao = localProvavelInfeccao;
    }

    public boolean isAutoctone() {
        return autoctone;
    }

    public void setAutoctone(boolean autoctone) {
        this.autoctone = autoctone;
    }

    @Override
    public String toArquivo() {
        return toArquivoBase() + "|" + tipoLamina + "|" + especieParasitaria + "|" + 
               localProvavelInfeccao + "|" + autoctone;
    }

    @Override
    public String getDetalhes() {
        return toString() + "\n" +
               "  Tipo de Lâmina: " + tipoLamina + "\n" +
               "  Espécie Parasitária: " + especieParasitaria + "\n" +
               "  Local Provável de Infecção: " + localProvavelInfeccao + "\n" +
               "  Autóctone: " + (autoctone ? "Sim" : "Não");
    }

    public static NotificacaoMalaria fromArquivo(String linha) {
        String[] partes = linha.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int id = Integer.parseInt(partes[0]);
        LocalDate dataNotif = LocalDate.parse(partes[1], formatter);
        String unidade = partes[3];
        
        Paciente paciente = Paciente.fromArquivo(String.join("|", 
            java.util.Arrays.copyOfRange(partes, 4, 16)));
        
        String tipoLamina = partes[16];
        String especieParasit = partes[17];
        String localInfec = partes[18];
        boolean autoctone = Boolean.parseBoolean(partes[19]);
        
        return new NotificacaoMalaria(id, dataNotif, paciente, unidade, tipoLamina, 
                                     especieParasit, localInfec, autoctone);
    }
}
