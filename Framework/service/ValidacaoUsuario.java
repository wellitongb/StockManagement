package service;

import exception.ServiceException;
import model.Usuario;

public interface ValidacaoUsuario {

	public String validacao(Usuario usuario, String param, String key) throws ServiceException;

}
