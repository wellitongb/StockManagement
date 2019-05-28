package dao;

import model.Usuario;
import java.util.List;

public interface IUsuarioDAO {

	public void adicionar(Usuario usuario);

	public void alterar(Usuario usuarioAlterado);

	public void remover(Usuario usuario);

	public Usuario consultar(String login);

	public List<Usuario> consultarTodos();

}
