package br.com.pi_2026_1_etec.model;

public class Aluno extends Usuario {

    private int questoesRespondidas;
    private int taxaAcertos;
    private String professorEmail;
    private int pessoaIdPessoa;

    public int getQuestoesRespondidas() {
        return questoesRespondidas;
    }

    public void setQuestoesRespondidas(int questoesRespondidas) {
        this.questoesRespondidas = questoesRespondidas;
    }

    public int getTaxaAcertos() {
        return taxaAcertos;
    }

    public void setTaxaAcertos(int taxaAcertos) {
        this.taxaAcertos = taxaAcertos;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public int getPessoaIdPessoa() {
        return pessoaIdPessoa;
    }

    public void setPessoaIdPessoa(int pessoaIdPessoa) {
        this.pessoaIdPessoa = pessoaIdPessoa;
    }
}