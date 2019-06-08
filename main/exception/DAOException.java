/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * Representa uma exceção da camada dao
 * @see Exception
 */
public class DAOException extends Exception{
    public DAOException(String mensagem){
        super( mensagem );
    }
}
