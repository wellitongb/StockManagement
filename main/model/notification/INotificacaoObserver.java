package model.notification;

import exception.ServiceException;

public interface INotificacaoObserver {

	public void notificar(String notificacao) throws ServiceException ;

}
