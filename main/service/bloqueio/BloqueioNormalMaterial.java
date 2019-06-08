package service.bloqueio;

import model.Material;
import dao.IMaterialDAO;
import exception.ServiceException;
import model.StatusSM;
//
public class BloqueioNormalMaterial extends BloqueioMaterial {

	/**
	 * @throws ServiceException 
	 * @see service.BloqueioMaterial#bloquear(Model.Material, dao.IMaterialDAO, Model.String)
	 */
	public void bloquear(Material material, IMaterialDAO materialDAO, String causa) throws ServiceException {
		if(material.getStatus().equals(StatusSM.Permanente)) throw new ServiceException("ERRO: Materiais bloqueados permanentemente não podem ter seu status alterado!");		
		
		else{
			material.setStatus( StatusSM.bloqueado );
			material.setCausa( causa );
			materialDAO.alterar( material );
		}
	}

}
