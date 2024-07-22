/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.services;

import com.coppel.controllers.recompensaController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import static javax.accessibility.AccessibleTableModelChange.DELETE;
import static javax.swing.event.TableModelEvent.DELETE;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author agonzalezr
 */
@Path("coppelRecompensa")
public class recompensaService {
    
    @Path("/Abonos")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String abonosService(
            String cliente
    )throws IOException,SQLException, ParseException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarAbonos(cliente);
    }
    
    @Path("/PlanLealtad")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarCortesCumplidos(
        @DefaultValue(value = "0") @QueryParam(value = "numCliente") String num_cliente
    ) throws IOException, SQLException, ParseException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.validaPlanLealtad(num_cliente);
        
    }
    
    @Path("/CortesCumplidos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarCortesCumplidos(
        @DefaultValue(value = "0") @QueryParam(value = "numCliente") int num_cliente,
            @DefaultValue(value = "0") @QueryParam(value = "flag") int flag
    ) throws IOException, SQLException, ParseException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarCortes(num_cliente,flag);
        
    }
    
    @Path("/CumpleCorte")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrarCorteCumplido(String datosCorte) throws IOException, SQLException {
        recompensaController recompensaController = new recompensaController();
        return recompensaController.registrarCorteCumplido(datosCorte);
    }
    
    @Path("/CumpleCorteAnterior")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrarCorteAnterior(String datosCorte) throws IOException, SQLException {
        recompensaController recompensaController = new recompensaController();
        return recompensaController.registrarCorteAnterior(datosCorte);
    }
    
    @Path("/importeCorte")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarImporteCortes(
        @DefaultValue(value = "0") @QueryParam(value = "numCliente") int numCliente,
        @DefaultValue(value = "0") @QueryParam(value = "numLogica") int numLogica,
        @DefaultValue(value = "0") @QueryParam(value = "numCorte") int numCorte
    ) throws IOException, SQLException, ParseException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarImporteCorte(numCliente,numLogica,numCorte);
        
    }
    

    @Path("/Puntualidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultaPuntualidad(
        @DefaultValue(value = "0") @QueryParam(value = "iduLogica") int idu_logica,
        @DefaultValue(value = "0") @QueryParam(value = "clvPuntualidad") String clv_puntualidad,
        @DefaultValue(value = "0") @QueryParam(value = "numCliente") int numCliente
    ) throws IOException, SQLException{
      recompensaController recompensaController = new recompensaController();
      return recompensaController.consultaPuntualidad(idu_logica,clv_puntualidad,numCliente);
    }
    
    @Path("/logicasRecompensa")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarLogicasRecompensa(
        @DefaultValue(value = "0") @QueryParam(value = "idu_logica") int idu_logica
    )throws IOException, SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarLogicasRecompensa(idu_logica);
    }
    
    @Path("/CiudadesRecompensa")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarCiudadesRecompensa(
        @DefaultValue(value = "0") @QueryParam(value = "numCiudad") int numCiudad
    )throws IOException, SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarCiudadesRecompensa(numCiudad);
        
    }
    
    @Path("/Nacional")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultaNacional(
        @DefaultValue(value = "") @QueryParam(value = "clvNacional") String clvNacional,
        @DefaultValue(value = "") @QueryParam(value = "clvStatus") String clvStatus
    )throws IOException, SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultaNacional(clvNacional,clvStatus);
        
    }
    
    @Path("/UrlService")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultaUrl(
        @DefaultValue(value = "") @QueryParam(value = "clvStatusPL") String clvStatus
    )throws IOException, SQLException, ParseException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultaUrl(clvStatus);
        
    }
    
    @Path("/CarterasParticipantes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarCarterasParticipantes(
        @DefaultValue(value="") @QueryParam(value = "claveMovto") String clvMovimiento
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarCarterasParticipantes(clvMovimiento);
        
    }
    
    @Path("/CarterasRecompensa")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarCarterasRecompensa(
        @DefaultValue(value="0") @QueryParam(value = "idu_logica") int idu_logica,
        @DefaultValue(value="0") @QueryParam(value = "numTipoCuenta") int numTipoCuenta,
            @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente
            
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarCarterasRecompensa(idu_logica,numTipoCuenta,numCliente);
    }
   
    
    @Path("/MovAbonosPuntuales")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarMovAbonosPuntuales(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarMovAbonosPuntuales(numCliente);
        
    }
    
    @Path("/Movdelantado")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarMovAdelantado(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarMovAdelantado(numCliente);
        
    }
    
    @Path("/SumAdelantado")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sumMovAdelantado(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente,
        @DefaultValue(value="0") @QueryParam(value = "fecCorteCartera") String fecCorteCartera
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.sumMovAdelantado(numCliente,fecCorteCartera);
        
    }
    
    
    @Path("/ImpAbonosPuntuales")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarImpAbonosPuntuales(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente,
        @DefaultValue(value="0") @QueryParam(value = "numCorte") int numCorte
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarImpAbonosPuntuales(numCliente,numCorte);
        
    }
    
    @Path("/ImpAbonosRegistrados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarImpAbonosRegistrados(
        @DefaultValue(value = "0") @QueryParam(value = "numCliente") int numCliente,
        @DefaultValue(value = "0") @QueryParam(value = "numLogica") int numLogica,
        @DefaultValue(value = "0") @QueryParam(value = "numCorte") int numCorte
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarImpAbonosRegistrados(numCliente,numCorte,numCorte);
        
    }
    
    @Path("/AbonosPuntuales")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrarEmpleado(String datosCliente) throws IOException, SQLException {
        recompensaController recompensaController = new recompensaController();
        return recompensaController.registrarMovAbonoPuntual(datosCliente);
    }
    
    
    
     @Path("/MovAbonosID")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarMovAbonosID(
        @DefaultValue(value="0") @QueryParam(value = "keyx") int keyx
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarMovAbonosID(keyx
        );
        
    }
    
    @Path("/MovDineroElectronicoRecompensa")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarMovDineroElectronico(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarMovDineroElectronico(numCliente);
    }
    
    @Path("/MovDineroElectronico")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrarMovDineroElectronico(String datosDE) throws IOException, SQLException {
        recompensaController recompensaController = new recompensaController();
        return recompensaController.registrarMovDineroElectronico(datosDE);
    }

    @Path("/Log")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrarLog(
            String datosLog
    ) throws IOException, SQLException {
        recompensaController recompensaController = new recompensaController();
        return recompensaController.registrarLog(datosLog);
    }
    
    @Path("/Cliente")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarCorteCliente(
        @QueryParam(value = "numCliente") int numCliente
    )throws IOException, SQLException, ParseException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarCorteCliente(numCliente);
    }
   
    @Path("/Abono")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String cancelarAbono(
        @DefaultValue(value = "0") @QueryParam(value = "numCliente") int numCliente,
        @DefaultValue(value = "0") @QueryParam(value = "folio") int folioabono
    ) throws SQLException, IOException, ParseException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.cancelarAbono(numCliente,folioabono);
    }
    
    @Path("/MovAbonos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarMovAbonos(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente,
        @DefaultValue(value="0") @QueryParam(value = "folio") int folioabono,
        @DefaultValue(value="0") @QueryParam(value = "flag") int flag
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultarMovAbonos(numCliente,folioabono,flag);
        
    }
    
    @Path("/Cortes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultaCorte(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente,
        @DefaultValue(value="0") @QueryParam(value = "fecCorteCartera") String fechaCorte
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultaCorte(numCliente,fechaCorte);
        
    }
    @Path("/CorteAnterior")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultaCorteAnterior(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente,
        @DefaultValue(value="0") @QueryParam(value = "fecCorteCartera") String fechaCorte
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultaCorteAnterior(numCliente,fechaCorte);
        
    }
    
    @Path("/movDineroE")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultaDineroElectronico(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultaDineroElectronico(numCliente);
        
    }
    
    @Path("/consultaSiGanoDE")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultaSiGanoDE(
        @DefaultValue(value="0") @QueryParam(value = "numCliente") int numCliente,
        @DefaultValue(value="0") @QueryParam(value = "numFolioabono") int numFolioabono,
        @DefaultValue(value="0") @QueryParam(value = "fecGeneracion") String fecGeneracion
    )throws IOException,SQLException{
        recompensaController recompensaController = new recompensaController();
        return recompensaController.consultaSiGanoDE(numCliente,numFolioabono,fecGeneracion);
        
    }
    
    @Path("/DineroElectronico")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarDineroE(String datosCliente) throws IOException, SQLException {
        recompensaController recompensaController = new recompensaController();
        return recompensaController.eliminarDineroE(datosCliente);
    }
    
    @Path("/CorteCliente")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarCorte(String datosCliente) throws IOException, SQLException {
        recompensaController recompensaController = new recompensaController();
        return recompensaController.eliminarCorte(datosCliente);
    }
    
    @Path("/CorteClienteAnt")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarCorteAnt(String datosCliente) throws IOException, SQLException {
        recompensaController recompensaController = new recompensaController();
        return recompensaController.eliminarCorteAnt(datosCliente);
    }
    
    @Path("/MovAbono")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarAbono(String datosCliente) throws IOException, SQLException {
        recompensaController recompensaController = new recompensaController();
        return recompensaController.eliminarAbono(datosCliente);
    }
}
