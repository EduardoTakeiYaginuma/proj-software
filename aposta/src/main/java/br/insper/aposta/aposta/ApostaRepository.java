package br.insper.aposta.aposta;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApostaRepository extends MongoRepository<Aposta, String>{
    List<Aposta> findByResultado(String resultado);
}
