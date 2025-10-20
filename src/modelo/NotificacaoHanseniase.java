package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotificacaoHanseniase extends Notificacao {
    private String formaClinica;
    private int numeroLesoes;
    private String classificacaoOperacional;
    private boolean grauIncapacidade;

    public NotificacaoHanseniase(LocalDate dataNotificacao, Paciente paciente, String unidadeSaude,
                                 String formaClinica, int numeroLesoes, String classificacaoOperacional,
                                 boolean grauIncapacidade) {
        super(dataNotificacao, paciente, TipoAgravo.HANSENIASE, unidadeSaude);
        this.formaClinica = formaClinica;
        this.numeroLesoes = numeroLesoes;
        this.classificacaoOperacional = classificacaoOperacional;
        this.grauIncapacidade = grauIncapacidade;
    }

    public NotificacaoHanseniase(int id, LocalDate dataNotificacao, Paciente paciente, String unidadeSaude,
                                 String formaClinica, int numeroLesoes, String classificacaoOperacional,
                                 boolean grauIncapacidade) {
        super(id, dataNotificacao, paciente, TipoAgravo.HANSENIASE, unidadeSaude);
        this.formaClinica = formaClinica;
        this.numeroLesoes = numeroLesoes;
        this.classificacaoOperacional = classificacaoOperacional;
        this.grauIncapacidade = grauIncapacidade;
    }

    public String getFormaClinica() {
        return formaClinica;
    }

    public void setFormaClinica(String formaClinica) {
        this.formaClinica = formaClinica;
    }

    public int getNumeroLesoes() {
        return numeroLesoes;
    }

    public void setNumeroLesoes(int numeroLesoes) {
        this.numeroLesoes = numeroLesoes;
    }

    public String getClassificacaoOperacional() {
        return classificacaoOperacional;
    }

    public void setClassificacaoOperacional(String classificacaoOperacional) {
        this.classificacaoOperacional = classificacaoOperacional;
    }

    public boolean isGrauIncapacidade() {
        return grauIncapacidade;
    }

    public void setGrauIncapacidade(boolean grauIncapacidade) {
        this.grauIncapacidade = grauIncapacidade;
    }

    @Override
    public String toArquivo() {
        return toArquivoBase() + "|" + formaClinica + "|" + numeroLesoes + "|" + 
               classificacaoOperacional + "|" + grauIncapacidade;
    }

    @Override
    public String getDetalhes() {
        return toString() + "\n" +
               "  Forma Clínica: " + formaClinica + "\n" +
               "  Número de Lesões: " + numeroLesoes + "\n" +
               "  Classificação Operacional: " + classificacaoOperacional + "\n" +
               "  Grau de Incapacidade: " + (grauIncapacidade ? "Sim" : "Não");
    }

    public static NotificacaoHanseniase fromArquivo(String linha) {
        String[] partes = linha.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int id = Integer.parseInt(partes[0]);
        LocalDate dataNotif = LocalDate.parse(partes[1], formatter);
        String unidade = partes[3];
        
        Paciente paciente = Paciente.fromArquivo(String.join("|", 
            java.util.Arrays.copyOfRange(partes, 4, 16)));
        
        String formaClinica = partes[16];
        int numeroLesoes = Integer.parseInt(partes[17]);
        String classificacao = partes[18];
        boolean grauIncap = Boolean.parseBoolean(partes[19]);
        
        return new NotificacaoHanseniase(id, dataNotif, paciente, unidade, formaClinica, 
                                        numeroLesoes, classificacao, grauIncap);
    }
}
