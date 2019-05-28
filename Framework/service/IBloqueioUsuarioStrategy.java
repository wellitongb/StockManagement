package service;

import model.Usuario;
import dao.IUsuarioDAO;

public interface IBloqueioUsuarioStrategy {

	public abstract void bloquear(Usuario usuario, IUsuarioDAO usuarioDAO, String causa);

}
