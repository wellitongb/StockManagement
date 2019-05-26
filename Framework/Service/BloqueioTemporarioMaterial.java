package Service;

import Model.Material;
import DAO.IMaterialDAO;
import Model.String;

public class BloqueioTemporarioMaterial implements IBloqueioMaterialStrategy, IBloqueioMaterialStrategy {


	/**
	 * @see Service.IBloqueioMaterialStrategy#bloquear(Model.Material, DAO.IMaterialDAO, Model.String)
	 */
	public void bloquear(Material material, IMaterialDAO materialDAO, String causa) {

	}

}
