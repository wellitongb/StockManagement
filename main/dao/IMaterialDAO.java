package dao;

import model.Material;
import exception.ServiceException;
import java.util.List;

public interface IMaterialDAO {

	public void adicionar(Material material) throws ServiceException;

	public void alterar(Material materialAlterado) throws ServiceException;

	public void remover(Material material) throws ServiceException;

	public Material consultar(long idMaterial) throws ServiceException;

	public List<Material> consultarTodos() throws ServiceException;

}
