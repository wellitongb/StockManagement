package service.bloqueio;

import model.Usuario;
import dao.IUsuarioDAO;
import model.StatusSM;

//Revisar classe!

public class BloqueioPermanenteUsuario extends BloqueioUsuario {


	/**
	 * @see service.BloqueioUsuario#bloquear(Model.Usuario, dao.IUsuarioDAO, Model.String)
	 */
	public void bloquear(Usuario usuario, IUsuarioDAO usuarioDAO, String causa) {
		usuario.setStatus( StatusSM.Permanente );
		usuario.setCausa( causa );
		usuarioDAO.alterar( usuario );
	}

}
