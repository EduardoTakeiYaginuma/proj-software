package br.insper.loja.partida.controller;

import br.insper.loja.common.Erro;
import br.insper.loja.partida.exception.PartidaNaoEncontradaException;
import br.insper.loja.time.exception.TimeNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class PartidaAdvice {

    @ExceptionHandler(PartidaNaoEncontradaException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Erro timeNaoEncontradoHandler(PartidaNaoEncontradaException e) {
        Erro erro = new Erro();
        erro.setMensagem(e.getMessage());
        erro.setData(LocalDateTime.now());
        erro.setCodigo(404);
        return erro;
    }

}
