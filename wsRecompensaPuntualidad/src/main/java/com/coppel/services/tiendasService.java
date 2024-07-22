/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.services;

import com.coppel.controllers.tiendasController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author agonzalezr
 */
@Path("tiendasRecompensa")
public class tiendasService {
    
    @Path("/Tiendas")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String validaTienda(
            String tienda
    )throws IOException,SQLException, ParseException{
        tiendasController tiendasController = new tiendasController();
        return tiendasController.consultarTiendas(tienda);
    }
    
    @Path("/logicasRecompensa")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarLogicas(
        @DefaultValue(value = "0") @QueryParam(value = "clvNacional") String clvNacional,
        @DefaultValue(value = "0") @QueryParam(value = "clvStatus") String clvStatus
    )throws IOException, SQLException{
        tiendasController tiendasController = new tiendasController();
        return tiendasController.consultarLogicas(clvNacional,clvStatus);
    }
    
    @Path("/ciudadesRecompensa")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarCiudaesRecompensa(
        @DefaultValue(value = "0") @QueryParam(value = "numCiudad") int numCiudad,
        @DefaultValue(value = "0") @QueryParam(value = "clvStatus") int clvStatus
    )throws IOException, SQLException{
        tiendasController tiendasController = new tiendasController();
        return tiendasController.consultarCiudadesRecompensa(numCiudad,clvStatus);
    }
    
    @Path("/logicasCiudad")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarLogicasCiudad(
        @DefaultValue(value = "0") @QueryParam(value = "idu_logica") int idu_logica,
        @DefaultValue(value = "0") @QueryParam(value = "prcDE") int prcDE,
        @DefaultValue(value = "0") @QueryParam(value = "fecha") String fecha
    )throws IOException, SQLException{
        tiendasController tiendasController = new tiendasController();
        return tiendasController.consultarLogicasCiudad(idu_logica,prcDE,fecha);
    }
    
    
}
