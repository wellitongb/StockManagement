package service;

import java.util.List;

import dao.ClienteDAOJDBC;
import dao.ClienteDAOJDBC;
import exception.ServiceException;
import model.Cliente;
import model.Usuario;

//Revisar classe!

public class ClienteService extends UsuarioService{

	ClienteService(){
		this.usuarioDAO = new ClienteDAOJDBC();
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
