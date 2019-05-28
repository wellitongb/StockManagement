package model;

import model.notification.INotificacaoSubject;
import model.notification.INotificacaoObserver;
import java.util.List;
import java.util.Calendar;

public abstract class Material implements INotificacaoSubject {

	/// ATRIBUTOS

	private long idMaterial;
	private static int quantidadeMateriasTotal;
	private static int quantidadeEspacoTotal;
	private int quantidade;
	private Status status;
	private String causa;
	private double valorUnitario;
	private Calendar dataEntrada;
	private List<INotificacaoObserver> observerList;
	
	/// GETTERS E SETTERS
	
	public long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(long idMaterial) {
		this.idMaterial = idMaterial;
	}

	public static int getQuantidadeMateriasTotal() {
		return quantidadeMateriasTotal;
	}

	public static void setQuantidadeMateriasTotal(int quantidadeMateriasTotal) {
		Material.quantidadeMateriasTotal = quantidadeMateriasTotal;
	}

	public static int getQuantidadeEspacoTotal() {
		return quantidadeEspacoTotal;
	}

	public static void setQuantidadeEspacoTotal(int quantidadeEspacoTotal) {
		Material.quantidadeEspacoTotal = quantidadeEspacoTotal;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Calendar getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Calendar dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public List<INotificacaoObserver> getObserverList() {
		return observerList;
	}

	public void setObserverList(List<INotificacaoObserver> observerList) {
		this.observerList = observerList;
	}

	
	/// MÉTODOS

	public abstract String toString();

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
