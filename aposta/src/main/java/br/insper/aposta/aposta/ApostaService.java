package br.insper.aposta.aposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApostaService {

    @Autowired
    private ApostaRepository apostaRepository;

    public void salvarAposta(Aposta aposta) {
        aposta.setId(UUID.randomUUID().toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RetornarPartidaDTO> partida = restTemplate.getForEntity(
                "http://54.209.245.182:8080/partida/" + aposta.getIdPartida(),
                RetornarPartidaDTO.class);

        if (partida.getStatusCode().is2xxSuccessful())  {
            apostaRepository.save(aposta);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar a partida relacionada a aposta!");
        }

    }

    public Aposta resultadoAposta(String id) {
        Optional<Aposta> aposta = apostaRepository.findById(String.valueOf(id));
        if (aposta.isPresent()) {
            int idPartida = aposta.get().getIdPartida();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<RetornarPartidaDTO> partida = restTemplate.getForEntity(
                    "http://54.209.245.182:8080/partida/" + idPartida,
                    RetornarPartidaDTO.class);
            if (partida.getStatusCode().is2xxSuccessful()) {
                RetornarPartidaDTO partida_ = partida.getBody();
                assert partida_ != null;
                if (partida_.getStatus().equals("REALIZADA")) {
                    if (partida_.getPlacarMandante() > partida_.getPlacarVisitante()) {
                        if (aposta.get().getResultado().equals("VITORIA_MANDANTE")) {
                            aposta.get().setStatus("GANHOU");
                        }
                    } else if (partida_.getPlacarMandante() < partida_.getPlacarVisitante()) {
                        if (aposta.get().getResultado().equals("VITORIA_VISITANTE")) {
                            aposta.get().setStatus("GANHOU");
                        }
                    } else if (partida_.getPlacarMandante().equals(partida_.getPlacarVisitante())) {
                        if (aposta.get().getResultado().equals("EMPATE")) {
                            aposta.get().setStatus("GANHOU");
                        }
                    } else {
                        aposta.get().setStatus("PERDEU");
                    }
                }
                return (Aposta) aposta.get();
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar a partida relacionada a aposta!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o aposta com esse id!");
    }

    public List<Aposta> listarApostas() {
        return apostaRepository.findAll();
    }

}
