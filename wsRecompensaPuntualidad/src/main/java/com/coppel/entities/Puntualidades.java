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
@Table(name="ctl_puntualidades_recompensa")
public class Puntualidades {
       
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private int keyx;
       
       @Column(name = "idu_logica")
       private int iduLogica;
       
       @Column(name ="clv_puntualidad")
       private String clvPuntualidad;
       
       public Puntualidades(){
       
       }
       
       public Puntualidades(int keyx,int iduLogica, String clvPuntualidad){
           this.keyx=keyx;
           this.iduLogica = iduLogica;
           this.clvPuntualidad = clvPuntualidad;
       }
       
       public Puntualidades(int iduLogica,String clvPuntualidad){
           this.iduLogica=iduLogica;
           this.clvPuntualidad = clvPuntualidad;
       }
       
       public int getKeyx(){
           return keyx;
       }
       
       public void setKeyx(int keyx){
           this.keyx=keyx;
       }
       
       public int getIduLogica(){
           return iduLogica;
       }
       
       public void setIduLogica(int iduLogica){
           this.iduLogica=iduLogica;
       }
       
       public String getClvPuntualidad(){
           return clvPuntualidad;
       }
       
       public void setClvPuntualidad(String clvPuntualidad){
           this.clvPuntualidad= clvPuntualidad;
       }
       
       
}
