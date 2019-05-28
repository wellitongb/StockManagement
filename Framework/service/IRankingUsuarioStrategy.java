package service;

import java.util.List;
import model.Usuario;

public interface IRankingUsuarioStrategy {

	public List<Usuario> ranquear(List<Usuario> usuarios);

}
