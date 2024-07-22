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
@Table(name="cat_carteras_recompensa")
public class CarterasParticipantes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="num_movimiento")
    private int num_tipocuenta;
    
    @Column(name="clv_movimiento")
    private String clvMovimiento;

    @Column(name="num_movimiento")
    private int numMovimiento;
    
    @Column(name="des_cartera")
    private String desCartera;
            
    @Column(name="clv_status")
    private String clvStatus;        
    
    public CarterasParticipantes(){
    }
    
    public CarterasParticipantes(int num_tipocuenta,String clvMovimiento,int numMovimiento,String desCartera,String clvStatus){
        this.num_tipocuenta=num_tipocuenta;
        this.clvMovimiento=clvMovimiento;
        this.numMovimiento=numMovimiento;
        this.desCartera=desCartera;
        this.clvStatus=clvStatus;
    }
    
    public CarterasParticipantes(String clvMovimiento,int numMovimiento,String desCartera,String clvStatus){
        this.clvMovimiento=clvMovimiento;
        this.numMovimiento=numMovimiento;
        this.desCartera=desCartera;
        this.clvStatus=clvStatus;
    }
    
    public int getNumTipoCuenta(){
        return num_tipocuenta;
    }
    
    public void setNumTipoCuenta(int num_tipocuenta){
        this.num_tipocuenta=num_tipocuenta;
    }
    
    public String getClvMovimiento(){
        return clvMovimiento;
    }
    public void setClvMovimiento(String clvMovimiento){
        this.clvMovimiento=clvMovimiento;
    }
    
    public int getNumMovimiento(){
        return numMovimiento;
    }
    public void setNumMovimiento(int numMovimiento){
        this.numMovimiento=numMovimiento;
    }
     public String getDesCartera(){
         return desCartera;
     }
     
     public void setDesCartera(String desCartera){
         this.desCartera=desCartera;
     }
     
     public String getClvStatus(){
         return clvStatus;
     }
     
     public void setClvStatus(String clvStatus){
         this.clvStatus=clvStatus;
     }
    

}
