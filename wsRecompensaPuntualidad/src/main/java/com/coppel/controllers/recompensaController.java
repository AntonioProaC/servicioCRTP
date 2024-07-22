/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.controllers;
import com.coppel.coppelframework.config.ApplicationConfiguration;
import com.coppel.coppelframework.controllers.Controller;
import com.coppel.coppelframework.exceptions.ApplicationException;

import com.coppel.entities.CarterasParticipantes;
import com.coppel.entities.CarterasRecompensa;
import com.coppel.entities.ConfiguracionServicio;
import com.coppel.entities.CorteAnterior;
import com.coppel.entities.cortesCumplidos;
import com.coppel.entities.CiudadesRecompensa;
import com.coppel.entities.LogCRTP;
import com.coppel.entities.LogicasRecompensa;
import com.coppel.entities.MovAbonosPuntuales;
import com.coppel.entities.MovDineroElectronicoRecompensa;
import com.coppel.entities.Puntualidades;
import com.coppel.main.App;

import com.coppel.models.recompensaModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;

import org.hibernate.HibernateException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLDataException;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author agonzalezr
 */
public class recompensaController extends Controller {
    
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());
    
    public String consultarAbonos(
        String Cliente
    )throws IOException, SQLException, ParseException{
        
        LOGGER.info("Inicia Validar Abono");
        LOGGER.info(Cliente);
        
        int numeroCliente=0;
        int numCiudad=0;
        int numTipoCuenta=0;
        int numeroTienda=0;
        int numLogica=0;
        int impAbono=0;
        int saldo=0;
        int abonoBase=0;
        int vencido=0;
        int minimo=0;
        int numTicket=0;
        int importeAbono=0;
        int porcentajeDE=0;
        int corteCliente=0;
        int flagSiValida=0;
        int flagLiquidaCuentas = 0;
        int iTerminaCuenta= 0;
        
        int sumMinimoMes=0;
        int sumMinMes = 0;
        int sumAbonoJson=0;
        int flagAbono=0;
        int contFlagAbono=0;
        int pasaCorte = 0;
        int pasaCorteA=0;
        int iRegistro=0;
        
        int iDiaUltimaCompra=0;
        int iDiaActual=0;
        int iMesUltimaCompra=0;
        int iMesActual=0;
        int flagValidaFecha=0;
        
        int SumAbonoBase=0;
        int banderaCiudad=1;
        int flagLealtad=0;
        int flagStatusLealtad=0;
        int flagVencido=0;
        int flagMinimo=0;
        int flagAbonoAdelantado=0;
        int flagPasa=0;
        int cumpleMinimo=0;
        int validaMinimo=0;
        int iBonificacion=0;
        int prceBonificacion=0;
        int saldaCon=0;
        int impAdelantado=0;
        int sumAbonoBoni=0;
        int adelantadoSum=0;
        int flagLiquidaAdelantado=0;
        int sumImpAdelantado=0;
        int sumaImporteAbono=0;
        
        int iSaldo=0;
        int iAbonoBoni=0;
        
        int totImpAbono=0;
        int saldaConBonificacion=0;
        double impAConciderar=0;
        double impFinal=0;
       // int impMovAbono=0;
       // int sumImpAbono=0;
        //int impTotal= 0;
        //int dineroCalculado=0;
        //int resImpAbono=0;
        
        double dineroCalculado=0;
        double resImpAbono=0;
        double impTotal=0;
        double sumImpAbono=0;
        double impMovAbono=0;
        

        long diffInMillies;
        long diff ;
        
        String response = null;
        String jeson=null;
        String validaCorte = null;
        String validaNumMesesCorte=null;
        String validaCiudad=null;
        String validaPuntualidad=null;
        String validaAbono=null;
        String validaMovAbono=null;
        String validaSumDE=null;
        String validaMesAdelantado=null;
        String ValidaImpMovAbono=null;
        String ValidaImpAbonoRegistrado=null;
        String ValidaSiGanoDE=null;
        String[] partSiGanoDE=null;
        String obtieneDatos=null;
        String[] partObtieneDatos=null;
        String[] partAdelantado=null;
        
        String datosMov=null;
        String validaMovAbonos="";
        
        String ValidarExisteAbono=null;
        String validaNacional=null;
        String validaPlanL=null;
        String clv_puntualidad=null;
        String cuenta=null;
        String datosClientes=null;
        String datosCorte=null;
        String datosDE=null;
        String clvMovimiento=null;
        String numMovimiento=null;
        String[] partvalidaAbono=null;
        String resRegistroMovAbono=null;
        String resRegistroCorte=null;
        String resRegistroDE=null;
        String[] partCortes=null;
        String[] partExiste=null;
        String fecUC="1900-01-01";
        String eliminaCorte=null; 
        String eliminaAbono=null;
        
        String datosLog=null;
        String data=null;
        String [] partValidaPL=null;
        String statusPL=null;
        String subStatusPL=null;
        
        String sDiaUltimaCompra=null;
        String sDiaActual=null;
        String sMesUltimaCompra=null;

        boolean resfecha;
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        JsonObject jsonDatos = new JsonObject();
        
        String fecCorteReg = null;
        LocalDate hoy = LocalDate.now(); 
                    
        Date fechaUC;
        LocalDate dateUC;
        Date fechaActual= java.sql.Date.valueOf(hoy);
        
        //System.out.println("fechaActual "+fechaActual);
        
        try{
            
            JsonParser parser = new JsonParser ();
            JsonElement jsonTree = parser.parse (Cliente);
            jsonTree.isJsonObject ();
            jsonTree.isJsonArray ();
            jsonTree.isJsonNull ();
            jsonTree.isJsonPrimitive ();
            
            JsonObject jsonObject = jsonTree.getAsJsonObject ();

            JsonElement numCliente = jsonObject.get("numCliente");
            JsonElement nombre = jsonObject.get("nombre");
            JsonElement apellidoPaterno = jsonObject.get("apellidoPaterno");
            JsonElement apellidoMaterno = jsonObject.get("apellidoMaterno");
            JsonElement sexo = jsonObject.get("sexo");
            JsonElement fechaNacimiento = jsonObject.get("fechaNacimiento");
            JsonElement telefonoCliente = jsonObject.get("telefonoCliente");
            JsonElement puntualidad = jsonObject.get("puntualidad");
            JsonElement situacionEspecial = jsonObject.get("situacionEspecial");
            JsonElement causasitesp = jsonObject.get("causasitesp");
            JsonElement fechaAlta = jsonObject.get("fechaAlta");
            JsonElement ciudad = jsonObject.get("ciudad");
            JsonElement numTienda = jsonObject.get("numTienda");
            JsonElement folioAbono = jsonObject.get("folioAbono");
            JsonElement cuentas = jsonObject.get("cuentas");
                   
            JsonParser parserJson = new JsonParser ();
            JsonArray jsonArray = cuentas.getAsJsonArray();

            numeroCliente=Integer.parseInt(numCliente.toString());
            numCiudad=Integer.parseInt(ciudad.toString());         
            clv_puntualidad =jsonObject.get("puntualidad").toString();
            numeroTienda=Integer.parseInt(numTienda.toString());

            //System.out.println("numeroCliente "+numeroCliente);
            
            LocalDate fechaCorteNuevo=LocalDate.now();
            int dia;
            dia = hoy.getDayOfMonth();
            LocalDate fechaCorteCartera = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
            LocalDate fechaCorteActual = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
            LocalDate fechaCorteMesSiguiente = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
            LocalDate fechaCorteSiguiente = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
            iDiaActual=hoy.getDayOfMonth();
            //System.out.println("iDiaActual: "+iDiaActual);
            iMesActual=hoy.getMonthValue();
            //System.out.println("iMesActual: "+iMesActual);
            if(dia < 21)
            {
              fechaCorteCartera=fechaCorteCartera.plusMonths(-1);
              fechaCorteActual=fechaCorteActual.plusMonths(0);
              
             
            }else{
                 fechaCorteMesSiguiente=fechaCorteMesSiguiente.plusMonths(1);
                 fechaCorteActual=fechaCorteActual.plusMonths(1);
            }
            
            if(numeroCliente>0){
                //Valida si el Cliente se encuentra en un corte
                
                validaCorte = this.consultarCortes(numeroCliente,0);
                validaMesAdelantado=this.consultarCortes(numeroCliente, 1);
                
                ValidarExisteAbono = this.consultarMovAbonos(numeroCliente,Integer.parseInt(folioAbono.toString()),5);
                //System.out.println("ValidarExisteAbono "+ValidarExisteAbono);
                if(ValidarExisteAbono.length()>2)
                {
                    partExiste = ValidarExisteAbono.split(",");

                    ValidaSiGanoDE = this.consultaSiGanoDE(numeroCliente, Integer.parseInt(folioAbono.toString()),partExiste[4].toString().substring(0,10));
                    
                    if(ValidaSiGanoDE.length()>1){
                        partSiGanoDE = ValidaSiGanoDE.split(",");
                        validaNumMesesCorte=this.consultarLogicasRecompensa(Integer.parseInt(partSiGanoDE[1].toString()));
                        validaMovAbono = this.consultarMovAbonosPuntuales(numeroCliente);
                        
                        String mensaje = "El cliente ya habia ganado dinero electronico el dia "+partSiGanoDE[2].toString();
                        json.addProperty("status", 0);
                        json.addProperty("mensaje", mensaje);
                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+Integer.parseInt(partSiGanoDE[1].toString())+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+Integer.parseInt(partSiGanoDE[1].toString())+",\"dineroganar\":"+Integer.parseInt(partSiGanoDE[0].toString())+",\"fecha\":\""+partSiGanoDE[2].toString()+"\""+
                            ",\"cortealcanzado\":"+4+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                ",\"totalAbono\":"+Integer.parseInt(validaMovAbono.toString())+"}";
                        
                        LOGGER.info(data);
                        json.addProperty("data", data);
                        this.registrarLog(datosLog);
                        //System.out.println("Json "+json.toString());
                        return response = json.toString();

                    
                    }else{
                        partExiste = ValidarExisteAbono.split(",");
                       // System.out.println("ValidarExisteAbono "+ValidarExisteAbono);
                        String mensaje = "El movimiento con folioabono: "+folioAbono+" para el cliente:"+numeroCliente+" ya fue procesado el dia "+partExiste[4].toString().substring(0,10);
                        json.addProperty("status", 1);
                        json.addProperty("mensaje", mensaje);
                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                    ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                        json.addProperty("data", data);
                        LOGGER.info(mensaje);
                        LOGGER.info(data);
                        this.registrarLog(datosLog);
                        return response =json.toString();
                    
                    }
                    
                    
                    //return "encontrado";
                    /**/

                }else
                {
                    //return "No encontrado";
                  // System.out.println("No encontrado");
                   if(validaMesAdelantado.length()>1 || validaCorte.length()>1 ){

                        banderaCiudad=0;
                        if(validaMesAdelantado.length()>1){
                            partCortes = validaMesAdelantado.split(",");
                        }else if(validaCorte.length()>1){
                            partCortes = validaCorte.split(",");
                        }
                        numLogica = Integer.parseInt(partCortes[0]);
                        int impAbonosCorteReg = Integer.parseInt(partCortes[1]);
                        corteCliente = Integer.parseInt(partCortes[2]);
                        porcentajeDE = Integer.parseInt(partCortes[3]);
                        int impDineroCorte = Integer.parseInt(partCortes[4]);
                        String fecAlcanzoMesPuntual = partCortes[5].toString();
                        fecAlcanzoMesPuntual=fecAlcanzoMesPuntual.substring(0,10);
                        fecCorteReg = partCortes[6].toString();
                        int keyx = Integer.parseInt(partCortes[7]);
                        int baseAbonoReg = Integer.parseInt(partCortes[8]);


                        System.out.println("Valida por numero de cliente ");
                        LOGGER.info("Valida por numero de cliente ");

                        ValidaImpMovAbono = this.consultarImpAbonosPuntuales(numeroCliente,corteCliente);
                        if(ValidaImpMovAbono.length()>0){
                            impMovAbono=Integer.parseInt(ValidaImpMovAbono);
                        }
                        //System.out.println("impMovAbono "+impMovAbono );
                        ValidaImpAbonoRegistrado = this.consultarImpAbonosRegistrados(numeroCliente,numLogica,corteCliente);
                         if(ValidaImpAbonoRegistrado.length()>0){
                            impAConciderar=Integer.parseInt(ValidaImpAbonoRegistrado);
                        }

                        /*ystem.out.println("fechaCorteActual "+fechaCorteActual.toString());
                        System.out.println("fecCorteReg "+fecCorteReg);*/
                        if(fechaCorteActual.toString().equals(fecCorteReg)){
                            flagAbonoAdelantado=1;
                            fechaCorteSiguiente=fechaCorteActual.plusMonths(1);
                        }
                        //System.out.println("fechaCorteSiguiente "+fechaCorteSiguiente);
                        validaNumMesesCorte=this.consultarLogicasRecompensa(numLogica);

                        if(corteCliente != Integer.parseInt(validaNumMesesCorte)){
                            validaPuntualidad = this.consultaPuntualidad(numLogica,clv_puntualidad,numeroCliente);
                            if(validaPuntualidad != "0"){
                                validaMovAbono = this.consultarMovAbonosPuntuales(numeroCliente);

                                for(int i =0; i < jsonArray.size();i++){

                                        JsonObject cuentasObject = jsonArray.get(i).getAsJsonObject ();

                                        JsonElement tipocuenta = cuentasObject.get("tipocuenta");
                                        JsonElement Saldo = cuentasObject.get("saldo");
                                        JsonElement abonobase = cuentasObject.get("abonobase");
                                        JsonElement plazo = cuentasObject.get("plazo");
                                        JsonElement Vencido = cuentasObject.get("vencido");
                                        JsonElement Minimo = cuentasObject.get("minimo");
                                        JsonElement bonificacion = cuentasObject.get("bonificacion");
                                        JsonElement prcBonificacion = cuentasObject.get("porcentajeBonificacion");
                                        JsonElement descripcion = cuentasObject.get("descripcion");
                                        JsonElement fecUltimaCompra = cuentasObject.get("fechaUltimaCompra");
                                        JsonElement factura = cuentasObject.get("factura");
                                        JsonElement imp_abono = cuentasObject.get("imp_abono");

                                        numTipoCuenta = Integer.parseInt(tipocuenta.toString());
                                        vencido = Integer.parseInt(Vencido.toString());
                                        minimo = Integer.parseInt(Minimo.toString());
                                        impAbono = Integer.parseInt(imp_abono.toString());
                                        numTicket =  Integer.parseInt(factura.toString());

                                        //System.out.println("ultimacompra: "+fecUltimaCompra);
                                        sDiaUltimaCompra=fecUltimaCompra.toString().substring(9,11);
                                        //System.out.println("Dia subs 1: "+sDiaUltimaCompra);


                                        if(prcBonificacion == null){
                                            prceBonificacion=0;
                                        }else{
                                            prceBonificacion=Integer.parseInt(prcBonificacion.toString());
                                        }
                                        if(fecUltimaCompra == null){    
                                            fecUC="1900-01-01";
                                        }
                                        else if(fecUltimaCompra.toString().length() <= 2){
                                                fecUC="1900-01-01";
                                        }else{
                                            fecUC=fecUltimaCompra.toString();
                                            fecUC=fecUC.substring(1,11);
                                        }
                                        dateUC = LocalDate.parse(fecUC);
                                        fechaUC = java.sql.Date.valueOf(dateUC);

                                        //System.out.println("fechaUC.getTime() "+fechaUC.toString());    


                                        diffInMillies = Math.abs(fechaUC.getTime() - fechaActual.getTime());
                                        diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);


                                        validaAbono = this.consultarCarterasRecompensa(numLogica, numTipoCuenta,numeroCliente);
                                        //System.out.println("validaAbono "+validaAbono);
                                        //System.out.println("validaAbonolength "+validaAbono.length());

                                        if(validaAbono.length()>1){ 
                                           flagSiValida=1;     
                                            //System.out.println("Saldo "+Saldo.toString());
                                           // System.out.println("bonificacion "+bonificacion.toString());

                                            saldaConBonificacion=Integer.parseInt(Saldo.toString())-Integer.parseInt(bonificacion.toString());
                                            //System.out.println("saldaConBonificacion "+saldaConBonificacion);
                                            if((impAbono>=Integer.parseInt(Saldo.toString()) && prceBonificacion == 10000) && numTipoCuenta==1 ||
                                                        (impAbono>=saldaConBonificacion && prceBonificacion == 10000 && numTipoCuenta==1)){
                                                        flagSiValida=0;         
                                                        String mensaje = "valida poridando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias";
                                                        json.addProperty("status", 0);
                                                        json.addProperty("mensaje", mensaje);
                                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                        json.addProperty("data", data);
                                                        LOGGER.info("El cliente esta liquidando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias");
                                                        LOGGER.info(data);
                                                        this.registrarLog(datosLog);

                                            }else if((impAbono>=Integer.parseInt(Saldo.toString()) && diff <= 30) && (numTipoCuenta==0 || numTipoCuenta==4 || numTipoCuenta==6)||
                                                    (impAbono>=saldaConBonificacion && diff <= 30 && (numTipoCuenta==0 || numTipoCuenta==4 || numTipoCuenta==6))){
                                                flagSiValida=0;
                                                String mensaje = "El cliente esta liquidando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias";
                                                json.addProperty("status", 0);
                                                json.addProperty("mensaje", mensaje);
                                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                        ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                json.addProperty("data", data);
                                                LOGGER.info("El cliente esta liquidando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias");
                                                LOGGER.info(data);
                                                this.registrarLog(datosLog);

                                            }
                                            else{
                                                
                                                    if(numTipoCuenta == 3 && diff <= 30){
                                                        minimo=0;
                                                        //System.out.println("El cliente tiene un prestamo menor de 30 dias "+fechaUC);
                                                        //System.out.println("minimo"+minimo);
                                                        //System.out.println("impAbono"+impAbono);
                                                        LOGGER.info("El cliente tiene un prestamo menor de 30 dias "+fechaUC);
                                                        String mensaje = "El cliente tiene un prestamo menor de 30 dias "+fechaUC;
                                                        //datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                        this.registrarLog(datosLog);
                                                    }
                                                //System.out.println("hasta aqui");
                                                if(Integer.parseInt(validaMovAbono)>1){
                                                    importeAbono=0;
                                                    importeAbono = Integer.parseInt(validaMovAbono);
                                                }

                                                    saldo = saldo + Integer.parseInt(Saldo.toString());
                                                    iSaldo= Integer.parseInt(Saldo.toString());
                                                    abonoBase = Integer.parseInt(abonobase.toString());
                                                    iBonificacion += Integer.parseInt(bonificacion.toString());
                                                    iAbonoBoni=impAbono+Integer.parseInt(bonificacion.toString());
                                                    SumAbonoBase+=abonoBase;

                                                    sumAbonoJson = sumAbonoJson + impAbono;
                                                    sumMinimoMes+=minimo;
     
                                                    /*if(Integer.parseInt(sDiaUltimaCompra.toString())>20 && numTipoCuenta==1){
                                                        sumMinimoMes+=0; 
                                                        minimo=0;
                                                        //System.out.println("Dia ultima compra mayor que 20");
                                                    }else if(Integer.parseInt(sDiaUltimaCompra.toString()) < iDiaActual && vencido==0){
                                                        sumMinimoMes+=minimo;  
                                                        //System.out.println("Dia ultima compra menor que dia actual");
                                                    }else {
                                                        sumMinimoMes+=minimo; 
                                                        //System.out.println("entro en el else");
                                                    }*/
                                                                 
                                                    if(impAbono>=minimo){
                                                        sumImpAbono+=impAbono;
                                                    }else{
                                                        sumImpAbono+=impAbono;
                                                        cumpleMinimo=1;
                                                    }
                                                    
                                                    if(impAbono<minimo && minimo>0){
                                                       validaMinimo=1;
                                                    }
                                                    
                                                    partvalidaAbono = validaAbono.split(",");
                                                    clvMovimiento = partvalidaAbono[0];
                                                    numMovimiento = partvalidaAbono[1];

                                                    if(validaMesAdelantado.equals("0")){
                                                        if(dia<21){
                                                            fechaCorteNuevo=fechaCorteActual;
                                                        }else{
                                                            fechaCorteNuevo=fechaCorteMesSiguiente;
                                                        }

                                                    }else{
                                                        fechaCorteNuevo=fechaCorteMesSiguiente;
                                                    }

                                                    sumAbonoBoni=(int)sumImpAbono+iBonificacion;
                                                    if(iAbonoBoni>=iSaldo){
                                                        iTerminaCuenta=1;
                                                    }
                                                    /*System.out.println("impAbono "+impAbono);
                                                    System.out.println("saldo "+saldo);
                                                    System.out.println("flagAbonoAdelantado "+flagAbonoAdelantado);*/
                                                    if (flagAbonoAdelantado>0 && impAbono>0) {
                                                         //System.out.println("entro por aqui");
                                                            impMovAbono=0;
                                                            impMovAbono+=importeAbono;
                                                            /*System.out.println("sumAbonoBoni "+sumAbonoBoni);
                                                            System.out.println("impAbono "+impAbono);
                                                            System.out.println("saldo "+saldo);*/
                                                            if(impAbono>=saldo || sumAbonoBoni>=saldo){
                                                                fechaCorteSiguiente=fechaCorteActual;
                                                              //  System.out.println("entro por aqui "+fechaCorteSiguiente);

                                                            }
                                                            // System.out.println("fechaCorteSiguiente "+fechaCorteSiguiente);
                                                            datosClientes="{\"numCliente\":"+numeroCliente+",\"numTienda\":"+numeroTienda+
                                                                   ",\"impAbono\":"+impAbono+",\"numTicket\":"+numTicket+
                                                                    ",\"clvMovimiento\":\""+clvMovimiento+"\""+
                                                                     ",\"numMovimiento\":\""+numMovimiento+"\""+
                                                                        ",\"fecAbono\":\""+hoy+"\""+
                                                                     ",\"fecCorteCartera\":\""+fechaCorteSiguiente+"\""+
                                                                        ",\"folio\":\""+folioAbono+"\""+"}";

                                                            resRegistroMovAbono = this.registrarMovAbonoPuntual(datosClientes);
                                                            obtieneDatos = consultarMovAbonosID(Integer.parseInt(resRegistroMovAbono.toString()));
                                                        // if(obtieneDatos.length()>0){
                                                            partObtieneDatos= obtieneDatos.split(",");
                                                            //System.out.println("valor: "+Integer.parseInt(partObtieneDatos[3].toString()));            
                                                            adelantadoSum=adelantadoSum+Integer.parseInt(partObtieneDatos[3].toString());
                                                            //System.out.println("sumAbonoBoni: "+sumAbonoBoni);
                                                            if(sumAbonoBoni<saldo){
                                                                fechaCorteNuevo=fechaCorteSiguiente;
                                                                flagPasa=1;

                                                                importeAbono=0;
                                                            }
                                                            //System.out.println("sumAbonoBoni: "+sumAbonoBoni);
                                                             datosMov="{\"keyx\":"+Integer.parseInt(partObtieneDatos[0].toString())+",\"numCliente\":"+numCliente+",\"fecAbono\":"+hoy+"}";
                                                             eliminaAbono=this.eliminarAbono(datosMov);
                                                             resRegistroMovAbono="";
                                                             datosClientes="{\"numCliente\":"+Integer.parseInt(partObtieneDatos[1].toString())+",\"numTienda\":"+Integer.parseInt(partObtieneDatos[2].toString())+
                                                                                    ",\"impAbono\":"+Integer.parseInt(partObtieneDatos[3].toString())+",\"numTicket\":"+Integer.parseInt(partObtieneDatos[4].toString())+
                                                                                     ",\"clvMovimiento\":\""+partObtieneDatos[5].toString()+"\""+
                                                                                      ",\"numMovimiento\":\""+partObtieneDatos[6].toString()+"\""+
                                                                                         ",\"fecAbono\":\""+hoy+"\""+
                                                                                      ",\"fecCorteCartera\":\""+fechaCorteSiguiente+"\""+
                                                                                         ",\"folio\":\""+Integer.parseInt(partObtieneDatos[9].toString())+"\""+"}";
                                                             resRegistroMovAbono = this.registrarMovAbonoPuntual(datosClientes);


                                                            iRegistro=1;     
                                                            //System.out.println("flagPasa: "+flagPasa);
                                                            if(flagPasa==0){
                                                                obtieneDatos = consultarMovAdelantado(numeroCliente);
                                                                //System.out.println("obtieneDatos: "+obtieneDatos);
                                                                if(obtieneDatos.length()>1){
                                                                    partAdelantado=obtieneDatos.split(",");
                                                                    flagLiquidaAdelantado=1;
                                                                   datosMov="{\"keyx\":"+Integer.parseInt(partAdelantado[0].toString())+",\"numCliente\":"+numCliente+",\"fecAbono\":"+hoy+"}";
                                                                   eliminaAbono=this.eliminarAbono(datosMov);
                                                                   resRegistroMovAbono="";

                                                                   //impAdelantado=Integer.parseInt(partAdelantado[3].toString())+importeAbono;
                                                                   impAdelantado=importeAbono;
                                                                   sumImpAdelantado+=Integer.parseInt(partAdelantado[3].toString());
                                                                   //System.out.println("sumImpAdelantado "+sumImpAdelantado);
                                                                   impMovAbono=0;
                                                                   datosClientes="{\"numCliente\":"+Integer.parseInt(partAdelantado[1].toString())+",\"numTienda\":"+Integer.parseInt(partAdelantado[2].toString())+
                                                                                          ",\"impAbono\":"+Integer.parseInt(partAdelantado[3].toString())+",\"numTicket\":"+Integer.parseInt(partAdelantado[4].toString())+
                                                                                           ",\"clvMovimiento\":\""+partAdelantado[5].toString()+"\""+
                                                                                            ",\"numMovimiento\":\""+partAdelantado[6].toString()+"\""+
                                                                                               ",\"fecAbono\":\""+hoy+"\""+
                                                                                            ",\"fecCorteCartera\":\""+fechaCorteSiguiente+"\""+
                                                                                               ",\"folio\":\""+Integer.parseInt(partAdelantado[9].toString())+"\""+"}";
                                                                   resRegistroMovAbono = this.registrarMovAbonoPuntual(datosClientes);
                                                                   iRegistro=1;
                                                               }
                                                            }
                                                    }else{
                                                        //System.out.println("impAbono2 "+impAbono);
                                                        //System.out.println("entro por aca");
                                                        if(impAbono > 0){

                                                            datosClientes="{\"numCliente\":"+numeroCliente+",\"numTienda\":"+numeroTienda+
                                                                       ",\"impAbono\":"+impAbono+",\"numTicket\":"+numTicket+
                                                                        ",\"clvMovimiento\":\""+clvMovimiento+"\""+
                                                                         ",\"numMovimiento\":\""+numMovimiento+"\""+
                                                                            ",\"fecAbono\":\""+hoy+"\""+
                                                                         ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                            ",\"folio\":\""+folioAbono+"\""+"}";
                                                            resRegistroMovAbono = this.registrarMovAbonoPuntual(datosClientes);
                                                            iRegistro=1;
                                                        }else{
                                                            //System.out.println("entro por aqui");
                                                            flagSiValida=0;
                                                            String mensaje=null;
                                                            iRegistro=0;
                                                            mensaje = "Abono No Registrado";
                                                            //System.out.println("mensaje "+mensaje);
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                        }
                                                    }

                                                    String mensaje=null;
                                                    mensaje="";

                                                    pasaCorteA=3;

                                                        if(iRegistro==1){
                                                            if(resRegistroMovAbono.length()>0){
                                                                mensaje = "Abono Registrado";
                                                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                        ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                            }    
                                                        }else{
                                                            flagSiValida=0;
                                                            //System.out.println("entro");
                                                            mensaje = "Abono No Registrado";
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                    ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                        }
                                                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                    this.registrarLog(datosLog);
                                            }    
                                        }else{
                                            flagSiValida=0;
                                        }
                                    }

                                /*System.out.println("sumImpAbono inicio "+sumImpAbono);
                                System.out.println("flagSiValida "+flagSiValida);
                                System.out.println("importeAbono "+importeAbono);
                                System.out.println("sumImpAbono "+sumImpAbono);
                                System.out.println("saldo "+saldo);
                                System.out.println("saldaCon "+saldaCon);
                                System.out.println("flagAbonoAdelantado "+flagAbonoAdelantado);*/

                                ///if(flagSiValida==1){
                                   saldaCon = saldo-iBonificacion;
                                   validaMovAbono = this.consultarMovAbonosPuntuales(numeroCliente);
                                   /*System.out.println(">>>validaMovAbono "+validaMovAbono);
                                   System.out.println(">>>importeAbono "+importeAbono);
                                    //System.out.println("flagAbonoAdelantado "+flagAbonoAdelantado);
                                    System.out.println("/////////////////////////////////////////////////");
                                    //
                                    System.out.println("sumImpAbono "+sumImpAbono);
                                    System.out.println("saldaCon "+saldaCon);
                                    System.out.println("flagAbonoAdelantado "+flagAbonoAdelantado);
                                    System.out.println("flagLiquidaAdelantado "+flagLiquidaAdelantado);*/
                                    if(sumImpAbono>=saldo && flagAbonoAdelantado==0 && sumImpAbono>0 || sumImpAbono>=saldo && sumImpAbono>=saldaCon  && sumImpAbono>0 ||
                                       sumImpAbono>=saldaCon && flagAbonoAdelantado==0 && sumImpAbono>0 || sumImpAbono>=sumMinimoMes && flagAbonoAdelantado==0 && sumImpAbono>0
                                       || sumImpAbono>=saldaCon && sumImpAbono>=sumMinimoMes  && sumImpAbono>0){
                                        flagSiValida=1;
                                        // System.out.println(">>>entrooo ");
                                    }
                                    //System.out.println("/////////////////////////////////////////////////");
                                    if(importeAbono>0){
                                        if(flagAbonoAdelantado==0){

                                            if(flagSiValida==1){
                                                sumImpAbono+=importeAbono;
                                            }else{
                                                sumImpAbono=importeAbono;
                                            }
                                            //System.out.println("entro 2");
                                        }else{
                                            //System.out.println("entro ");
                                            /*System.out.println("impAdelantado1-- "+impAdelantado);
                                            System.out.println("sumImpAbono1-- "+sumImpAbono);
                                            System.out.println("flagLiquidaAdelantado1-- "+flagLiquidaAdelantado);
                                            System.out.println("sumImpAdelantado-- "+sumImpAdelantado);*/
                                            impAdelantado=impAdelantado+sumImpAdelantado;
                                            if(flagLiquidaAdelantado==1 && sumImpAbono >= saldo){
                                                 //System.out.println("POR AQUI LLEGO ");
                                                sumImpAbono=Integer.parseInt(validaMovAbono.toString());
                                                 /*System.out.println("<>>>-- "+sumImpAbono);
                                                 System.out.println("<validaMovAbono>>>-- "+validaMovAbono);*/
                                            }else{
                                                //System.out.println("entro ");
                                                if(impAdelantado>0){
                                                    sumImpAbono+=impAdelantado;
                                                }else if(importeAbono>0){
                                                    sumImpAbono+=importeAbono;
                                                }
                                                //sumImpAbono+=impMovAbono;
                                            }
                                        }
                                    }

                                   
                                    if(sumImpAbono >= sumMinimoMes && sumImpAbono > 0 && flagSiValida==1){
                                        flagAbono=1;
                                        flagMinimo=0;
                                        cumpleMinimo=0;
                                    }else{
                                        flagAbono=0;
                                        flagMinimo=1;
                                        contFlagAbono=1;
                                        cumpleMinimo=1;   
                                    }     
                                    if(validaMinimo > 0){
                                       flagSiValida = 0;
                                    }
                                    
                                    if (sumImpAbono >= sumMinimoMes && sumImpAbono > 0 && flagSiValida == 1) {
                                        flagAbono = 1;
                                        flagMinimo = 0;
                                        cumpleMinimo = 0;

                                    } else {
                                        flagAbono = 0;
                                        flagMinimo = 1;
                                        contFlagAbono = 1;
                                        cumpleMinimo = 1;
                                    }

                                    if(sumImpAbono>=saldo && sumImpAbono>0 && flagSiValida==1|| sumImpAbono >= saldaCon && sumImpAbono>0 && flagSiValida==1){
                                        //System.out.println("sumImpAbono 1 "+sumImpAbono);
                                        if(sumImpAbono>0){
                                           pasaCorte=1;
                                        }else{
                                            pasaCorte=0;
                                        }
                                    }else{
                                        pasaCorte=0;
                                    }
                                    /*System.out.println("flagAbonoAdelantado "+flagAbonoAdelantado);
                                    System.out.println("flagAbono "+flagAbono);*/
                                    if(flagAbonoAdelantado>0){
                                        /*System.out.println("sumImpAbono "+sumImpAbono);
                                        System.out.println("saldo "+saldo);
                                        System.out.println("saldaCon "+saldaCon);*/

                                        if(sumImpAbono>=saldo && sumImpAbono>0 && flagSiValida==1|| sumImpAbono >= saldaCon && sumImpAbono>0 && flagSiValida==1){
                                            LOGGER.info("Cuenta liquidada en un mismo corte");
                                            //System.out.println("Entro aqui ");
                                            datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                             ",\"impAbonosCorte\":"+impAbonosCorteReg+",\"numCorte\":"+corteCliente+
                                                            ",\"prcDineroE\":"+porcentajeDE+
                                                             ",\"impDineroCorte\":"+impDineroCorte+
                                                              ",\"fecAlcanzoMesPuntual\":\""+fecAlcanzoMesPuntual+"\""+
                                                               ",\"fecCorteCartera\":\""+fecCorteReg+"\""+
                                                               ",\"abonoBase\":"+baseAbonoReg+"}";           
                                            this.registrarCorteAnterior(datosCorte);
                                            LOGGER.info("El cliente liquido cuenta en el mismo mes teniendo un corte ya marcado");
                                            LOGGER.info(datosCorte);


                                            datosCorte="";
                                            datosCorte="{\"keyx\":"+keyx+",\"numCliente\":"+numCliente+",\"fecAlcanzoMesPuntual\":\""+fecAlcanzoMesPuntual+"\""+",\"fecCorteCartera\":\""+fecCorteReg+"\""+"}";
                                            eliminaCorte=this.eliminarCorte(datosCorte);
                                            LOGGER.info("Se cambio de tabla el registro del corte que tenia el cliente");
                                            LOGGER.info(datosCorte);



                                            pasaCorte=1;
                                        }else if(flagPasa==1){
                                            //System.out.println("Abono adelantado ");
                                            LOGGER.info("Abono adelantado para el siguiente mes");
                                            LOGGER.info("Se registro el abono del cliente");
                                            String mensaje = "Se registro el abono del cliente";
                                            json.addProperty("status", 0);
                                            json.addProperty("mensaje", mensaje);
                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                    ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                            LOGGER.info(data);
                                            json.addProperty("data", data);
                                            this.registrarLog(datosLog);
                                            return response=json.toString();
                                        }
                                    }

                                    if(flagAbono>0){

                                        dineroCalculado=Math.floor((sumImpAbono * porcentajeDE)/100);
                                        if(Integer.parseInt(validaNumMesesCorte)>0){
                                            //System.out.println("corteCliente "+corteCliente);
                                            if(corteCliente == Integer.parseInt(validaNumMesesCorte)){

                                                    if(validaMesAdelantado.equals("0")){
                                                         if(dia<21){
                                                            fechaCorteNuevo=fechaCorteActual;
                                                        }else{
                                                            fechaCorteNuevo=fechaCorteMesSiguiente;
                                                        }

                                                        if(sumImpAbono>=saldo || sumImpAbono>=saldaCon){    

                                                            corteCliente = Integer.parseInt(validaNumMesesCorte);
                                                            datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                                ",\"impAbonosCorte\":"+sumImpAbono+",\"numCorte\":"+corteCliente+
                                                               ",\"prcDineroE\":"+porcentajeDE+
                                                                ",\"impDineroCorte\":"+dineroCalculado+
                                                                 ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                  ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                  ",\"abonoBase\":"+SumAbonoBase+"}";

                                                           resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                           String mensaje=null;

                                                           mensaje="El cliente avanzo de corte";
                                                           data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                  ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                           json.addProperty("status", 0);
                                                           json.addProperty("mensaje", mensaje);
                                                           json.addProperty("data", data);
                                                           response=json.toString();

                                                            impTotal=Math.floor((sumImpAbono * porcentajeDE)/100);
                                                            impAConciderar=impAConciderar+sumImpAbono;
                                                            impFinal=Math.floor((impAConciderar * porcentajeDE)/100);
                                                            impMovAbono=impMovAbono+sumImpAbono;

                                                            /*datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                                   ",\"iduLogica\":"+numLogica+
                                                                       ",\"fecGeneracion\":\""+hoy+"\"}";*/
                                                            datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                                    ",\"iduLogica\":"+numLogica+
                                                                        ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                            resRegistroDE = this.registrarMovDineroElectronico(datosDE); 

                                                            mensaje = "El cliente gano dinero electronico";
                                                            LOGGER.info("El cliente gano dinero electronico");
                                                            json.addProperty("status", 0);
                                                            json.addProperty("mensaje", mensaje);
                                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+Integer.parseInt(resRegistroDE)+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                    ",\"totalAbono\":"+impMovAbono+"}";
                                                            LOGGER.info(data);
                                                            json.addProperty("data", data);
                                                            this.registrarLog(datosLog);
                                                            return response = json.toString();
                                                        }else{
                                                            corteCliente = 1;
                                                            datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                                ",\"impAbonosCorte\":"+sumImpAbono+",\"numCorte\":"+corteCliente+
                                                               ",\"prcDineroE\":"+porcentajeDE+
                                                                ",\"impDineroCorte\":"+dineroCalculado+
                                                                 ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                  ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                  ",\"abonoBase\":"+SumAbonoBase+"}";

                                                           resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                           String mensaje=null;

                                                           LOGGER.info("El cliente avanzo de corte");
                                                           mensaje="El cliente avanzo de corte";
                                                           data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                  ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                           LOGGER.info(data);
                                                           json.addProperty("status", 0);
                                                           json.addProperty("mensaje", mensaje);
                                                           json.addProperty("data", data);
                                                           response=json.toString();  
                                                        } 
                                                    }
                                            }else{
                                               // System.out.println("pasaCorte "+pasaCorte);
                                                if(pasaCorte>0){

                                                     String mensaje=null;
                                                        if(dia<21){
                                                            fechaCorteNuevo=fechaCorteActual;
                                                        }else{
                                                            fechaCorteNuevo=fechaCorteMesSiguiente;
                                                        }

                                                            datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                               ",\"impAbonosCorte\":"+sumImpAbono+",\"numCorte\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                ",\"prcDineroE\":"+porcentajeDE+
                                                                 ",\"impDineroCorte\":"+dineroCalculado+
                                                                  ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                    ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                    ",\"abonoBase\":"+SumAbonoBase+"}";
                                                            resRegistroCorte= this.registrarCorteCumplido(datosCorte);

                                                        mensaje="El cliente avanzo de corte";
                                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                        json.addProperty("status", 0);
                                                        json.addProperty("mensaje", mensaje);
                                                        json.addProperty("data", data);

                                                    validaSumDE = this.consultarImporteCorte(numeroCliente,numLogica,corteCliente);


                                                    impMovAbono=impMovAbono+sumImpAbono;
                                                    if(Integer.parseInt(validaSumDE)>0){
                                                        //impTotal=Math.floor(Integer.parseInt(validaSumDE));
                                                        impTotal=Math.floor((Integer.parseInt(validaSumDE) * porcentajeDE)/100);
                                                    }else{
                                                        impTotal=Math.floor((sumImpAbono * porcentajeDE)/100);
                                                    }

                                                    impAConciderar=impAConciderar+sumImpAbono;
                                                    impFinal=Math.floor((impAConciderar * porcentajeDE)/100);
                                                    if(flagAbonoAdelantado>0){
                                                        datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impTotal+
                                                               ",\"iduLogica\":"+numLogica+
                                                                   ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                    }else{
                                                        datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                          ",\"iduLogica\":"+numLogica+
                                                              ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                    }
                                                    resRegistroDE = this.registrarMovDineroElectronico(datosDE); 

                                                    mensaje = "El cliente gano dinero electronico";
                                                    json.addProperty("status", 0);
                                                    json.addProperty("mensaje", mensaje);
                                                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                    data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+Integer.parseInt(resRegistroDE)+",\"fecha\":\""+hoy+"\""+
                                                            ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                                            ",\"totalAbono\":"+impMovAbono+"}";
                                                    LOGGER.info("El cliente gano dinero electronico");
                                                    LOGGER.info(data);
                                                    json.addProperty("data", data);
                                                    this.registrarLog(datosLog);
                                                    return response = json.toString();
                                                }else if(pasaCorte==0 && pasaCorteA==3 && cumpleMinimo==0){

                                                    //System.out.println("pasaCorteA "+pasaCorteA);
                                                   // System.out.println("cumpleMinimo "+cumpleMinimo);
                                                    int iCorte=corteCliente;
                                                    corteCliente=corteCliente+1;
                                                    String mensaje=null;
                                                        if(dia<21){
                                                            fechaCorteNuevo=fechaCorteActual;
                                                        }else{
                                                            fechaCorteNuevo=fechaCorteMesSiguiente;
                                                        }
                                                        datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                           ",\"impAbonosCorte\":"+sumImpAbono+",\"numCorte\":"+corteCliente+
                                                            ",\"prcDineroE\":"+porcentajeDE+
                                                             ",\"impDineroCorte\":"+dineroCalculado+
                                                              ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                               ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                ",\"abonoBase\":"+SumAbonoBase+"}";

                                                        resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                        mensaje="El cliente avanzo de corte";
                                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                        json.addProperty("status", 0);
                                                        json.addProperty("mensaje", mensaje);
                                                        json.addProperty("data", data);
                                                        response=json.toString();
                                                        LOGGER.info("El cliente avanzo de corte");
                                                        LOGGER.info(data);
                                                    //}
                                                    //System.out.println("corteCliente "+corteCliente);
                                                    //System.out.println("validaNumMesesCorte "+Integer.parseInt(validaNumMesesCorte));
                                                    if(corteCliente == Integer.parseInt(validaNumMesesCorte)){
                                                        validaSumDE = this.consultarImporteCorte(numeroCliente,numLogica,iCorte);
                                                        impAConciderar=impAConciderar+sumImpAbono;
                                                        impFinal=Math.floor((impAConciderar * porcentajeDE)/100);

                                                        datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                               ",\"iduLogica\":"+numLogica+
                                                                   ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                        resRegistroDE = this.registrarMovDineroElectronico(datosDE);
                                                        impMovAbono=impMovAbono+sumImpAbono;



                                                        mensaje = "El cliente gano dinero electronico";
                                                        json.addProperty("status", 0);
                                                        json.addProperty("mensaje", mensaje);
                                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";

                                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+Integer.parseInt(resRegistroDE)+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                ",\"totalAbono\":"+impMovAbono+"}";
                                                        json.addProperty("data", data);

                                                        LOGGER.info("El cliente gano dinero electronico");
                                                        LOGGER.info(data);
                                                        this.registrarLog(datosLog);
                                                        return response = json.toString();

                                                    }
                                                }else{
                                                    //System.out.println("entro 1");
                                                    //System.out.println("entro 1 "+pasaCorte+"-"+pasaCorteA+"-"+cumpleMinimo);
                                                    String mensaje = "El movimiento "+numTipoCuenta+" no participa en el programa";
                                                    json.addProperty("status", 1);
                                                    json.addProperty("mensaje", mensaje);
                                                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                    data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                                    json.addProperty("data", data);
                                                    LOGGER.info("El movimiento "+numTipoCuenta+" no participa en el programa");
                                                    LOGGER.info(data);
                                                    this.registrarLog(datosLog);
                                                    return response =json.toString();
                                                }                                     
                                            }
                                        }else{

                                            String mensaje = "No se encontro Logica";
                                            json.addProperty("status", 1);
                                            json.addProperty("mensaje", mensaje);
                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                            json.addProperty("data", data);
                                            LOGGER.info("No se encontro Logica");
                                            LOGGER.info(data);
                                            this.registrarLog(datosLog);
                                            return response = json.toString();
                                        }       
                                    }else{

                                        String mensaje = "No avanza en los cortes el abono es menor que el minimo";
                                         json.addProperty("status", 1);
                                        json.addProperty("mensaje", mensaje);
                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                        json.addProperty("data", data);
                                        LOGGER.info( "No avanza en los cortes el abono es menor que el minimo");
                                        LOGGER.info(data);
                                        this.registrarLog(datosLog);
                                        response=json.toString();
                                    }  
                                /*}else{
                                    String mensaje = "El movimiento "+numTipoCuenta+" no participa en el programa";
                                    json.addProperty("status", 1);
                                    json.addProperty("mensaje", mensaje);

                                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                    data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                    json.addProperty("data", data);
                                    LOGGER.info("El movimiento "+numTipoCuenta+" no participa en el programa");
                                    LOGGER.info(data);
                                    this.registrarLog(datosLog);
                                    return response =json.toString();

                                } *///para cerrar valida flagSiValida            
                            }else{

                                String mensaje = "No se encontro puntualidad del Cliente";
                                json.addProperty("status", 1);
                                json.addProperty("mensaje", mensaje);
                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+0+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                json.addProperty("data", data);

                                LOGGER.info("No se encontro puntualidad del Cliente");
                                LOGGER.info(data);
                                this.registrarLog(datosLog);
                                return response=json.toString();
                            }
                        }else{
                         banderaCiudad=1;   
                        }   
                    }
                }
                if(banderaCiudad == 1){
                    //System.out.println("banderaCiudad  "+banderaCiudad); 
                    validaPlanL= this.validaPlanLealtad(numCliente.toString());
                     //System.out.println("validaPlanL  "+validaPlanL); 
                    if(validaPlanL.equals("0")){
                        
                        flagLealtad=0;
                        flagStatusLealtad=1;
                    }
                    else if(validaPlanL.equals("5")){
                        String mensaje = "Ocurrio error en servicio plan lealtad ";
                         json.addProperty("status", "5");
                         json.addProperty("mensaje", mensaje);
                         data="0";
                         json.addProperty("data", data);
                        return response=json.toString();
                    }
                    else if(validaPlanL.equals("1")){
                        
                        String mensaje = "El cliente "+numeroCliente+" no esta registrado en el Plan de Lealtad";
                        json.addProperty("status",1);
                        json.addProperty("mensaje", mensaje);
                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                        data="{\"cliente\":"+numCliente+",\"numlogica\":"+0+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+"}";
                        LOGGER.info("El cliente "+numeroCliente+" no esta registrado en el Plan de Lealtad");
                        LOGGER.info(data);
                        this.registrarLog(datosLog);
                        json.addProperty("data", data);
                        response = json.toString();
                        return response;
                    }else{

                        flagLealtad=1;
                        partValidaPL = validaPlanL.split(",");
                        statusPL = partValidaPL[0].toString();
                        subStatusPL = partValidaPL[1].toString();
                    }
                    
                    
                    if(flagLealtad == 1){
                        if(Integer.parseInt(statusPL)== 2 && Integer.parseInt(subStatusPL)== 1 ){
                            flagStatusLealtad=1;
                        }else{
                            String mensaje = "El cliente "+numeroCliente+" no esta registrado en el Plan de Lealtad";
                            json.addProperty("status", 1);
                            json.addProperty("mensaje", mensaje);
                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+0+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                           ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                            
                            LOGGER.info("El cliente "+numeroCliente+" no esta registrado en el Plan de Lealtad");
                            LOGGER.info(data);
                            json.addProperty("data", data);
                            this.registrarLog(datosLog);
                            return response=json.toString();
                        }
                    }
                    
                    if(flagStatusLealtad == 1 ){
                    //principal
                        validaCiudad= this.consultarCiudadesRecompensa(numCiudad);
                        //System.out.println("validaCiudad "+validaCiudad);
                        if(validaCiudad == null){ 

                           System.out.println("Valida por nacional");
                            LOGGER.info("Valida por Nacional");
                            
                            validaNacional= this.consultaNacional("1","1");
                            corteCliente=0;
                            if(validaNacional.length()>2){

                                String[] partNacional = validaNacional.split(",");
                                numLogica = Integer.parseInt(partNacional[0]);
                                porcentajeDE = Integer.parseInt(partNacional[1]);

                                //System.out.println("paso");
                                ValidaImpMovAbono = this.consultarImpAbonosPuntuales(numeroCliente,corteCliente);
                                

                                
                                if(fechaCorteActual.toString().equals(fecCorteReg)){
                                    flagAbonoAdelantado=1;
                                    fechaCorteSiguiente=fechaCorteActual.plusMonths(1);
                                }
                               // System.out.println("ENTRO PORA QUI "+ValidaImpMovAbono.length()); 
                                if(ValidaImpMovAbono.length()>0){
                                    impMovAbono=Integer.parseInt(ValidaImpMovAbono);
                                }
                               // System.out.println("paso aqui.......");  
                                ValidaImpAbonoRegistrado = this.consultarImpAbonosRegistrados(numeroCliente,numLogica,corteCliente);
                                // System.out.println("paso aqui");
                                if(ValidaImpMovAbono.length()>0){
                                    impAConciderar=Integer.parseInt(ValidaImpAbonoRegistrado);
                                }
                                validaNumMesesCorte=this.consultarLogicasRecompensa(numLogica);
                                
                                validaPuntualidad=this.consultaPuntualidad(numLogica,clv_puntualidad,numeroCliente);
                                if(validaPuntualidad != "0"){
                                    validaMovAbono = this.consultarMovAbonosPuntuales(numeroCliente);
                                                
                                    if(Integer.parseInt(validaMovAbono)>1){
                                        importeAbono=0;
                                        resImpAbono=0;
                                        importeAbono = Integer.parseInt(validaMovAbono);
                                        resImpAbono=resImpAbono+importeAbono;
                                    }
                                    //System.out.println("---<importeAbono "+importeAbono);
                               
                                    for(int i =0; i < jsonArray.size();i++){     
                                            JsonObject cuentasObject = jsonArray.get(i).getAsJsonObject (); 
                                            JsonElement tipocuenta = cuentasObject.get("tipocuenta");
                                            JsonElement Saldo = cuentasObject.get("saldo");
                                            JsonElement abonobase = cuentasObject.get("abonobase");
                                            JsonElement plazo = cuentasObject.get("plazo");
                                            JsonElement Vencido = cuentasObject.get("vencido");
                                            JsonElement Minimo = cuentasObject.get("minimo");
                                            JsonElement bonificacion = cuentasObject.get("bonificacion");
                                            JsonElement prcBonificacion = cuentasObject.get("porcentajeBonificacion");
                                            JsonElement descripcion = cuentasObject.get("descripcion");
                                            JsonElement fecUltimaCompra = cuentasObject.get("fechaUltimaCompra");
                                            JsonElement factura = cuentasObject.get("factura");
                                            JsonElement imp_abono = cuentasObject.get("imp_abono");

                                            minimo = Integer.parseInt(Minimo.toString());
                                            impAbono = Integer.parseInt(imp_abono.toString());
                                            numTicket =  Integer.parseInt(factura.toString());

                                            vencido = Integer.parseInt(Vencido.toString());
                                            sDiaUltimaCompra=fecUltimaCompra.toString().substring(9,11);
                                            if(prcBonificacion == null){
                                                prceBonificacion=0;
                                            }else{
                                                prceBonificacion=Integer.parseInt(prcBonificacion.toString());
                                            }
                                            
                                            if(fecUltimaCompra == null){    
                                                fecUC="1900-01-01";
                                            }else if(fecUltimaCompra.toString().length() <= 2){
                                                fecUC="1900-01-01";
                                            }else{

                                                fecUC=fecUltimaCompra.toString();
                                                fecUC=fecUC.substring(1,11);
                                            }
                                            //System.out.println("fecUltimaCompra "+fecUltimaCompra);
                                            //System.out.println("fecUC "+fecUC.substring(8,10));
                                            
                                           
                                            dateUC = LocalDate.parse(fecUC);
                                            fechaUC = java.sql.Date.valueOf(dateUC);
                                            System.out.println("fechaUC "+fechaUC);
                                            diffInMillies = Math.abs(fechaUC.getTime() - fechaActual.getTime());
                                            diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                                            System.out.println("diff "+diff);

                                            numTipoCuenta = Integer.parseInt(tipocuenta.toString());
                                            validaAbono = this.consultarCarterasRecompensa(numLogica, numTipoCuenta,numeroCliente);

                                            if(validaAbono.length()>1){ 
                                               flagSiValida=1; 
                                                saldaConBonificacion=Integer.parseInt(Saldo.toString())-Integer.parseInt(bonificacion.toString());

                                                if((impAbono>=Integer.parseInt(Saldo.toString()) && prceBonificacion == 10000) && numTipoCuenta==1 ||
                                                    (impAbono>=saldaConBonificacion && prceBonificacion == 10000 && numTipoCuenta==1)){
                                                    flagSiValida=0;
                                                    String mensaje = "El cliente esta liquidando su cuenta (factura: "+numTicket+") antes de los 30 dias";
                                                    json.addProperty("status", 0);
                                                    json.addProperty("mensaje", mensaje);
                                                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                    data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                            ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                    json.addProperty("data", data);
                                                    LOGGER.info("El cliente esta liquidando su cuenta (factura: "+numTicket+") antes de los 30 dias");
                                                    LOGGER.info(data);
                                                    this.registrarLog(datosLog);

                                                } else if((impAbono>=Integer.parseInt(Saldo.toString()) && diff <= 30) && (numTipoCuenta==0 || numTipoCuenta==4 || numTipoCuenta==6)||
                                                (impAbono>=saldaConBonificacion && diff <= 30 && (numTipoCuenta==0 || numTipoCuenta==4 || numTipoCuenta==6))){
                                                   flagSiValida=0;
                                                    String mensaje = "El cliente esta liquidando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias";
                                                    json.addProperty("status", 0);
                                                    json.addProperty("mensaje", mensaje);
                                                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                    data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                            ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                    json.addProperty("data", data);
                                                    LOGGER.info("El cliente esta liquidando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias");
                                                    LOGGER.info(data);
                                                    this.registrarLog(datosLog);
                                                }else{

                                                    if(numTipoCuenta == 3 && diff <= 30){
                                                        minimo=0;
                                                        ///System.out.println("El cliente tiene un prestamo menor de 30 dias "+fechaUC);
                                                        LOGGER.info("El cliente tiene un prestamo menor de 30 dias "+fechaUC);
                                                        String mensaje = "El cliente tiene un prestamo menor de 30 dias "+fechaUC;
                                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                        this.registrarLog(datosLog);
                                                    }
                                                    saldo = saldo + Integer.parseInt(Saldo.toString());
                                                    abonoBase = Integer.parseInt(abonobase.toString());
                                                    iBonificacion += Integer.parseInt(bonificacion.toString());

                                                    if(vencido > 0){
                                                        if(impAbono>0){
                                                            //totImpAbono=impAbono-vencido;
                                                            //System.out.println("totImpAbono  "+ totImpAbono);
                                                            if((impAbono - vencido)>0){
                                                                totImpAbono = impAbono - vencido;
                                                            }
                                                           
                                                            if(totImpAbono<0){
                                                               // System.out.println("entro  ");
                                                                totImpAbono=0;
                                                            }                                                            
                                                            else{
                                                                
                                                                impAbono=totImpAbono;
                                                                //sumMinimoMes=sumMinimoMes-vencido;
                                                                sumMinMes+=minimo;
                                                                
                                                                //minimo=minimo-vencido;
                                                                minimo -= vencido;
                                                                //saldo=saldo-vencido;
                                                                saldo -= vencido;
                                                            }
                                                            
                                                            if(impAbono<minimo && minimo>0){   
                                                                validaMinimo=1;
                                                            }
                                                        }else{
                                                            totImpAbono=0;
                                                            sumMinMes+=minimo;
                                                            minimo -= vencido;
                                                            if(impAbono<minimo && minimo>0){   
                                                                validaMinimo=1;
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        totImpAbono = impAbono;
                                                        sumMinMes+=minimo;
                                                        if(impAbono<minimo && minimo>0){   
                                                            validaMinimo=1;
                                                        }
                                                        
                                                    }
                                                        resImpAbono+=totImpAbono;
                                                        SumAbonoBase+=abonoBase;
                                                        sumAbonoJson = sumAbonoJson + impAbono;
 
                                                        sumMinimoMes+=minimo;
                                                        
                
                                                        if(impAbono>=minimo){
                                                            sumImpAbono+=impAbono;

                                                        }else{
                                                            flagMinimo=1;
                                                            sumImpAbono+=impAbono;
                                                            cumpleMinimo=1;
                                                        }

                                                        partvalidaAbono = validaAbono.split(",");
                                                        clvMovimiento = partvalidaAbono[0];
                                                        numMovimiento = partvalidaAbono[1];
                                                        if(validaMesAdelantado.equals("0")){
                                                             if(dia<21){
                                                                    fechaCorteNuevo=fechaCorteActual;
                                                                }else{
                                                                    fechaCorteNuevo=fechaCorteMesSiguiente;
                                                                }
                                                        }else{
                                                            fechaCorteNuevo=fechaCorteMesSiguiente;
                                                        }

                                                        if (flagAbonoAdelantado>0 && impAbono>0) {
                                                                datosClientes="{\"numCliente\":"+numeroCliente+",\"numTienda\":"+numeroTienda+
                                                                       ",\"impAbono\":"+totImpAbono+",\"numTicket\":"+numTicket+
                                                                        ",\"clvMovimiento\":\""+clvMovimiento+"\""+
                                                                         ",\"numMovimiento\":\""+numMovimiento+"\""+
                                                                            ",\"fecAbono\":\""+hoy+"\""+
                                                                         ",\"fecCorteCartera\":\""+fechaCorteSiguiente+"\""+
                                                                            ",\"folio\":\""+folioAbono+"\""+"}";
                                                        }else{
                                                            datosClientes="{\"numCliente\":"+numeroCliente+",\"numTienda\":"+numeroTienda+
                                                                       ",\"impAbono\":"+totImpAbono+",\"numTicket\":"+numTicket+
                                                                        ",\"clvMovimiento\":\""+clvMovimiento+"\""+
                                                                         ",\"numMovimiento\":\""+numMovimiento+"\""+
                                                                            ",\"fecAbono\":\""+hoy+"\""+
                                                                         ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                            ",\"folio\":\""+folioAbono+"\""+"}";
                                                        }
                                                        //if(totImpAbono>0){
                                                        if(impAbono > 0){
                                                            resRegistroMovAbono = this.registrarMovAbonoPuntual(datosClientes);
                                                            String mensaje=null;
                                                            pasaCorteA=3;

                                                            System.out.println("LLego ciuad 3");
                                                            if(resRegistroMovAbono.length()>0){
                                                                // flagSiValida=1;
                                                                mensaje = "Abono Registrado";
                                                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";

                                                            }else{
                                                                flagSiValida=0;
                                                                mensaje = "Abono No Registrado";
                                                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                    ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                            }
                                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                            this.registrarLog(datosLog);
                                                        }else{
                                                            flagSiValida=0;
                                                            String mensaje=null;
                                                            iRegistro=0;
                                                            mensaje = "Abono No Registrado";
                                                            //System.out.println("mensaje "+mensaje);
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                        }

                                                }
                                            }else{
                                                flagSiValida=0;
                                            }
                                        }
                                    //aqui
                                        if(flagAbonoAdelantado>0){
                                            String mensaje = "Se registro el abono del cliente";
                                            json.addProperty("status", 0);
                                            json.addProperty("mensaje", mensaje);
                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                            
                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                    ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                            json.addProperty("data", data);
                                            
                                            LOGGER.info("Se registro el abono del cliente");
                                            LOGGER.info(data);
                                            this.registrarLog(datosLog);
                                            return response=json.toString();
                                        }
                                    
                                        saldaCon = saldo-iBonificacion;
                                        sumaImporteAbono+=sumImpAbono;
                                        if(sumaImporteAbono==saldaCon || sumaImporteAbono==saldo)
                                        {
                                            flagLiquidaCuentas=1;
                                        }
                                        if(sumImpAbono < sumMinMes){ 
                                           flagSiValida = 0;
                                        }
                                       
                                        if(importeAbono>0 && sumImpAbono>=sumMinimoMes){
                                            sumImpAbono+=importeAbono;
                                        }
                                        
                                        if(sumImpAbono>=saldo && sumImpAbono>0|| sumImpAbono>=saldaCon && sumImpAbono>0 || sumImpAbono >= sumMinimoMes ){
                                            flagSiValida=1;
                                            //importeAbono=1;
                                        }
                                        if(validaMinimo > 0){
                                            flagSiValida = 0;
                                        }
                                        
                                        if (sumMinMes < 0) {
                                            sumMinimoMes=0;
                                        }

                                        if(sumImpAbono >= sumMinimoMes && sumImpAbono > 0 && flagSiValida==1  && sumMinimoMes >=0 || sumImpAbono == saldaCon || sumImpAbono == saldo){
                                            flagAbono=1;
                                            //importeAbono=0;
                                            flagMinimo=0;
                                            cumpleMinimo=0;
                                        }else{
                                            flagAbono=0;
                                            flagMinimo=1;
                                            contFlagAbono=1;
                                            cumpleMinimo=1;
                                        }     

                                        if(sumImpAbono>=saldo  && sumImpAbono>0 && flagSiValida==1 || sumImpAbono >= saldaCon  && sumImpAbono>0 && flagSiValida==1){
                                            if(sumImpAbono>0){
                                                pasaCorte=1;
                                            }else{
                                                pasaCorte=0;
                                            }
                                        }else{
                                            pasaCorte=0;
                                        }
                                      // System.out.println("flagAbono "+flagAbono); 
                                       //System.out.println("flagMinimo "+flagMinimo); 
                                        if(flagAbono>0 && flagMinimo==0){

                                            dineroCalculado=Math.floor((resImpAbono * porcentajeDE)/100);

                                            if(Integer.parseInt(validaNumMesesCorte)>0){            

                                                if(corteCliente == Integer.parseInt(validaNumMesesCorte)){

                                                    if(flagLealtad == 1){
                                                        if(Integer.parseInt(statusPL)== 2 && Integer.parseInt(subStatusPL)== 1 ){
                                                            flagStatusLealtad=1;
                                                        }else{
                                                            String mensaje = "El cliente "+numeroCliente+" no esta registrado en el Plan de Lealtad";
                                                            json.addProperty("status", 1);
                                                            json.addProperty("mensaje", mensaje);
                                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+0+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                                           ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                                            LOGGER.info("El cliente "+numeroCliente+" no esta registrado en el Plan de Lealtad");
                                                            LOGGER.info(data);
                                                            json.addProperty("data", data);
                                                            this.registrarLog(datosLog);
                                                            return response=json.toString();
                                                        }
                                                    }

                                                    if( flagStatusLealtad == 1 ){

                                                            if(dia<21){
                                                               fechaCorteNuevo=fechaCorteActual;
                                                            }else{
                                                               fechaCorteNuevo=fechaCorteMesSiguiente;
                                                            }
                                                            if(sumImpAbono>=saldo || sumImpAbono>= saldaCon){

                                                                corteCliente = Integer.parseInt(validaNumMesesCorte);
                                                                datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                                    ",\"impAbonosCorte\":"+resImpAbono+",\"numCorte\":"+corteCliente+
                                                                   ",\"prcDineroE\":"+porcentajeDE+
                                                                    ",\"impDineroCorte\":"+dineroCalculado+
                                                                     ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                      ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                      ",\"abonoBase\":"+SumAbonoBase+"}";

                                                               resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                               String mensaje=null;

                                                               mensaje="El cliente avanzo de corte";
                                                               data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                      ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                               json.addProperty("status", 0);
                                                               json.addProperty("mensaje", mensaje);
                                                               json.addProperty("data", data);
                                                               response=json.toString();

                                                                impTotal=Math.floor((resImpAbono * porcentajeDE)/100);
                                                                impAConciderar=impAConciderar+resImpAbono;
                                                                impFinal=Math.floor((impAConciderar * porcentajeDE)/100);
                                                                impMovAbono=impMovAbono+sumImpAbono;
                                                                //System.out.println("FOLIO: "+Integer.parseInt(folioAbono.toString()));
                                                                datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                                       ",\"iduLogica\":"+numLogica+
                                                                           ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                                resRegistroDE = this.registrarMovDineroElectronico(datosDE);
                                                                mensaje = "El cliente gano dinero electronico";
                                                                json.addProperty("status", 0);
                                                                json.addProperty("mensaje", mensaje);
                                                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+Integer.parseInt(resRegistroDE)+",\"fecha\":\""+hoy+"\""+
                                                                    ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                        ",\"totalAbono\":"+impMovAbono+"}";
                                                                json.addProperty("data", data);
                                                                LOGGER.info("El cliente gano dinero electronico");
                                                                LOGGER.info(data);
                                                                
                                                                this.registrarLog(datosLog);
                                                                return response = json.toString();
                                                            }else{
                                                                corteCliente = 1;

                                                                datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                                    ",\"impAbonosCorte\":"+resImpAbono+",\"numCorte\":"+corteCliente+
                                                                   ",\"prcDineroE\":"+porcentajeDE+
                                                                    ",\"impDineroCorte\":"+dineroCalculado+
                                                                     ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                      ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                      ",\"abonoBase\":"+SumAbonoBase+"}";

                                                               resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                               String mensaje=null;

                                                               mensaje="El cliente avanzo de corte";
                                                               data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                      ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                               json.addProperty("status", 0);
                                                               json.addProperty("mensaje", mensaje);
                                                               json.addProperty("data", data);
                                                               LOGGER.info("El cliente avanzo de corte");
                                                               LOGGER.info(data);
                                                               response=json.toString();  
                                                            }
                                                   }

                                            }else{
                                                    /*System.out.println("pasaCorte "+pasaCorte);
                                                    System.out.println("pasaCorteA "+pasaCorteA  );
                                                    System.out.println("cumpleMinimo "+cumpleMinimo);*/
                                                    
                                                    if(pasaCorte>0){

                                                            if(dia<21){
                                                                fechaCorteNuevo=fechaCorteActual;
                                                            }else{
                                                                fechaCorteNuevo=fechaCorteMesSiguiente;
                                                            }
                                                            datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                                   ",\"impAbonosCorte\":"+resImpAbono+",\"numCorte\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                    ",\"prcDineroE\":"+porcentajeDE+
                                                                     ",\"impDineroCorte\":"+dineroCalculado+
                                                                      ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                       ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                        ",\"abonoBase\":"+SumAbonoBase+"}";

                                                            resRegistroCorte= this.registrarCorteCumplido(datosCorte);

                                                        validaSumDE = this.consultarImporteCorte(numeroCliente,numLogica,corteCliente);
                                                        
                                                        if(Integer.parseInt(validaSumDE)>0){
                                                            impTotal=Math.floor(Integer.parseInt(validaSumDE));
                                                        }else{
                                                            impTotal=Math.floor((resImpAbono * porcentajeDE)/100);
                                                        }
                                                        impMovAbono=impMovAbono+sumImpAbono;
                                                        impAConciderar=impAConciderar+resImpAbono;
                                                        impFinal=Math.floor((impAConciderar * porcentajeDE)/100);
                                                        //System.out.println("FOLIO1_: "+Integer.parseInt(folioAbono.toString()));
                                                        datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                               ",\"iduLogica\":"+numLogica+
                                                                   ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                        //System.out.println("datosDE: "+datosDE);
                                                        resRegistroDE = this.registrarMovDineroElectronico(datosDE);
                                                        String mensaje = "El cliente gano dinero electronico";
                                                        json.addProperty("status", 0);
                                                        json.addProperty("mensaje", mensaje);
                                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+Integer.parseInt(resRegistroDE)+",\"fecha\":\""+hoy+"\""+
                                                            ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                ",\"totalAbono\":"+impMovAbono+"}";
                                                        json.addProperty("data", data);
                                                        LOGGER.info("El cliente gano dinero electronico");
                                                        LOGGER.info(data);
                                                        this.registrarLog(datosLog);
                                                        return response = json.toString();
                                                    }else if(pasaCorte==0 && pasaCorteA==3 && cumpleMinimo==0){
                                                        String mensaje=null;    

                                                            int iCorte=corteCliente;
                                                            corteCliente=corteCliente+1;
                                                            if(dia<21){
                                                                fechaCorteNuevo=fechaCorteActual;
                                                            }else{
                                                                fechaCorteNuevo=fechaCorteMesSiguiente;
                                                            }
                                                            datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                                   ",\"impAbonosCorte\":"+resImpAbono+",\"numCorte\":"+corteCliente+
                                                                    ",\"prcDineroE\":"+porcentajeDE+
                                                                     ",\"impDineroCorte\":"+dineroCalculado+
                                                                      ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                       ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                        ",\"abonoBase\":"+SumAbonoBase+"}";

                                                            resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                            mensaje="El cliente avanzo de corte";
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                    ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                            json.addProperty("status", 0);
                                                            json.addProperty("mensaje", mensaje);
                                                            json.addProperty("data", data);
                                                            LOGGER.info("El cliente avanzo de corte");
                                                            LOGGER.info(data);
                                                            response=json.toString();
                                                        
                                                        if(corteCliente == Integer.parseInt(validaNumMesesCorte)){
                                                            corteCliente = Integer.parseInt(validaNumMesesCorte);
                                                            validaSumDE = this.consultarImporteCorte(numeroCliente,numLogica,iCorte);
                                                            impAConciderar=impAConciderar+resImpAbono;
                                                            impFinal=Math.floor((impAConciderar * porcentajeDE)/100);
                                                            impMovAbono=impMovAbono+sumImpAbono;
                                                            //System.out.println("FOLIO2_: "+Integer.parseInt(folioAbono.toString()));
                                                            datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                                   ",\"iduLogica\":"+numLogica+
                                                                       ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                            resRegistroDE = this.registrarMovDineroElectronico(datosDE);
                                                            mensaje = "El cliente gano dinero electronico";
                                                            json.addProperty("status", 0);
                                                            json.addProperty("mensaje", mensaje);
                                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+Integer.parseInt(resRegistroDE)+",\"fecha\":\""+hoy+"\""+
                                                                    ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                    ",\"totalAbono\":"+impMovAbono+"}";
                                                            json.addProperty("data", data);
                                                            LOGGER.info("El cliente gano dinero electronico");
                                                            LOGGER.info(data);
                                                            this.registrarLog(datosLog);
                                                            return response = json.toString();
                                                        }
                                                    }else{

                                                        String mensaje = "El movimiento "+numTipoCuenta+" no participa en el programa";
                                                        json.addProperty("status", 1);
                                                        json.addProperty("mensaje", mensaje);

                                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                    ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                                        json.addProperty("data", data);
                                                        LOGGER.info("El movimiento "+numTipoCuenta+" no participa en el programa");
                                                        LOGGER.info(data);
                                                        this.registrarLog(datosLog);
                                                        return response =json.toString();
                                                    }                                    
                                                }
                                            }else{

                                                String mensaje = "No se encontro Logica";
                                                json.addProperty("status", 1);
                                                json.addProperty("mensaje", mensaje);
                                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                    ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                                this.registrarLog(datosLog);
                                                LOGGER.info("no se encontro Logica");
                                                LOGGER.info(data);
                                                json.addProperty("data", data);
                                                return response=json.toString();
                                            }       
                                        }else{
                                            String mensaje ="";
                                            if(flagSiValida==1){
                                                
                                                mensaje = "No avanza en los cortes el abono es menor que el minimo";
                                            }else{
                                                mensaje = "El movimiento "+numTipoCuenta+" no participa en el programa";
                                            }    
                                            
                                            json.addProperty("status",1);
                                            json.addProperty("mensaje", mensaje);
                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                    ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                            this.registrarLog(datosLog);
                                            json.addProperty("data", data);
                                            LOGGER.info(mensaje);
                                            LOGGER.info(data);
                                            response=json.toString();
                                        }   
                                }else{
                                     String mensaje = "No se encontro puntualidad del Cliente";
                                     json.addProperty("status", 1);
                                     json.addProperty("mensaje", mensaje);
                                     datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                     data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+0+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                    ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                     json.addProperty("data", data);
                                     LOGGER.info("No se encontro puntualidad del Cliente");
                                     LOGGER.info(data);
                                     this.registrarLog(datosLog);
                                     return response=json.toString();
                                }
                            }else{
                                String mensaje = "No se encontro al cliente con una logica ni ciudad relacionada";
                                json.addProperty("status", 1);
                                json.addProperty("mensaje", mensaje);
                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+0+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                this.registrarLog(datosLog);
                                LOGGER.info("No se encontro al cliente con una logica ni ciudad relacionada");
                                LOGGER.info(data);
                                json.addProperty("data", data);
                                return response = json.toString();
                            }
                        }else{

                            System.out.println("Valida por numero de ciudad ");
                            String[] partCiudad = validaCiudad.split(",");
                            numLogica = Integer.parseInt(partCiudad[0]);
                            porcentajeDE = Integer.parseInt(partCiudad[1]);
                            corteCliente=0;
                            LOGGER.info("Valida por numero de ciudad ");

                            if(fechaCorteActual.toString().equals(fecCorteReg)){
                                flagAbonoAdelantado=1;
                                fechaCorteSiguiente=fechaCorteActual.plusMonths(1);
                            }
                            
                            //System.out.println("fechaCorteActual "+fechaCorteActual);
                            ValidaImpMovAbono = this.consultarImpAbonosPuntuales(numeroCliente,corteCliente);

                            if(ValidaImpMovAbono.length()>0){
                                //impMovAbono=Integer.parseInt(ValidaImpMovAbono);
                                impMovAbono=0;
                            }
                            
                            ValidaImpAbonoRegistrado = this.consultarImpAbonosRegistrados(numeroCliente,numLogica,corteCliente);
                            if(ValidaImpAbonoRegistrado.length()>0){
                                impAConciderar=Integer.parseInt(ValidaImpAbonoRegistrado);
                            }
                             
                            validaNumMesesCorte=this.consultarLogicasRecompensa(numLogica);
                            
                            validaPuntualidad=this.consultaPuntualidad(numLogica,clv_puntualidad,numeroCliente);
                            
                            if(validaPuntualidad != "0"){
                                validaMovAbono = this.consultarMovAbonosPuntuales(numeroCliente);
                                //System.out.println("validaMovAbono "+validaMovAbono);
                                if(Integer.parseInt(validaMovAbono)>1){
                                    importeAbono=0;
                                    resImpAbono=0;
                                    importeAbono = Integer.parseInt(validaMovAbono);
                                    resImpAbono=resImpAbono+importeAbono;
                                }
                                for(int i =0; i < jsonArray.size();i++){     
                                        JsonObject cuentasObject = jsonArray.get(i).getAsJsonObject (); 
                                        JsonElement tipocuenta = cuentasObject.get("tipocuenta");
                                        JsonElement Saldo = cuentasObject.get("saldo");
                                        JsonElement abonobase = cuentasObject.get("abonobase");
                                        JsonElement plazo = cuentasObject.get("plazo");
                                        JsonElement Vencido = cuentasObject.get("vencido");
                                        JsonElement Minimo = cuentasObject.get("minimo");
                                        JsonElement bonificacion = cuentasObject.get("bonificacion");
                                        JsonElement prcBonificacion = cuentasObject.get("porcentajeBonificacion");
                                        JsonElement descripcion = cuentasObject.get("descripcion");

                                        JsonElement fecUltimaCompra = cuentasObject.get("fechaUltimaCompra");
                                        JsonElement factura = cuentasObject.get("factura");
                                        JsonElement imp_abono = cuentasObject.get("imp_abono");
                                            
                                        minimo = Integer.parseInt(Minimo.toString());
                                        impAbono = Integer.parseInt(imp_abono.toString());
                                        numTicket =  Integer.parseInt(factura.toString());
                                        
                                      
                                        //sDiaUltimaCompra=fecUltimaCompra.toString().substring(9,11);
                                        //sMesUltimaCompra=fecUltimaCompra.toString().substring(6,8);
                                        //System.out.println("MESUC "+sMesUltimaCompra);
                                        
                                        //if(fecUltimaCompra.equals(null)){
                                        //System.out.println("fecha "+fecUltimaCompra.toString());

                                       
                                        if(fecUltimaCompra == null){    
                                            fecUC="1900-01-01";
                                        }else if(fecUltimaCompra.toString().length() <= 2){
                                            fecUC="1900-01-01";
                                        }
                                        else{
                                            //System.out.println("fecUltimaCompra "+fecUltimaCompra.toString());
                                            resfecha = this.validarFecha(fecUltimaCompra.toString());
                                            if(resfecha==true){
                                                //System.out.println("La fecha es valida");
                                                flagValidaFecha=1;
                                                fecUC=fecUltimaCompra.toString();
                                                fecUC=fecUC.substring(1,11);
                                                //System.out.println("entro 1");
                                            }else{
                                                //System.out.println("La fecha no es valida");
                                                flagValidaFecha=0;
                                                //System.out.println("entro 2");
                                                fecUC=fecUltimaCompra.toString();
                                                if(fecUltimaCompra.toString().length()<12){
                                                    fecUC=fecUltimaCompra.toString();
                                                    fecUC=fecUC.substring(0,5)+"-"+fecUC.substring(5,11);
                                                    fecUC=fecUC.substring(1,11);
                                                }else{
                                                    fecUC=fecUC.substring(1,11);
                                                }
                                             }
                                        }
                                        
                                        
                                        
                                        if(prcBonificacion == null){
                                            prceBonificacion=0;
                                        }else{
                                            prceBonificacion=Integer.parseInt(prcBonificacion.toString());
                                        }
                                        //System.out.println("fecUC "+fecUC);
                                        sDiaUltimaCompra=fecUC.toString().substring(8,10);
                                        //System.out.println("trono aqui");
                                        //System.out.println("LLego Ciudad 1");
                                        dateUC = LocalDate.parse(fecUC);
                                        fechaUC = java.sql.Date.valueOf(dateUC);
                                        
                                        diffInMillies = Math.abs(fechaUC.getTime() - fechaActual.getTime());
                                        diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                                        
                                        vencido = Integer.parseInt(Vencido.toString());
                                        
                                        numTipoCuenta = Integer.parseInt(tipocuenta.toString());

                                        validaAbono = this.consultarCarterasRecompensa(numLogica, numTipoCuenta,numeroCliente);
                                        //System.out.println("LLego Ciudad 2");
                                        if(validaAbono.length()>1){
                                            flagSiValida=1;
                                            saldaConBonificacion=Integer.parseInt(Saldo.toString())-Integer.parseInt(bonificacion.toString());

                                            if((impAbono>=Integer.parseInt(Saldo.toString()) && prceBonificacion == 10000) && numTipoCuenta==1 ||
                                                (impAbono>=saldaConBonificacion && prceBonificacion == 10000 && numTipoCuenta==1)){
                                                flagSiValida=0;
                                                String mensaje = "El cliente esta liquidando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias";
                                                json.addProperty("status", 0);
                                                json.addProperty("mensaje", mensaje);
                                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                        ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                json.addProperty("data", data);
                                                LOGGER.info("El cliente esta liquidando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias");
                                                LOGGER.info(data);
                                                this.registrarLog(datosLog);

                                            }
                                            else if((impAbono>=Integer.parseInt(Saldo.toString()) && diff <= 30) && (numTipoCuenta==0 || numTipoCuenta==4 || numTipoCuenta==6)||
                                                (impAbono>=saldaConBonificacion && diff <= 30 && (numTipoCuenta==0 || numTipoCuenta==4 || numTipoCuenta==6))){
                                                flagSiValida=0;
                                                String mensaje = "El cliente esta liquidando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias";
                                                json.addProperty("status", 0);
                                                json.addProperty("mensaje", mensaje);
                                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                        ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                json.addProperty("data", data);
                                                LOGGER.info("El cliente esta liquidando su cuenta (factura: "+numTicket+", TipoCuenta: "+numTipoCuenta+") antes de los 30 dias");
                                                LOGGER.info(data);
                                                this.registrarLog(datosLog);

                                            }
                                            else{
                                                
                                                if(numTipoCuenta == 3 && diff <= 30){
                                                    minimo=0;
                                                    //System.out.println("El cliente tiene un prestamo menor de 30 dias "+fechaUC);
                                                    LOGGER.info("El cliente tiene un prestamo menor de 30 dias "+fechaUC);
                                                    String mensaje = "El cliente tiene un prestamo menor de 30 dias "+fechaUC;
                                                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                    this.registrarLog(datosLog);
                                                }
                                                ///System.out.println("minimoo "+minimo);
                                                saldo = saldo + Integer.parseInt(Saldo.toString());
                                                abonoBase = Integer.parseInt(abonobase.toString());
                                                iBonificacion += Integer.parseInt(bonificacion.toString());
                                                sumMinimoMes+=minimo;
                                                //System.out.println("sDiaUltimaCompra "+sDiaUltimaCompra.toString());
                                                /*if(Integer.parseInt(sDiaUltimaCompra.toString())>20 && numTipoCuenta==1){
                                                    if(vencido>0){
                                                        sumMinimoMes+=vencido;
                                                       // System.out.println("Ciudad Dia ultima compra mayor que 20");
                                                    }else{
                                                        sumMinimoMes+=0;
                                                    }
                                                }                                            
                                                else if(Integer.parseInt(sDiaUltimaCompra.toString()) < iDiaActual 
                                                        && numTipoCuenta==1
                                                        && Integer.parseInt(sDiaUltimaCompra.toString())<=20){
                                                    if(vencido>0){
                                                        sumMinimoMes+=vencido;  
                                                        //System.out.println("Ciudad Dia ultima compra menor que dia actual");
                                                    }else{
                                                        sumMinimoMes+=minimo;
                                                       //System.out.println("aqui 1");
                                                    }
                                                }else {
                                                    sumMinimoMes+=minimo; 
                                                   // System.out.println("Ciudad entro en el else");
                                                }*/

                                                //System.out.println("min mes "+sumMinimoMes);
                                                
                                                if(vencido > 0){                                                
                                                    if(impAbono>0){
                                                        totImpAbono=impAbono-vencido;

                                                        if(totImpAbono<0){
                                                            totImpAbono=0;
                                                            
                                                        }else{

                                                            impAbono=totImpAbono;
                                                            sumMinimoMes=sumMinimoMes-vencido;
                                                            minimo=minimo-vencido;
                                                            saldo=saldo-vencido;
                                                        }
                                                    }else{
                                                        totImpAbono=0;
                                                    }
                                                }else{
                                                    totImpAbono=impAbono;
                                                    
                                                }

                                                    resImpAbono+=totImpAbono;

                                                    SumAbonoBase+=abonoBase;

                                                    sumAbonoJson = sumAbonoJson + impAbono;
                                                    //sumImpAbono+=impAbono;

                                                    if(impAbono>=minimo){
                                                        sumImpAbono+=impAbono;

                                                    }else{
                                                        flagMinimo=1;
                                                        sumImpAbono+=impAbono;
                                                        cumpleMinimo=1;
                                                    }

                                                    partvalidaAbono = validaAbono.split(",");
                                                    clvMovimiento = partvalidaAbono[0];
                                                    numMovimiento = partvalidaAbono[1];
                                                    if(validaMesAdelantado.equals("0")){
                                                         if(dia<21){
                                                                fechaCorteNuevo=fechaCorteActual;
                                                            }else{
                                                                fechaCorteNuevo=fechaCorteMesSiguiente;
                                                            }
                                                    }else{
                                                        fechaCorteNuevo=fechaCorteMesSiguiente;
                                                    }
                                                    //impAConciderar=+totImpAbono;

                                                    if (flagAbonoAdelantado>0) {
                                                        datosClientes="{\"numCliente\":"+numeroCliente+",\"numTienda\":"+numeroTienda+
                                                               ",\"impAbono\":"+totImpAbono+",\"numTicket\":"+numTicket+
                                                                ",\"clvMovimiento\":\""+clvMovimiento+"\""+
                                                                 ",\"numMovimiento\":\""+numMovimiento+"\""+
                                                                    ",\"fecAbono\":\""+hoy+"\""+
                                                                 ",\"fecCorteCartera\":\""+fechaCorteSiguiente+"\""+
                                                                    ",\"folio\":\""+folioAbono+"\""+"}";
                                                    }else{
                                                        datosClientes="{\"numCliente\":"+numeroCliente+",\"numTienda\":"+numeroTienda+
                                                                   ",\"impAbono\":"+totImpAbono+",\"numTicket\":"+numTicket+
                                                                    ",\"clvMovimiento\":\""+clvMovimiento+"\""+
                                                                     ",\"numMovimiento\":\""+numMovimiento+"\""+
                                                                        ",\"fecAbono\":\""+hoy+"\""+
                                                                     ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                        ",\"folio\":\""+folioAbono+"\""+"}";
                                                    }
                                                    //if(totImpAbono>0){
                                                    if(impAbono > 0){
                                                        resRegistroMovAbono = this.registrarMovAbonoPuntual(datosClientes);
                                                        String mensaje=null;
                                                   
                                                        pasaCorteA=3;

                                                        if(resRegistroMovAbono.length()>0){
                                                            mensaje = "Abono Registrado";
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";

                                                        }else{
                                                            flagSiValida=0;
                                                            mensaje = "Abono No Registrado";
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                        }
                                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";

                                                        this.registrarLog(datosLog);
                                                    }
                                                    else{
                                                            flagSiValida=0;
                                                            String mensaje=null;
                                                            iRegistro=0;
                                                            mensaje = "Abono No Registrado";
                                                            //System.out.println("mensaje "+mensaje);
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                        }
                                            }
                                        }else{
                                            flagSiValida=0;
                                        }
                                    }
                                
                                    if(flagAbonoAdelantado>0){
                                        String mensaje = "Se registro el abono del cliente";
                                        json.addProperty("status", 0);
                                        json.addProperty("mensaje", mensaje);
                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                        json.addProperty("data", data);
                                        LOGGER.info("Se registro el abono del cliente");
                                        LOGGER.info(data);
                                        this.registrarLog(datosLog);
                                        return response=json.toString();
                                    }
                                    saldaCon=saldo-iBonificacion;
                                   // System.out.println("SumImpAbono "+sumImpAbono);
                                   // System.out.println("importeAbono "+importeAbono);
                                    if(importeAbono>0){
                                        sumImpAbono+=importeAbono;
                                    }
                                    
                                    if(sumImpAbono>=saldo || sumImpAbono>=saldaCon || sumImpAbono>0){
                                        flagSiValida=1;
                                        importeAbono=1;
                                    }
                                    
                                    /*System.out.println("saldaCon "+saldaCon);
                                    System.out.println("saldo "+saldo);
                                    System.out.println("sumMinimoMes "+sumMinimoMes);*/
                                    if(sumImpAbono >= sumMinimoMes  && sumImpAbono > 0 && flagSiValida==1){
                                        flagAbono=1;
                                        flagMinimo=0;
                                        cumpleMinimo=0;
                                    }else{
                                        flagAbono=0;
                                        flagMinimo=1;
                                        contFlagAbono=1;
                                        cumpleMinimo=1;
                                    }     

                                    if(sumImpAbono>=saldo && sumImpAbono > 0 && flagSiValida==1 || sumImpAbono>= saldaCon && sumImpAbono > 0 && flagSiValida==1){    
                                       if(sumImpAbono>0){
                                            pasaCorte=1;
                                        }else{
                                           pasaCorte=0;
                                       }
                                    }else{
                                        pasaCorte=0;
                                    }
                                
                                    if(flagAbono>0 && flagMinimo==0){

                                        dineroCalculado=  Math.floor((resImpAbono * porcentajeDE)/100);
                                       
                                        if(Integer.parseInt(validaNumMesesCorte)>0){            

                                            if(corteCliente == Integer.parseInt(validaNumMesesCorte)){
                                                
                                                if(flagLealtad == 1){
                                                    if(Integer.parseInt(statusPL)== 2 && Integer.parseInt(subStatusPL)== 1 ){
                                                        flagStatusLealtad=1;
                                                    }else{
                                                        String mensaje = "El cliente "+numeroCliente+" no esta registrado en el Plan de Lealtad";
                                                        json.addProperty("status", 1);
                                                        json.addProperty("mensaje", mensaje);
                                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+0+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                                       ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                                        json.addProperty("data", data);
                                                        LOGGER.info("El cliente "+numeroCliente+" no esta registrado en el Plan de Lealtad");
                                                        LOGGER.info(data);
                                                        this.registrarLog(datosLog);
                                                        return response=json.toString();
                                                    }
                                                }
                                                
                                                if( flagStatusLealtad == 1 ){
                                                   
                                                        if(dia<21){
                                                           fechaCorteNuevo=fechaCorteActual;
                                                        }else{
                                                           fechaCorteNuevo=fechaCorteMesSiguiente;
                                                        }

                                                      
                                                       
                                                        if(sumImpAbono>=saldo || sumImpAbono>=saldaCon){    
                                                            
                                                            corteCliente = Integer.parseInt(validaNumMesesCorte);
                                                            datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                                ",\"impAbonosCorte\":"+resImpAbono+",\"numCorte\":"+corteCliente+
                                                               ",\"prcDineroE\":"+porcentajeDE+
                                                                ",\"impDineroCorte\":"+dineroCalculado+
                                                                 ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                  ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                  ",\"abonoBase\":"+SumAbonoBase+"}";

                                                           resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                           String mensaje=null;

                                                           mensaje="El cliente avanzo de corte";
                                                           data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                  ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                           json.addProperty("status", 0);
                                                           json.addProperty("mensaje", mensaje);
                                                           json.addProperty("data", data);
                                                           response=json.toString();

                                                            impTotal=Math.floor((resImpAbono * porcentajeDE)/100);
                                                            impAConciderar=impAConciderar+resImpAbono;
                                                            impFinal=Math.floor((impAConciderar * porcentajeDE)/100);
                                                            impMovAbono=impMovAbono+sumImpAbono;
                                                            datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                                   ",\"iduLogica\":"+numLogica+
                                                                       ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                            resRegistroDE = this.registrarMovDineroElectronico(datosDE);
                                                            mensaje = "El cliente gano dinero electronico";
                                                            json.addProperty("status", 0);
                                                            json.addProperty("mensaje", mensaje);
                                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+Integer.parseInt(resRegistroDE)+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                    ",\"totalAbono\":"+impMovAbono+"}";
                                                            json.addProperty("data", data);
                                                            LOGGER.info("El cliente gano dinero electronico");
                                                            LOGGER.info(data);
                                                            this.registrarLog(datosLog);
                                                            return response = json.toString();
                                                        }else{
                                                            corteCliente = 1;
                                                            
                                                            datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                                ",\"impAbonosCorte\":"+resImpAbono+",\"numCorte\":"+corteCliente+
                                                               ",\"prcDineroE\":"+porcentajeDE+
                                                                ",\"impDineroCorte\":"+dineroCalculado+
                                                                 ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                  ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                  ",\"abonoBase\":"+SumAbonoBase+"}";

                                                           resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                           String mensaje=null;

                                                           mensaje="El cliente avanzo de corte";
                                                           data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                  ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                           json.addProperty("status", 0);
                                                           json.addProperty("mensaje", mensaje);
                                                           json.addProperty("data", data);
                                                           LOGGER.info("El cliente avanzo de corte");
                                                           LOGGER.info(data);
                                                           response=json.toString();  
                                                        }   
                                               }
                                               
                                        }else{
                                                
                                                if(pasaCorte>0){

                                                        if(dia<21){
                                                            fechaCorteNuevo=fechaCorteActual;
                                                        }else{
                                                            fechaCorteNuevo=fechaCorteMesSiguiente;
                                                        }
                                                        datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                               ",\"impAbonosCorte\":"+resImpAbono+",\"numCorte\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                ",\"prcDineroE\":"+porcentajeDE+
                                                                 ",\"impDineroCorte\":"+dineroCalculado+
                                                                  ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                   ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                    ",\"abonoBase\":"+SumAbonoBase+"}";

                                                        resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                     
                                                    validaSumDE = this.consultarImporteCorte(numeroCliente,numLogica,corteCliente);
                                                    
                                                    if(Integer.parseInt(validaSumDE)>0){
                                                        impTotal=Math.floor(Integer.parseInt(validaSumDE));
                                                    }else{
                                                        impTotal=Math.floor((resImpAbono * porcentajeDE)/100);
                                                    }
                                                    impMovAbono=impMovAbono+sumImpAbono;
                                                    //impAConciderar=impAConciderar+resImpAbono;
                                                    //impFinal=(impAConciderar * porcentajeDE)/100;
                                                    impAConciderar=impAConciderar+resImpAbono;
                                                    impFinal=Math.floor((impAConciderar * porcentajeDE)/100);
                                                    datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                           ",\"iduLogica\":"+numLogica+
                                                               ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                    resRegistroDE = this.registrarMovDineroElectronico(datosDE);
                                                    String mensaje = "El cliente gano dinero electronico";
                                                    json.addProperty("status", 0);
                                                    json.addProperty("mensaje", mensaje);
                                                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                                    data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+Integer.parseInt(resRegistroDE)+",\"fecha\":\""+hoy+"\""+
                                                        ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                                            ",\"totalAbono\":"+impMovAbono+"}";
                                                    json.addProperty("data", data);
                                                    LOGGER.info("El cliente gano dinero electronico");
                                                    LOGGER.info(data);
                                                    this.registrarLog(datosLog);
                                                    return response = json.toString();
                                                }else if(pasaCorte==0 && pasaCorteA==3 && cumpleMinimo==0){
                                                   
                                                    String mensaje=null;    
                                                        int iCorte=corteCliente;
                                                        corteCliente=corteCliente+1;
                                                        if(dia<21){
                                                            fechaCorteNuevo=fechaCorteActual;
                                                        }else{
                                                            fechaCorteNuevo=fechaCorteMesSiguiente;
                                                        }
                                                        datosCorte = "{\"numCliente\":"+numeroCliente+",\"iduLogica\":"+numLogica+
                                                               ",\"impAbonosCorte\":"+resImpAbono+",\"numCorte\":"+corteCliente+
                                                                ",\"prcDineroE\":"+porcentajeDE+
                                                                 ",\"impDineroCorte\":"+dineroCalculado+
                                                                  ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                                                   ",\"fecCorteCartera\":\""+fechaCorteNuevo+"\""+
                                                                    ",\"abonoBase\":"+SumAbonoBase+"}";

                                                        resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                                        mensaje="El cliente avanzo de corte";
                                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+"}";
                                                        json.addProperty("status", 0);
                                                        json.addProperty("mensaje", mensaje);
                                                        json.addProperty("data", data);
                                                        LOGGER.info("El cliente avanzo de corte");
                                                        LOGGER.info(data);
                                                        response=json.toString();

                                                    if(corteCliente == Integer.parseInt(validaNumMesesCorte)){

                                                        corteCliente = Integer.parseInt(validaNumMesesCorte);
                                                        validaSumDE = this.consultarImporteCorte(numeroCliente,numLogica,iCorte);
                                                        impMovAbono=impMovAbono+sumImpAbono;
                                                        impAConciderar=impAConciderar+resImpAbono;
                                                        impFinal=Math.floor((impAConciderar * porcentajeDE)/100);
                                                        datosDE="{\"numCliente\":"+numeroCliente+",\"impDineroElectronico\":"+impFinal+
                                                               ",\"iduLogica\":"+numLogica+
                                                                   ",\"fecGeneracion\":\""+hoy+"\""+
                                                                        ",\"numFolioabono\":\""+Integer.parseInt(folioAbono.toString())+"\""+"}";
                                                        resRegistroDE = this.registrarMovDineroElectronico(datosDE);
                                                        mensaje = "El cliente gano dinero electronico";
                                                        json.addProperty("status", 0);
                                                        json.addProperty("mensaje", mensaje);
                                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+Integer.parseInt(resRegistroDE)+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+Integer.parseInt(resRegistroCorte)+",\"corteslogica\":"+Integer.parseInt(validaNumMesesCorte)+
                                                                ",\"totalAbono\":"+impMovAbono+"}";
                                                        json.addProperty("data", data);
                                                        this.registrarLog(datosLog);
                                                        LOGGER.info("El cliente gano dinero electronico");
                                                        LOGGER.info(data);
                                                        return response = json.toString();
                                                    }
                                                }else{

                                                    String mensaje = "El movimiento "+numTipoCuenta+" no participa en el programa";
                                                    json.addProperty("status", 1);
                                                    json.addProperty("mensaje", mensaje);
                                                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                                    data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                                    json.addProperty("data", data);
                                                    this.registrarLog(datosLog);
                                                    LOGGER.info("El movimiento "+numTipoCuenta+" no participa en el programa");
                                                    LOGGER.info(data);
                                                    return response =json.toString();
                                                }                                       
                                            }
                                        }else{

                                            String mensaje = "No se encontro Logica";
                                            json.addProperty("status", 1);
                                            json.addProperty("mensaje", mensaje);
                                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                            data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                            this.registrarLog(datosLog);
                                            json.addProperty("data", data);
                                            LOGGER.info("No se encontro Logica");
                                            LOGGER.info(data);
                                            return response=json.toString();
                                        }       
                                    }else{
                                        String mensaje="";
                                        if(flagSiValida==1){
                                            mensaje = "No avanza en los cortes el abono es menor que el minimo";
                                        }else{
                                            mensaje = "El movimiento "+numTipoCuenta+" no participa en el programa";
                                        }
                                        json.addProperty("status",1);
                                        json.addProperty("mensaje", mensaje);
                                        datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+numLogica+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                        data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+numLogica+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                        this.registrarLog(datosLog);
                                        LOGGER.info(mensaje);
                                        LOGGER.info(data);
                                        json.addProperty("data", data);

                                        response=json.toString();
                                    }   
                            }else{
                                 String mensaje = "No se encontro puntualidad del Cliente";
                                 json.addProperty("status", 1);
                                 json.addProperty("mensaje", mensaje);
                                 datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numeroCliente+",\"fechaReg\":"+hoy+"}";
                                 data="{\"cliente\":"+numeroCliente+",\"numlogica\":"+0+",\"dineroganar\":"+0+",\"fecha\":\""+hoy+"\""+
                                                                ",\"cortealcanzado\":"+0+",\"corteslogica\":"+0+"}";
                                 json.addProperty("data", data);
                                 LOGGER.info("No se encontro puntualidad del Cliente");
                                 LOGGER.info(data);
                                 this.registrarLog(datosLog);
                                 return response=json.toString();
                            }
                        }
                    }
                        
               }
           }   
        }catch(JsonSyntaxException jse){ 
           // this.manejarExcepcion(jse, "Json Invalido.", Response.Status.INTERNAL_SERVER_ERROR);
           // LOGGER.info("Json Invalido "+Cliente);
            String mensaje = "Json Invalido";
            json.addProperty("status", 2);
            json.addProperty("mensaje", mensaje);
            json.addProperty("data", "json invalido");
             LOGGER.info("Json Invalido "+json.toString());
            return response=json.toString();
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultarAbonos.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    public String validaPlanLealtad(
        String num_cliente        
    ) throws IOException, SQLException{
        String validaUrl = this.consultaUrl("1");
        String response=null;
        JsonObject json = new JsonObject();
        JsonObject jsonDatos = new JsonObject();
        LocalDate hoy = LocalDate.now(); 
        if(validaUrl.equals("0")){
            response="0";
        }else{

            String []partvalidaUrl = validaUrl.split(",");
            String  status = partvalidaUrl[0];
            String urlServicio = partvalidaUrl[1];
            //System.out.println("Paln lealtad "+urlServicio);
            //--inicio llamado servicio plan de lealtad
            //URL url = new URL ("http://qa-apipos.coppel.com:9000/appcoppel/api/v1/validar_estatuscliente");
            URL url = new URL (urlServicio.toString());
            //URL url = new URL ("http://10.40.116.20:9002/appcoppel/api/v1/validar_estatuscliente");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            //System.out.println("con "+con);
            
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"num_cliente\":"+Integer.parseInt(num_cliente)+"}";

            try(OutputStream os = con.getOutputStream()) {
                 //os.close();
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
                //System.out.println("plan 3");

            }            
           // System.out.println("plan " );
    //        StringBuilder response = new StringBuilder();
                // System.out.println("plan 2");
                
        try{            
            StringBuilder respuesta = new StringBuilder();
            
            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
                //HttpURLConnection.setFollowRedirects(false);
                //con.setRequestMethod("HEAD");
                con.setConnectTimeout(5000); 
                  /*final Duration timeout = Duration.ofSeconds(5);
                  timeout.minusSeconds(br::readLine);*/
                  //String line = timeout.callWithTimeout(br::readLine, 10, TimeUnit.SECONDS);
                    //String line = timeLimiter.callWithTimeout(br::readLine, 10, TimeUnit.SECONDS);
             
                  //System.out.println("plan ccccc");
                  String responseLine = null;

                  while ((responseLine = br.readLine()) != null) {
                      
                      respuesta.append(responseLine.trim());
                      
                  }
                  //System.out.println(respuesta.toString());
              }catch(java.net.SocketTimeoutException e){
                    LOGGER.info("Ocurrio  timeout en servicio plan lealtad "+url);
                    String mensaje = "Ocurrio error en servicio plan lealtad "+url;
                    json.addProperty("status", "2");
                    json.addProperty("mensaje", mensaje);
                    String data="0";
                    json.addProperty("data", data);
                   //return response=json.toString();
                   //response 5 es cuando falla el servicio plan lealtad
                    return response="5";
              }catch(IllegalStateException | HibernateException | IOException ex  ){
                   //this.manejarExcepcion(ex, "Ocurrió un error con servicio plan lealtad.", Response.Status.INTERNAL_SERVER_ERROR);
                   LOGGER.info("Ocurrio error en servicio plan lealtad "+url);
                    String mensaje = "Ocurrio error en servicio plan lealtad "+url;
                    json.addProperty("status", "2");
                    json.addProperty("mensaje", mensaje);
                    String data="0";
                    json.addProperty("data", data);
                   //return response=json.toString();
                   //response 5 es cuando falla el servicio plan lealtad
                    return response="5";
                      //int num=500;
                   ///return response=Integer.toString(num);
                   //response="0";
              }
            
             
                //System.out.println("response; "+respuesta);
                JsonParser parser = new JsonParser ();
                JsonElement jsonTree = parser.parse (respuesta.toString());
                jsonTree.isJsonObject ();
                jsonTree.isJsonArray ();
                jsonTree.isJsonNull ();
                jsonTree.isJsonPrimitive ();

                JsonObject jsonObject = jsonTree.getAsJsonObject ();

                JsonElement data = jsonObject.get("data");
                JsonElement meta = jsonObject.get("meta");
                
                JsonParser jParserMeta = new JsonParser();
                JsonElement treeJsonMeta = jParserMeta.parse(meta.toString());
                treeJsonMeta.isJsonObject ();
                treeJsonMeta.isJsonArray ();
                treeJsonMeta.isJsonNull ();
                treeJsonMeta.isJsonPrimitive ();
                JsonObject objectJsonMeta = treeJsonMeta.getAsJsonObject();
                JsonElement mstatus = objectJsonMeta.get("status");
                

                JsonParser jParser = new JsonParser();
                JsonElement treeJson = jParser.parse(data.toString());
                treeJson.isJsonObject ();
                treeJson.isJsonArray ();
                treeJson.isJsonNull ();
                jsonTree.isJsonPrimitive ();
                JsonObject objectJson = treeJson.getAsJsonObject();
                JsonElement datosCliente = objectJson.get("response");


                //String msj="\"No se encontraron registros\"";
                //String msj="\"No Existe Cliente\"";
                String msj="\"ERROR\"";
                if(mstatus.toString().equals(msj)){
                    response="1";
                }else{

                    JsonParser jeisonParser = new JsonParser();
                    JsonElement treeJeison = jeisonParser.parse(datosCliente.toString());
                    treeJeison.isJsonObject ();
                    treeJeison.isJsonArray ();
                    treeJeison.isJsonNull ();
                    treeJeison.isJsonPrimitive ();
                    JsonObject objectJeison = treeJeison.getAsJsonObject();

                    JsonElement id = objectJeison.get("_id");
                    JsonElement desAutenticacion = objectJeison.get("des_autenticacion");
                    JsonElement desCorreo = objectJeison.get("des_correo");
                    JsonElement desEstatus = objectJeison.get("des_estatus");
                    JsonElement desOrigen = objectJeison.get("des_origen");
                    JsonElement desSubestatus = objectJeison.get("des_subestatus");
                    JsonElement desSuborigen = objectJeison.get("des_suborigen");
                    JsonElement fecExpira = objectJeison.get("fec_expira");
                    JsonElement fecRegistro = objectJeison.get("fec_registro");
                    JsonElement iduAutenticacion = objectJeison.get("idu_autenticacion");
                    JsonElement iduEstatus = objectJeison.get("idu_estatus");////Se Necesita
                    JsonElement iduOrigen = objectJeison.get("idu_origen");
                    JsonElement iduPlanlealtad = objectJeison.get("idu_planlealtad");
                    JsonElement iduSubestatus = objectJeison.get("idu_subestatus");//Se Necesita
                    JsonElement iduSuborigen = objectJeison.get("idu_suborigen");
                    JsonElement nomApellidomaterno = objectJeison.get("nom_apellidomaterno");
                    JsonElement nom_Apellidopaterno = objectJeison.get("nom_apellidopaterno");
                    JsonElement nomCliente = objectJeison.get("nom_cliente");
                    JsonElement numCaja = objectJeison.get("num_caja");
                    JsonElement numCelular = objectJeison.get("num_celular");
                    JsonElement numCliente = objectJeison.get("num_cliente");
                    JsonElement numEmpleado = objectJeison.get("num_empleado");
                    JsonElement numTienda = objectJeison.get("num_tienda");
                    JsonElement opcActivo = objectJeison.get("opc_activo");
                    JsonElement opcCelular = objectJeison.get("opc_celular");
                    JsonElement opcClientetitular = objectJeison.get("opc_clientetitular");
                    JsonElement opcCorreo = objectJeison.get("opc_correo");
                    JsonElement updatedAt = objectJeison.get("updatedAt");
                    
                    con.disconnect();   

                    response = iduEstatus.toString()+","+iduSubestatus.toString();
                }  
             }catch(IllegalStateException | HibernateException ex){
                  // this.manejarExcepcion(ex, "Ocurrió un error con servicio plan lealtad 2.", Response.Status.INTERNAL_SERVER_ERROR);
                   LOGGER.info("Ocurrio error en servicio plan lealtad "+url);
                   String mensaje = "Ocurrio error en servicio plan lealtad "+url;
                    json.addProperty("status", "2");
                    json.addProperty("mensaje", mensaje);
                    String data="0";
                    json.addProperty("data", data);
                    //return response=json.toString(); 
                    //response 5 es cuando falla el servicio plan lealtad
                    return response="5";
//int num=500;
                  // return response=Integer.toString(num);
             }    
        }
       
        return response;
    }
    
     public String consultaUrl(
             String clvStatus
    ) throws IOException, SQLException{
        recompensaModel recompensaModel = null; 
        String response = null;
       String claveStatus=null;
       String url=null;
        Gson gson = new Gson();
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <ConfiguracionServicio> configuracionServicio = recompensaModel.consultaUrl(clvStatus);
            if(configuracionServicio.size()>0){
                claveStatus= configuracionServicio.get(0).getClvStatusPL();
                if(claveStatus.equals("1")){
                    response = claveStatus+","+configuracionServicio.get(0).getDesServicio();
                }else{
                    response="0";
                }
            }
            else
            {
                response="0";
            }
            
                       
            //response = gson.toJson(configuracionServicio);
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultaURL.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultarCortes(
        int num_cliente,
        int flag
    ) throws IOException, SQLException, ParseException{
        recompensaModel recompensaModel = null; 
        String response = null;
        int numLogica=0;
        int prcDE =0;
        Date fechaCorte=null;
        Gson gson = new Gson();
        try{
            LocalDate hoy = LocalDate.now();
            int dia;
            dia = hoy.getDayOfMonth();
            LocalDate fechaCorteBuscar = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
            LocalDate fechaCorteActual = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
            LocalDate fecCorte = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
            
            if(dia < 21)
            {
              if(flag==0){
                  fecCorte=fechaCorteBuscar.plusMonths(-1);
              }else if(flag==1){
                  fecCorte=fechaCorteActual.plusMonths(0);
              }else{
                fecCorte=fechaCorteActual.plusMonths(1);
              }
            }else{
                if(flag==0){
                  fecCorte=fechaCorteBuscar.plusMonths(0);
                }else if(flag==1){
                    fecCorte=fechaCorteActual.plusMonths(1);
                }else{
                  fecCorte=fechaCorteActual.plusMonths(1);
                }
            }
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <cortesCumplidos> cortesCumplidos = recompensaModel.consultarCortes(num_cliente,fecCorte);
            Date date = java.sql.Date.valueOf(fecCorte);
            
            if(cortesCumplidos.size() > 0){
                fechaCorte = (Date) cortesCumplidos.get(0).getfecCorteCartera();
                prcDE = cortesCumplidos.get(0).getprcDineroE();
                numLogica = cortesCumplidos.get(0).getiduLogica();
                if(date.equals(fechaCorte)){
                    response = Integer.toString(numLogica)+","
                            +Integer.toString(cortesCumplidos.get(0).getimpAbonosCorte())+","
                            +Integer.toString(cortesCumplidos.get(0).getnumCorte())+","
                            +Integer.toString(prcDE)+","
                            +Integer.toString(cortesCumplidos.get(0).getimpDineroCorte())+","
                            +cortesCumplidos.get(0).getfecAlcanzoMesPuntual()+","
                            +cortesCumplidos.get(0).getfecCorteCartera()+","
                            +Integer.toString(cortesCumplidos.get(0).getKeyx())+","
                            +Integer.toString(cortesCumplidos.get(0).getAbonoBase());
                }else{
                    response = "0";
                }
            }else{      
                response ="0";
            }
        }catch(IOException | IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error Cortes.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
    
   public String registrarCorteCumplido(
        String datosCorte
    )throws IOException,SQLException {
        String response = null;
        recompensaModel recompensaModel = null;
        Set<String> propiedadesEsperadas = new HashSet<>();
        propiedadesEsperadas.add("numCliente");
        propiedadesEsperadas.add("iduLogica");
        propiedadesEsperadas.add("impAbonosCorte");
        propiedadesEsperadas.add("numCorte");
        propiedadesEsperadas.add("prcDineroE");
        propiedadesEsperadas.add("impDineroCorte");
        propiedadesEsperadas.add("fecAlcanzoMesPuntual");
        propiedadesEsperadas.add("fecCorteCartera");
        propiedadesEsperadas.add("abonoBase");
        this.validarCuerpoPeticion(datosCorte, propiedadesEsperadas);
	
        try {
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            cortesCumplidos nuevoCorteCumplido = this.gson.fromJson(datosCorte, cortesCumplidos.class);
            nuevoCorteCumplido = recompensaModel.registrarCorteCumplido(nuevoCorteCumplido);
            response = Integer.toString(nuevoCorteCumplido.getnumCorte());
            //response = gson.toJson(nuevoCorteCumplido);
            
        } catch(ApplicationException ex) {
            throw ex;
        } 
        catch(IllegalStateException | HibernateException ex) {
            this.manejarExcepcion(ex, "Ocurrió un error al guardar el corte que avanza.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
   
   public String registrarCorteAnterior(
        String datosCorte
    )throws IOException,SQLException {
        String response = null;
        recompensaModel recompensaModel = null;
        Set<String> propiedadesEsperadas = new HashSet<>();
        propiedadesEsperadas.add("numCliente");
        propiedadesEsperadas.add("iduLogica");
        propiedadesEsperadas.add("impAbonosCorte");
        propiedadesEsperadas.add("numCorte");
        propiedadesEsperadas.add("prcDineroE");
        propiedadesEsperadas.add("impDineroCorte");
        propiedadesEsperadas.add("fecAlcanzoMesPuntual");
        propiedadesEsperadas.add("fecCorteCartera");
        propiedadesEsperadas.add("abonoBase");
       this.validarCuerpoPeticion(datosCorte, propiedadesEsperadas);

        try {
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            CorteAnterior nuevoCorteCumplido = this.gson.fromJson(datosCorte, CorteAnterior.class);
            nuevoCorteCumplido = recompensaModel.registrarCorteAnterior(nuevoCorteCumplido);
            //response = Integer.toString(nuevoCorteCumplido.getnumCorte());
            response = gson.toJson(nuevoCorteCumplido);
            
        } catch(ApplicationException ex) {
            throw ex;
        } 
        catch(IllegalStateException | HibernateException ex) {
            this.manejarExcepcion(ex, "Ocurrió un error al guardar el corte que avanza.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
    
    public String consultarImporteCorte(
            int numCliente,
            int numLogica,
            int numCorte
    )throws IOException,SQLException {
        recompensaModel recompensaModel = null; 
        String response = null;
        Gson gson = new Gson();

        int sumImporte=0;
        try{
            LocalDate hoy = LocalDate.now();
            int dia;
            int i;
            dia = hoy.getDayOfMonth();
            LocalDate fechaCorteBuscar = hoy.of(hoy.getYear(), hoy.getMonth(), 20);

            //System.out.println("numCorte "+numCorte);
            if(dia < 21)
            {
               fechaCorteBuscar=fechaCorteBuscar.plusMonths(-(numCorte));
               //System.out.println("fechaCorteBuscar "+fechaCorteBuscar);
            }else{
                fechaCorteBuscar=fechaCorteBuscar.plusMonths(-(numCorte-1));
                //System.out.println("fechaCorteBuscar-> "+fechaCorteBuscar);
            }
            
            Date date = java.sql.Date.valueOf(fechaCorteBuscar);
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <cortesCumplidos> cortesCumplidos = recompensaModel.consultarImporteCorte(numCliente,numLogica,date);
            
            if(cortesCumplidos.size()>0)
            {
                for(i=0;i < cortesCumplidos.size();i++){
                    //System.out.println("cortesCumplidos.get(i).getimpDineroCorte()-> "+cortesCumplidos.get(i).getimpDineroCorte());
                    sumImporte+=cortesCumplidos.get(i).getimpAbonosCorte();

                }
                response =Integer.toString(sumImporte);
            }else{
                response =Integer.toString(0);
            }

        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultarImporteCorte.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    
    public String consultarImporteCorteCRTP(
            int numCliente,
            int numLogica,
            int numCorte
    )throws IOException,SQLException {
        recompensaModel recompensaModel = null; 
        String response = null;
        Gson gson = new Gson();

        int sumImporte=0;
        int i;
        try{
            LocalDate hoy = LocalDate.now();
            int dia;
            dia = hoy.getDayOfMonth();
            LocalDate fechaCorteBuscar = hoy.of(hoy.getYear(), hoy.getMonth(), 20);

            //System.out.println("numCorte "+numCorte);
            if(dia < 21)
            {
               fechaCorteBuscar=fechaCorteBuscar.plusMonths(-(numCorte));
              // System.out.println("fechaCorteBuscar "+fechaCorteBuscar);
            }else{
                fechaCorteBuscar=fechaCorteBuscar.plusMonths(-(numCorte-1));
                //System.out.println("fechaCorteBuscar-> "+fechaCorteBuscar);
            }
            
            Date date = java.sql.Date.valueOf(fechaCorteBuscar);
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <cortesCumplidos> cortesCumplidos = recompensaModel.consultarImporteCorte(numCliente,numLogica,date);
            
            if(cortesCumplidos.size()>0)
            {
                for(i=0;i < cortesCumplidos.size();i++){
                    // System.out.println("cortesCumplidos.get(i).getimpDineroCorte()-> "+cortesCumplidos.get(i).getimpDineroCorte());
                    sumImporte+=cortesCumplidos.get(i).getimpDineroCorte();

                }
                response =Integer.toString(sumImporte);
            }else{
                response =Integer.toString(0);
            }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultarImporteCorte.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
        
    public String consultaPuntualidad(
        int idu_logica,
        String clv_puntualidad,
        int numCliente
    )throws IOException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        int puntualidad=0;
        String clvPuntualidad=null;
        String datosLog=null;
        Gson gson = new Gson();
        try{
            
            clv_puntualidad=clv_puntualidad.substring(1,2);

            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <Puntualidades> puntualidades = recompensaModel.consultaPuntualidad(idu_logica,clv_puntualidad);
            puntualidad = puntualidades.size();

            if(puntualidad>0){
                clvPuntualidad = puntualidades.get(0).getClvPuntualidad();
                response=clvPuntualidad;
            }else{
               response = "0";
               return response;
            }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error Puntualidades.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    public String consultarLogicasRecompensa(
            int idu_logica
    )throws IOException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        Gson gson = new Gson();

        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List<LogicasRecompensa> logicasRecompensa = recompensaModel.consultarLogicasRecompensa(idu_logica);

            if(logicasRecompensa.size()>0){ 
                response = Integer.toString(logicasRecompensa.get(0).getNumMeses());
            }
            else{
               response="0";
            }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrio un error LogicasRecompensa", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultarCiudadesRecompensa( 
            int numCiudad
    )throws IOException,SQLDataException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        Gson gson = new Gson();
        int tieneCiudad=0;
        int ciudadVal=0;
        int status=0;
        int prcDE=0;
        int logicaID =0;
        int i;
        try{
            
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <CiudadesRecompensa> ciudadesRecompensa = recompensaModel.consultarCiudadesRecompensa(numCiudad);
            tieneCiudad = ciudadesRecompensa.size();
            if(tieneCiudad>0)
            {
                for(i=0;i < tieneCiudad;i++){
                    ciudadVal = ciudadesRecompensa.get(i).getNumCiudad();
                    status = ciudadesRecompensa.get(i).getClvStatus();
                    if(ciudadVal == numCiudad && status == 1){
                        logicaID=ciudadesRecompensa.get(i).getIduLogica();
                        prcDE = ciudadesRecompensa.get(i).getPrcDinero();
                        response =Integer.toString(logicaID)+","+Integer.toString(prcDE);
                    }
                }
            }    
            //System.out.println("response "+response);
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrio un error CiudadesRecompensa", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultaNacional( 
            String claveNacional,
            String claveStatus
    )throws IOException,SQLDataException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        Gson gson = new Gson();

        try{
            
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <LogicasRecompensa> logicasRecompensa = recompensaModel.consultaNacional(claveNacional,claveStatus);

            if(logicasRecompensa.size()>0){
                response = logicasRecompensa.get(0).getidu_logica()+","+logicasRecompensa.get(0).getprcDinero();
            }else{
                response="0";    
            }
            
            //response = gson.toJson(logicasRecompensa);
            
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrio un error en consultaNacional", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultarCarterasParticipantes( 
            String clvMovimiento
    )throws IOException,SQLDataException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        Gson gson = new Gson();
        try{
            
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List<CarterasParticipantes> CarterasParticipantes = recompensaModel.consultarCarterasParticipantes(clvMovimiento);
            
            response = gson.toJson(CarterasParticipantes);
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrio un error CarterasRecompensa", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultarCarterasRecompensa( 
            int idu_logica,
            int numTipoCuenta,
            int numCliente
    )throws IOException,SQLDataException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        Gson gson = new Gson();
        String clvMovimiento=null;
        String numMovimiento=null;
        String datosLog = null;
        LocalDate hoy = LocalDate.now(); 
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List<CarterasRecompensa> carterasRecompensa = recompensaModel.consultarCarterasRecompensa(idu_logica,numTipoCuenta);

            if(carterasRecompensa.size()>0){
                clvMovimiento=carterasRecompensa.get(0).getClvMovimiento();
                numMovimiento=carterasRecompensa.get(0).getNumMovimiento();
                response=clvMovimiento+","+numMovimiento;
            }else{
               JsonObject json = new JsonObject();
               String mensaje = "El movimiento "+numTipoCuenta+" no participa en el programa";
               json.addProperty("mensaje", mensaje);
               json.addProperty("numCliente", numCliente);
               json.addProperty("iduLogica", idu_logica);
               datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+idu_logica+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
               this.registrarLog(datosLog);
               response = "0";
            }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrio un error CarterasRecompensa", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    public String consultarMovAbonosPuntuales(
        int numeroCliente
    )throws IOException,SQLException{
        recompensaModel recompensaModel=null;
        String response = null;
        String fecha=null;
        Gson gson = new Gson();
        int importeAbono=0;
        LocalDate hoy = LocalDate.now(); 
        int dia;
        dia = hoy.getDayOfMonth();
        LocalDate fechaCorteCartera = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
        if(dia < 21)
        {
          //fechaCorteCartera=fechaCorteCartera.plusMonths(-1);
          fechaCorteCartera=fechaCorteCartera.plusMonths(0);
        }else{
          fechaCorteCartera=fechaCorteCartera.plusMonths(1);
        }
        
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <MovAbonosPuntuales> movAbonosPuntuales = recompensaModel.consultarMovAbonosPuntuales(numeroCliente,fechaCorteCartera);
            if(movAbonosPuntuales.size()>0){
                for(int i=0; i<movAbonosPuntuales.size();i++){
                    fecha=movAbonosPuntuales.get(i).getFecCorteCartera().toString();
                    fecha=fecha.substring(0, 10);

                    if(fechaCorteCartera.toString().equals(fecha)){

                        importeAbono = importeAbono+ movAbonosPuntuales.get(i).getImpAbono();
                    }
                    response = Integer.toString(importeAbono);
                }
            }else{
                response="0";
            }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "MovAbonosPuntuales: "+ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultarMovAdelantado(
        int numeroCliente
    )throws IOException,SQLException{
        recompensaModel recompensaModel=null;
        String response = null;
        String fecha=null;
        Gson gson = new Gson();
        int importeAbono=0;
        LocalDate hoy = LocalDate.now(); 
        int dia;
        dia = hoy.getDayOfMonth();
        LocalDate fechaCorteCartera = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
        if(dia < 21)
        {
          //fechaCorteCartera=fechaCorteCartera.plusMonths(-1);
          fechaCorteCartera=fechaCorteCartera.plusMonths(0);
        }else{
          fechaCorteCartera=fechaCorteCartera.plusMonths(1);
        }
        System.out.println("fechaCorteCartera "+fechaCorteCartera);
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <MovAbonosPuntuales> movAbonosPuntuales = recompensaModel.consultarMovAdelantado(numeroCliente,fechaCorteCartera);

            if(movAbonosPuntuales.size()>0){

                    response = movAbonosPuntuales.get(0).getKeyx()+","+movAbonosPuntuales.get(0).getNumCliente()+","
                           +movAbonosPuntuales.get(0).getNumTienda()+","+movAbonosPuntuales.get(0).getImpAbono()+","
                            +movAbonosPuntuales.get(0).getNumTicket()+","+movAbonosPuntuales.get(0).getClvMovimiento()+","
                            +movAbonosPuntuales.get(0).getNumMovimiento()+","+movAbonosPuntuales.get(0).getFecAbono()+","
                            +movAbonosPuntuales.get(0).getFecCorteCartera()+","+movAbonosPuntuales.get(0).getFolioAbono();
            }else{
                response="0";
            }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "MovAbonosPuntuales: "+ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String sumMovAdelantado(
        int numeroCliente,
        String fechaCorteCartera
    )throws IOException,SQLException{
        recompensaModel recompensaModel=null;
        String response = null;
        Gson gson = new Gson();
        int importeAbono=0;
        //fechaCorteCartera=fechaCorteCartera.substring(0,10);
        LocalDate dateUC = LocalDate.parse(fechaCorteCartera);

        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <MovAbonosPuntuales> movAbonosPuntuales = recompensaModel.sumMovAdelantado(numeroCliente,dateUC);

            if(movAbonosPuntuales.size()>0){
                for(int i=0;i<movAbonosPuntuales.size();i++){

                    importeAbono=importeAbono+movAbonosPuntuales.get(i).getImpAbono();
                }

                response = String.valueOf(importeAbono);
                //response="1";
            }else{
                response="0";
            }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "MovAbonosPuntuales: "+ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultarImpAbonosPuntuales(
            int numCliente,
            int numCorte
    )throws IOException,SQLException {
        recompensaModel recompensaModel = null; 
        String response = null;
        Gson gson = new Gson();

        int sumImporte=0;
        try{
            LocalDate hoy = LocalDate.now();
            int dia;
            int i;
            dia = hoy.getDayOfMonth();
            LocalDate fechaCorteBuscar = hoy.of(hoy.getYear(), hoy.getMonth(), 20);

            if(dia < 21)
            {
               fechaCorteBuscar=fechaCorteBuscar.plusMonths(-(numCorte));
            }else{
                fechaCorteBuscar=fechaCorteBuscar.plusMonths(-(numCorte-1));
            }
            
            //consultarImporteCorte
            Date date = java.sql.Date.valueOf(fechaCorteBuscar);

            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <MovAbonosPuntuales> movAbonosPuntuales = recompensaModel.consultarImpAbonosPuntuales(numCliente,date);
            
            if( movAbonosPuntuales.size()>0)
            {    
                for(i=0;i < movAbonosPuntuales.size();i++){

                    sumImporte+=movAbonosPuntuales.get(i).getImpAbono();

                }
                response =Integer.toString(sumImporte);
            }else{
                response =Integer.toString(0);
            }    
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultarImporteCorte.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    public String consultarImpAbonosRegistrados(
            int numCliente,
            int numLogica,
            int numCorte
    )throws IOException,SQLException {
        recompensaModel recompensaModel = null; 
        String response = null;
        Gson gson = new Gson();

        int sumImporte=0;
        try{
            LocalDate hoy = LocalDate.now();
            int dia;
            int i;
            dia = hoy.getDayOfMonth();
            LocalDate fechaCorteBuscar = hoy.of(hoy.getYear(), hoy.getMonth(), 20);

            if(dia < 21)
            {
               fechaCorteBuscar=fechaCorteBuscar.plusMonths(-(numCorte));
            }else{
                fechaCorteBuscar=fechaCorteBuscar.plusMonths(-(numCorte-1));
            }
            
            Date date = java.sql.Date.valueOf(fechaCorteBuscar);

            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <cortesCumplidos> cortesCumplidos = recompensaModel.consultarImpAbonosRegistrados(numCliente,numLogica,date);
            
            if(cortesCumplidos.size()>0)
            {    
                for(i=0;i < cortesCumplidos.size();i++){

                    sumImporte+=cortesCumplidos.get(i).getimpAbonosCorte();

                }
                response =Integer.toString(sumImporte);
            }else{
                 response =Integer.toString(0);
            }    
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultarImporteCorte.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
   public String registrarMovAbonoPuntual(
        String datosCliente
    )throws IOException,SQLException {
        
       
       String response = null;
        
        recompensaModel recompensaModel = null;
        Set<String> propiedadesEsperadas = new HashSet<>();
        propiedadesEsperadas.add("numCliente");
        propiedadesEsperadas.add("numTienda");
        propiedadesEsperadas.add("impAbono");
        propiedadesEsperadas.add("numTicket");
        propiedadesEsperadas.add("clvMovimiento");
        propiedadesEsperadas.add("numMovimiento");
        propiedadesEsperadas.add("fecAbono");
        propiedadesEsperadas.add("fecCorteCartera");
        propiedadesEsperadas.add("folio");
        this.validarCuerpoPeticion(datosCliente, propiedadesEsperadas);
	
        try {
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            MovAbonosPuntuales nuevoAbonoPuntual = this.gson.fromJson(datosCliente, MovAbonosPuntuales.class);
            nuevoAbonoPuntual = recompensaModel.registrarMovAbonoPuntual(nuevoAbonoPuntual);
            response = Integer.toString(nuevoAbonoPuntual.getKeyx());
            
        } catch(ApplicationException ex) {
            throw ex;
        } 
        catch(IllegalStateException | HibernateException ex) {
            this.manejarExcepcion(ex, "Ocurrió un error al guardar abono puntual.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
    
    public String consultarMovDineroElectronico(
       int numCliente
    )throws IOException,SQLException{
        recompensaModel recompensaModel = null;
        String response= null;
        Gson gson = new Gson();
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <MovDineroElectronicoRecompensa> movDineroElectronico = recompensaModel.consultarMovDineroElectronico(numCliente);
            response = gson.toJson(movDineroElectronico);
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrio un error MovDineroElectronicoRecompensa", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String registrarMovDineroElectronico(
        String datosDE
    )throws IOException,SQLException {
        String response = null;
        
        recompensaModel recompensaModel = null;
        String mensaje=null;
        Set<String> propiedadesEsperadas = new HashSet<>();
        propiedadesEsperadas.add("numCliente");
        propiedadesEsperadas.add("impDineroElectronico");
        propiedadesEsperadas.add("iduLogica");
        propiedadesEsperadas.add("fecGeneracion");
        propiedadesEsperadas.add("numFolioabono");
        this.validarCuerpoPeticion(datosDE, propiedadesEsperadas);
	JsonObject json = new JsonObject();
        try {
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            MovDineroElectronicoRecompensa nuevoMovDE = this.gson.fromJson(datosDE, MovDineroElectronicoRecompensa.class);
            nuevoMovDE = recompensaModel.registrarMovDineroElectronico(nuevoMovDE);
            
            mensaje="Se registro movimiento de dinero electronico";
            json.addProperty("status", 0);
            json.addProperty("mensaje", mensaje);
            //response = gson.toJson(nuevoMovDE);
            json.addProperty("data", gson.toJson(nuevoMovDE));
            
            //response=json.toString();
            
            response= Integer.toString(nuevoMovDE.getImpDineroElectronico());
            //return json.toString();
            return response;
            
        } catch(ApplicationException ex) {
            throw ex;
        } 
        catch(IllegalStateException | HibernateException ex) {
            this.manejarExcepcion(ex, "Ocurrió un error al guardar dinero electronico.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
    
    public String registrarLog(
        String datosLog
    )throws IOException,SQLException {
        String response = null;
       
        recompensaModel recompensaModel = null;
        Set<String> propiedadesEsperadas = new HashSet<>();
        propiedadesEsperadas.add("msj");
        propiedadesEsperadas.add("numeroCliente");
        propiedadesEsperadas.add("iduLogica");
        propiedadesEsperadas.add("fechaReg");
        this.validarCuerpoPeticion(datosLog, propiedadesEsperadas);
        try {
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            //gson.toJson(datosLog);
            LogCRTP nuevoRegLog = this.gson.fromJson(datosLog, LogCRTP.class);
            
            nuevoRegLog = recompensaModel.registrarLog(nuevoRegLog);
            response = gson.toJson(nuevoRegLog);
            
        } catch(ApplicationException ex) {
            throw ex;
        } 
        catch(IllegalStateException | HibernateException ex) {
            this.manejarExcepcion(ex, "Ocurrió un error al registrar LOG.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
    
    public String consultarCorteCliente(
       int numCliente
    )throws IOException,SQLException, ParseException{
                            
        System.out.println("Inicia la consulta por cliente");
        LOGGER.info("Inicia la consulta por cliente");
        
        recompensaModel recompensaModel = null;
        String response= null;
        Gson gson = new Gson();
        String mensaje=null;
        String datosLog=null;
        String validaSumImporte=null;
        JsonObject json = new JsonObject();
        JsonObject jsonDatos = new JsonObject();
        String validaNumMesesCorte=null;
        String validaCorte=null;
        int flag=0;
        //System.out.println("numCliente "+numCliente);
        try{
            LocalDate hoy = LocalDate.now();
            int dia;
            dia = hoy.getDayOfMonth();
            //dia=22;
            LocalDate fechaCorteBuscar = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
            LocalDate fechaCorteActual = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
            LocalDate fechaCorteMesSiguiente = hoy.of(hoy.getYear(), hoy.getMonth(), 20);
           
            if(dia < 21)
            {
              fechaCorteBuscar=fechaCorteBuscar.plusMonths(-1);
              fechaCorteActual=fechaCorteActual.plusMonths(0);
              fechaCorteMesSiguiente=fechaCorteMesSiguiente.plusMonths(1);
            }else{
                fechaCorteActual=fechaCorteActual.plusMonths(1);
            }         
            
            if(numCliente>0){
                recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
                List <cortesCumplidos> cortesCumplidos;
                cortesCumplidos = recompensaModel.consultarCorteCliente(numCliente,fechaCorteActual);
                //System.out.println("si: "+cortesCumplidos.get(0).getNumCliente());

                if(cortesCumplidos.size()>0){
                    flag=1;
                }else{
                    cortesCumplidos = recompensaModel.consultarCorteCliente(numCliente,fechaCorteBuscar);
                    if(cortesCumplidos.size()>0){
                        
                        flag=1;
                    }else{
                        
                        flag=0;
                    }
                }
                
                //Date date = java.sql.Date.valueOf(fechaCorteBuscar);
                //if(cortesCumplidos.size()>0){
                if(flag>0){

                    mensaje="Informacion del Cliente encontrada";

                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+0+"}";

                    json.addProperty("status", 0);
                    json.addProperty("mensaje", mensaje);

                    jsonDatos.addProperty("cliente", cortesCumplidos.get(0).getNumCliente()); 
                    
                    validaSumImporte=this.consultarImporteCorteCRTP(numCliente, cortesCumplidos.get(0).getiduLogica() , cortesCumplidos.get(0).getnumCorte());

                    validaNumMesesCorte=this.consultarLogicasRecompensa(cortesCumplidos.get(0).getiduLogica());
                    
                    jsonDatos.addProperty("corteActual", cortesCumplidos.get(0).getnumCorte());
                    jsonDatos.addProperty("cortesLogica", Integer.parseInt(validaNumMesesCorte));
                    jsonDatos.addProperty("dineroacumulado", validaSumImporte);
                    jsonDatos.addProperty("porcentaje", cortesCumplidos.get(0).getprcDineroE());
                    jsonDatos.addProperty("numlogica", cortesCumplidos.get(0).getiduLogica());
                    jsonDatos.addProperty("fecCorteCartera", cortesCumplidos.get(0).getfecCorteCartera().toString()); 

                    json.addProperty("data",jsonDatos.toString());

                    response = json.toString();
                }else{
                   json.addProperty("status", 1);
                   mensaje ="No se encontro Cliente con un Corte";
                   json.addProperty("mensaje", mensaje);
                   datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                   json.addProperty("data", datosLog);
                   //this.registrarLog(datosLog);
                   response = json.toString();
                }
            }
            else{
                   json.addProperty("status", 1);
                   mensaje ="No es un Cliente Valido";
                   json.addProperty("mensaje", mensaje);
                   datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                   json.addProperty("data", datosLog);
                   //this.registrarLog(datosLog);
                   response = json.toString();
                }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrio un error al consultar el corte del cliente", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String cancelarAbono(
        int numCliente,
        int folioabono
    ) throws IOException, SQLException, ParseException {
        System.out.println("Inicia la cancelacion de abono");
        LOGGER.info("Inicia la cancelacion de abono");
        
        recompensaModel recompensaModel = null;
        
        int impDe=0;
        int iduLogica=0;
        
        JsonObject json = new JsonObject();
        
        String response = null;
        String validaMovAbonos=null;
        String validaMovAbono=null;
        String totalMovAbono=null;
        String validaCorteCliente=null;
        String validaCorteAnterior=null;
        String validaDineroE=null;
        String validaSumAbono=null;
        String eliminaDE=null;
        String eliminaCorte=null;
        String eliminaCorteAnt=null;
        String eliminaAbono=null;
        String mensaje=null;
        String datosLog=null;
        String datosCliente=null;
        String datosCorte=null;
        String datosMov=null;
        String data=null;
        String partValidaMovAbono[]=null;
        String partMovDe[]=null;
        String iduMov=null;
        String fechaCorte=null;
        String resRegistroCorte=null;
        String[] partCorteAnt=null;

        try {
            LocalDate hoy = LocalDate.now(); 
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            if(numCliente>0){
                
                
                validaMovAbonos = this.consultarMovAbonos(numCliente,folioabono,0);
                //System.out.println("validaMovAbono: "+validaMovAbonos);
                if(validaMovAbonos.equals("0")){
                    json.addProperty("status", 1);
                    mensaje ="No se encontro abono del cliente "+numCliente+" con ningun movimiento de abono";
                    json.addProperty("mensaje", mensaje);
                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                    data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                    
                    LOGGER.info("No se encontro abono del cliente "+numCliente+" con ningun movimiento de abono");
                    LOGGER.info(data);
                    json.addProperty("data", data);
                    this.registrarLog(datosLog);
                    response = json.toString();
                    return response;
                }else{
                    partValidaMovAbono = validaMovAbonos.toString().split(",");
                    validaMovAbono = partValidaMovAbono[0].toString();
                    totalMovAbono = partValidaMovAbono[1].toString();
                    fechaCorte = partValidaMovAbono[2].toString();
                    fechaCorte=fechaCorte.substring(0,10);
                    
                }
                if(Integer.parseInt(validaMovAbono)>0){
                   
                    validaCorteCliente=this.consultaCorte(numCliente,fechaCorte);

                    validaCorteAnterior=this.consultaCorteAnterior(numCliente, fechaCorte);
                    
                    if(Integer.parseInt(validaCorteCliente)>0){
                        
                        validaDineroE=this.consultaDineroElectronico(numCliente);
                        //if(Integer.parseInt(validaDineroE)>0){
                        if(validaDineroE.length()>1){    
                           partMovDe = validaDineroE.toString().split(",");
                            iduMov = partMovDe[0].toString();
                            impDe = Integer.parseInt(partMovDe[1].toString());
                            iduLogica = Integer.parseInt(partMovDe[2].toString());
                            //Elimino en la tabla mov_dineroelectronicorecompensa
                            //datosCliente="{\"idu_movimiento\":"+Integer.parseInt(validaDineroE)+",\"numCliente\":"+numCliente+",\"fecGeneracion\":"+hoy+"}";
                            datosCliente="{\"idu_movimiento\":"+Integer.parseInt(iduMov)+",\"numCliente\":"+numCliente+",\"fecGeneracion\":"+hoy+"}";
                            eliminaDE=this.eliminarDineroE(datosCliente);
                           
                            //Elimino en la ctl_cortescumplidos
                            //datosCorte="{\"keyx\":"+Integer.parseInt(validaCorteCliente)+",\"numCliente\":"+numCliente+",\"fecAlcanzoMesPuntual\":"+hoy+"}";
                            datosCorte="{\"keyx\":"+Integer.parseInt(validaCorteCliente)+",\"numCliente\":"+numCliente+",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+",\"fecCorteCartera\":\""+fechaCorte+"\""+"}";
                            
                            eliminaCorte=this.eliminarCorte(datosCorte);
                         
                            
                            if(validaCorteAnterior.length()>1){
                                partCorteAnt = validaCorteAnterior.toString().split(",");
                                 
                               /* datosCorte = "{\"numCliente\":"+Integer.parseInt(partCorteAnt[1].toString())+",\"iduLogica\":"+Integer.parseInt(partCorteAnt[2].toString())+
                                    ",\"impAbonosCorte\":"+(Integer.parseInt(validaSumAbono)+(Integer.parseInt(partCorteAnt[3].toString())))+",\"numCorte\":"+Integer.parseInt(partCorteAnt[4].toString())+
                                   ",\"prcDineroE\":"+Integer.parseInt(partCorteAnt[5].toString())+
                                    ",\"impDineroCorte\":"+Integer.parseInt(partCorteAnt[6].toString())+
                                     ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                      ",\"fecCorteCartera\":\""+fechaCorte+"\""+
                                      ",\"abonoBase\":"+Integer.parseInt(partCorteAnt[9].toString())+"}";

                               resRegistroCorte= this.registrarCorteCumplido(datosCorte);*/
                               
                                datosCorte="";
                                datosCorte="{\"keyx\":"+Integer.parseInt(partCorteAnt[0].toString())+",\"numCliente\":"+numCliente+",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+",\"fecCorteCartera\":\""+fechaCorte+"\""+"}";
                                eliminaCorteAnt=this.eliminarCorteAnt(datosCorte);
                            }

                            //Elimino en la mov_abonospuntuales
                            if(Integer.parseInt(totalMovAbono)>0){
                                for(int i=0;i<Integer.parseInt(totalMovAbono);i++){
                                    validaMovAbonos = this.consultarMovAbonos(numCliente,folioabono,1);
                                    
                                    datosMov="{\"keyx\":"+Integer.parseInt(validaMovAbonos)+",\"numCliente\":"+numCliente+",\"fecAbono\":"+hoy+"}";
                                    eliminaAbono=this.eliminarAbono(datosMov);
                                }
                                validaSumAbono = sumMovAdelantado(numCliente, fechaCorte);

                                if(validaCorteAnterior.length()>1){
                                     datosCorte = "{\"numCliente\":"+Integer.parseInt(partCorteAnt[1].toString())+",\"iduLogica\":"+Integer.parseInt(partCorteAnt[2].toString())+
                                        ",\"impAbonosCorte\":"+Integer.parseInt(validaSumAbono)+",\"numCorte\":"+Integer.parseInt(partCorteAnt[4].toString())+
                                       ",\"prcDineroE\":"+Integer.parseInt(partCorteAnt[5].toString())+
                                        ",\"impDineroCorte\":"+Integer.parseInt(partCorteAnt[6].toString())+
                                         ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                          ",\"fecCorteCartera\":\""+fechaCorte+"\""+
                                          ",\"abonoBase\":"+Integer.parseInt(partCorteAnt[9].toString())+"}";

                                   resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                }
                                
                            }
                           
                            
                            if(eliminaDE.length()>0 && eliminaCorte.length()>0 && eliminaAbono.length()>0){
                                json.addProperty("status", 0);
                                mensaje ="Se cancelo la asignacion de dinero electronico al cliente "+numCliente;
                                json.addProperty("mensaje", mensaje);
                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                
                                data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                                LOGGER.info("Se cancelo la asignacion de dinero electronico al cliente "+numCliente);
                                LOGGER.info(data);
                                //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                                json.addProperty("data", data);
                                this.registrarLog(datosLog);
                                response = json.toString();
                            }else if(eliminaDE.equals("0") && eliminaCorte.length()>0 && eliminaAbono.length()>0){
                                json.addProperty("status", 0);
                                mensaje ="Se cancelo el nuevo corte del cliente "+numCliente;
                                json.addProperty("mensaje", mensaje);
                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                                data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                                json.addProperty("data", data);
                                LOGGER.info("Se cancelo el nuevo corte del cliente "+numCliente);
                                LOGGER.info(data);
                                
                                this.registrarLog(datosLog);
                                response = json.toString(); 
                            }else if(eliminaDE.equals("0") && eliminaCorte.equals("0") && eliminaAbono.length()>0){
                                json.addProperty("status", 0);
                                mensaje ="Se cancelo el movimiento de abono que realizo el cliente "+numCliente;
                                json.addProperty("mensaje", mensaje);
                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                                data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                                json.addProperty("data", data);
                                this.registrarLog(datosLog);
                                LOGGER.info("Se cancelo el movimiento de abono que realizo el cliente "+numCliente);
                                LOGGER.info(data);
                                
                                response = json.toString();
                            }else{
                                json.addProperty("status", 1);
                                mensaje ="No se encontro abono del cliente "+numCliente+" con ningun movimiento de abono";
                                json.addProperty("mensaje", mensaje);
                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                                data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                                json.addProperty("data", data);
                                this.registrarLog(datosLog);
                                LOGGER.info("No se encontro abono del cliente "+numCliente+" con ningun movimiento de abono");
                                LOGGER.info(data);
                                response = json.toString();
                            }  
                        }else{
                            
                            //datosCorte="{\"keyx\":"+Integer.parseInt(validaCorteCliente)+",\"numCliente\":"+numCliente+",\"fecAlcanzoMesPuntual\":"+hoy+"}";
                            datosCorte="{\"keyx\":"+Integer.parseInt(validaCorteCliente)+",\"numCliente\":"+numCliente+",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+",\"fecCorteCartera\":\""+fechaCorte+"\""+"}";
                            
                            eliminaCorte=this.eliminarCorte(datosCorte);
                           
                            if(validaCorteAnterior.length()>1){
                                partCorteAnt = validaCorteAnterior.toString().split(",");
                                //validaSumAbono = sumMovAdelantado(numCliente, fechaCorte);

                                /*datosCorte = "{\"numCliente\":"+Integer.parseInt(partCorteAnt[1].toString())+",\"iduLogica\":"+Integer.parseInt(partCorteAnt[2].toString())+
                                    ",\"impAbonosCorte\":"+(Integer.parseInt(validaSumAbono)+(Integer.parseInt(partCorteAnt[3].toString())))+",\"numCorte\":"+Integer.parseInt(partCorteAnt[4].toString())+
                                   ",\"prcDineroE\":"+Integer.parseInt(partCorteAnt[5].toString())+
                                    ",\"impDineroCorte\":"+Integer.parseInt(partCorteAnt[6].toString())+
                                     ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                      ",\"fecCorteCartera\":\""+fechaCorte+"\""+
                                      ",\"abonoBase\":"+Integer.parseInt(partCorteAnt[9].toString())+"}";

                               resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                               */
                                datosCorte="";
                                datosCorte="{\"keyx\":"+Integer.parseInt(partCorteAnt[0].toString())+",\"numCliente\":"+numCliente+",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+",\"fecCorteCartera\":\""+fechaCorte+"\""+"}";
                                eliminaCorteAnt=this.eliminarCorteAnt(datosCorte);
                            }

                            //Elimino en la mov_abonospuntuales
                            /*datosMov="{\"keyx\":"+Integer.parseInt(validaMovAbono)+",\"numCliente\":"+numCliente+",\"fecAbono\":"+hoy+"}";
                            eliminaAbono=this.eliminarAbono(datosMov);*/
                            if(Integer.parseInt(totalMovAbono)>0){
                                for(int i=0;i<Integer.parseInt(totalMovAbono);i++){
                                    validaMovAbonos = this.consultarMovAbonos(numCliente,folioabono,1);
                                    
                                    datosMov="{\"keyx\":"+Integer.parseInt(validaMovAbonos)+",\"numCliente\":"+numCliente+",\"fecAbono\":"+hoy+"}";
                                    eliminaAbono=this.eliminarAbono(datosMov);
                                }
                                
                                validaSumAbono = sumMovAdelantado(numCliente, fechaCorte);
                                if(validaCorteAnterior.length()>1){
                                     datosCorte = "{\"numCliente\":"+Integer.parseInt(partCorteAnt[1].toString())+",\"iduLogica\":"+Integer.parseInt(partCorteAnt[2].toString())+
                                        ",\"impAbonosCorte\":"+Integer.parseInt(validaSumAbono)+",\"numCorte\":"+Integer.parseInt(partCorteAnt[4].toString())+
                                       ",\"prcDineroE\":"+Integer.parseInt(partCorteAnt[5].toString())+
                                        ",\"impDineroCorte\":"+Integer.parseInt(partCorteAnt[6].toString())+
                                         ",\"fecAlcanzoMesPuntual\":\""+hoy+"\""+
                                          ",\"fecCorteCartera\":\""+fechaCorte+"\""+
                                          ",\"abonoBase\":"+Integer.parseInt(partCorteAnt[9].toString())+"}";

                                   resRegistroCorte= this.registrarCorteCumplido(datosCorte);
                                }
                                
                            }
                            
                            if(eliminaCorte.length()>0 && eliminaAbono.length()>0){
                                json.addProperty("status", 0);
                                mensaje ="Se cancelo el abono y nuevo corte del cliente "+numCliente;
                                json.addProperty("mensaje", mensaje);
                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                                data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                                json.addProperty("data", data);
                                LOGGER.info("Se cancelo el abono y nuevo corte del cliente "+numCliente);
                                LOGGER.info(data);
                                
                                this.registrarLog(datosLog);
                                response = json.toString(); 
                            }else if(eliminaCorte.equals("0") && eliminaAbono.length()>0){
                                json.addProperty("status", 0);
                                mensaje ="Se cancelo el movimiento de abono que realizo el cliente "+numCliente;
                                json.addProperty("mensaje", mensaje);
                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                                data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                                json.addProperty("data", data);
                                
                                LOGGER.info("Se cancelo el movimiento de abono que realizo el cliente "+numCliente);
                                LOGGER.info(data);
                                this.registrarLog(datosLog);
                                response = json.toString();
                            }else{
                                json.addProperty("status", 1);
                                mensaje ="No se encontro abono del cliente "+numCliente+" con ningun movimiento de abono";
                                json.addProperty("mensaje", mensaje);
                                datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                                //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                                data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                                json.addProperty("data", data);
                                this.registrarLog(datosLog);
                                LOGGER.info("No se encontro abono del cliente "+numCliente+" con ningun movimiento de abono");
                                LOGGER.info(data);
                                response = json.toString();
                            }

                        }
                    }else{
                        //Elimino en la mov_abonospuntuales
                        /*datosMov="{\"keyx\":"+Integer.parseInt(validaMovAbono)+",\"numCliente\":"+numCliente+",\"fecAbono\":"+hoy+"}";
                        eliminaAbono=this.eliminarAbono(datosMov);*/
                        if(Integer.parseInt(totalMovAbono)>0){
                            for(int i=0;i<Integer.parseInt(totalMovAbono);i++){
                                validaMovAbonos = this.consultarMovAbonos(numCliente,folioabono,1);
                                //System.out.println("validaMovAbonos");
                                datosMov="{\"keyx\":"+Integer.parseInt(validaMovAbonos)+",\"numCliente\":"+numCliente+",\"fecAbono\":\""+hoy+"\""+"}";
                                eliminaAbono=this.eliminarAbono(datosMov);
                            }
                        }
                            
                        if(eliminaAbono.length()>0){
                            json.addProperty("status", 0);
                            mensaje ="Se cancelo el movimiento de abono que realizo el cliente "+numCliente;
                            json.addProperty("mensaje", mensaje);
                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                            //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                            data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                            json.addProperty("data", data);
                            this.registrarLog(datosLog);
                            LOGGER.info("Se cancelo el movimiento de abono que realizo el cliente "+numCliente);
                            LOGGER.info(data);
                            response = json.toString();
                        }else{
                            json.addProperty("status", 1);
                            mensaje ="No se encontro abono del cliente "+numCliente;
                            json.addProperty("mensaje", mensaje);
                            datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                            //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                            data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                            json.addProperty("data", data);
                            LOGGER.info("No se encontro abono del cliente "+numCliente);
                            LOGGER.info(data);
                            this.registrarLog(datosLog);
                            response = json.toString();
                        }
                    }
                }else{
                    json.addProperty("status", 1);
                    mensaje ="EL cliente no tiene ningun abono en el dia";
                    json.addProperty("mensaje", mensaje);
                    datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
                   //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
                   data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
                    json.addProperty("data", data);
                    LOGGER.info("EL cliente no tiene ningun abono en el dia");
                    LOGGER.info(data);
                    this.registrarLog(datosLog);
                    response = json.toString();
                }
            }else{
               json.addProperty("status", 1);
               mensaje ="EL cliente no es valido";
               json.addProperty("mensaje", mensaje);
               datosLog="{\"msj\":\""+mensaje+"\""+",\"iduLogica\":"+0+",\"numeroCliente\":"+numCliente+",\"fechaReg\":"+hoy+"}";
               //data="{\"numeroCliente\":"+numCliente+",\"fechaReg\":\""+hoy+"\""+"}";
               data="{\"numeroCliente\":"+numCliente+",\"iduLogica\":"+iduLogica+",\"dinerocancelar\":"+impDe+",\"fechaReg\":\""+hoy+"\""+"}";
               json.addProperty("data", data);
               LOGGER.info("EL cliente no es valido");
               LOGGER.info(data);
               this.registrarLog(datosLog);
               response = json.toString();
            }
        } catch(ArrayIndexOutOfBoundsException | HibernateException | IllegalStateException ex) {
            this.manejarExcepcion(ex, "Ocurrio un error al consultar el corte del cliente", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public String consultarMovAbonos(
        int numCliente,
        int folioabono,
        int flag
    )throws IOException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        //System.out.println("entro");
        Gson gson = new Gson();
        LocalDate fechaActual = LocalDate.now();
        LocalDate hoy = LocalDate.now();
        LocalDate fecCorte = hoy.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth());
        //System.out.println("fecCorte "+fecCorte);
        //fecCorte=fechaCorteBuscar;
        if(flag==5){
           fechaActual=fecCorte.plusDays(-1);
           //System.out.println("fechaActual "+fechaActual);
        }
       // System.out.println("fechaActual "+fechaActual);
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <MovAbonosPuntuales> movAbonosPuntuales = recompensaModel.consultarMovAbonos(numCliente,folioabono,fechaActual);
            //System.out.println("movAbonosPuntuales.size(): "+movAbonosPuntuales.size());
            if(movAbonosPuntuales.size()>0){
                if(flag==0){
                    response= Integer.toString(movAbonosPuntuales.get(0).getKeyx())+","+movAbonosPuntuales.size()+","+movAbonosPuntuales.get(0).getFecCorteCartera();
                }else if(flag==5){
                    response= Integer.toString(movAbonosPuntuales.get(0).getKeyx())+","+movAbonosPuntuales.size()+","+movAbonosPuntuales.get(0).getFecCorteCartera()+","+movAbonosPuntuales.get(0).getFolioAbono()+","+movAbonosPuntuales.get(0).getFecAbono();
                }
                else{
                    response= Integer.toString(movAbonosPuntuales.get(0).getKeyx());
                }
            }else{
                response="0";
            }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultarMovAbonos.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    
    public String consultaCorte(
        int numCliente,
        String fechaCorte
    )throws IOException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        Gson gson = new Gson();
        LocalDate fechaActual = LocalDate.now();

        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <cortesCumplidos> cortesCumplidos = recompensaModel.consultaCorte(numCliente,fechaActual,fechaCorte);
            if(cortesCumplidos.size()>0){
                response=Integer.toString(cortesCumplidos.get(0).getKeyx());
            }else{
                response="0";
            }
            //response = gson.toJson(cortesCumplidos);
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultaCorte.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    public String consultaCorteAnterior(
        int numCliente,
        String fechaCorte
    )throws IOException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        Gson gson = new Gson();
        LocalDate fechaActual = LocalDate.now();
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <CorteAnterior> cortesCumplidos = recompensaModel.consultaCorteAnterior(numCliente,fechaActual,fechaCorte);
            if(cortesCumplidos.size()>0){
                response=cortesCumplidos.get(0).getKeyx()+","+cortesCumplidos.get(0).getNumCliente()+","
                        +cortesCumplidos.get(0).getiduLogica()+","+cortesCumplidos.get(0).getimpAbonosCorte()+","
                        +cortesCumplidos.get(0).getnumCorte()+","+cortesCumplidos.get(0).getprcDineroE()+","
                        +cortesCumplidos.get(0).getimpDineroCorte()+","+cortesCumplidos.get(0).getfecAlcanzoMesPuntual()+","
                        +cortesCumplidos.get(0).getfecCorteCartera()+","+cortesCumplidos.get(0).getAbonoBase();
            }else{
                response="0";
            }
            //response = gson.toJson(cortesCumplidos);
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultaCorteAnterior.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    public String consultaDineroElectronico(
        int numCliente
    )throws IOException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        Gson gson = new Gson();
        LocalDate fechaActual = LocalDate.now();
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <MovDineroElectronicoRecompensa> MovDineroElectronicoRecompensa = recompensaModel.consultaDineroElectronico(numCliente,fechaActual);
            if(MovDineroElectronicoRecompensa.size()>0){
                response=Integer.toString(MovDineroElectronicoRecompensa.get(0).getIduMovimiento())+","
                        +Integer.toString(MovDineroElectronicoRecompensa.get(0).getImpDineroElectronico())+","
                        +Integer.toString(MovDineroElectronicoRecompensa.get(0).getIduLogica());
                //response = gson.toJson(MovDineroElectronicoRecompensa);
            }else{
                response="0";
            }
            //response = gson.toJson(MovDineroElectronicoRecompensa);
            //response="0";
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultaDineroElectronico.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    
    public String consultaSiGanoDE(
        int numCliente,
        int numFolioabono,
        String fecGeneracion
    )throws IOException, SQLException{
        recompensaModel recompensaModel = null;
        String response = null;
        Gson gson = new Gson();
       // LocalDate fechaActual = LocalDate.now();
       //System.out.println("fecGeneracion "+fecGeneracion);
        
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            List <MovDineroElectronicoRecompensa> MovDineroElectronicoRecompensa = recompensaModel.consultaSiGanoDE(numCliente,numFolioabono,fecGeneracion);
             
            if(MovDineroElectronicoRecompensa.size()>0){
                response=Integer.toString(MovDineroElectronicoRecompensa.get(0).getImpDineroElectronico())+","
                        +Integer.toString(MovDineroElectronicoRecompensa.get(0).getIduLogica())+","
                        +MovDineroElectronicoRecompensa.get(0).getFecGeneracion().toString().substring(0,10);
                //response = gson.toJson(MovDineroElectronicoRecompensa);
            }else{
                response="0";
            }
            //response = gson.toJson(MovDineroElectronicoRecompensa);
            //response="0";
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "Ocurrió un error consultaSiGanoDE.", Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    public String eliminarDineroE(
        String datosCliente
    )throws IOException,SQLException {
        String response = null;
        recompensaModel recompensaModel = null;
        Set<String> propiedadesEsperadas = new HashSet<>();
        propiedadesEsperadas.add("idu_movimiento");
        propiedadesEsperadas.add("numCliente");
        propiedadesEsperadas.add("fecGeneracion");
        this.validarCuerpoPeticion(datosCliente, propiedadesEsperadas);
        try {
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            //gson.toJson(datosLog);
            MovDineroElectronicoRecompensa cancelaDE = this.gson.fromJson(datosCliente, MovDineroElectronicoRecompensa.class);
            
            cancelaDE = recompensaModel.eliminarDineroE(cancelaDE);
            
            response = gson.toJson(cancelaDE);
            
        } catch(ApplicationException ex) {
            throw ex;
        } 
        catch(IllegalStateException | HibernateException ex) {
            this.manejarExcepcion(ex, "Ocurrió un error al registrar LOG.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
    
    public String eliminarCorte(
        String datosCliente
    )throws IOException,SQLException {
        String response = null;
        recompensaModel recompensaModel = null;
        
        Set<String> propiedadesEsperadas = new HashSet<>();
        propiedadesEsperadas.add("keyx");
        propiedadesEsperadas.add("numCliente");
        propiedadesEsperadas.add("fecAlcanzoMesPuntual");
        propiedadesEsperadas.add("fecCorteCartera");
        this.validarCuerpoPeticion(datosCliente, propiedadesEsperadas);
        try {
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            //gson.toJson(datosLog);
            cortesCumplidos cancelaCorte = this.gson.fromJson(datosCliente, cortesCumplidos.class);
            
            cancelaCorte = recompensaModel.eliminarCorte(cancelaCorte);
            
            response = gson.toJson(cancelaCorte);
            
        } catch(ApplicationException ex) {
            throw ex;
        } 
        catch(IllegalStateException | HibernateException ex) {
            this.manejarExcepcion(ex, "Ocurrió un error al registrar LOG.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
    
    public String eliminarCorteAnt(
        String datosCliente
    )throws IOException,SQLException {
        String response = null;
        recompensaModel recompensaModel = null;
        
        Set<String> propiedadesEsperadas = new HashSet<>();
        propiedadesEsperadas.add("keyx");
        propiedadesEsperadas.add("numCliente");
        propiedadesEsperadas.add("fecAlcanzoMesPuntual");
        propiedadesEsperadas.add("fecCorteCartera");
        this.validarCuerpoPeticion(datosCliente, propiedadesEsperadas);
        try {
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            //gson.toJson(datosLog);
            CorteAnterior cancelaCorte = this.gson.fromJson(datosCliente, CorteAnterior.class);
            
            cancelaCorte = recompensaModel.eliminarCorteAnt(cancelaCorte);
            
            response = gson.toJson(cancelaCorte);
            
        } catch(ApplicationException ex) {
            throw ex;
        } 
        catch(IllegalStateException | HibernateException ex) {
            this.manejarExcepcion(ex, "Ocurrió un error al registrar LOG.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }    
    
    public String eliminarAbono(
        String datosCliente
    )throws IOException,SQLException {
        String response = null;

        recompensaModel recompensaModel = null;
        Set<String> propiedadesEsperadas = new HashSet<>();
        //propiedadesEsperadas.add("keyx");
        propiedadesEsperadas.add("numCliente");
        propiedadesEsperadas.add("fecAbono");
        this.validarCuerpoPeticion(datosCliente, propiedadesEsperadas);
        try {
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            //gson.toJson(datosLog);
            MovAbonosPuntuales cancelaAbono = this.gson.fromJson(datosCliente, MovAbonosPuntuales.class);
            
            cancelaAbono = recompensaModel.eliminarAbono(cancelaAbono);
            
            response = gson.toJson(cancelaAbono);
            
        } catch(ApplicationException ex) {
            throw ex;
        } 
        catch(IllegalStateException | HibernateException ex) {
            this.manejarExcepcion(ex, "Ocurrió un error al registrar LOG.", Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }
    
    public String consultarMovAbonosID(
        int keyx
    )throws IOException,SQLException{
        recompensaModel recompensaModel=null;
        String response = null;
        String fecha=null;
        Gson gson = new Gson();
        int importeAbono=0;

        
        try{
            recompensaModel = new recompensaModel(ApplicationConfiguration.getInstance().getProperties());
            MovAbonosPuntuales movAbonosPuntuales = recompensaModel.consultarMovAbonosID(keyx);
            if(movAbonosPuntuales != null){
                response = movAbonosPuntuales.getKeyx()+","+movAbonosPuntuales.getNumCliente()+","
                           +movAbonosPuntuales.getNumTienda()+","+movAbonosPuntuales.getImpAbono()+","
                            +movAbonosPuntuales.getNumTicket()+","+movAbonosPuntuales.getClvMovimiento()+","
                            +movAbonosPuntuales.getNumMovimiento()+","+movAbonosPuntuales.getFecAbono()+","
                            +movAbonosPuntuales.getFecCorteCartera()+","+movAbonosPuntuales.getFolioAbono();
            }else{
                response="0";
            }
        }catch(IllegalStateException | HibernateException ex){
            this.manejarExcepcion(ex, "MovAbonosPuntuales: "+ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    
    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
}
