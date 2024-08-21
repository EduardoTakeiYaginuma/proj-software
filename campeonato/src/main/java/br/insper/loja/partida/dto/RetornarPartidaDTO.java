package br.insper.loja.partida.dto;

import br.insper.loja.partida.model.Partida;

public class RetornarPartidaDTO {
    private Integer id;
    private String nomeMandante;
    private String nomeVisitante;
    private Integer placarMandante;
    private Integer placarVisitante;

    private String status;

    public static RetornarPartidaDTO getRetornarPartidaDTO(Partida partida) {
        RetornarPartidaDTO retornarPartidaDTO = new RetornarPartidaDTO();
        retornarPartidaDTO.setId(partida.getId());
        retornarPartidaDTO.setNomeMandante(partida.getMandante().getNome());
        retornarPartidaDTO.setNomeVisitante(partida.getVisitante().getNome());
        retornarPartidaDTO.setPlacarMandante(partida.getPlacarMandante());
        retornarPartidaDTO.setPlacarVisitante(partida.getPlacarVisitante());
        retornarPartidaDTO.setStatus(partida.getStatus());
        return retornarPartidaDTO;
    }

    public String getNomeMandante() {
        return nomeMandante;
    }

    public void setNomeMandante(String nomeMandante) {
        this.nomeMandante = nomeMandante;
    }

    public String getNomeVisitante() {
        return nomeVisitante;
    }

    public void setNomeVisitante(String nomeVisitante) {
        this.nomeVisitante = nomeVisitante;
    }

    public Integer getPlacarMandante() {
        return placarMandante;
    }

    public void setPlacarMandante(Integer placarMandante) {
        this.placarMandante = placarMandante;
    }

    public Integer getPlacarVisitante() {
        return placarVisitante;
    }

    public void setPlacarVisitante(Integer placarVisitante) {
        this.placarVisitante = placarVisitante;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
