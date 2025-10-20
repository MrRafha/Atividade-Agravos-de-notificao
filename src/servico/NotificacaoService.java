package servico;

import modelo.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class NotificacaoService {
    private List<Notificacao> notificacoes;
    private static final String ARQUIVO_DADOS = "notificacoes.txt";

    public NotificacaoService() {
        this.notificacoes = new ArrayList<>();
        carregarDados();
    }

    // ============= CADASTRO =============
    public void registrarNotificacao(Notificacao notificacao) {
        notificacoes.add(notificacao);
        salvarDados();
        System.out.println("✓ Notificação registrada com sucesso! ID: " + notificacao.getId());
    }

    // ============= CONSULTAS =============
    public List<Notificacao> consultarPorNome(String nome) {
        return notificacoes.stream()
                .filter(n -> n.getPaciente().getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Notificacao> listarPorBairro(String bairro) {
        return notificacoes.stream()
                .filter(n -> n.getPaciente().getEndereco().getBairro().equalsIgnoreCase(bairro))
                .collect(Collectors.toList());
    }

    public List<Notificacao> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return notificacoes.stream()
                .filter(n -> !n.getDataNotificacao().isBefore(inicio) && 
                            !n.getDataNotificacao().isAfter(fim))
                .collect(Collectors.toList());
    }

    public List<Notificacao> listarPorAgravo(TipoAgravo agravo) {
        return notificacoes.stream()
                .filter(n -> n.getTipoAgravo() == agravo)
                .collect(Collectors.toList());
    }

    public List<Notificacao> listarTodas() {
        return new ArrayList<>(notificacoes);
    }

    // ============= RELATÓRIOS =============
    public Map<TipoAgravo, Long> relatorioTotalPorAgravo() {
        return notificacoes.stream()
                .collect(Collectors.groupingBy(Notificacao::getTipoAgravo, Collectors.counting()));
    }

    public Map<String, Long> relatorioTotalPorBairro() {
        return notificacoes.stream()
                .collect(Collectors.groupingBy(
                    n -> n.getPaciente().getEndereco().getBairro(), 
                    Collectors.counting()));
    }

    public Map<String, Long> relatorioTotalPorMesAno() {
        return notificacoes.stream()
                .collect(Collectors.groupingBy(Notificacao::getMesAno, Collectors.counting()));
    }

    public Map<String, Long> relatorioTotalPorFaixaEtaria() {
        return notificacoes.stream()
                .collect(Collectors.groupingBy(
                    n -> n.getPaciente().getFaixaEtaria(), 
                    Collectors.counting()));
    }

    public Map<Sexo, Long> relatorioTotalPorSexo() {
        return notificacoes.stream()
                .collect(Collectors.groupingBy(
                    n -> n.getPaciente().getSexo(), 
                    Collectors.counting()));
    }

    public Map<Raca, Long> relatorioTotalPorRaca() {
        return notificacoes.stream()
                .collect(Collectors.groupingBy(
                    n -> n.getPaciente().getRaca(), 
                    Collectors.counting()));
    }

    public Map<Escolaridade, Long> relatorioTotalPorEscolaridade() {
        return notificacoes.stream()
                .collect(Collectors.groupingBy(
                    n -> n.getPaciente().getEscolaridade(), 
                    Collectors.counting()));
    }

    // ============= PERSISTÊNCIA =============
    private void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DADOS))) {
            for (Notificacao notificacao : notificacoes) {
                writer.write(notificacao.toArquivo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        File arquivo = new File(ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DADOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    Notificacao notificacao = parseNotificacao(linha);
                    if (notificacao != null) {
                        notificacoes.add(notificacao);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private Notificacao parseNotificacao(String linha) {
        try {
            String[] partes = linha.split("\\|");
            TipoAgravo tipo = TipoAgravo.valueOf(partes[2]);
            
            switch (tipo) {
                case HANSENIASE:
                    return NotificacaoHanseniase.fromArquivo(linha);
                case TUBERCULOSE:
                    return NotificacaoTuberculose.fromArquivo(linha);
                case MALARIA:
                    return NotificacaoMalaria.fromArquivo(linha);
                default:
                    return null;
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar linha: " + linha);
            return null;
        }
    }

    public int getTotalNotificacoes() {
        return notificacoes.size();
    }
}
