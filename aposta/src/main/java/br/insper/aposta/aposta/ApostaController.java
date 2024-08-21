package br.insper.aposta.aposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aposta")
public class ApostaController {

    @Autowired
    private ApostaService apostaService;

    @GetMapping
    public List<Aposta> listarApostas() {
        return apostaService.listarApostas();
    }

    @GetMapping("/{id}")
    public Aposta resultadoAposta(String id){
        return apostaService.resultadoAposta(id);
    }

    @PostMapping
    public void salvarAposta(@RequestBody Aposta aposta) {
        apostaService.salvarAposta(aposta);
    }

}
