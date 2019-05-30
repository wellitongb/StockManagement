package model;

import model.notification.INotificacaoSubject;
import model.notification.INotificacaoObserver;
import java.util.List;

public abstract class Material implements INotificacaoSubject {

	/// ATRIBUTOS

	private long idMaterial;
	private static int quantidadeMateriasTotal;
	private static int quantidadeEspacoTotal;
	private int quantidade;
	private StatusSM status;
	private String causa;
	private double valorUnitario;
	private Data dataEntrada;
	private List<INotificacaoObserver> observerList;
	
	
	
	/// GETTERS E SETTERS
	
	/**
	 * 
	 * @param 	
	 * @return
	 */
	public long getIdMaterial() {
		return this.idMaterial;
	}

	public void setIdMaterial(long idMaterial) {
		this.idMaterial = idMaterial;
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
	

	public void setDataEntrada(int dia, int mes, int ano) {		
		this.dataEntrada.set(dia,mes,ano);
	}

	public List<INotificacaoObserver> getObserverList() {
		return observerList;
	}

	public void setObserverList(List<INotificacaoObserver> observerList) {
		this.observerList = observerList;
	}

	
	/// MÉTODOS

	protected abstract String ImplementYourToString(); 
	
	public String toString(){
		String myObjectInString = "";
		
		myObjectInString+= this.causa.toString();
		myObjectInString+= this.dataEntrada.toString();
		myObjectInString+= String.valueOf(this.idMaterial);
		myObjectInString+= this.observerList.toString();
		myObjectInString+= this.status.toString();
		myObjectInString+= String.valueOf(this.valorUnitario);
		myObjectInString+= String.valueOf(Material.quantidadeEspacoTotal);
		myObjectInString+= String.valueOf(Material.quantidadeMateriasTotal);
				
		myObjectInString += ImplementYourToString();
		return myObjectInString;
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
