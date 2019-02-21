package br.com.b2w.infraestructure.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<ErroDto> responseHttpClientErrorException(final HttpClientErrorException ex, final WebRequest request) {

        final ErroDto erro = new ErroDto();
        erro.setMensagem(ex.getMessage());
        erro.setCodigo(ex.getRawStatusCode());

        return new ResponseEntity<>(erro, ex.getStatusCode());
    }

}
