package service;

import model.Material;
import model.Status;
import dao.IMaterialDAO;

public class BloqueioPermanenteMaterial implements IBloqueioMaterialStrategy {

	/**
	 * @see service.IBloqueioMaterialStrategy#bloquear(Model.Material, dao.IMaterialDAO, Model.String)
	 */
	public void bloquear(Material material, IMaterialDAO materialDAO, String causa) {
		material.setStatus( Status.Permanente );
		material.setCausa( causa );
		materialDAO.alterar( material );
	}

}
