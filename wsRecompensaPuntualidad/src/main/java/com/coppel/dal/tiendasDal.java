/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.dal;

import com.coppel.coppelframework.dal.AccesoDatosBase;
import com.coppel.coppelframework.dal.HibernateFactoryManager;
import com.coppel.coppelframework.dal.utils.FetchInfo;
import com.coppel.coppelframework.dal.utils.FilterInfo;
import com.coppel.coppelframework.dal.utils.PaginadorInfo;
import com.coppel.coppelframework.dal.utils.ResultadoInfo;
import com.coppel.entities.CiudadesRecompensa;
import com.coppel.entities.LogicasRecompensa;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author agonzalezr
 */
public class tiendasDal {
    public List<LogicasRecompensa> consultarLogicas(
            PaginadorInfo paginadorInfo,
            Collection<FilterInfo> filtros,
            Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <LogicasRecompensa> logicasRecompensa=null;
        try(Session session =  HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo <LogicasRecompensa> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    LogicasRecompensa.class,
                    filtros,
                    new LinkedList<>()
            );
            logicasRecompensa = new LinkedList<>(resultado.getElementos());
        }
        return logicasRecompensa;
    }
    
      public List<CiudadesRecompensa> consultarCiudadesRecompensa(
            PaginadorInfo paginadorInfo,
            Collection<FilterInfo> filtros,
            Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <CiudadesRecompensa> ciudadesRecompensa=null;
        try(Session session =  HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo <CiudadesRecompensa> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    CiudadesRecompensa.class,
                    filtros,
                    new LinkedList<>()
            );
            ciudadesRecompensa = new LinkedList<>(resultado.getElementos());
        }
        
        return ciudadesRecompensa;
    }
      
    public List<LogicasRecompensa> consultarLogicasCiudad(
            PaginadorInfo paginadorInfo,
            Collection<FilterInfo> filtros,
            Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <LogicasRecompensa> logicasRecompensa=null;
        try(Session session =  HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo <LogicasRecompensa> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    LogicasRecompensa.class,
                    filtros,
                    new LinkedList<>()
            );
            logicasRecompensa = new LinkedList<>(resultado.getElementos());
        }
        return logicasRecompensa;
    }
}
