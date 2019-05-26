package Service;

import DAO.IMaterialDAO;
import Model.Material;
import Model.String;

public interface IBloqueioMaterialStrategy {

	public abstract void bloquear(Material material, IMaterialDAO materialDAO, String causa);

}
