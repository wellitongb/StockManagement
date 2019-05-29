package service;

import model.Material;
import exception.ServiceException;

public interface ValidacaoMaterial {

	/// MÉTODOS

	public String validacao(Material material, String param, String key) throws ServiceException;

}
