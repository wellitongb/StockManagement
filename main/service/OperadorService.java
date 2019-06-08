package service;

import service.validacao.ValidacaoUsuario;
import java.util.List;

import model.Usuario;

public class OperadorService extends UsuarioService {

	@Override
	public String adicionar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String alterar(Usuario usuario, Usuario usuarioAlterado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remover(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String consultar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
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
