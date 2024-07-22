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

import com.coppel.entities.CarterasParticipantes;
import com.coppel.entities.CarterasRecompensa;
import com.coppel.entities.CiudadesRecompensa;
import com.coppel.entities.ConfiguracionServicio;
import com.coppel.entities.CorteAnterior;
import com.coppel.entities.cortesCumplidos;
import com.coppel.entities.LogCRTP;
import com.coppel.entities.LogicasRecompensa;
import com.coppel.entities.MovAbonosPuntuales;
import com.coppel.entities.MovDineroElectronicoRecompensa;
import com.coppel.entities.Puntualidades;

import org.hibernate.Session;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author agonzalezr
 */
public class recompensaDal {
    
    public List<cortesCumplidos> obtenerCortes(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        //List<cortesCumplidos> cortesCumplidos = null;
        List<cortesCumplidos> cortesCumplidos = null;
        try(Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            
            ResultadoInfo <cortesCumplidos> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    cortesCumplidos.class,
                    filtros,
                    new LinkedList<>()
            );       
            cortesCumplidos = new LinkedList<>(resultado.getElementos());
        }
        return cortesCumplidos;
    }
    
    public List<ConfiguracionServicio> consultaUrl(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        //List<cortesCumplidos> cortesCumplidos = null;
        List<ConfiguracionServicio> confServicio = null;
        try(Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            
            ResultadoInfo <ConfiguracionServicio> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    ConfiguracionServicio.class,
                    filtros,
                    new LinkedList<>()
            );       
            confServicio = new LinkedList<>(resultado.getElementos());
        }
        return confServicio;
    }
    
    public cortesCumplidos registrarCorteCumplido(cortesCumplidos datosCorte) throws IOException, SQLException {
        try (Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
             AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
             accesoDatosBase.guardar(datosCorte);
        }
        
        return datosCorte;
    }
    
    public CorteAnterior registrarCorteAnterior(CorteAnterior datosCorte) throws IOException, SQLException {
        try (Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
             AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
             accesoDatosBase.guardar(datosCorte);
        }
        
        return datosCorte;
    }
    
    public List<cortesCumplidos> consultarImporteCorte(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        //List<cortesCumplidos> cortesCumplidos = null;
        List<cortesCumplidos> cortesCumplidos = null;
        try(Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            
            ResultadoInfo <cortesCumplidos> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    cortesCumplidos.class,
                    filtros,
                    new LinkedList<>()
            );       
            cortesCumplidos = new LinkedList<>(resultado.getElementos());
        }
        return cortesCumplidos;
    }
    
    public List<Puntualidades> obtenerPuntualidades(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List<Puntualidades> puntualidades = null;
        
        try(Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo <Puntualidades> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    Puntualidades.class,
                    filtros,
                    new LinkedList<>() 
            );
            puntualidades = new LinkedList<>(resultado.getElementos());
            /*puntualidades = accesoDatosBase.obtenerPorId(
                Puntualidades.class,
                idu_logica
            );*/
        }
        return puntualidades;
    }
    
    public List<LogicasRecompensa> consultarLogicasRecompensa(
            PaginadorInfo paginadorInfo,
            Collection<FilterInfo> filtros,
            Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <LogicasRecompensa> logicasRecompensa=null;
        try(Session session =  HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            /*logicasRecompensa = accesoDatosBase.obtenerPorId(
                LogicasRecompensa.class,
                idu_logica);*/
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
    
     public List<LogicasRecompensa> consultaNacional(
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
    
    public List<CarterasParticipantes> consultarCarterasParticipantes(
            PaginadorInfo paginadorInfo,
            Collection<FilterInfo> filtros,
            Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        //CarterasRecompensa carterasRecompensa=null;
        List<CarterasParticipantes> CarterasParticipantes;
        try(Session session =  HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            
            ResultadoInfo<CarterasParticipantes> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    CarterasParticipantes.class,
                    filtros,
                    new LinkedList<>()
            );
            CarterasParticipantes = new LinkedList<>(resultado.getElementos());
        }
        
        return CarterasParticipantes;
    }
    
    public List<CarterasRecompensa> consultarCarterasRecompensa(
           PaginadorInfo paginadorInfo,
            Collection<FilterInfo> filtros,
              Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        //CarterasRecompensa carterasRecompensa=null;
        List<CarterasRecompensa> carterasRecompensa;
        try(Session session =  HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            
            ResultadoInfo<CarterasRecompensa> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    CarterasRecompensa.class,
                    filtros,
                    new LinkedList<>()
            );
            carterasRecompensa = new LinkedList<>(resultado.getElementos());
            
            /*carterasRecompensa = accesoDatosBase.obtenerPorId(
                    CarterasRecompensa.class,
                    idu_logica);*/
        }
        
        return carterasRecompensa;
    }
    
    public List<MovAbonosPuntuales> consultarMovAbonosPuntuales(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <MovAbonosPuntuales> movAbonosPuntuales;
        try(Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<MovAbonosPuntuales> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    MovAbonosPuntuales.class,
                    filtros,
                    new LinkedList<>());
            movAbonosPuntuales = new LinkedList<>(resultado.getElementos());
        }
        return movAbonosPuntuales;
    }
    
   public List<MovAbonosPuntuales> consultarMovAdelantado(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <MovAbonosPuntuales> movAbonosPuntuales;
        try(Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<MovAbonosPuntuales> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    MovAbonosPuntuales.class,
                    filtros,
                    new LinkedList<>());
            movAbonosPuntuales = new LinkedList<>(resultado.getElementos());
        }
        return movAbonosPuntuales;
    }
   
    public List<MovAbonosPuntuales> sumMovAdelantado(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <MovAbonosPuntuales> movAbonosPuntuales;
        try(Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<MovAbonosPuntuales> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    MovAbonosPuntuales.class,
                    filtros,
                    new LinkedList<>());
            movAbonosPuntuales = new LinkedList<>(resultado.getElementos());
        }
        return movAbonosPuntuales;
    }
    
    
    public List<MovAbonosPuntuales> consultarImpAbonosPuntuales(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        //List<cortesCumplidos> cortesCumplidos = null;
        List<MovAbonosPuntuales> movAbonosPuntuales = null;
        try(Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            
            ResultadoInfo <MovAbonosPuntuales> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    MovAbonosPuntuales.class,
                    filtros,
                    new LinkedList<>()
            );       
            movAbonosPuntuales = new LinkedList<>(resultado.getElementos());
        }
        return movAbonosPuntuales;
    }
    
    public List<cortesCumplidos> consultarImpAbonosRegistrados(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        //List<cortesCumplidos> cortesCumplidos = null;
        List<cortesCumplidos> cortesCumplidos = null;
        try(Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            
            ResultadoInfo <cortesCumplidos> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    cortesCumplidos.class,
                    filtros,
                    new LinkedList<>()
            );       
            cortesCumplidos = new LinkedList<>(resultado.getElementos());
        }
        return cortesCumplidos;
    }
    
    public MovAbonosPuntuales registrarMovAbonoPuntual(MovAbonosPuntuales datosCliente) throws IOException, SQLException {
        try (Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
             AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
             accesoDatosBase.guardar(datosCliente);
        }
        
        return datosCliente;
    }
    
    
    
    public List<MovDineroElectronicoRecompensa> consultarMovDineroElectronico(
          PaginadorInfo paginadorInfo,
            Collection<FilterInfo> filtros,
            Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List<MovDineroElectronicoRecompensa> movDineroElectronico;
        try(Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<MovDineroElectronicoRecompensa> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    MovDineroElectronicoRecompensa.class,
                    filtros,
                    new LinkedList<>());
            movDineroElectronico = new LinkedList<>(resultado.getElementos());
        }
        return movDineroElectronico;
    }
    
    public MovDineroElectronicoRecompensa registrarMovDineroElectronico(MovDineroElectronicoRecompensa datosDE) throws IOException, SQLException {
        try (Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
             AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
             accesoDatosBase.guardar(datosDE);
        }
        
        return datosDE;
    }
    
    public LogCRTP registrarLog(LogCRTP nuevoRegLog) throws IOException, SQLException {
        try (Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            accesoDatosBase.guardar(nuevoRegLog);
             
        }
        
        return nuevoRegLog;
    }
    
    public List<cortesCumplidos> consultarCorteCliente(
          PaginadorInfo paginadorInfo,
            Collection<FilterInfo> filtros,
            Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List<cortesCumplidos> cortesCumplidos;
        try(Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<cortesCumplidos> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    cortesCumplidos.class,
                    filtros,
                    new LinkedList<>());
            cortesCumplidos = new LinkedList<>(resultado.getElementos());
        }
        return cortesCumplidos;
    }
    
    /* public void cancelarAbono(
        int numCliente,
        int numTicket
    ){
       try(Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
             accesoDatosBase.
            
        } catch (Exception ex) {
            System.out.println("Exception en DAL: " + ex);
            throw ex;
        }
    }*/
    public List<MovAbonosPuntuales> consultarMovAbonos(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <MovAbonosPuntuales> movAbonosPuntuales;
        try(Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<MovAbonosPuntuales> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    MovAbonosPuntuales.class,
                    filtros,
                    new LinkedList<>());
            movAbonosPuntuales = new LinkedList<>(resultado.getElementos());
        }
        return movAbonosPuntuales;
    }
    
    public List<cortesCumplidos> consultaCorte(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <cortesCumplidos> cortesCumplidos;
        try(Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<cortesCumplidos> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    cortesCumplidos.class,
                    filtros,
                    new LinkedList<>());
            cortesCumplidos = new LinkedList<>(resultado.getElementos());
        }
        return cortesCumplidos;
    }
    
    public List<CorteAnterior> consultaCorteAnterior(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <CorteAnterior> cortesCumplidos;
        try(Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<CorteAnterior> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    CorteAnterior.class,
                    filtros,
                    new LinkedList<>());
            cortesCumplidos = new LinkedList<>(resultado.getElementos());
        }
        return cortesCumplidos;
    }
    
    public List<MovDineroElectronicoRecompensa> consultaDineroElectronico(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <MovDineroElectronicoRecompensa> MovDineroElectronicoRecompensa;
        try(Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<MovDineroElectronicoRecompensa> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    MovDineroElectronicoRecompensa.class,
                    filtros,
                    new LinkedList<>());
            MovDineroElectronicoRecompensa = new LinkedList<>(resultado.getElementos());
        }
        return MovDineroElectronicoRecompensa;
    }
    
    public List<MovDineroElectronicoRecompensa> consultaSiGanoDE(
        PaginadorInfo paginadorInfo,
        Collection<FilterInfo> filtros,
        Collection<FetchInfo> fetchs
    )throws IOException,SQLException{
        List <MovDineroElectronicoRecompensa> MovDineroElectronicoRecompensa;
        try(Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<MovDineroElectronicoRecompensa> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    MovDineroElectronicoRecompensa.class,
                    filtros,
                    new LinkedList<>());
            MovDineroElectronicoRecompensa = new LinkedList<>(resultado.getElementos());
        }
        return MovDineroElectronicoRecompensa;
    }
    
    public MovDineroElectronicoRecompensa eliminarDineroE(MovDineroElectronicoRecompensa cancelaDE) throws IOException, SQLException {
        try (Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            accesoDatosBase.eliminar(cancelaDE);
             
        }
        
        return cancelaDE;
    }
    
    public cortesCumplidos eliminarCorte(cortesCumplidos cancelaCorte) throws IOException, SQLException {
        try (Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            accesoDatosBase.eliminar(cancelaCorte);    
        }
        return cancelaCorte;
    }
    
    public CorteAnterior eliminarCorteAnt(CorteAnterior cancelaCorte) throws IOException, SQLException {
        try (Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            accesoDatosBase.eliminar(cancelaCorte);    
        }
        return cancelaCorte;
    }
    
    public MovAbonosPuntuales eliminarAbono(MovAbonosPuntuales cancelaAbono) throws IOException, SQLException {
        try (Session session = HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            accesoDatosBase.eliminar(cancelaAbono);    
        }
        return cancelaAbono;
    }
        
    public MovAbonosPuntuales consultarMovAbonosID(
        int keyx
    )throws IOException,SQLException{
        
        
        MovAbonosPuntuales empleado = null;
        try (Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            //empleado = this.accesoDatosBase.(MovAbonosPuntuales.class, keyx);
            empleado = accesoDatosBase.obtenerPorId(MovAbonosPuntuales.class, keyx);
        } catch (Exception ex) {
            
            throw ex;
        }
        return empleado;
        /*List <MovAbonosPuntuales> movAbonosPuntuales;
        try(Session session= HibernateFactoryManager.getInstance("hibernate").getSession()){
            AccesoDatosBase accesoDatosBase = new AccesoDatosBase(session);
            ResultadoInfo<MovAbonosPuntuales> resultado = accesoDatosBase.obtenerElementosPaginados(
                    paginadorInfo,
                    MovAbonosPuntuales.class,
                    filtros,
                    new LinkedList<>());
            movAbonosPuntuales = new LinkedList<>(resultado.getElementos());
        }
        return movAbonosPuntuales;*/
    }    
        
}
