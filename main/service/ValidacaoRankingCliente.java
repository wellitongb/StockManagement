package service;

import exception.ServiceException;
import model.Cliente;
import model.Usuario;

public class ValidacaoRankingCliente implements ValidacaoUsuario{

	@Override
	public String validacao(Usuario usuario, String param, String key) throws ServiceException {
		
		if ( !usuario.getClass().equals(Cliente.class) ) 
			throw new ServiceException("Tipo de usuario invalido!");
		
		if(!param.equals("rankingCliente"))
			throw new ServiceException("Parametro invalido!");
			
		if(Integer.parseInt(key) < 0 || Integer.parseInt(key) > 5 ) 
	            throw new ServiceException("Valor do ranking do cliente inválido!");
	     
		return "OK";
	}

}
