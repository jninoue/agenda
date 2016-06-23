package br.uff.ic.agenda.model;

public class Pessoa {
    private String nome;
    private String telefone;
    private String detalhes;

    public Pessoa() {
        nome = "Nova Pessoa";
        telefone = "";
        detalhes = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    @Override
    public String toString() {
        return nome;
    }    
}
