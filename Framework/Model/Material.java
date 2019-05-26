package Model;

import Notification.INotificacaoSubject;
import Notification.INotificacaoObserver;

public abstract class Material implements INotificacaoSubject {

	/// ATRIBUTOS

	private long idMaterial;
	private static int quantidadeMateriasTotal;
	private static int quantidadeEspacoTotal;
	private int quantidade;
	private Status status;
	private String causa;
	private double valorUnitario;
	private int DataEntrada;
	private List<INotificacaoObserver> observerList;
	
	/// GETTERS E SETTERS

	
	/// MÉTODOS

	public abstract String toString();

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
