package br.insper.loja.partida.model;

import br.insper.loja.time.model.Time;
import jakarta.persistence.*;

@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identificador;

    @ManyToOne
    @JoinColumn(name = "id_mandante")
    private Time mandante;
    @ManyToOne
    @JoinColumn(name = "id_visitante")
    private Time visitante;
    private Integer placarMandante;

    private Integer placarVisitante;


    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Time getMandante() {
        return mandante;
    }

    public void setMandante(Time mandante) {
        this.mandante = mandante;
    }

    public Time getVisitante() {
        return visitante;
    }

    public void setVisitante(Time visitante) {
        this.visitante = visitante;
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

}
