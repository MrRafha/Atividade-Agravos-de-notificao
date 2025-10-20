import modelo.*;
import servico.NotificacaoService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final NotificacaoService service = new NotificacaoService();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE NOTIFICAÇÃO DE AGRAVOS DE SAÚDE PÚBLICA  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        boolean continuar = true;
        while (continuar) {
            continuar = exibirMenuPrincipal();
        }
        
        scanner.close();
        System.out.println("\n✓ Sistema encerrado. Até logo!");
    }

    private static boolean exibirMenuPrincipal() {
        System.out.println("\n╔═══════════════ MENU PRINCIPAL ═══════════════╗");
        System.out.println("║ 1. Registrar Notificação                     ║");
        System.out.println("║ 2. Consultar Notificações                    ║");
        System.out.println("║ 3. Gerar Relatórios                          ║");
        System.out.println("║ 0. Sair                                      ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1 -> menuRegistrarNotificacao();
                case 2 -> menuConsultar();
                case 3 -> menuRelatorios();
                case 0 -> {
                    return false;
                }
                default -> System.out.println("✗ Opção inválida!");
            }
        } catch (InputMismatchException e) {
            System.out.println("✗ Entrada inválida! Digite um número.");
            scanner.nextLine(); // Limpar buffer
        }
        
        return true;
    }

    // ===================== REGISTRAR NOTIFICAÇÃO =====================
    private static void menuRegistrarNotificacao() {
        System.out.println("\n┌─────── REGISTRAR NOTIFICAÇÃO ───────┐");
        System.out.println("│ 1. Hanseníase                        │");
        System.out.println("│ 2. Tuberculose                       │");
        System.out.println("│ 3. Malária                           │");
        System.out.println("│ 0. Voltar                            │");
        System.out.println("└──────────────────────────────────────┘");
        System.out.print("Escolha o tipo de agravo: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> registrarHanseniase();
                case 2 -> registrarTuberculose();
                case 3 -> registrarMalaria();
                case 0 -> {
                    return;
                }
                default -> System.out.println("✗ Opção inválida!");
            }
        } catch (InputMismatchException e) {
            System.out.println("✗ Entrada inválida!");
            scanner.nextLine();
        }
    }

    private static void registrarHanseniase() {
        System.out.println("\n═══ REGISTRO DE HANSENÍASE ═══");
        try {
            Paciente paciente = coletarDadosPaciente();
            
            System.out.print("Data da Notificação (dd/MM/yyyy): ");
            LocalDate dataNotificacao = LocalDate.parse(scanner.nextLine(), dateFormatter);
            
            System.out.print("Unidade de Saúde: ");
            String unidade = scanner.nextLine();
            
            System.out.print("Forma Clínica (Indeterminada/Tuberculoide/Dimorfa/Virchowiana): ");
            String formaClinica = scanner.nextLine();
            
            System.out.print("Número de Lesões: ");
            int numeroLesoes = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Classificação Operacional (Paucibacilar/Multibacilar): ");
            String classificacao = scanner.nextLine();
            
            System.out.print("Apresenta Grau de Incapacidade? (S/N): ");
            boolean grauIncapacidade = scanner.nextLine().toUpperCase().equals("S");
            
            NotificacaoHanseniase notificacao = new NotificacaoHanseniase(
                dataNotificacao, paciente, unidade, formaClinica, numeroLesoes, 
                classificacao, grauIncapacidade
            );
            
            service.registrarNotificacao(notificacao);
            
        } catch (DateTimeParseException e) {
            System.out.println("✗ Data inválida! Use o formato dd/MM/yyyy");
        } catch (InputMismatchException e) {
            System.out.println("✗ Entrada inválida!");
            scanner.nextLine();
        }
    }

    private static void registrarTuberculose() {
        System.out.println("\n═══ REGISTRO DE TUBERCULOSE ═══");
        try {
            Paciente paciente = coletarDadosPaciente();
            
            System.out.print("Data da Notificação (dd/MM/yyyy): ");
            LocalDate dataNotificacao = LocalDate.parse(scanner.nextLine(), dateFormatter);
            
            System.out.print("Unidade de Saúde: ");
            String unidade = scanner.nextLine();
            
            System.out.print("Forma Clínica (Pulmonar/Extrapulmonar/Ambas): ");
            String formaClinica = scanner.nextLine();
            
            System.out.print("Baciloscopia Positiva? (S/N): ");
            boolean baciloscopia = scanner.nextLine().toUpperCase().equals("S");
            
            System.out.print("Realizou Raio-X de Tórax? (S/N): ");
            boolean raioX = scanner.nextLine().toUpperCase().equals("S");
            
            System.out.print("Tipo de Entrada (Caso Novo/Recidiva/Reingresso/Transferência): ");
            String tipoEntrada = scanner.nextLine();
            
            NotificacaoTuberculose notificacao = new NotificacaoTuberculose(
                dataNotificacao, paciente, unidade, formaClinica, baciloscopia, 
                raioX, tipoEntrada
            );
            
            service.registrarNotificacao(notificacao);
            
        } catch (DateTimeParseException e) {
            System.out.println("✗ Data inválida! Use o formato dd/MM/yyyy");
        } catch (InputMismatchException e) {
            System.out.println("✗ Entrada inválida!");
            scanner.nextLine();
        }
    }

    private static void registrarMalaria() {
        System.out.println("\n═══ REGISTRO DE MALÁRIA ═══");
        try {
            Paciente paciente = coletarDadosPaciente();
            
            System.out.print("Data da Notificação (dd/MM/yyyy): ");
            LocalDate dataNotificacao = LocalDate.parse(scanner.nextLine(), dateFormatter);
            
            System.out.print("Unidade de Saúde: ");
            String unidade = scanner.nextLine();
            
            System.out.print("Tipo de Lâmina (LVC/LVD/LD): ");
            String tipoLamina = scanner.nextLine();
            
            System.out.print("Espécie Parasitária (P. falciparum/P. vivax/P. malariae/Mista): ");
            String especie = scanner.nextLine();
            
            System.out.print("Local Provável de Infecção: ");
            String local = scanner.nextLine();
            
            System.out.print("Caso Autóctone? (S/N): ");
            boolean autoctone = scanner.nextLine().toUpperCase().equals("S");
            
            NotificacaoMalaria notificacao = new NotificacaoMalaria(
                dataNotificacao, paciente, unidade, tipoLamina, especie, 
                local, autoctone
            );
            
            service.registrarNotificacao(notificacao);
            
        } catch (DateTimeParseException e) {
            System.out.println("✗ Data inválida! Use o formato dd/MM/yyyy");
        } catch (InputMismatchException e) {
            System.out.println("✗ Entrada inválida!");
            scanner.nextLine();
        }
    }

    private static Paciente coletarDadosPaciente() throws DateTimeParseException {
        System.out.println("\n--- Dados do Paciente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), dateFormatter);
        
        System.out.print("Sexo (M/F/I): ");
        String sexoInput = scanner.nextLine().toUpperCase();
        Sexo sexo = switch (sexoInput) {
            case "M" -> Sexo.MASCULINO;
            case "F" -> Sexo.FEMININO;
            case "I" -> Sexo.IGNORADO;
            default -> throw new IllegalArgumentException("Sexo inválido! Use M, F ou I.");
        };
        
        System.out.println("\nRaça/Cor:");
        System.out.println("1. Branca");
        System.out.println("2. Preta");
        System.out.println("3. Amarela");
        System.out.println("4. Parda");
        System.out.println("5. Indígena");
        System.out.println("6. Ignorado");
        System.out.print("Escolha: ");
        int racaOpcao = scanner.nextInt();
        scanner.nextLine();
        Raca raca = switch (racaOpcao) {
            case 1 -> Raca.BRANCA;
            case 2 -> Raca.PRETA;
            case 3 -> Raca.AMARELA;
            case 4 -> Raca.PARDA;
            case 5 -> Raca.INDIGENA;
            case 6 -> Raca.IGNORADO;
            default -> throw new IllegalArgumentException("Opção inválida!");
        };
        
        System.out.println("\nEscolaridade:");
        System.out.println("1. Analfabeto");
        System.out.println("2. Fundamental Incompleto");
        System.out.println("3. Fundamental Completo");
        System.out.println("4. Médio Incompleto");
        System.out.println("5. Médio Completo");
        System.out.println("6. Superior Incompleto");
        System.out.println("7. Superior Completo");
        System.out.println("8. Não se Aplica");
        System.out.println("9. Ignorado");
        System.out.print("Escolha: ");
        int escolaridadeOpcao = scanner.nextInt();
        scanner.nextLine();
        Escolaridade escolaridade = switch (escolaridadeOpcao) {
            case 1 -> Escolaridade.ANALFABETO;
            case 2 -> Escolaridade.FUNDAMENTAL_INCOMPLETO;
            case 3 -> Escolaridade.FUNDAMENTAL_COMPLETO;
            case 4 -> Escolaridade.MEDIO_INCOMPLETO;
            case 5 -> Escolaridade.MEDIO_COMPLETO;
            case 6 -> Escolaridade.SUPERIOR_INCOMPLETO;
            case 7 -> Escolaridade.SUPERIOR_COMPLETO;
            case 8 -> Escolaridade.NAO_SE_APLICA;
            case 9 -> Escolaridade.IGNORADO;
            default -> throw new IllegalArgumentException("Opção inválida!");
        };
        
        System.out.print("\nNome da Mãe: ");
        String nomeMae = scanner.nextLine();
        
        Endereco endereco = coletarEndereco();
        
        return new Paciente(nome, dataNascimento, sexo, raca, escolaridade, nomeMae, endereco);
    }

    private static Endereco coletarEndereco() {
        System.out.println("\n--- Endereço ---");
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        
        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();
        
        System.out.print("Município: ");
        String municipio = scanner.nextLine();
        
        System.out.print("UF: ");
        String uf = scanner.nextLine();
        
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        
        return new Endereco(logradouro, numero, bairro, municipio, uf, cep);
    }

    // ===================== CONSULTAR =====================
    private static void menuConsultar() {
        System.out.println("\n┌─────── CONSULTAR NOTIFICAÇÕES ──────┐");
        System.out.println("│ 1. Por Nome do Paciente              │");
        System.out.println("│ 2. Por Bairro                        │");
        System.out.println("│ 3. Por Período                       │");
        System.out.println("│ 4. Por Tipo de Agravo                │");
        System.out.println("│ 5. Listar Todas                      │");
        System.out.println("│ 0. Voltar                            │");
        System.out.println("└──────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> consultarPorNome();
                case 2 -> consultarPorBairro();
                case 3 -> consultarPorPeriodo();
                case 4 -> consultarPorAgravo();
                case 5 -> listarTodas();
                case 0 -> {
                    return;
                }
                default -> System.out.println("✗ Opção inválida!");
            }
        } catch (InputMismatchException e) {
            System.out.println("✗ Entrada inválida!");
            scanner.nextLine();
        }
    }

    private static void consultarPorNome() {
        System.out.print("\nDigite o nome do paciente: ");
        String nome = scanner.nextLine();
        List<Notificacao> resultado = service.consultarPorNome(nome);
        exibirResultadoConsulta(resultado, "Notificações para: " + nome);
    }

    private static void consultarPorBairro() {
        System.out.print("\nDigite o nome do bairro: ");
        String bairro = scanner.nextLine();
        List<Notificacao> resultado = service.listarPorBairro(bairro);
        exibirResultadoConsulta(resultado, "Notificações do bairro: " + bairro);
    }

    private static void consultarPorPeriodo() {
        try {
            System.out.print("\nData inicial (dd/MM/yyyy): ");
            LocalDate inicio = LocalDate.parse(scanner.nextLine(), dateFormatter);
            
            System.out.print("Data final (dd/MM/yyyy): ");
            LocalDate fim = LocalDate.parse(scanner.nextLine(), dateFormatter);
            
            List<Notificacao> resultado = service.listarPorPeriodo(inicio, fim);
            exibirResultadoConsulta(resultado, "Notificações de " + 
                inicio.format(dateFormatter) + " a " + fim.format(dateFormatter));
        } catch (DateTimeParseException e) {
            System.out.println("✗ Data inválida! Use o formato dd/MM/yyyy");
        }
    }

    private static void consultarPorAgravo() {
        System.out.println("\n1. Hanseníase");
        System.out.println("2. Tuberculose");
        System.out.println("3. Malária");
        System.out.print("Escolha o agravo: ");
        
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            TipoAgravo agravo = switch (opcao) {
                case 1 -> TipoAgravo.HANSENIASE;
                case 2 -> TipoAgravo.TUBERCULOSE;
                case 3 -> TipoAgravo.MALARIA;
                default -> null;
            };
            
            if (agravo != null) {
                List<Notificacao> resultado = service.listarPorAgravo(agravo);
                exibirResultadoConsulta(resultado, "Notificações de " + agravo);
            } else {
                System.out.println("✗ Opção inválida!");
            }
        } catch (InputMismatchException e) {
            System.out.println("✗ Entrada inválida!");
            scanner.nextLine();
        }
    }

    private static void listarTodas() {
        List<Notificacao> resultado = service.listarTodas();
        exibirResultadoConsulta(resultado, "Todas as Notificações");
    }

    private static void exibirResultadoConsulta(List<Notificacao> notificacoes, String titulo) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  " + titulo);
        System.out.println("═".repeat(60));
        
        if (notificacoes.isEmpty()) {
            System.out.println("  ✗ Nenhuma notificação encontrada.");
        } else {
            System.out.println("  Total: " + notificacoes.size() + " notificação(ões)\n");
            for (Notificacao n : notificacoes) {
                System.out.println(n.getDetalhes());
                System.out.println("-".repeat(60));
            }
        }
    }

    // ===================== RELATÓRIOS =====================
    private static void menuRelatorios() {
        System.out.println("\n┌─────── GERAR RELATÓRIOS ───────────┐");
        System.out.println("│ 1. Total por Agravo                │");
        System.out.println("│ 2. Total por Bairro                │");
        System.out.println("│ 3. Total por Mês/Ano               │");
        System.out.println("│ 4. Total por Faixa Etária          │");
        System.out.println("│ 5. Total por Sexo                  │");
        System.out.println("│ 6. Total por Raça/Cor              │");
        System.out.println("│ 7. Total por Escolaridade          │");
        System.out.println("│ 8. Relatório Completo              │");
        System.out.println("│ 0. Voltar                          │");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> exibirRelatorio("TOTAL POR AGRAVO", service.relatorioTotalPorAgravo());
                case 2 -> exibirRelatorio("TOTAL POR BAIRRO", service.relatorioTotalPorBairro());
                case 3 -> exibirRelatorio("TOTAL POR MÊS/ANO", service.relatorioTotalPorMesAno());
                case 4 -> exibirRelatorio("TOTAL POR FAIXA ETÁRIA", service.relatorioTotalPorFaixaEtaria());
                case 5 -> exibirRelatorio("TOTAL POR SEXO", service.relatorioTotalPorSexo());
                case 6 -> exibirRelatorio("TOTAL POR RAÇA/COR", service.relatorioTotalPorRaca());
                case 7 -> exibirRelatorio("TOTAL POR ESCOLARIDADE", service.relatorioTotalPorEscolaridade());
                case 8 -> gerarRelatorioCompleto();
                case 0 -> {
                    return;
                }
                default -> System.out.println("✗ Opção inválida!");
            }
        } catch (InputMismatchException e) {
            System.out.println("✗ Entrada inválida!");
            scanner.nextLine();
        }
    }

    private static <K> void exibirRelatorio(String titulo, Map<K, Long> dados) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  📊 " + titulo);
        System.out.println("═".repeat(60));
        
        if (dados.isEmpty()) {
            System.out.println("  ✗ Sem dados para exibir.");
        } else {
            dados.forEach((chave, valor) -> 
                System.out.printf("  %-40s: %d%n", chave, valor)
            );
            System.out.println("─".repeat(60));
            long total = dados.values().stream().mapToLong(Long::longValue).sum();
            System.out.printf("  %-40s: %d%n", "TOTAL", total);
        }
        System.out.println("═".repeat(60));
    }

    private static void gerarRelatorioCompleto() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("  📊 RELATÓRIO COMPLETO DO SISTEMA");
        System.out.println("═".repeat(70));
        System.out.println("  Total de Notificações: " + service.getTotalNotificacoes());
        System.out.println("═".repeat(70));
        
        exibirRelatorio("TOTAL POR AGRAVO", service.relatorioTotalPorAgravo());
        exibirRelatorio("TOTAL POR BAIRRO", service.relatorioTotalPorBairro());
        exibirRelatorio("TOTAL POR MÊS/ANO", service.relatorioTotalPorMesAno());
        exibirRelatorio("TOTAL POR FAIXA ETÁRIA", service.relatorioTotalPorFaixaEtaria());
        exibirRelatorio("TOTAL POR SEXO", service.relatorioTotalPorSexo());
        exibirRelatorio("TOTAL POR RAÇA/COR", service.relatorioTotalPorRaca());
        exibirRelatorio("TOTAL POR ESCOLARIDADE", service.relatorioTotalPorEscolaridade());
    }
}