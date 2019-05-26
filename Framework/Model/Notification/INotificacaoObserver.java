package Notification;

public interface INotificacaoObserver {

	private INotificacaoSubject iNotificacaoSubject;

	public void notificar(String notificacao);

}
