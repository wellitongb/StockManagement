package model.notification;

import exception.ServiceException;

public interface INotificacaoSubject {

    /**
     * Registra um observador, que no framework é vinculado a classe usuario.
     * @param  obs  Observador a ser registrado(podendo ser material ou usuario).
     * @throws exception.ServiceException
     */
    public void registrarObserver(INotificacaoObserver obs) throws ServiceException;
    
    /**
     * Desregistra um observador, que no framework é vinculado a classe usuario.
     * @param obs Observador a ser desregistrado(podendo ser material ou usuario).
     * @throws exception.ServiceException
     */
    public void desregistrarObserver(INotificacaoObserver obs) throws ServiceException;

    /**
     * Notifica todos observadores registrados com um mensagem de notificacão.
     * @param notificacao Mensagem a ser notificada.
     * @throws exception.ServiceException
     */
    public void notificarObservers(String notificacao) throws ServiceException;

}
