package service;

import exception.ServiceException;
import model.Usuario;

public class ValidacaoQuantidadeDeMovimentacoes implements ValidacaoUsuario{

	@Override
	public String validacao(Usuario usuario, String param, String key) throws ServiceException {
		if( !param.equals("quantidadeDeMovimentacoes"))
			throw new ServiceException("Parametro invalido!");
		
		if( Integer.parseInt(key) < 0 ) 
			throw new ServiceException("Quantidade de movimentações invalida!");
		
		return "OK";
	}

}
