package Model;

import Notification.INotificacaoObserver;
import Notification.INotificacaoSubject;

public abstract class Usuario implements INotificacaoObserver, INotificacaoSubject {

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
	
	public abstract String toString();

	/**
	 * @see Notification.INotificacaoObserver#notificar(Model.String)
	 */
	public void notificar(String notificacao) {

	}

	/**
	 * @see Notification.INotificacaoSubject#registrarObserver(Notification.INotificacaoObserver)
	 */
	public void registrarObserver(INotificacaoObserver obs) {

	}

	/**
	 * @see Notification.INotificacaoSubject#desregistrarObserver(Notification.INotificacaoObserver)
	 */
	public void desregistrarObserver(INotificacaoObserver obs) {

	}

	/**
	 * @see Notification.INotificacaoSubject#notificarObservers(Model.String)
	 */
	public void notificarObservers(String notificacao) {

	}

}
