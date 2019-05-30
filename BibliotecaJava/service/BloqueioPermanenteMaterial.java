package service;

import model.Material;
import model.StatusSM;
import dao.IMaterialDAO;

public class BloqueioPermanenteMaterial extends BloqueioMaterial {

	/**
	 * @see service.BloqueioMaterial#bloquear(Model.Material, dao.IMaterialDAO, Model.String)
	 */
	public void bloquear(Material material, IMaterialDAO materialDAO, String causa) {
		material.setStatus( StatusSM.Permanente );
		material.setCausa( causa );
		materialDAO.alterar( material );
	}

}
