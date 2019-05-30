package service;

import exception.ServiceException;
import model.Cliente;
import model.Usuario;

public class ValidacaoNumeroDevolucoes implements ValidacaoUsuario{

	@Override
	public String validacao(Usuario usuario, String param, String key) throws ServiceException {
		if ( !usuario.getClass().equals(Cliente.class) ) 
			throw new ServiceException("Tipo de usuario invalido!");
		
		Cliente cliente = (Cliente) usuario;
		
		if(!param.equals("numeroDevolucoes"))
			throw new ServiceException("Parametro invalido!");
			
		if(Integer.parseInt(key) < cliente.getLivrosAlugados().size()) 
	            throw new ServiceException("Valor de numero de devoluções invalido!");
	     
		return "OK";
	}

}
