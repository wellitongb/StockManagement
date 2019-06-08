package service.bloqueio;

import model.Material;
import model.StatusSM;
import dao.IMaterialDAO;
import exception.ServiceException;

public abstract class BloqueioMaterial {

	public abstract void bloquear(Material material, IMaterialDAO materialDAO, String causa) throws ServiceException;
	
	
	/**
	 * @throws ServiceException 
	 * @see service.BloqueioMaterial#bloquear(Model.Material, dao.IMaterialDAO, Model.String)
	 */
	public void desbloquear(Material material, IMaterialDAO materialDAO, String causa) throws ServiceException {		
		
		if(material.getStatus().equals(StatusSM.Permanente))
			throw new ServiceException("ERRO: Materiais bloqueados permanentemente não podem ter seu status alterado!");	
		
		else{
			material.setStatus(StatusSM.NaoBloqueado);
			material.setCausa(causa);
			materialDAO.alterar(material);			
		}
		
	}
	
}
