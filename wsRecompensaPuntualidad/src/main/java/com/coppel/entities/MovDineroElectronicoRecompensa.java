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
/**
 *
 * @author agonzalezr
 */

@Entity
@Table(name="mov_dineroelectronicorecompensa")
public class MovDineroElectronicoRecompensa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idu_movimiento;
    
    @Column(name="num_cliente")
    private int numCliente;
    
    @Column(name="imp_dineroelectronico")
    private int impDineroElectronico;
    
    @Column(name="idu_logica")
    private int iduLogica;
    
    @Column(name="fec_generacion")
    private Date fecGeneracion;
    
    @Column(name="num_folioabono")
    private int numFolioabono;
    
    public MovDineroElectronicoRecompensa(){
    
    }
    
    public MovDineroElectronicoRecompensa(int idu_movimiento,int numCliente,int impDineroElectronico,int iduLogica,Date fecGeneracion, int numFolioabono){
        this.idu_movimiento=idu_movimiento;
        this.numCliente=numCliente;
        this.impDineroElectronico=impDineroElectronico;
        this.iduLogica=iduLogica;
        this.fecGeneracion=fecGeneracion;
        this.numFolioabono=numFolioabono;
    }
    
    public MovDineroElectronicoRecompensa(int numCliente,int impDineroElectronico,int iduLogica,Date fecGeneracion, int numFolioabono){
        this.numCliente=numCliente;
        this.impDineroElectronico=impDineroElectronico;
        this.iduLogica=iduLogica;
        this.fecGeneracion=fecGeneracion;
        this.numFolioabono=numFolioabono;
    }
    
    public int getIduMovimiento(){
        return idu_movimiento;
    }
    public void setIduMovimiento( int idu_movimiento){
        this.idu_movimiento=idu_movimiento;
    }
    
    public int getNumCliente(){
        return numCliente;
    }
    
    public void setNumCliente(int numCliente){
        this.numCliente=numCliente;
    }
    
    public int getImpDineroElectronico(){
        return impDineroElectronico;
    }
    
    public void setImpDineroElectronico(int impDineroElectronico){
        this.impDineroElectronico=impDineroElectronico;
    }
    
    public int getIduLogica(){
        return iduLogica;
    }
    public void setIduLogica(int iduLogica){
        this.iduLogica=iduLogica;
    }
    
    public Date getFecGeneracion(){
        return fecGeneracion;
    }
    
    public void setFecGeneracion(Date fecGeneracion){
        this.fecGeneracion=fecGeneracion;
    }
    
    public int getnumFolioabono(){
        return numFolioabono;
    }
    
    public void setnumFolioabono(int numFolioabono){
        this.numFolioabono=numFolioabono;
    }
}
