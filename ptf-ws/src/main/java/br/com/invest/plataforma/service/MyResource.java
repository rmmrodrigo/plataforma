package br.com.invest.plataforma.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("myresource")
public class MyResource {

    @Inject
    private MyServiceImpl myService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return myService.hello("Jersey");
    }
}