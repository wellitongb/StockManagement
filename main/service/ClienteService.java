package service;

import service.raqueamento.IRankingUsuarioStrategy;
import service.validacao.ValidacaoUsuario;
import java.util.List;

import dao.ClienteJpaController;
import exception.ServiceException;
import model.tiposUsuario.Cliente;
import model.Usuario;

//Revisar classe!

/**
 * Classe que representa as funcionalidades desempenhadas pelo cliente
 * @see Cliente
 */
public class ClienteService extends UsuarioService{

	ClienteService(){
		this.usuarioDAO = ClienteJpaController.getInstance();
	}
	
	@Override
	public String adicionar(Usuario usuario) {
	
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

}
