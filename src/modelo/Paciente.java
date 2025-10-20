package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Paciente {
    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private Raca raca;
    private Escolaridade escolaridade;
    private String nomeMae;
    private Endereco endereco;

    public Paciente(String nome, LocalDate dataNascimento, Sexo sexo, Raca raca, 
                    Escolaridade escolaridade, String nomeMae, Endereco endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.raca = raca;
        this.escolaridade = escolaridade;
        this.nomeMae = nomeMae;
        this.endereco = endereco;
    }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public String getFaixaEtaria() {
        int idade = getIdade();
        if (idade < 1) return "< 1 ano";
        if (idade <= 4) return "1-4 anos";
        if (idade <= 9) return "5-9 anos";
        if (idade <= 14) return "10-14 anos";
        if (idade <= 19) return "15-19 anos";
        if (idade <= 39) return "20-39 anos";
        if (idade <= 59) return "40-59 anos";
        return "60+ anos";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String toArquivo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nome + "|" + dataNascimento.format(formatter) + "|" + sexo.name() + "|" + 
               raca.name() + "|" + escolaridade.name() + "|" + nomeMae + "|" + endereco.toArquivo();
    }

    public static Paciente fromArquivo(String linha) {
        String[] partes = linha.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNasc = LocalDate.parse(partes[1], formatter);
        Endereco end = new Endereco(partes[6], partes[7], partes[8], partes[9], partes[10], partes[11]);
        return new Paciente(partes[0], dataNasc, Sexo.valueOf(partes[2]), 
                           Raca.valueOf(partes[3]), Escolaridade.valueOf(partes[4]), partes[5], end);
    }
}
