/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * Representa uma exceção da camada serviço
 * @see Exception
 */
public class ServiceException extends Exception{
    public ServiceException(String mensagem){
        super( mensagem );
    }
}
