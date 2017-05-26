package br.com.invest.plataforma.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

import br.com.investimentos.core.apresentacao.dto.CorretoraDTO;
import br.com.investimentos.core.apresentacao.fachada.CorretoraFachada;
import br.com.investimentos.core.dominio.validation.validador.ValidationException;

@Path("corretora")
public class CorretoraServico {

    @Inject
    private CorretoraFachada corretoraFachada;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void salvar(CorretoraDTO corretoraDTO) throws ValidationException {
        this.corretoraFachada.salvar(corretoraDTO);
    }
}