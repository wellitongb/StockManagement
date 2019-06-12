/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.ServiceException;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    public Material(){ 
        this.observerList = new ArrayList<>();
        this.status = StatusSM.NaoBloqueado;
        this.causa = "";
    }
    
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

    
    /// MÉTODOS **********************************************************************************
    
    /**
     * Trata-se, basicamente de um método toString() porém que deve-se obrigatoriamente ser implementado 
     * @return  String contendo todos os atributos
     */
    protected abstract String implementYourToString();
	
    @Override
    public String toString() {
        return "Material{" + "id=" + this.id + 
               ", quantidade=" + this.quantidade + 
               ", causa=" + this.causa + 
               ", valorUnitario=" + this.valorUnitario + 
               ", dataEntrada=" + this.dataEntrada + 
               ", observerList=" + this.observerList + 
               ", status=" + this.status +
               ", " +
                implementYourToString() +
                + '}';
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
    
    @Override
    public void notificarObservers(String notificacao) throws ServiceException{
        for( INotificacaoObserver observer: this.observerList )
                observer.notificar("Notificação: " + notificacao);
    }

    @Override
    public void registrarObserver(INotificacaoObserver obs) throws ServiceException{
        if(obs == null)
            throw new ServiceException("Observador invalido!");
        
        try{
            this.observerList.add(obs);
        }catch(Exception ex){
            throw new ServiceException("Fila de observadores não existe!");
        }
    }

    @Override
    public void desregistrarObserver(INotificacaoObserver obs) throws ServiceException{
        boolean result = false;
        
        if(obs == null)
            throw new ServiceException("Observador invalido!");
        
        try{
            result = this.observerList.remove(obs);
        }catch(Exception ex){
            throw new ServiceException("Fila de observadores não existe!");
        }
        
        if(result == false)
            throw new ServiceException("Não há esse observador na lista!");
    }
    
}
