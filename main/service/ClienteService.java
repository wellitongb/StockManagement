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

        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                Cliente cliente = (Cliente) usuario;
                usuarioDAO.adicionar(cliente);
                break;
            } catch (DAOException ex) {
                if( cont == 0)
                    throw new ServiceException( "Operacao invalida" );
            }
        }
        
        return "OK";
    }

	@Override
	public String alterar(Usuario usuario, Usuario usuarioAlterado) throws ServiceException {

            int cont = 3;
            while (cont > 0){
                --cont;
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), false );
                    Cliente cliente = (Cliente) usuario;
                    usuarioDAO.alterar(cliente);
                    break;
                } catch (DAOException ex) {
                    if( cont == 0)
                        throw new ServiceException( "Operacao invalida" );
                }
            }
        
        return "OK";
	}

	@Override
	public String remover(Usuario usuario) throws ServiceException{

            Cliente cliente = (Cliente) usuario;
            
            int cont = 3;
            while (cont > 0){
                --cont;
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    usuarioDAO.remover(cliente);
                    break;
                } catch (DAOException ex) {
                    if( cont == 0)
                        throw new ServiceException( "Operacao invalida" );
                } catch( ServiceException ex){
                    if( ex.getMessage().equals("Usuario existente!") )
                        try {
                            usuarioDAO.remover(cliente);
                        } catch (DAOException ex1) {
                            throw new ServiceException( "Operacao invalida" );
                    }
                    else{
                        throw new ServiceException( ex.getMessage() );
                    } 
                }
                
            }

            return "OK";
	}

	@Override
	public String consultar(Usuario usuario) throws ServiceException{

            String mensagem = "";
            Cliente cliente = (Cliente) usuario;
            
            int cont = 3;
            while (cont > 0){
                --cont;
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    break;
                } catch (DAOException ex) {
                    if( cont == 0)
                        throw new ServiceException( "Operacao invalida" );
                } catch( ServiceException ex){
                    if( ex.getMessage().equals("Usuario existente!") )
                        try {
                            usuarioDAO.consultar(cliente.getLogin());
                        } catch (DAOException ex1) {
                            throw new ServiceException( "Operacao invalida" );
                    }
                    
                    mensagem = ex.getMessage();
                } 
            }
	    throw new ServiceException( mensagem );
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
