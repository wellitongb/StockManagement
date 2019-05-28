package service;

import model.Usuario;
import dao.IUsuarioDAO;
import model.Status;

public class BloqueioPermanenteUsuario implements IBloqueioUsuarioStrategy {


	/**
	 * @see service.IBloqueioUsuarioStrategy#bloquear(Model.Usuario, dao.IUsuarioDAO, Model.String)
	 */
	public void bloquear(Usuario usuario, IUsuarioDAO usuarioDAO, String causa) {
		usuario.setStatus( Status.Permanente );
		usuario.setCausa( causa );
		usuarioDAO.alterar( usuario );
	}

}
