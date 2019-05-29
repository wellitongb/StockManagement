package service;

import model.StatusSM;
import model.Usuario;
import dao.IUsuarioDAO;
import exception.ServiceException;

public abstract class BloqueioUsuario {

	public abstract void bloquear(Usuario usuario, IUsuarioDAO usuarioDAO, String causa) throws ServiceException;
	
	public void desbloquear(Usuario usuario, IUsuarioDAO usuarioDAO, String causa) throws ServiceException {		
		
		if(usuario.getStatus().equals(StatusSM.Permanente))
			throw new ServiceException("ERRO: Usuários bloqueados permanentemente não podem ter seu status alterado!");	
		
		else{
			usuario.setStatus(StatusSM.NaoBloqueado);
			usuario.setCausa(causa);
			usuarioDAO.alterar(usuario);			
		}
		
	}

}
