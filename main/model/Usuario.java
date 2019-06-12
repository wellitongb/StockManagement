/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;
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

/// CLASSES PRÓPRIAS
import model.notification.INotificacaoObserver;
import model.notification.INotificacaoSubject;
import exception.ServiceException;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Representa um usuário abstrato
 * @see INotificacaoObserver
 * @see INotificacaoSubject
 * @see ServiceException
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario implements Serializable, INotificacaoObserver, INotificacaoSubject {

    /// ATRIBUTOS ********************************************************************************
    
    protected static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    protected Long id;

    @Column(nullable = false)
    protected String nome;

    @Column(nullable = false, unique = true)
    protected String login;

    @Column(nullable = false)
    protected String senha;

    @Enumerated(EnumType.STRING)
    protected StatusSM status;

    @Column(nullable = false)
    protected int quantidadeDeMovimentacoes;

    @Column(nullable = true)
    protected String causa;

    @OneToMany
    protected List<INotificacaoObserver> observerList;

    @OneToMany
    protected Queue<String> notificacoes;

    @Column(nullable = true)
    protected int quantidadeTentativasIncorretasDeAcesso;
    
    /// CONSTRUTOR *******************************************************************************
    
    public Usuario(){ 
        this.observerList = new ArrayList<>();
        this.notificacoes = new ArrayDeque<>();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public StatusSM getStatus() {
        return status;
    }

    public void setStatus(StatusSM statusSM) {
        this.status = statusSM;
    }

    public int getQuantidadeDeMovimentacoes() {
        return quantidadeDeMovimentacoes;
    }

    public void setQuantidadeDeMovimentacoes(int quantidadeDeMovimentacoes) {
        this.quantidadeDeMovimentacoes = quantidadeDeMovimentacoes;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public List<INotificacaoObserver> getObserverList() {
        return observerList;
    }

    public Queue<String> getNotificacoes() {
        return notificacoes;
    }

    public int getQuantidadeTentativasIncorretasDeAcesso() {
        return quantidadeTentativasIncorretasDeAcesso;
    }

    public void setQuantidadeTentativasIncorretasDeAcesso(int quantidadeTentativasIncorretasDeAcesso) {
        this.quantidadeTentativasIncorretasDeAcesso = quantidadeTentativasIncorretasDeAcesso;
    }

    public String getNoficacao() throws ServiceException{
        if(notificacoes.isEmpty()) throw new ServiceException("Não há notificações cadastradas!");
            return notificacoes.remove();
    }
    
    /// MÉTODOS **********************************************************************************

    @Override
    public String toString() {
        return "Usuario{" + "id&" + id + 
               ", nome&" + nome + 
               ", login&" + login + 
               ", senha&" + senha + 
               ", status&" + status + 
               ", quantidadeDeMovimentacoes&" + quantidadeDeMovimentacoes + 
               ", causa&" + causa + 
               ", observerList&" + observerList + 
               ", notificacoes&" + notificacoes + 
               ", quantidadeTentativasIncorretasDeAcesso&" + quantidadeTentativasIncorretasDeAcesso + 
               ", " +
                implementYourToString() +
                '}';
    } 

//    }
    /**
     * Trata-se basicamente de um método toString() porém deve-se obrigatoriamente ser implementado
     * pelas classes filhas de Usuario, a fim de adicionar as informações de seus atributos.
     * @return  String contendo todos os atributos seguindo o padrão do método
     * toString de Usuario.
     */
    protected abstract String implementYourToString(); 
    
    @Override
    public void notificar(String notificacao) throws ServiceException {
        try{
            this.notificacoes.add(notificacao);
        }catch(Exception ex){
            throw new ServiceException("Fila de notificações não existe!");
        }
    }
    
    @Override
    public void registrarObserver(INotificacaoObserver obs) throws ServiceException {
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

        
    @Override
    public void notificarObservers(String notificacao) throws ServiceException{
        for( INotificacaoObserver observer: this.observerList )
            observer.notificar("Notificação: " + notificacao);
    }
	
    
    @Override
    public int hashCode() {
        return implementYourHashCode();
    }
    
    /**
     * Trata-se basicamente de um método hasCode() porém deve-se obrigatoriamente ser implementado
     * pelas classes filhas de Usuario.
     * @return  Um valor inteiro que identifica a instância.
     */
    protected abstract int implementYourHashCode();

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        
        Usuario usuario = (Usuario) object;
        
        return !((this.id == null && usuario.id != null) || 
                 (!this.causa.equals(usuario.causa)) ||
                 (!this.login.equals(usuario.login)) ||
                 (!this.nome.equals(usuario.nome)) ||
                 (!this.notificacoes.equals(usuario.notificacoes)) ||
                 (!this.observerList.equals(usuario.observerList)) ||
                 (this.quantidadeDeMovimentacoes != usuario.quantidadeDeMovimentacoes) ||
                 (this.quantidadeTentativasIncorretasDeAcesso != usuario.quantidadeTentativasIncorretasDeAcesso) ||
                 (!this.senha.equals(usuario.senha)) ||
                 (!this.status.equals(usuario.status)) ||
                 (!this.id.equals(usuario.id))) 
                && implementYourEquals(object);
    }
    
    /**
     * Trata-se basicamente de um método equals() porém que deve-se obrigatoriamente ser implementado
     * pelas classes filhas de Usuario.
     * @param object
     * @return  Um valor boolean que, se verdadeiro, indica que object é igual à
     * implementação em questão. Caso contrário, são diferentes.
     */
    protected abstract boolean implementYourEquals(Object object);
    
}
