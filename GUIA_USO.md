#  Sistema de Notificação de Agravos de Saúde Pública

##  Descrição
Sistema desenvolvido em Java para gerenciar notificações de agravos de saúde pública, incluindo:
- **Hanseníase**
- **Tuberculose** 
- **Malária**

##  Funcionalidades Implementadas

###  Registrar Notificações
- Cadastro completo de notificações com dados do paciente
- Informações específicas para cada tipo de agravo
- Dados persistidos automaticamente em arquivo de texto

###  Consultar Notificações
-  **Por Nome do Paciente**: Busca parcial por nome
-  **Por Bairro**: Lista todas as notificações de um bairro específico
-  **Por Período**: Filtra notificações entre duas datas
-  **Por Tipo de Agravo**: Lista por Hanseníase, Tuberculose ou Malária
-  **Listar Todas**: Exibe todas as notificações cadastradas

###  Gerar Relatórios
-  Total de notificações por agravo
-  Total de notificações por bairro
-  Total de notificações por mês/ano
-  Total de notificações por faixa etária
- Total de notificações por sexo
-  Total de notificações por raça/cor
-  Total de notificações por escolaridade
-  Relatório completo consolidado

## 🛠️ Tecnologias Utilizadas
- **Java 21** (LTS)
- **Maven** para gerenciamento de dependências
- Programação Orientada a Objetos (POO)
- Java Streams API
- Persistência em arquivos de texto

##  Estrutura do Projeto
```
src/
├── Main.java                          # Interface CLI (Menu)
├── modelo/
│   ├── Notificacao.java              # Classe abstrata base
│   ├── NotificacaoHanseniase.java    # Notificação de Hanseníase
│   ├── NotificacaoTuberculose.java   # Notificação de Tuberculose
│   ├── NotificacaoMalaria.java       # Notificação de Malária
│   ├── Paciente.java                 # Dados do paciente
│   ├── Endereco.java                 # Endereço do paciente
│   ├── TipoAgravo.java               # Enum de agravos
│   ├── Sexo.java                     # Enum de sexo
│   ├── Raca.java                     # Enum de raça/cor
│   └── Escolaridade.java             # Enum de escolaridade
└── servico/
    └── NotificacaoService.java       # Lógica de negócio e persistência
```

##  Como Executar

### Pré-requisitos
- Java 21 ou superior instalado
- Maven (opcional, mas recomendado)

### Opção 1: Usando IntelliJ IDEA
1. Abra o projeto no IntelliJ IDEA
2. Configure o SDK para Java 21
3. Execute a classe `Main.java`

### Opção 2: Linha de Comando
```bash
# Compilar
javac -d bin -sourcepath src src/Main.java

# Executar
java -cp bin Main
```

### Opção 3: Usando Maven
```bash
# Compilar
mvn compile

# Executar
mvn exec:java -Dexec.mainClass="Main"
```

##  Persistência de Dados
Os dados são salvos automaticamente no arquivo `notificacoes.txt` na raiz do projeto.
O formato é legível e estruturado com separadores `|`.

##  Exemplo de Uso

### Menu Principal
```
╔═══════════════ MENU PRINCIPAL ═══════════════╗
║ 1. Registrar Notificação                     ║
║ 2. Consultar Notificações                    ║
║ 3. Gerar Relatórios                          ║
║ 0. Sair                                      ║
╚══════════════════════════════════════════════╝
```

### Registrar uma Notificação
1. Selecione "1. Registrar Notificação"
2. Escolha o tipo de agravo (1-Hanseníase, 2-Tuberculose, 3-Malária)
3. Preencha os dados solicitados:
   - Dados do paciente (nome, data nascimento, sexo, etc.)
   - Endereço completo
   - Informações específicas do agravo

### Consultar Notificações
Várias opções de consulta disponíveis com filtros específicos.

### Gerar Relatórios
Relatórios estatísticos completos com agrupamentos por diferentes critérios.


##  Autores
- Rafhael hanry marques granja 

##  Data de Entrega
**31 de outubro de 2025**

##  Referências
- [Portal SINAN - Hanseníase](https://portalsinan.saude.gov.br/hanseniase)
- [Portal SINAN - Tuberculose](https://portalsinan.saude.gov.br/tuberculose)
- [Portal SINAN - Malária](https://portalsinan.saude.gov.br/malaria)

---
