package br.insper.loja.partida.service;

import br.insper.loja.partida.dto.EditarPartidaDTO;
import br.insper.loja.partida.exception.PartidaNaoEncontradaException;
import br.insper.loja.partida.model.Partida;
import br.insper.loja.partida.repository.PartidaRepository;
import br.insper.loja.partida.dto.RetornarPartidaDTO;
import br.insper.loja.partida.dto.SalvarPartidaDTO;
import br.insper.loja.time.model.Time;
import br.insper.loja.time.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private TimeService timeService;

    public RetornarPartidaDTO cadastrarPartida(SalvarPartidaDTO salvarPartidaDTO) {

        Time mandante = timeService.getTime(salvarPartidaDTO.getMandante());
        Time visitante = timeService.getTime(salvarPartidaDTO.getVisitante());

        Partida partida = new Partida();
        partida.setMandante(mandante);
        partida.setVisitante(visitante);
        partida.setStatus("AGENDADA");

        partida = partidaRepository.save(partida);
        return RetornarPartidaDTO.getRetornarPartidaDTO(partida);
    }



    public List<RetornarPartidaDTO> listarPartidas(String mandante) {
        List<Partida> partidas = partidaRepository.findAll();
        if  (mandante != null) {
            ArrayList<RetornarPartidaDTO> lista = new ArrayList<>();

            for (Partida partida  : partidas) {
                if (partida.getMandante().getIdentificador().equals(mandante))  {
                    lista.add(RetornarPartidaDTO.getRetornarPartidaDTO(partida));
                }
            }
            return lista;
        }

        List<RetornarPartidaDTO> response = new ArrayList<>();
        for (Partida partida : partidas) {
            response.add(RetornarPartidaDTO.getRetornarPartidaDTO(partida));
        }
        return response;
    }

    public RetornarPartidaDTO editarPartida(EditarPartidaDTO dto, Integer id) {

        Partida partida = partidaRepository.findById(id).get();
        partida.setPlacarMandante(dto.getPlacarMandante());
        partida.setPlacarVisitante(dto.getPlacarVisitante());
        partida.setStatus("REALIZADA");

        partida = partidaRepository.save(partida);
        return RetornarPartidaDTO.getRetornarPartidaDTO(partida);

    }

    public RetornarPartidaDTO getPartida(Integer id) {
        Optional<Partida> partida = partidaRepository.findById(id);
        if (partida.isPresent()) {
            return RetornarPartidaDTO.getRetornarPartidaDTO(partida.get());
        }
        throw new PartidaNaoEncontradaException("Partida não encontrada");
    }

}
