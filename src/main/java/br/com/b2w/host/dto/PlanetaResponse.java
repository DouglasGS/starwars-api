package br.com.b2w.host.dto;

public class PlanetaResponse {

    private Integer id;
    private String nome;
    private String clima;
    private String terreno;
    private int quantidadeAparicoes;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(final String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(final String terreno) {
        this.terreno = terreno;
    }

    public int getQuantidadeAparicoes() {
        return quantidadeAparicoes;
    }

    public void setQuantidadeAparicoes(final int quantidadeAparicoes) {
        this.quantidadeAparicoes = quantidadeAparicoes;
    }

}
