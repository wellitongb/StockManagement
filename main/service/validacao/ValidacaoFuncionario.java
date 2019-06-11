/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import model.Usuario;
import model.tiposUsuario.Funcionario;

/**
 * Representa uma validacao de usuario do tipo funcionario
 */
public class ValidacaoFuncionario extends ValidacaoUsuario{

    @Override
    protected void validacaoImplementacao(Usuario usuario) throws ServiceException {
        if ( !usuario.getClass().equals(Funcionario.class) ) 
            throw new ServiceException("Tipo de usuário inválido!");
        
        Funcionario funcionario = (Funcionario) usuario;
        
        if( funcionario.getSalario() < 0 )
            throw new ServiceException("salario de funcionario é invalido!");
        
        if( funcionario.getStatusFuncionario() == null)
            throw new ServiceException("Status de funcionario inválido!");
        
    }
    
}
