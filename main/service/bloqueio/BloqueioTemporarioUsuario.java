package service.bloqueio;

import model.Status;
import model.Usuario;
import dao.IUsuarioDAO;

//Revisar classe!

public class BloqueioTemporarioUsuario implements IBloqueioUsuarioStrategy {


	/**
	 * @see service.IBloqueioUsuarioStrategy#bloquear(Model.Usuario, dao.IUsuarioDAO, Model.String)
	 */
	public void bloquear(Usuario usuario, IUsuarioDAO usuarioDAO, String causa) {
		usuario.setStatus( Status.Temporario );
		usuario.setCausa( causa );
		usuarioDAO.alterar( usuario );
	}

}
