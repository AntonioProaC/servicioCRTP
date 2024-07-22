/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

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
@Table(name="cat_configuracionservicio")
public class ConfiguracionServicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keyx;
    
    @Column(name="clv_status_plan_lealtad")
    private String clvStatusPL;
    
    @Column(name ="des_servicio_plan_lealtad")
    private String desServicio;
    
    public ConfiguracionServicio(){
    
    }
    
    public ConfiguracionServicio(int keyx,String clvStatusPL,String desServicio){
        this.keyx=keyx;
        this.clvStatusPL=clvStatusPL;
        this.desServicio=desServicio;
    }
    
    public ConfiguracionServicio(String clvStatusPL,String desServicio){
        this.clvStatusPL=clvStatusPL;
        this.desServicio=desServicio;
    }
    
    public int getKeyx(){
        return keyx;
    }
    
    public void setKeyx(int keyx){
        this.keyx=keyx;
    }
    
    public String getClvStatusPL(){
        return clvStatusPL;
    }
    
    public void setClvStatusPL(String clvStatusPL){
        this.clvStatusPL=clvStatusPL;
    }
    
    public String getDesServicio(){
        return desServicio;
    }
    
    public void setDesServicio(String desServicio){
        this.desServicio=desServicio;
    }
}
