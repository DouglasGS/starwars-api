package br.com.b2w.infraestructure.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroDto {

    private Integer codigo;
    private String mensagem;

    public ErroDto(final Integer codigo, final String mensagem) {
        super();
        this.codigo = codigo;
        this.mensagem = mensagem;

    }

    public ErroDto(final String mensagem) {
        super();
        this.mensagem = mensagem;

    }

    public ErroDto() {
        super();

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(final Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;

    }

    @Override
    public String toString() {

        return mensagem;
    }

}
