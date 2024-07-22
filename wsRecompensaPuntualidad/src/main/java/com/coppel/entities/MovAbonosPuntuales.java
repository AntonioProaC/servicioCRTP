/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author agonzalezr
 */
@Entity
@Table(name="MOV_ABONOSPUNTUALES")
public class MovAbonosPuntuales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keyx;
    
    @Column(name="num_cliente")
    private int numCliente;
    
    @Column(name="num_tienda")
    private int numTienda;
    
    @Column(name="imp_abono")
    private int impAbono;
    
    @Column(name="num_ticket")
    private int numTicket;
    
    @Column(name="clv_movimiento")
    private String clvMovimiento;
    
    @Column(name="num_movimiento")
    private String numMovimiento;
    
    @Column(name="fec_abono")
    private Date fecAbono;
    
    @Column(name="fec_cortecartera")
    private Date fecCorteCartera;
    
    @Column(name="num_folioAbono")
    private int folio;

   public MovAbonosPuntuales(){
   
   }
   
   public MovAbonosPuntuales(int keyx, int numCliente,int numTienda,int impAbono,int numTicket,String clvMovimiento,String numMovimiento,Date fecAbono, Date fecCorteCartera,int folio){
       this.keyx=keyx;
       this.numCliente=numCliente;
       this.numTienda=numTienda;
       this.impAbono=impAbono;
       this.numTicket=numTicket;
       this.clvMovimiento=clvMovimiento;
       this.numMovimiento=numMovimiento;
       this.fecAbono=fecAbono;
       this.fecCorteCartera=fecCorteCartera;
       this.folio=folio;
   }
   
   public MovAbonosPuntuales(int numCliente,int numTienda,int impAbono,int numTicket,String clvMovimiento,String numMovimiento,Date fecAbono,Date fecCorteCartera,int folio){
       this.numCliente=numCliente;
       this.numTienda=numTienda;
       this.impAbono=impAbono;
       this.numTicket=numTicket;
       this.clvMovimiento=clvMovimiento;
       this.numMovimiento=numMovimiento;
       this.fecAbono=fecAbono;
       this.fecCorteCartera=fecCorteCartera;
       this.folio=folio;
   }
   
   public int getKeyx(){
       return keyx;
   }
   
   public void setKeyx(int keyx){
       this.keyx=keyx;
   }
   
   public int getNumCliente(){
       return numCliente;
   }
   public void setNumCliente(int numCliente){
       this.numCliente=numCliente;
   }
   
   public int getNumTienda(){
       return numTienda;
   }
   public void setNumTienda(int numTienda){
       this.numTienda=numTienda;
   }
   
   public int getImpAbono(){
       return impAbono;
   }
   public void setImpAbono(int impAbono){
       this.impAbono=impAbono;
   }
   public int getNumTicket(){
       return numTicket;
   }
   
   public void setNumTicket(int numTicket){
       this.numTicket=numTicket; 
   }
   
   public String getClvMovimiento(){
       return clvMovimiento;
   }
   
   public void setClvMovimiento(String clvMovimiento){
       this.clvMovimiento=clvMovimiento;
   }
   
   public String getNumMovimiento(){
       return numMovimiento;
   }
   public void setNumMovimiento(String numMovimiento){
       this.numMovimiento=numMovimiento;
   }
   
   public Date getFecAbono(){
       return fecAbono;
   }
   
   public void setFecAbono(Date fecAbono){
       this.fecAbono=fecAbono; 
   }
   
   public Date getFecCorteCartera (){
       return fecCorteCartera;
   }
   
   public void setfecCorteCartera(Date fecCorteCartera){
       this.fecCorteCartera=fecCorteCartera;
   }
   
   public int getFolioAbono(){
       return folio;
   }
   
   public void setFolioAbono(int folio){
       this.folio=folio;
   }
}
