package service;

import model.Material;
import dao.IMaterialDAO;
import model.Status;

public class BloqueioTemporarioMaterial implements IBloqueioMaterialStrategy {

	/**
	 * @see service.IBloqueioMaterialStrategy#bloquear(Model.Material, dao.IMaterialDAO, Model.String)
	 */
	public void bloquear(Material material, IMaterialDAO materialDAO, String causa) {
		material.setStatus( Status.Temporario );
		material.setCausa( causa );
		materialDAO.alterar( material );
	}

}
