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
@Table(name="log_coppel_recompensa")
public class LogCRTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keyx;
    
    @Column(name="mensaje")
    private String msj;
    
    @Column(name="num_cliente")
    private int numeroCliente;
    
    @Column(name="idu_logica")
    private int iduLogica;
    
    @Column(name="fecha")
    private Date fechaReg;
    
    public LogCRTP(){
    
    }
    
    public LogCRTP(int keyx,String msj,int numeroCliente,int iduLogica,Date fechaReg){
        this.keyx=keyx;
        this.msj=msj;
        this.numeroCliente=numeroCliente;
        this.iduLogica=iduLogica;
        this.fechaReg=fechaReg;
    }
    
    public LogCRTP(String msj,int numeroCliente,int iduLogica,Date fechaReg){
        this.msj=msj;
        this.numeroCliente=numeroCliente;
        this.iduLogica=iduLogica;
        this.fechaReg=fechaReg;
    }
    
    public int getKeyx(){
        return keyx;
    }
    public void setKeyx(int keyx){
        this.keyx=keyx;
    }
        
    public String getMsj(){
        return msj;
    }
    
    public void setMsj(String msj){
        this.msj=msj;
    }
    
    public int getNumCliente(){
        return numeroCliente;
    }
    
    public void setNumCliente(int numeroCliente){
        this.numeroCliente=numeroCliente;
    }
    
    public int getIduLogica(){
        return iduLogica;
    }
    
    public void setIduLogica(int iduLogica){
        this.iduLogica=iduLogica;
    }
    
    public Date getFechaReg(){
        return fechaReg;
    }
    
    public void setFechaReg(Date fechaReg){
        this.fechaReg=fechaReg;
    }
}
