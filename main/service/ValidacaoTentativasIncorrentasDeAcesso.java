package service;

import exception.ServiceException;
import model.Usuario;

public class ValidacaoTentativasIncorrentasDeAcesso implements ValidacaoUsuario {

	@Override
	public String validacao(Usuario usuario, String param, String key) throws ServiceException {
		if( !param.equals("quantidadeTentativasIncorretasDeAcesso"))
			throw new ServiceException("Parametro invalido!");
		
		if( Integer.parseInt(key) < 0 || Integer.parseInt(key) > 3) 
			throw new ServiceException("Quantidade de tentativas incorretas de Acesso invalida!");
		
		return "OK";
	}
	
}
