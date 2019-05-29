package service;

import exception.ServiceException;
import model.Material;

public class ValidacaoAlteracaoEspacoTotal implements ValidacaoMaterial {

	/// MÃ‰TODOS

	public String validacao(Material material, String param, String key) throws ServiceException {

		if(!param.equals("quantidadeEspacoTotal"))
			throw new ServiceException("Parametro invalido!");
			
		if( Integer.parseInt(key) < material.getQuantidadeMateriasTotal() ) 
	            throw new ServiceException("Quantidade inválida de espaco!");
	     
		return "OK";
	}

}
