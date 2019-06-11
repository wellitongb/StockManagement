/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/// CLASSES PRÓPRIAS
import model.notification.INotificacaoObserver;
import model.notification.INotificacaoSubject;

/**
 * Representa um material
 */
@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Material implements Serializable, INotificacaoSubject {

    /// ATRIBUTOS ********************************************************************************
    
    protected static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = true)
    protected int quantidade;

    @Column(nullable = true)
    protected String causa;

    @Column(nullable = true)
    protected double valorUnitario;

    @Column
    @Temporal(TemporalType.DATE)
    protected Data dataEntrada;
    
    @OneToMany
    protected List<INotificacaoObserver> observerList;

    @Enumerated(EnumType.STRING)
    protected StatusSM status;
    
    protected static int quantidadeMateriasTotal;
    protected static int quantidadeEspacoTotal;

    /// CONSTRUTOR *******************************************************************************
    
    public Material(){ /** vazio **/ }
    
    /// GETTERS E SETTERS ************************************************************************
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static int getQuantidadeMateriasTotal() {
        return Material.quantidadeMateriasTotal;
    }

    public static void setQuantidadeMateriasTotal(int quantidadeMateriasTotal) {
        Material.quantidadeMateriasTotal = quantidadeMateriasTotal;
    }

    public static int getQuantidadeEspacoTotal() {
        return Material.quantidadeEspacoTotal;
    }

    public static void setQuantidadeEspacoTotal(int quantidadeEspacoTotal) {
        Material.quantidadeEspacoTotal = quantidadeEspacoTotal;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public StatusSM getStatus() {
        return this.status;
    }

    public void setStatus(StatusSM status) {
        this.status = status;
    }

    public String getCausa() {
        return this.causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public double getValorUnitario() {
        return this.valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Data getDataEntrada(){		
        return dataEntrada;
    }
    
    public void setDataEntrada(Data dataEntrada) {		
        this.dataEntrada = dataEntrada;
    }
    
    public void setDataEntrada(int dia, int mes, int ano) {		
        this.dataEntrada.set(dia,mes,ano);
    }

    public List<INotificacaoObserver> getObserverList() {
        return observerList;
    }

    public void setObserverList(List<INotificacaoObserver> observerList) {
        this.observerList = observerList;
    }
    
    /// MÉTODOS **********************************************************************************
    
    /**
     * Trata-se, basicamente de um método toString() porém que deve-se obrigatoriamente ser implementado 
     * @return  String contendo todos os atributos
     */
    protected abstract String ImplementYourToString(); 
	
    @Override
    public String toString(){
        String myObjectInString = "";

        myObjectInString+= this.causa;
        myObjectInString+= this.dataEntrada.toString();
        myObjectInString+= String.valueOf(this.id);
        myObjectInString+= this.observerList.toString();
        myObjectInString+= this.status.toString();
        myObjectInString+= String.valueOf(this.valorUnitario);
        myObjectInString+= String.valueOf(Material.quantidadeEspacoTotal);
        myObjectInString+= String.valueOf(Material.quantidadeMateriasTotal);

        myObjectInString += ImplementYourToString();
        return myObjectInString;
    }

    @Override
    public int hashCode() {
        return implementYourHashCode();
    }
    
    protected abstract int implementYourHashCode();

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Material)) {
            return false;
        }
        return implementYourEquals(object);
    }
    
    protected abstract boolean implementYourEquals(Object object);
    
}
