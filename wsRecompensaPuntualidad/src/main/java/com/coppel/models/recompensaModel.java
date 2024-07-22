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

import com.coppel.dal.recompensaDal;

import com.coppel.entities.CarterasParticipantes;
import com.coppel.entities.CarterasRecompensa;
import com.coppel.entities.ConfiguracionServicio;
import com.coppel.entities.CiudadesRecompensa;
import com.coppel.entities.CorteAnterior;
import com.coppel.entities.cortesCumplidos;
import com.coppel.entities.LogCRTP;
import com.coppel.entities.LogicasRecompensa;
import com.coppel.entities.MovAbonosPuntuales;
import com.coppel.entities.MovDineroElectronicoRecompensa;
import com.coppel.entities.Puntualidades;

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
public class recompensaModel {
    
    private Properties configProperties;
    
    public recompensaModel(Properties configProperties){
        this.configProperties=configProperties;
    }
    
    public recompensaModel(){
    }
    
     public List<ConfiguracionServicio> consultaUrl(
            String clvStatus
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 100000);
        List<FilterInfo> filtros = new LinkedList<>();        
                FilterInfo filtroNumCliente = new FilterInfo();
                //Criterion clvStatus = Restrictions.eq("clvStatus", clvStatus);

                filtroNumCliente.setExpresion(Restrictions.eq("clvStatusPL", clvStatus));
                filtros.add(filtroNumCliente);


            
        return dalRecompensa.consultaUrl(paginadorInfo,filtros,null);
    }
    
    public List<cortesCumplidos> consultarCortes(
            int num_cliente,
            LocalDate fechaCorteBuscar
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 100000);
        List<FilterInfo> filtros = new LinkedList<>();        
        Date date = java.sql.Date.valueOf(fechaCorteBuscar);

            if(num_cliente >0) {
                FilterInfo filtroNumCliente = new FilterInfo();
                //filtroNumCliente.setExpresion(Restrictions.eq("numCliente", num_cliente));    
                Criterion numeroCliente = Restrictions.eq("numCliente", num_cliente);
                Criterion corteBuscar = Restrictions.eq("fecCorteCartera", date);

                filtroNumCliente.setExpresion(Restrictions.and(numeroCliente,corteBuscar));
                filtros.add(filtroNumCliente);

               //cortesCumplidos = dalRecompensa.obtenerCortes(numCliente);
            }
        return dalRecompensa.obtenerCortes(paginadorInfo,filtros,null);
    }

    public cortesCumplidos registrarCorteCumplido(cortesCumplidos datosCorte) throws IOException, SQLException {
        recompensaDal dalRecompensa = new recompensaDal();
        if (datosCorte.getNumCliente()>0) {
            return dalRecompensa.registrarCorteCumplido(datosCorte);
        }
        throw new ApplicationException("{\"mensaje\" : \"no pudo registrarse\"}", Response.Status.BAD_REQUEST);
    }
    
    public CorteAnterior registrarCorteAnterior(CorteAnterior datosCorte) throws IOException, SQLException {

        recompensaDal dalRecompensa = new recompensaDal();
        if (datosCorte.getNumCliente()>0) {
            return dalRecompensa.registrarCorteAnterior(datosCorte);
        }
        throw new ApplicationException("{\"mensaje\" : \"no pudo registrarse\"}", Response.Status.BAD_REQUEST);
    }
    
    public List<cortesCumplidos> consultarImporteCorte(
            int numCliente,
            int iduLogica,
            Date fecha
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 1000000);
        List<FilterInfo> filtros = new LinkedList<>();        
            if(numCliente >0) {
                FilterInfo filtroNumCliente = new FilterInfo();
                //filtroNumCliente.setExpresion(Restrictions.eq("numCliente", num_cliente));    
                Criterion numeroCliente = Restrictions.eq("numCliente", numCliente);
                Criterion idLogica = Restrictions.eq("iduLogica", iduLogica);
                Criterion fechaC = Restrictions.gt("fecCorteCartera", fecha);

                filtroNumCliente.setExpresion(Restrictions.and(numeroCliente,idLogica,fechaC));
                filtros.add(filtroNumCliente);
            }
        return dalRecompensa.consultarImporteCorte(paginadorInfo,filtros,null);
    }
    
    
    public List<Puntualidades> consultaPuntualidad(
        int idu_logica,
        String clv_puntualidad
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 100000);
        List<FilterInfo> filtros = new LinkedList<>();
        if(idu_logica>0){
            FilterInfo filtroPuntualidad = new FilterInfo();
            Criterion iduLogica = Restrictions.eq("iduLogica", idu_logica);
            Criterion clvPuntualidad = Restrictions.eq("clvPuntualidad", clv_puntualidad);
            filtroPuntualidad.setExpresion(Restrictions.and(iduLogica,clvPuntualidad));
            filtros.add(filtroPuntualidad);

        }
        //return puntualidades;
        return dalRecompensa.obtenerPuntualidades(paginadorInfo, filtros,null);
    }
    
    public List<LogicasRecompensa> consultarLogicasRecompensa(
            int idu_logica
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        LogicasRecompensa logicasRecompensa = null;
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 100000);
        List<FilterInfo> filtros = new LinkedList<>();
        if(idu_logica>0){
            FilterInfo filtroLogicas = new FilterInfo();
            //logicasRecompensa= dalRecompensa.consultarLogicasRecompensa(idu_logica);
            filtroLogicas.setExpresion(Restrictions.eq("idu_logica", idu_logica));
            filtros.add(filtroLogicas);
        }
        return dalRecompensa.consultarLogicasRecompensa(paginadorInfo,filtros,null);
    }
    public List<CiudadesRecompensa> consultarCiudadesRecompensa(
            int numCiudad
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 100000);
        List<FilterInfo> filtros = new LinkedList<>();        
        LocalDate hoy = LocalDate.now(); 
        Date date = java.sql.Date.valueOf(hoy);
        //System.out.println("Date: "+date);
        if(numCiudad>0){
            FilterInfo filtroId = new FilterInfo();
            Criterion ciudad =Restrictions.eq("numCiudad", numCiudad);
            Criterion fecAct = Restrictions.le("fecActivacion", date);
            filtroId.setExpresion(Restrictions.and(ciudad,fecAct));
            filtros.add(filtroId);
        }    
        return dalRecompensa.consultarCiudadesRecompensa(paginadorInfo,filtros,null);
    }
    
    public List<LogicasRecompensa> consultaNacional(
            String claveNacional,
            String claveStatus
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 100000);
        List<FilterInfo> filtros = new LinkedList<>();
        LocalDate hoy = LocalDate.now(); 
        Date date = java.sql.Date.valueOf(hoy);

        if(claveNacional.length()>0){
            FilterInfo filtroNacional = new FilterInfo();
            Criterion nacional = Restrictions.eq("clvNacional", claveNacional);
            Criterion status = Restrictions.eq("clvStatus", claveStatus);
            Criterion fecAct = Restrictions.le("fecActivacion", date);
            filtroNacional.setExpresion(Restrictions.and(nacional,status,fecAct));
            filtros.add(filtroNacional);
        }    
        return dalRecompensa.consultaNacional(paginadorInfo,filtros,null);
    }
    
    public List<CarterasParticipantes> consultarCarterasParticipantes(
        String claveMovimiento
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 100000);
        List<FilterInfo> filtros = new LinkedList<>();
        if(claveMovimiento.length()>0){
            FilterInfo filtroClv = new FilterInfo();
            filtroClv.setExpresion(Restrictions.eq("clvMovimiento", claveMovimiento));   
            filtros.add(filtroClv);
        }
        
        return  dalRecompensa.consultarCarterasParticipantes(paginadorInfo,filtros,null);
    } 
    
    public List<CarterasRecompensa> consultarCarterasRecompensa(
        int idu_logica,
        int numTipoCuenta
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 10000);
        List<FilterInfo> filtros = new LinkedList<>();
        if(idu_logica>0){
            FilterInfo filtroCarterasR = new FilterInfo();
            Criterion iduLogica = Restrictions.eq("idu_logica", idu_logica);
            Criterion tipoCuenta = Restrictions.eq("numTipoCuenta", numTipoCuenta);
            filtroCarterasR.setExpresion(Restrictions.and(iduLogica,tipoCuenta));
            filtros.add(filtroCarterasR);
        }
        
        return  dalRecompensa.consultarCarterasRecompensa(paginadorInfo,filtros,null);
    }
   
    public List<MovAbonosPuntuales> consultarMovAbonosPuntuales(
        int numeroCliente,    
        LocalDate fecCorteCartera
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1,10000);
        List <FilterInfo> filtros = new LinkedList<>();
        Date date = java.sql.Date.valueOf(fecCorteCartera);
        if(fecCorteCartera.lengthOfMonth()>0){
            /*FilterInfo filtroNumCliente = new FilterInfo();
            filtroNumCliente.setExpresion(Restrictions.eq("fecCorteCartera", date));
            filtros.add(filtroNumCliente);*/
            
            FilterInfo filtroImporteMovAbono = new FilterInfo();
            Criterion numCliente = Restrictions.eq("numCliente", numeroCliente);
            Criterion fecCorte = Restrictions.eq("fecCorteCartera", date);
            filtroImporteMovAbono.setExpresion(Restrictions.and(numCliente,fecCorte));
            filtros.add(filtroImporteMovAbono);
        }
        return dalRecompensa.consultarMovAbonosPuntuales(paginadorInfo,filtros,null);
    }
    
    public List<MovAbonosPuntuales> consultarMovAdelantado(
        int numeroCliente,    
        LocalDate fecCorteCartera
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1,10000);
        List <FilterInfo> filtros = new LinkedList<>();
        Date date = java.sql.Date.valueOf(fecCorteCartera);
        if(fecCorteCartera.lengthOfMonth()>0){
            /*FilterInfo filtroNumCliente = new FilterInfo();
            filtroNumCliente.setExpresion(Restrictions.eq("fecCorteCartera", date));
            filtros.add(filtroNumCliente);*/
            
            FilterInfo filtroImporteMovAbono = new FilterInfo();
            Criterion numCliente = Restrictions.eq("numCliente", numeroCliente);
            Criterion fecCorte = Restrictions.gt("fecCorteCartera", date);
            filtroImporteMovAbono.setExpresion(Restrictions.and(numCliente,fecCorte));
            filtros.add(filtroImporteMovAbono);
        }
        return dalRecompensa.consultarMovAdelantado(paginadorInfo,filtros,null);
    }
    
    public List<MovAbonosPuntuales> sumMovAdelantado(
        int numeroCliente,    
        LocalDate fecCorteCartera
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1,10000);
        List <FilterInfo> filtros = new LinkedList<>();
        Date date = java.sql.Date.valueOf(fecCorteCartera);
        if(fecCorteCartera.lengthOfMonth()>0){
            
            FilterInfo filtroImporteMovAbono = new FilterInfo();
            Criterion numCliente = Restrictions.eq("numCliente", numeroCliente);
            Criterion fecCorte = Restrictions.ge("fecCorteCartera", date);
            filtroImporteMovAbono.setExpresion(Restrictions.and(numCliente,fecCorte));
            filtros.add(filtroImporteMovAbono);
        }
        return dalRecompensa.sumMovAdelantado(paginadorInfo,filtros,null);
    }
    
    public List<MovAbonosPuntuales> consultarImpAbonosPuntuales(
            int numCliente,
            Date fecha
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 1000000);
        List<FilterInfo> filtros = new LinkedList<>();        
            if(numCliente >0) {
                FilterInfo filtroNumCliente = new FilterInfo();
                //filtroNumCliente.setExpresion(Restrictions.eq("numCliente", num_cliente));    
                Criterion numeroCliente = Restrictions.eq("numCliente", numCliente);
                Criterion fechaC = Restrictions.gt("fecCorteCartera", fecha);

                filtroNumCliente.setExpresion(Restrictions.and(numeroCliente,fechaC));
                filtros.add(filtroNumCliente);
            }
        return dalRecompensa.consultarImpAbonosPuntuales(paginadorInfo,filtros,null);
    }
     
    public List<cortesCumplidos> consultarImpAbonosRegistrados(
            int numCliente,
            int iduLogica,
            Date fecha
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 1000000);
        List<FilterInfo> filtros = new LinkedList<>();        
            if(numCliente >0) {
                FilterInfo filtroNumCliente = new FilterInfo();
                //filtroNumCliente.setExpresion(Restrictions.eq("numCliente", num_cliente));    
                Criterion numeroCliente = Restrictions.eq("numCliente", numCliente);
                Criterion idLogica = Restrictions.eq("iduLogica", iduLogica);
                Criterion fechaC = Restrictions.ge("fecCorteCartera", fecha);

                filtroNumCliente.setExpresion(Restrictions.and(numeroCliente,idLogica,fechaC));
                filtros.add(filtroNumCliente);
            }
        return dalRecompensa.consultarImpAbonosRegistrados(paginadorInfo,filtros,null);
    } 
    
    public MovAbonosPuntuales registrarMovAbonoPuntual(MovAbonosPuntuales datosCliente) throws IOException, SQLException {
        recompensaDal dalRecompensa = new recompensaDal();
        if (datosCliente.getNumCliente()>0) {
            return dalRecompensa.registrarMovAbonoPuntual(datosCliente);
        }
        throw new ApplicationException("{\"mensaje\" : \"no pudo registrarse\"}", Response.Status.BAD_REQUEST);
    }
    
    public List<MovDineroElectronicoRecompensa> consultarMovDineroElectronico(
            int numCliente
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1,10000);
        List <FilterInfo> filtros = new LinkedList<>();
        if(numCliente >0){
            FilterInfo filtroNumCliente = new FilterInfo();
            filtroNumCliente.setExpresion(Restrictions.eq("numCliente",numCliente));
            filtros.add(filtroNumCliente);
        }
        return dalRecompensa.consultarMovDineroElectronico(paginadorInfo,filtros,null);
    }
    
    public MovDineroElectronicoRecompensa registrarMovDineroElectronico(MovDineroElectronicoRecompensa datosDE) throws IOException, SQLException {
        recompensaDal dalRecompensa = new recompensaDal();
        if (datosDE.getNumCliente()>0) {
            return dalRecompensa.registrarMovDineroElectronico(datosDE);
        }
        throw new ApplicationException("{\"mensaje\" : \"no pudo registrarse\"}", Response.Status.BAD_REQUEST);
    }
    
    public LogCRTP registrarLog(LogCRTP nuevoRegLog) throws IOException, SQLException {
        recompensaDal dalRecompensa = new recompensaDal();
        if (nuevoRegLog.getMsj().length()>0) {
            return dalRecompensa.registrarLog(nuevoRegLog);
        }
        throw new ApplicationException("{\"mensaje\" : \"no pudo registrarse\"}", Response.Status.BAD_REQUEST);
    }
      
    public List<cortesCumplidos> consultarCorteCliente(
            int numCliente,
            LocalDate fechaCorteBuscar
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1,10);
        List <FilterInfo> filtros = new LinkedList<>();
        Date date = java.sql.Date.valueOf(fechaCorteBuscar);
        if(numCliente >0){
            FilterInfo filtroNumCliente = new FilterInfo();
            Criterion numeroCliente = Restrictions.eq("numCliente", numCliente);
            Criterion corteBuscar = Restrictions.eq("fecCorteCartera", date);

            filtroNumCliente.setExpresion(Restrictions.and(numeroCliente,corteBuscar));
            filtros.add(filtroNumCliente);
        }
        return dalRecompensa.consultarCorteCliente(paginadorInfo,filtros,null);
    }
    
    /*public void cancelarAbono(int numCliente,int numTicket) {
        recompensaDal dalRecompensa= new recompensaDal();
        dalRecompensa.cancelarAbono(numCliente,numTicket);
    }*/
    
    public List<MovAbonosPuntuales> consultarMovAbonos(
        int numCliente,
        int folioabono,
        LocalDate fecAbono
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 10);
        List<FilterInfo> filtros = new LinkedList<>();
        Date date = java.sql.Date.valueOf(fecAbono);
        if(numCliente>0){
            FilterInfo filtroCarterasR = new FilterInfo();
            Criterion cliente = Restrictions.eq("numCliente", numCliente);
            Criterion ticket = Restrictions.eq("folio", folioabono);
            Criterion fecha = Restrictions.ge("fecAbono", date);
            filtroCarterasR.setExpresion(Restrictions.and(cliente,ticket,fecha));
            filtros.add(filtroCarterasR);
        }
        
        return dalRecompensa.consultarMovAbonos(paginadorInfo,filtros,null);
    }
    
    public List<cortesCumplidos> consultaCorte(
        int numCliente,
        LocalDate fecAbono,
        String fechaCorte
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 10);
        List<FilterInfo> filtros = new LinkedList<>();
        Date date = java.sql.Date.valueOf(fecAbono);
        Date fecCorteAbono =java.sql.Date.valueOf(fechaCorte);
        if(numCliente>0){
            FilterInfo filtroCarterasR = new FilterInfo();
            Criterion cliente = Restrictions.eq("numCliente", numCliente);
            Criterion fecha = Restrictions.eq("fecAlcanzoMesPuntual", date);
            Criterion fecCorte = Restrictions.eq("fecCorteCartera", fecCorteAbono);
            filtroCarterasR.setExpresion(Restrictions.and(cliente,fecha,fecCorte));
            filtros.add(filtroCarterasR);
        }
        
        return  dalRecompensa.consultaCorte(paginadorInfo,filtros,null);
    }
    
    public List<CorteAnterior> consultaCorteAnterior(
        int numCliente,
        LocalDate fecAbono,
        String fechaCorte
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 10);
        List<FilterInfo> filtros = new LinkedList<>();
        Date date = java.sql.Date.valueOf(fecAbono);
        Date fecCorteAbono =java.sql.Date.valueOf(fechaCorte);
        if(numCliente>0){
            FilterInfo filtroCarterasR = new FilterInfo();
            Criterion cliente = Restrictions.eq("numCliente", numCliente);
            //Criterion fecha = Restrictions.eq("fecAlcanzoMesPuntual", date);
            Criterion fecCorte = Restrictions.eq("fecCorteCartera", fecCorteAbono);
            //filtroCarterasR.setExpresion(Restrictions.and(cliente,fecha,fecCorte));
            filtroCarterasR.setExpresion(Restrictions.and(cliente,fecCorte));
            filtros.add(filtroCarterasR);
        }
        
        return  dalRecompensa.consultaCorteAnterior(paginadorInfo,filtros,null);
    }
    
    
    public List<MovDineroElectronicoRecompensa> consultaDineroElectronico(
        int numCliente,
        LocalDate fecAbono
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 1000);
        List<FilterInfo> filtros = new LinkedList<>();
        Date date = java.sql.Date.valueOf(fecAbono);
        if(numCliente>0){
            FilterInfo filtroDineroE = new FilterInfo();
            Criterion cliente = Restrictions.eq("numCliente", numCliente);
            Criterion fecha = Restrictions.eq("fecGeneracion", date);
            filtroDineroE.setExpresion(Restrictions.and(cliente,fecha));
            filtros.add(filtroDineroE);
        }
        return  dalRecompensa.consultaDineroElectronico(paginadorInfo,filtros,null);
    }
    
    public List<MovDineroElectronicoRecompensa> consultaSiGanoDE(
        int numCliente,
        int numFolioabono,
        String fecGeneracion
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        PaginadorInfo paginadorInfo = new PaginadorInfo(1, 1000);
        List<FilterInfo> filtros = new LinkedList<>();
        
        Date date = java.sql.Date.valueOf(fecGeneracion);
        
        if(numCliente>0){
            FilterInfo filtroDineroE = new FilterInfo();
            Criterion cliente = Restrictions.eq("numCliente", numCliente);
            Criterion folioabono = Restrictions.eq("numFolioabono", numFolioabono);
            Criterion fecha = Restrictions.ge("fecGeneracion", date);
            filtroDineroE.setExpresion(Restrictions.and(cliente,folioabono,fecha));
            filtros.add(filtroDineroE);
        }
        return  dalRecompensa.consultaSiGanoDE(paginadorInfo,filtros,null);
    }
    
    public MovDineroElectronicoRecompensa eliminarDineroE(MovDineroElectronicoRecompensa cancelaDE) throws IOException, SQLException {
        recompensaDal dalRecompensa = new recompensaDal();
        if (cancelaDE.getNumCliente()>0){
            return dalRecompensa.eliminarDineroE(cancelaDE);
        }
        throw new ApplicationException("{\"mensaje\" : \"no pudo registrarse\"}", Response.Status.BAD_REQUEST);
    }
    
    public cortesCumplidos eliminarCorte(cortesCumplidos cancelaCorte) throws IOException, SQLException {
        recompensaDal dalRecompensa = new recompensaDal();
        if (cancelaCorte.getNumCliente()>0){
            return dalRecompensa.eliminarCorte(cancelaCorte);
        }
        throw new ApplicationException("{\"mensaje\" : \"no pudo registrarse\"}", Response.Status.BAD_REQUEST);
    }
    
    public CorteAnterior eliminarCorteAnt(CorteAnterior cancelaCorte) throws IOException, SQLException {
        recompensaDal dalRecompensa = new recompensaDal();
        if (cancelaCorte.getNumCliente()>0){
            return dalRecompensa.eliminarCorteAnt(cancelaCorte);
        }
        throw new ApplicationException("{\"mensaje\" : \"no pudo registrarse\"}", Response.Status.BAD_REQUEST);
    }
    
    public MovAbonosPuntuales eliminarAbono(MovAbonosPuntuales cancelaAbono) throws IOException, SQLException {
        recompensaDal dalRecompensa = new recompensaDal();
        if (cancelaAbono.getNumCliente()>0){
            return dalRecompensa.eliminarAbono(cancelaAbono);
        }
        throw new ApplicationException("{\"mensaje\" : \"no pudo registrarse\"}", Response.Status.BAD_REQUEST);
    }
    
    public MovAbonosPuntuales consultarMovAbonosID(
        int keyx
    )throws IOException,SQLException{
        recompensaDal dalRecompensa = new recompensaDal();
        MovAbonosPuntuales movAbonos =null;
        movAbonos = dalRecompensa.consultarMovAbonosID(keyx);
        return movAbonos;
    }
    
}
