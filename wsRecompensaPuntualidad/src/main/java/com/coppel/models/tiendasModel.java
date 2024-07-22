/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.models;

import com.coppel.coppelframework.dal.HibernateFactoryManager;
import com.coppel.coppelframework.exceptions.ApplicationException;
import com.coppel.coppelframework.dal.utils.FilterInfo;
import com.coppel.coppelframework.dal.utils.PaginadorInfo;

import com.coppel.dal.tiendasDal;

import com.coppel.entities.CiudadesRecompensa;
import com.coppel.entities.LogCRTP;
import com.coppel.entities.LogicasRecompensa;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Criterion;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.ws.rs.core.Response;

/**
 *
 * @author agonzalezr
 */
public class tiendasModel {

    private Properties configProperties;
    
    public tiendasModel(Properties configProperties){
        this.configProperties=configProperties;
    }
    
    public tiendasModel(){
    }
    
    public List<LogicasRecompensa> consultarLogicas(
            String clvNacional,
            String clvStatus
    )throws IOException,SQLException{
        tiendasDal dalTiendas = new tiendasDal();
        LogicasRecompensa logicasRecompensa = null;
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 100);
        List<FilterInfo> filtros = new LinkedList<>();
        if(clvNacional.length()>0){
            FilterInfo filtroLogicas = new FilterInfo();
            Criterion nacionalClv = Restrictions.eq("clvNacional", clvNacional);
            Criterion statusClv = Restrictions.eq("clvStatus", clvStatus);
            filtroLogicas.setExpresion(Restrictions.and(nacionalClv,statusClv));
            filtros.add(filtroLogicas);
        }
        return dalTiendas.consultarLogicas(paginadorInfo,filtros,null);
    }
    
     public List<CiudadesRecompensa> consultarCiudadesRecompensa(
            int numCiudad,
            int clvStatus
    )throws IOException,SQLException{
        tiendasDal dalTiendas = new tiendasDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 1000);
        List<FilterInfo> filtros = new LinkedList<>();
        if(numCiudad>0){
            FilterInfo filtroCiudades = new FilterInfo();
             Criterion numeroCiudad = Restrictions.eq("numCiudad", numCiudad);
            Criterion statusClv = Restrictions.eq("clvStatus", clvStatus);
            filtroCiudades.setExpresion(Restrictions.and(numeroCiudad,statusClv));
            filtros.add(filtroCiudades);
        }    
        return dalTiendas.consultarCiudadesRecompensa(paginadorInfo,filtros,null);
    }
     
    public List<LogicasRecompensa> consultarLogicasCiudad(
            int idu_logica
    )throws IOException,SQLException{
        tiendasDal dalTiendas = new tiendasDal();
        LogicasRecompensa logicasRecompensa = null;
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 100);
        List<FilterInfo> filtros = new LinkedList<>();
        if(idu_logica>0){
            FilterInfo filtroLogicas = new FilterInfo();
            filtroLogicas.setExpresion(Restrictions.eq("idu_logica", idu_logica));
            filtros.add(filtroLogicas);
        }
        return dalTiendas.consultarLogicasCiudad(paginadorInfo,filtros,null);
    }
    
}
