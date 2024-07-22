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
@Table(name="ctl_cortescumplidos")
public class cortesCumplidos {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private int keyx;
       
       @Column(name="num_cliente")
       private int numCliente;
       
       @Column(name ="idu_logica")
       private int iduLogica;
       
       @Column(name="imp_abonoscorte")
       private int impAbonosCorte;
       
       @Column(name="num_corte")
       private int numCorte;
       
       @Column(name="prc_dineroe")
       private int prcDineroE;
               
       @Column (name="imp_dineroecorte")
       private int impDineroCorte;
       
       @Column(name="fec_alcanzomespuntual")
       private Date fecAlcanzoMesPuntual;
              
       @Column(name="fec_cortecartera")
       @Temporal(value = TemporalType.DATE)
       private Date fecCorteCartera;
       
       @Column(name="abono_base")
       private int abonoBase;
       
       public cortesCumplidos(){
       }
       
        public cortesCumplidos(int keyx,int numCliente,int iduLogica,int impAbonosCorte,int numCorte, int prcDineroE,int impDineroCorte,Date fecAlcanzoMesPuntual,Date fecCorteCartera,int abonoBase){
           this.keyx=keyx;
           this.numCliente = numCliente;
           this.iduLogica = iduLogica;
           this.impAbonosCorte = impAbonosCorte;
           this.prcDineroE = prcDineroE;
           this.numCorte =  numCorte;
           this.impDineroCorte = impDineroCorte;
           this.fecAlcanzoMesPuntual = fecAlcanzoMesPuntual;
           this.fecCorteCartera = fecCorteCartera;
           this.abonoBase=abonoBase;
       }

       public cortesCumplidos(int numCliente,int iduLogica,int impAbonosCorte,int numCorte, int prcDineroE,int impDineroCorte,Date fecAlcanzoMesPuntual,Date fecCorteCartera,int abonoBase){
           this.numCliente = numCliente;
           this.iduLogica = iduLogica;
           this.impAbonosCorte = impAbonosCorte;
           this.prcDineroE = prcDineroE;
           this.numCorte =  numCorte;
           this.impDineroCorte = impDineroCorte;
           this.fecAlcanzoMesPuntual = fecAlcanzoMesPuntual;
           this.fecCorteCartera = fecCorteCartera;
           this.abonoBase=abonoBase;
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
       public void setNumCliente(int num_cliente){
           this.numCliente = num_cliente;
       }
       
       public int getiduLogica(){
           return iduLogica;
       }
       public void setIduLogica(int iduLogica){
           this.iduLogica=iduLogica;
       }
       
       public int getimpAbonosCorte(){
           return impAbonosCorte;
       }
       public void setImpAbonosCorte(int impAbonosCorte){
           this.impAbonosCorte=impAbonosCorte;
       }
       
       public int getprcDineroE(){
           return prcDineroE;
       }
       public void setPrcDineroE(int prcDineroE){
           this.prcDineroE = prcDineroE;
       }
       
       public int getnumCorte(){
           return numCorte;
       }
       public void setNumCorte(int numCorte){
           this.numCorte=numCorte;
       }
       
       public int getimpDineroCorte(){
           return impDineroCorte;
       }
       public void setImpDineroCorte(int impDineroCorte){
           this.impDineroCorte = impDineroCorte;        
       }
       
       public Date getfecAlcanzoMesPuntual(){
           return fecAlcanzoMesPuntual;
       }
       public void setFecAlcanzoMesPuntual(Date fecAlcanzoMesPuntual){
           this.fecAlcanzoMesPuntual = fecAlcanzoMesPuntual;
       }
       
       public Date getfecCorteCartera(){
           return fecCorteCartera;
       }
       public void setFecCorteCartera(Date fecCorteCartera){
           this.fecCorteCartera = this.fecCorteCartera;
       }
       
       public int getAbonoBase(){
           return abonoBase;
       }
       
       public void setAbonoBase(int abonoBase){
           this.abonoBase=abonoBase;
       }
}
