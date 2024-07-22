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
@Table(name="ctl_carteras_recompensa")
public class CarterasRecompensa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idu_logica;
    
    @Column(name ="clv_movimiento")
    private String clvMovimiento;
    
    @Column(name="num_movimiento") 
    private String numMovimiento;
    
    @Column(name="num_tipocuenta")
    private int numTipoCuenta;
    
    public CarterasRecompensa(){
    
    }
    
    public CarterasRecompensa(int idu_logica,String clvMovimiento,String numMovimiento,int numTipoCuenta){
        this.idu_logica=idu_logica;
        this.clvMovimiento=clvMovimiento;
        this.numMovimiento=numMovimiento;
        this.numTipoCuenta=numTipoCuenta;
    }
    
    public CarterasRecompensa(String clvMovimiento, String numMovimiento,int numTipoCuenta){
        this.clvMovimiento=clvMovimiento;
        this.numMovimiento=numMovimiento;
        this.numTipoCuenta=numTipoCuenta;
    }
    
    public int getIduLogica(){
        return idu_logica;
    }
    
    public void setIduLogica(int idu_logica){
        this.idu_logica=idu_logica;
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
    public int getNumTipoCuenta(){
        return numTipoCuenta;
    }
    
    public void setNumTipoCuenta(int numTipoCuenta){
        this.numTipoCuenta=numTipoCuenta;
    }
}
