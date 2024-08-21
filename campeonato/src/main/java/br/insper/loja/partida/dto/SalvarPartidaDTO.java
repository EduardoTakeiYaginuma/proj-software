package br.insper.loja.partida.dto;

public class SalvarPartidaDTO {
    private Integer mandante;
    private Integer visitante;

    public Integer getMandante() {
        return mandante;
    }

    public void setMandante(Integer mandante) {
        this.mandante = mandante;
    }

    public Integer getVisitante() {
        return visitante;
    }

    public void setVisitante(Integer visitante) {
        this.visitante = visitante;
    }

}
