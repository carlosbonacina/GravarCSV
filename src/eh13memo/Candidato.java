/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eh13memo;

/**
 *
 * @author linuxifrs
 */
public class Candidato {

    private long sequencial;
    private String nome;
    private String cpf;
    private String nascimento;
    private String sexo;
    private int Ocupacao;

    public long getSequencial() {
        return sequencial;
    }

    public Candidato() {
    }

    public Candidato(long sequencial, String nome, String cpf, String nascimento, String sexo) {
        this.sequencial = sequencial;
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.sexo = sexo;

    }

    public void setSequencial(long sequencial) {
        this.sequencial = sequencial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getOcupacao() {
        return Ocupacao;
    }

    public void setOcupacao(int Ocupacao) {
        this.Ocupacao = Ocupacao;
    }

    public String retornarDados() {
        return getNome() + ";" + getCpf() + ";" + getSequencial() + ";" + getNascimento() + ";" + getSexo() + ";" + getOcupacao();
    }

}
