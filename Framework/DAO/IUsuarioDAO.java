package DAO;

import Model.Usuario;

public interface IUsuarioDAO {

	public void adicionar(Usuario usuario);

	public void alterar(Usuario usuarioAlterado);

	public void remover(Usuario usuario);

	public Usuario consultar(String login);

	public List<Usuario> consultarTodos();

}
