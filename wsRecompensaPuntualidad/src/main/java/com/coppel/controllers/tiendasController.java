/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.controllers;

import com.coppel.coppelframework.config.ApplicationConfiguration;
import com.coppel.coppelframework.controllers.Controller;
import com.coppel.coppelframework.exceptions.ApplicationException;

import com.coppel.controllers.recompensaController;
import com.coppel.models.tiendasModel;

import com.coppel.entities.CiudadesRecompensa;
import com.coppel.entities.LogCRTP;
import com.coppel.entities.LogicasRecompensa;
import com.coppel.main.App;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.hibernate.HibernateException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLDataException;
import java.time.LocalDate;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author agonzalezr
 */
public class tiendasController {
    
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());
    
    public String consultarTiendas(
       String tienda    
    )throws IOException, SQLException, ParseException{
        
        LOGGER.info("Valida la tienda ");
        LOGGER.info(tienda);
        Gson gson = new Gson();
        recompensaController recController = new recompensaController();
        String response = null;
        String datosLog=null;
        String mensaje=null;
        String validaLogica=null;
        String validaCiudad=null;
        String validaCiudadRecompensa=null;
        String clvStatus="1";
        int status=0;
        int numCiudad=0;
        int idLogica = 0;
        int porcentajeDE = 0;
        String fecha="";
        try{
            
            LocalDate hoy = LocalDate.now(); 
            JsonObject json = new JsonObject();
            JsonParser parser = new JsonParser ();
            JsonElement jsonTree = parser.parse (tienda);
            jsonTree.isJsonObject ();
            jsonTree.isJsonArray ();
            jsonTree.isJsonNull ();
            jsonTree.isJsonPrimitive ();
            JsonObject jsonObject = jsonTree.getAsJsonObject ();

            JsonElement numTienda = jsonObject.get("numTienda");
            JsonElement numCiudadTienda = jsonObject.get("numCiudadTienda");
            numCiudad=Integer.parseInt(numCiudadTienda.toString());
            status=Integer.parseInt(clvStatus.toString());
            if(Integer.parseInt(numCiudadTienda.toString()) > 0){
                validaLogica=this.consultarLogicas("1", clvStatus);
                
                if(validaLogica.equals("0")){
                    validaCiudad= this.consultarCiudadesRecompensa(numCiudad, status);
                    
                    if(validaCiudad.equals("0")){
                        mensaje="No se encontro logica participante";
                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+0+",\"fechaReg\":"+hoy+"}";
                        String datos="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"fechaReg\":"+hoy+"\""+"}";
                        recController.registrarLog(datosLog);
                        LOGGER.info("No se encontro logica participante");
                        LOGGER.info(datos);
                        json.addProperty("status", 1);
                        json.addProperty("mensaje", mensaje);
                        json.addProperty("data",datos);
                        recController.registrarLog(datosLog);
                        response=json.toString();
                        
                    }else{
                        String[] partCortes = validaCiudad.split(",");
                        idLogica = Integer.parseInt(partCortes[0]);
                        porcentajeDE = Integer.parseInt(partCortes[1]);
                        fecha = partCortes[2].toString();
                        validaCiudadRecompensa=this.consultarLogicasCiudad(idLogica,porcentajeDE,fecha);
                        
                        if(validaCiudadRecompensa.equals("0")){
                            mensaje="No se encontro Logica";
                        }else{
                            mensaje="Se encontro Logica";
                        }
                        json.addProperty("status", 0);
                        json.addProperty("mensaje", mensaje);
                        json.addProperty("data",validaCiudadRecompensa);
                        LOGGER.info(mensaje);
                        LOGGER.info(validaCiudadRecompensa);
                        response=json.toString();
                    }
                }
                else{
                    
                    mensaje="Se encontro logica Nacional";
                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+0+",\"fechaReg\":"+hoy+"}";
                    String datos="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"fechaReg\":"+hoy+"\""+"}";
                    json.addProperty("status", 1);
                    json.addProperty("mensaje", mensaje);
                    json.addProperty("data",datos);
                    LOGGER.info(mensaje);
                    LOGGER.info(datos);
                    recController.registrarLog(datosLog);
                    response=json.toString();
                }
            }else{
                mensaje="No se encontro tienda con una logica";
                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+0+",\"fechaReg\":"+hoy+"}";
                String datos="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"fechaReg\":"+hoy+"\""+"}";
                json.addProperty("status", 1);
                json.addProperty("mensaje", mensaje);
                json.addProperty("data",datos);
                LOGGER.info(mensaje);
                LOGGER.info(datos);
                recController.registrarLog(datosLog);
                response=json.toString();
            }
            
        }catch(HibernateException ex) {
            System.err.println("Exception: " + ex.getMessage());
            JsonObject responseBody = new JsonObject();
            responseBody.addProperty("mensaje", ApplicationException.GENERIC_MESSAGE);
            responseBody.addProperty("devMessage", ex.getMessage());
            response = responseBody.toString();
            throw new ApplicationException(response, Response.Status.INTERNAL_SERVER_ERROR);
        }      
        return response;
    }
    
    public String consultarLogicas(
            String clvNacional,
            String clvStatus 
    )throws IOException, SQLException{
        tiendasModel tiendasModel = null;
        String response = null;
        Gson gson = new Gson();

        try{
            tiendasModel = new tiendasModel(ApplicationConfiguration.getInstance().getProperties());
            List<LogicasRecompensa> logicasRecompensa = tiendasModel.consultarLogicas(clvNacional,clvStatus);
            if(logicasRecompensa.size()>0){
                if(logicasRecompensa.get(0).getclvStatus().equals("1")){
                    JsonObject json = new JsonObject();

                    json.addProperty("idu_logica", logicasRecompensa.get(0).getidu_logica());
                    json.addProperty("clvStatus", logicasRecompensa.get(0).getclvStatus());
                    json.addProperty("numMeses", logicasRecompensa.get(0).getNumMeses());
                    json.addProperty("fecActivacion", logicasRecompensa.get(0).getFecActivacion().toString());
                    //response = gson.toJson(logicasRecompensa);
                    response=json.toString();
                }else{
                    response ="0";
                }
            }
            else{
               response="0";
            }
        } catch(HibernateException ex) {
            System.err.println("Exception: " + ex.getMessage());
            JsonObject responseBody = new JsonObject();
            responseBody.addProperty("mensaje", ApplicationException.GENERIC_MESSAGE);
            responseBody.addProperty("devMessage", ex.getMessage());
            response = responseBody.toString();
            throw new ApplicationException(response, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultarCiudadesRecompensa( 
            int numCiudad,
            int clvStatus
    )throws IOException,SQLDataException, SQLException{
        tiendasModel tiendasModel = null;
        String response = null;
        Gson gson = new Gson();
        int tieneCiudad=0;
        
        try{
            clvStatus=1;
            tiendasModel = new tiendasModel(ApplicationConfiguration.getInstance().getProperties());
            List <CiudadesRecompensa> ciudadesRecompensa = tiendasModel.consultarCiudadesRecompensa(numCiudad,clvStatus);
            tieneCiudad = ciudadesRecompensa.size();
            if(tieneCiudad>0){
                response=Integer.toString(ciudadesRecompensa.get(0).getIduLogica())+","+ciudadesRecompensa.get(0).getPrcDinero()+","+ciudadesRecompensa.get(0).getFecActivacion();
            }else{
                response="0";
            }
            
        }catch(HibernateException ex) {
            System.err.println("Exception: " + ex.getMessage());
            JsonObject responseBody = new JsonObject();
            responseBody.addProperty("mensaje", ApplicationException.GENERIC_MESSAGE);
            responseBody.addProperty("devMessage", ex.getMessage());
            response = responseBody.toString();
            throw new ApplicationException(response, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultarLogicasCiudad(
            int idu_logica,
            int prcDE,
            String fecha
    )throws IOException, SQLException{
        tiendasModel tiendasModel = null;
        String response = null;
        Gson gson = new Gson();

        try{
            tiendasModel = new tiendasModel(ApplicationConfiguration.getInstance().getProperties());
            List<LogicasRecompensa> logicasRecompensa = tiendasModel.consultarLogicasCiudad(idu_logica);
            if(logicasRecompensa.size()>0){
                
                if(logicasRecompensa.get(0).getclvStatus().equals("1")){
                    JsonObject json = new JsonObject();
                    json.addProperty("numlogica", logicasRecompensa.get(0).getidu_logica());
                    json.addProperty("estatuslogica", logicasRecompensa.get(0).getclvStatus());
                    json.addProperty("numcortes", logicasRecompensa.get(0).getNumMeses());
                    //json.addProperty("fechaInicio", logicasRecompensa.get(0).getFecActivacion().toString());
                    json.addProperty("fechaInicio", fecha);
                    json.addProperty("porcentaje", prcDE);
                    response=json.toString();
                }else{
                    response ="0";
                }
            }
            else{
               response="0";
            }
        }catch(IllegalStateException | HibernateException ex){
            System.err.println("Exception: " + ex.getMessage());
            JsonObject responseBody = new JsonObject();
            responseBody.addProperty("mensaje", ApplicationException.GENERIC_MESSAGE);
            responseBody.addProperty("devMessage", ex.getMessage());
            response = responseBody.toString();
            throw new ApplicationException(response, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
