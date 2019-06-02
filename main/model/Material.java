package model;

import model.notification.INotificacaoSubject;
import model.notification.INotificacaoObserver;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//Revisar classe!

@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Material implements INotificacaoSubject {

	/// ATRIBUTOS
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long idMaterial;
	
	@Column(nullable = true)
	private int quantidade;
	
	@Column(nullable = true)
	private String causa;
	
	@Column(nullable = true)
	private double valorUnitario;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Data dataEntrada;
	
	private List<INotificacaoObserver> observerList;
	
	private StatusSM status;
	private static int quantidadeMateriasTotal;
	private static int quantidadeEspacoTotal;
	
	
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
