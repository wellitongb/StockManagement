package service;

import service.validacao.ValidacaoUsuario;
import exception.ServiceException;
import model.tiposUsuario.Cliente;
import model.Usuario;

public class ValidacaoNumeroEmprestimos implements ValidacaoUsuario{

	@Override
	public String validacao(Usuario usuario, String param, String key) throws ServiceException {
		if ( !usuario.getClass().equals(Cliente.class) ) 
			throw new ServiceException("Tipo de usuario invalido!");
		
		Cliente cliente = (Cliente) usuario;
		
		if(!param.equals("numeroEmprestimos"))
			throw new ServiceException("Parametro invalido!");
			
		if(Integer.parseInt(key) < cliente.getLivrosAlugados().size()) 
	            throw new ServiceException("Valor de numero de emprestimos invalido!");
	     
		return "OK";
	}

}
