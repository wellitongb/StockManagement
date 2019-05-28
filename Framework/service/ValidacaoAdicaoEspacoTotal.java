package service;

import model.Material;
import exception.ServiceException;

public class ValidacaoAdicaoEspacoTotal implements ValidacaoMaterial {

	/// MÉTODOS

	/**
	 * @see service.ValidacaoMaterial#validacao(Model.Material, Model.String, Model.String)
	 */
	public String validacao(Material material, String param, String key) throws ServiceException{

			throw new ServiceException("ERRO!");
		
		return "";
	}

}
