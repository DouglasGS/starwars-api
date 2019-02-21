package br.com.b2w.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "planetas")
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String clima;
    private String terreno;
    @Column(name = "quantidade_aparicoes")
    private int quantidadeAparicoes;

    public Planeta() {
        super();
    }

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
