package service;

import exception.ServiceException;
import model.Livro;
import model.Material;

public class ValidacaoQuantidadeDeExemplaresEmprestados implements ValidacaoMaterial{

	@Override
	public String validacao(Material material, String param, String key) throws ServiceException {
		
		if ( !material.getClass().equals(Livro.class) ) 
			throw new ServiceException("Tipo de material invalido!");
		
		Livro livro = (Livro) material;
		
		if(!param.equals("quantidadeDeExemplaresEmprestados"))
			throw new ServiceException("Parametro invalido!");
			
		if( Integer.parseInt(key) < 0 && 
				 Integer.parseInt(key) > livro.getQuantidadeDeTotalDeExemplares() ) 
			throw new ServiceException("Quantidade inválida de exemplares emprestados!");
	     
		return "OK";

	}

}
