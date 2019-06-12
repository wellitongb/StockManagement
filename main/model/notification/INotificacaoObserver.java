package model.notification;

import exception.ServiceException;

public interface INotificacaoObserver {

    /**
     * Adiciona uma notificação à fila de notificações do usuário
     * @param notificacao Conteúdo da notificação
     * @throws exception.ServiceException
     */
    public void notificar(String notificacao) throws ServiceException ;

}
