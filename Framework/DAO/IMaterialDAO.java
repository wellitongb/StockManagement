package DAO;

import Model.Material;

public interface IMaterialDAO {

	public void adicionar(Material material);

	public void alterar(Material materialAlterado);

	public void remover(Material material);

	public Material consultar(long idMaterial);

	public List<Material> consultarTodos();

}
