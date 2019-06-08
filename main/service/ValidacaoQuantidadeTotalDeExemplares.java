package service;

import service.validacao.ValidacaoMaterial;
import model.Material;
import model.tiposMaterial.Livro;
import exception.ServiceException;

public class ValidacaoQuantidadeTotalDeExemplares implements ValidacaoMaterial {

	@Override
	public String validacao(Material material, String param, String key) throws ServiceException {

		if ( !material.getClass().equals(Livro.class) ) 
			throw new ServiceException("Tipo de material invalido!");
		
		Livro livro = (Livro) material;
		
		if(!param.equals("quantidadeDeTotalDeExemplares"))
			throw new ServiceException("Parametro invalido!");
			
		if( Integer.parseInt(key) < livro.getQuantidadeDeExemplaresEmprestados() ) 
	            throw new ServiceException("Quantidade inv�lida de exemplares!");
	     
		return "OK";
	}

}
