package Service;

import DAO.IUsuarioDAO;
import Model.Usuario;
import Model.String;

public interface IBloqueioUsuarioStrategy {

	public abstract void bloquear(Usuario usuario, IUsuarioDAO usuarioDAO, String causa);

}
