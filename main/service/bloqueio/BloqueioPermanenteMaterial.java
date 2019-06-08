package service.bloqueio;

import model.Material;
import model.StatusSM;
import dao.IMaterialDAO;
import exception.ServiceException;

public class BloqueioPermanenteMaterial extends BloqueioMaterial {

	/**
	 * @throws ServiceException 
	 * @see service.BloqueioMaterial#bloquear(Model.Material, dao.IMaterialDAO, Model.String)
	 */
	public void bloquear(Material material, IMaterialDAO materialDAO, String causa) throws ServiceException {
		material.setStatus( StatusSM.Permanente );
		material.setCausa( causa );
		materialDAO.alterar( material );
	}

}
