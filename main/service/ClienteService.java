package service;

import service.raqueamento.IRankingUsuarioStrategy;
import service.validacao.ValidacaoUsuario;
import java.util.List;

import dao.ClienteJpaController;
import dao.IUsuarioDAO;
import exception.DAOException;
import exception.ServiceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.tiposUsuario.Cliente;
import model.Usuario;
import service.validacao.ValidacaoCliente;

//Revisar classe!

/**
 * Classe que representa as funcionalidades desempenhadas pelo cliente
 * @see Cliente
 * @see Usuario
 */
public class ClienteService extends UsuarioService{

    /// ATRIBUTOS ********************************************************************************
    
    private Usuario usuarioAtual;
    private Usuario usuarioAlterado;
    
    ClienteService(){
        this.usuarioDAO = ClienteJpaController.getInstance();
        this.validacaoUsuario = new ValidacaoCliente();
    }
    
    /// MÉTODOS **********************************************************************************

    @Override
    public String adicionar(Usuario usuario) throws ServiceException{

        try {
            validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos() );
        } catch (DAOException ex) {
            throw new ServiceException( ex.getMessage() );
        }
        
        Cliente cliente = (Cliente) usuario;

        try {	
                usuarioDAO.adicionar(cliente);
        }catch( ServiceException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

	@Override
	public String alterar(Usuario usuario, Usuario usuarioAlterado) {

		Cliente cliente = (Cliente) usuario;
		
		try {	
			usuarioDAO.alterar(cliente);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String remover(Usuario usuario) {
		Cliente cliente = (Cliente) usuario;
		
		try {	
			usuarioDAO.remover(cliente);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String consultar(Usuario usuario) {

		try {	
			Cliente cliente = (Cliente) usuarioDAO.consultar(cliente.getIdUsuario());
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public List<String> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bloquear(Usuario Usuario, BloqueioUsuario bloqueioUsuario, String causa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> ranquear(IRankingUsuarioStrategy rankingUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validacao(Usuario usuario, ValidacaoUsuario validacaoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String verNotificacao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String entrarLoginSenha(String login, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public String validacao(Usuario usuario, List<Usuario> usuarios) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notificar(String notificacao) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
