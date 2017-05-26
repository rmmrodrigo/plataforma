package br.com.invest.plataforma.util;

import java.util.Collections;
import java.util.List;

import com.google.inject.Module;
import com.google.inject.servlet.ServletModule;
import com.squarespace.jersey2.guice.JerseyGuiceServletContextListener;

import br.com.invest.plataforma.service.MyService;
import br.com.invest.plataforma.service.MyServiceImpl;
import br.com.investimentos.core.apresentacao.fachada.CorretoraFachada;

public class MyJerseyGuiceServletContextListener extends JerseyGuiceServletContextListener {
    @Override
    protected List<? extends Module> modules() {
        return Collections.singletonList(new ServletModule() {
            @Override
            protected void configureServlets() {
            	bind(CorretoraFachada.class);
                bind(MyService.class).to(MyServiceImpl.class);
            }
        });
    }
}