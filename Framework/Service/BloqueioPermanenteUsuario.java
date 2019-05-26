package Service;

import Model.Usuario;
import DAO.IUsuarioDAO;

public class BloqueioPermanenteUsuario implements IBloqueioUsuarioStrategy {


	/**
	 * @see Service.IBloqueioUsuarioStrategy#bloquear(Model.Usuario, DAO.IUsuarioDAO, Model.String)
	 */
	public void bloquear(Usuario usuario, IUsuarioDAO usuarioDAO, String causa) {
		usuario.setStatus( Status.BloqueioPermanente );
		usuario.setCausa( causa );
		usuarioDAO.alterar( usuario );
	}

}
