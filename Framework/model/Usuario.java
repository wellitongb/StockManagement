package model;

import java.util.Queue;
import java.util.List;

import exception.ServiceException;

import model.notification.INotificacaoObserver;
import model.notification.INotificacaoSubject;

public abstract class Usuario implements INotificacaoObserver, INotificacaoSubject {

	/// ATRIBUTOS
	
	private long idUsuario;
	private String nome;
	private String login;
	private String senha;
	private StatusSM status;
	private int quantidadeDeMovimentacoes;
	private String causa;
	private List<INotificacaoObserver> observerList;
	private Queue<String> notificacoes;
	private int quantidadeTentativasIncorretasDeAcesso;
	
	/// GETTERS E SETTERS
	
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
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

	/// MÉTODOS
	
	protected abstract String ImplementYourToString(); 
	
	public String toString(){
		String myObjectInString = "";
		
		myObjectInString+= this.causa.toString();
		myObjectInString+= this.login.toString();
		myObjectInString+= this.nome.toString();
		myObjectInString+= this.senha.toString();
		myObjectInString+= String.valueOf(this.idUsuario);
		myObjectInString+= this.notificacoes.toString();
		myObjectInString+= this.observerList.toString();
		myObjectInString+= String.valueOf(this.quantidadeDeMovimentacoes);
		myObjectInString+= String.valueOf(this.quantidadeTentativasIncorretasDeAcesso);
		myObjectInString+= this.status.toString();
				
		myObjectInString += ImplementYourToString();
		return myObjectInString;
	}	

	/**
	 * @see Notification.INotificacaoObserver#notificar(Model.String)
	 */
	public void notificar(String notificacao) {
		notificacoes.add(notificacao);
	}

	/**
	 * @see Notification.INotificacaoSubject#registrarObserver(Notification.INotificacaoObserver)
	 */
	public void registrarObserver(INotificacaoObserver obs) {
		observerList.add(obs);
	}

	/**
	 * @see Notification.INotificacaoSubject#desregistrarObserver(Notification.INotificacaoObserver)
	 */
	public void desregistrarObserver(INotificacaoObserver obs) {
		observerList.remove(obs);
	}

	/**
	 * @see Notification.INotificacaoSubject#notificarObservers(Model.String)
	 */
	public void notificarObservers(String notificacao) {
		
		for( INotificacaoObserver i: observerList )
			i.notificar(notificacao);
	}

	public String getNoficacao() throws ServiceException{
		if(notificacoes.isEmpty()) throw new ServiceException("Não há notificações cadastradas!");
		return notificacoes.remove();
	}
	
}
