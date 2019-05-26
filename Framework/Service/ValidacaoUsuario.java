package Service;

import Model.String;
import Model.Usuario;

public interface ValidacaoUsuario {

	public abstract String validacao(Usuario usuario, String param, String key);

}
