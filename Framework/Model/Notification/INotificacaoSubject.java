package Notification;

public interface INotificacaoSubject {

	public abstract void registrarObserver(INotificacaoObserver obs);

	public abstract void desregistrarObserver(INotificacaoObserver obs);

	public abstract void notificarObservers(String notificacao);

}
