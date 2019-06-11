package service;

/// CLASSES PRÓPRIAS
import service.raqueamento.IRankingUsuarioStrategy;
import service.validacao.ValidacaoUsuario;
import model.Usuario;
import dao.IUsuarioDAO;
import exception.ServiceException;

import java.util.List;

/**
 * Classe que representa as funcionalidades desempenhadas pelo usuario 
 * @see Usuario
 * @see IUsuarioDAO
 */
public abstract class UsuarioService {

    /// ATRIBUTOS ********************************************************************************
    
    protected IUsuarioDAO usuarioDAO;

    /// MÉTODOS **********************************************************************************
    
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract String adicionar(Usuario usuario) throws ServiceException;
    
    /**
     * Altera os dados de um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser alterado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract String alterar(Usuario usuario, Usuario usuarioAlterado) throws ServiceException;
    /**
     * Remove um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser removido
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract String remover(Usuario usuario) throws ServiceException;
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract String consultar(Usuario usuario) throws ServiceException;
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract List<String> consultarTodos() throws ServiceException;
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException;
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract String bloquear(Usuario Usuario, BloqueioUsuario bloqueioUsuario, String causa);
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract List<String> ranquear(IRankingUsuarioStrategy rankingUsuario) throws ServiceException;
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract String validacao(Usuario usuario, ValidacaoUsuario validacaoUsuario) throws ServiceException;
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract String verNotificacao() throws ServiceException;
   
    public abstract 
    /**
     * Adiciona um usuario caso bem sucedido. Caso contrario, lanca-se uma excecao 
     * @param usuario   Usuario a ser adicionado
     * @return Um string contendo ok caso bem sucedido
     */
    public abstract String entrarLoginSenha(String login, String senha) throws ServiceException;

}
