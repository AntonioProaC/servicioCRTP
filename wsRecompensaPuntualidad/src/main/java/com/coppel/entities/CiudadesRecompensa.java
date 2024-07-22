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
@Table(name="cat_ciudades_recompensa")
public class CiudadesRecompensa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keyx;
    
    @Column(name="idu_logica")
    private int idu_logica;
    
    @Column(name ="num_ciudad")
    private int numCiudad;
    
    @Column(name="nom_ciudad")
    private String nomCiudad;
    
    @Column(name="prc_dinero")
    private int prcDinero;
    
    @Column(name="clv_masiva")
    private String clvMasiva;
    
    @Column(name="clv_personalizada")
    private String clvPersonalizada;
    
    @Column(name="clv_status")
    private int clvStatus;
    
    @Column(name="fec_activacion")
    private Date fecActivacion;
    
    @Column(name="fec_desactivacion")
    private Date fecDesactivacion;
    
    public CiudadesRecompensa(){
    
    }
    
    public CiudadesRecompensa(int keyx,int idu_logica,int numCiudad,String nomCiudad,int prcDinero,String clvMasiva,String clvPersonalizada,int clvStatus,Date fecActivacion,Date fecDesactivacion){
        this.keyx=keyx;
        this.idu_logica=idu_logica;
        this.numCiudad=numCiudad;
        this.nomCiudad=nomCiudad;
        this.prcDinero=prcDinero;
        this.clvMasiva=clvMasiva;
        this.clvPersonalizada=clvPersonalizada;
        this.clvStatus=clvStatus;
        this.fecActivacion=fecActivacion;
        this.fecDesactivacion=fecDesactivacion;
    }
    
    public CiudadesRecompensa(int idu_logica,int numCiudad,String nomCiudad,int prcDinero,String clvMasiva,String clvPersonalizada,int clvStatus,Date fecActivacion,Date fecDesactivacion){
        this.idu_logica=idu_logica;
        this.numCiudad=numCiudad;
        this.nomCiudad=nomCiudad;
        this.prcDinero=prcDinero;
        this.clvMasiva=clvMasiva;
        this.clvPersonalizada=clvPersonalizada;
        this.clvStatus=clvStatus;
        this.fecActivacion=fecActivacion;
        this.fecDesactivacion=fecDesactivacion;
    }
    
    public int getKeyc(){
        return keyx;
    }
    
    public void setKeyx(int keyx){
        this.keyx=keyx;
    }
    
    public int getIduLogica(){
        return idu_logica;
    }
    
    public void setIduLogica(int idu_logica){
        this.idu_logica = idu_logica;
    }
    
    public int getNumCiudad(){
        return numCiudad;
    }

    public void setNumCiudad(int numCiudad){
        this.numCiudad=numCiudad;
    }
    
    public String getNomCiudad(){
        return nomCiudad;
    }
    public void setNomCiudad(String nomCiudad){
        this.nomCiudad=nomCiudad;
    }
    
    public int getPrcDinero(){
        return prcDinero;
    }
    
    public void setPrcDinero(int prcDinero){
        this.prcDinero = prcDinero;
    }
    
    public String getClvMasiva(){
        return clvMasiva;
    }
    
    public void setClvMasiva(String clvMasiva){
        this.clvMasiva= clvMasiva;
    }
    
    public String getClvPersonalizada(){
        return clvPersonalizada;
    }
    
    public void setClvPersonalizada(String clvPersonalizada){
        this.clvPersonalizada=clvPersonalizada;
    }
    
    public int getClvStatus(){
        return clvStatus;
    }
    
    public void setClvStatus(int clvStatus){
        this.clvStatus=clvStatus;
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
    
    public void setFecDesactivacion(Date fecDesactivacion){
        this.fecDesactivacion=fecDesactivacion;
    }
    
    
}
