package Service;

import Model.Usuario;
import DAO.IUsuarioDAO;

public class UsuarioService {

	/// ATRIBUTOS

	protected IUsuarioDAO usuarioDAO;
	
	/// MÉTODOS

	public abstract String adicionar(Usuario usuario);

	public abstract String alterar(Usuario usuario, Usuario usuarioAlterado);

	public abstract String remover(Usuario usuario);

	public abstract String consultar(Usuario usuario);

	public abstract List<String> consultarTodos();

	public abstract List<String> consultaEspecifica(List<String> params, List<String> keys);

	public abstract String bloquear(Usuario Usuario, IBloqueioUsuarioStrategy bloqueioUsuario, String causa);

	public abstract List<String> ranquear(IRankingUsuarioStrategy rankingUsuario);

	public abstract String validacao(Usuario usuario, ValidacaoUsuario validacaoUsuario);

	public abstract String verNotificacao();

	public abstract String entrarLoginSenha(String login, String senha);

}
