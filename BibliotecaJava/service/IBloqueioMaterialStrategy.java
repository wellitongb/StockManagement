package service;

import model.Material;
import dao.IMaterialDAO;

public interface IBloqueioMaterialStrategy {

	public abstract void bloquear(Material material, IMaterialDAO materialDAO, String causa);

}
