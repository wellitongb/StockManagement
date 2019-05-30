package service;

import exception.ServiceException;
import model.Livro;
import model.Material;

public class ValidacaoVolume implements ValidacaoMaterial{

	@Override
	public String validacao(Material material, String param, String key) throws ServiceException {
		
		if ( !material.getClass().equals(Livro.class) ) 
			throw new ServiceException("Tipo de material invalido!");
				
		if(!param.equals("volume"))
			throw new ServiceException("Parametro invalido!");
		
		if(Integer.parseInt(key) > 0 ) 
            throw new ServiceException("Quantidade de volume inválida!");
		
		return "OK";
	}
	
}
