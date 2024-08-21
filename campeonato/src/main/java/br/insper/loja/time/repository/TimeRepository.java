package br.insper.loja.time.repository;

import br.insper.loja.time.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer> {

    public List<Time> findByEstado(String estado);


}
