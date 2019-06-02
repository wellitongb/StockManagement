package dao;

import model.Usuario;
import exception.DAOException;
import java.util.List;

public interface IUsuarioDAO {

	public void adicionar(Usuario usuario) throws DAOException;

	public void alterar(Usuario usuarioAlterado) throws DAOException;

	public void remover(Usuario usuario) throws DAOException;

	public Usuario consultar(String login) throws DAOException;

	public List<Usuario> consultarTodos() throws DAOException;

}
