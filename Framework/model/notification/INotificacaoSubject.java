package model.notification;

public interface INotificacaoSubject {

	public void registrarObserver(INotificacaoObserver obs);

	public void desregistrarObserver(INotificacaoObserver obs);

	public void notificarObservers(String notificacao);

}
