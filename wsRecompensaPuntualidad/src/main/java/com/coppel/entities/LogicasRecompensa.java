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
@Table(name="cat_logicas_recompensa")
public class LogicasRecompensa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idu_logica;
    
    @Column(name ="num_meses")
    private int numMeses;
    
    @Column(name="fec_creacion")
    private Date fecCreacion;
    
    @Column(name="clv_nacional")
    private String clvNacional;
    
    @Column(name="clv_status")
    private String clvStatus;
    
    @Column(name="prc_dinero")
    private int prcDinero;
    
    @Column(name="fec_activacion")
    private Date fecActivacion;
    
    @Column(name="fec_desactivacion")
    private Date fecDesactivacion;
    
    public LogicasRecompensa(){
    
    }
    
    public LogicasRecompensa(int idu_logica,int numMeses,Date fecCreacion,String clvNacional,String clvStatus,int prcDinero,Date fecActivacion,Date fecDesactivacion){
        this.idu_logica=idu_logica;
        this.numMeses=numMeses;
        this.fecCreacion=fecCreacion;
        this.clvNacional=clvNacional;
        this.clvStatus=clvStatus;
        this.prcDinero=prcDinero;
        this.fecActivacion=fecActivacion;
        this.fecDesactivacion=fecDesactivacion;
    }
    
    public LogicasRecompensa(int numMeses,Date fecCreacion,String clvNacional,String clvStatus,int prcDinero,Date fecActivacion,Date fecDesactivacion){
        this.numMeses=numMeses;
        this.fecCreacion=fecCreacion;
        this.clvNacional=clvNacional;
        this.clvStatus=clvStatus;
        this.prcDinero=prcDinero;
        this.fecActivacion=fecActivacion;
        this.fecDesactivacion=fecDesactivacion;
    }
    
    public int getidu_logica(){
           return idu_logica;
    }
    public void setidu_logica(int idu_logica){
        this.idu_logica = idu_logica;
    }
    
    public int getNumMeses(){
        return numMeses;
    }
    public void setNumMeses(int numMeses){
        this.numMeses=numMeses;
    }
    
    public Date getfecCreacion(){
        return fecCreacion;
    }
    
    public void setfecCreacion(Date fecCreacion){
        this.fecCreacion=fecCreacion;
    }
    
    public String getclvNacional(){
        return clvNacional;
    }
    public void setclvNacional(String clvNacional){
        this.clvNacional=clvNacional;
    }
    
    public String getclvStatus(){
        return clvStatus;
    }
    public void setclvStatus(String clvStatus){
        this.clvStatus=clvStatus;
    }
    
    public int getprcDinero(){
        return prcDinero;
    }
    public void setprcDineroo(int prcDinero){
        this.prcDinero=prcDinero;
    }
    
    public Date getFecActivacion(){
        return fecActivacion;
    }
    
    public void setFecActivacion(Date fecActivacion){
        this.fecActivacion=fecActivacion;
    }
    
    public Date getFecDesactivacion(){
        return fecDesactivacion;
    }
    
    public void setFecDesactivacion(){
        this.fecDesactivacion=fecDesactivacion;
    }
}
