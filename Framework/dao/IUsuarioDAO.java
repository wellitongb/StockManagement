package dao;

import model.Usuario;
import exception.ServiceException;
import java.util.List;

public interface IUsuarioDAO {

	public void adicionar(Usuario usuario) throws ServiceException;

	public void alterar(Usuario usuarioAlterado) throws ServiceException;

	public void remover(Usuario usuario) throws ServiceException;

	public Usuario consultar(String login) throws ServiceException;

	public List<Usuario> consultarTodos() throws ServiceException;

}
