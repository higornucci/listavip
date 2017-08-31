package br.com.higornucci.listavip.controller;

import br.com.higornucci.listavip.model.Convidado;
import br.com.higornucci.listavip.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConvidadoController {
    private final ConvidadoRepository repository;

    @Autowired
    public ConvidadoController(ConvidadoRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("listaconvidados")
    public String listaConvidados(Model model) {
        Iterable<Convidado> convidados = repository.findAll();
        model.addAttribute("convidados", convidados);
        return "listaconvidados";
    }

    @RequestMapping(value = "salvar", method = RequestMethod.POST)
    public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
                         @RequestParam("telefone") String telefone, Model model) {

        Convidado novoConvidado = new Convidado(nome, email, telefone);
        repository.save(novoConvidado);

        Iterable<Convidado> convidados = repository.findAll();
        model.addAttribute("convidados", convidados);

        return "listaconvidados";
    }
}
