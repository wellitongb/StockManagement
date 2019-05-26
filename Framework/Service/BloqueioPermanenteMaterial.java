package Service;

import Model.Material;
import DAO.IMaterialDAO;
import Model.Status;

public class BloqueioPermanenteMaterial implements IBloqueioUsuarioStrategy, IBloqueioMaterialStrategy {

	/**
	 * @see Service.IBloqueioMaterialStrategy#bloquear(Model.Material, DAO.IMaterialDAO, Model.String)
	 */
	public void bloquear(Material material, IMaterialDAO materialDAO, String causa) {
		material.setStatus( Status.BloqueioPermanente );
		material.setCausa( causa );
		materialDAO.alterar( material );
	}

}
