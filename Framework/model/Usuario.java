package model;

import java.util.Queue;
import java.util.List;
import model.notification.INotificacaoObserver;
import model.notification.INotificacaoSubject;

public abstract class Usuario implements INotificacaoObserver, INotificacaoSubject {

	/// ATRIBUTOS
	
	private long idUsuario;
	private String nome;
	private String login;
	private String senha;
	private Status status;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public void setObserverList(List<INotificacaoObserver> observerList) {
		this.observerList = observerList;
	}

	public Queue<String> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(Queue<String> notificacoes) {
		this.notificacoes = notificacoes;
	}

	public int getQuantidadeTentativasIncorretasDeAcesso() {
		return quantidadeTentativasIncorretasDeAcesso;
	}

	public void setQuantidadeTentativasIncorretasDeAcesso(int quantidadeTentativasIncorretasDeAcesso) {
		this.quantidadeTentativasIncorretasDeAcesso = quantidadeTentativasIncorretasDeAcesso;
	}

	/// MÉTODOS
	
	public abstract String toString();

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

	
}
