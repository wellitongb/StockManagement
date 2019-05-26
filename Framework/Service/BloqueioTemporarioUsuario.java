package Service;

import Model.Usuario;
import DAO.IUsuarioDAO;
import Model.String;

public class BloqueioTemporarioUsuario implements IBloqueioMaterialStrategy, IBloqueioUsuarioStrategy {


	/**
	 * @see Service.IBloqueioUsuarioStrategy#bloquear(Model.Usuario, DAO.IUsuarioDAO, Model.String)
	 */
	public void bloquear(Usuario usuario, IUsuarioDAO usuarioDAO, String causa) {

	}

}
