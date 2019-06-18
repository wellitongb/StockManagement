package service;

import service.ranqueamento.IRankingUsuarioStrategy;
import java.util.List;

import dao.ClienteJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
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
                    throw new ServiceException( "Operação não concluida!" );
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
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), false );  /// Verifica se existe e se houve problemna na passagem de informacao
                    validacaoUsuario.validacao(usuarioAlterado, usuarioDAO.consultarTodos(), false );   /// Verifica se existe e se houve problemna na passagem de informacao                   
                    
                    //validar os atributos segundo as regras de negocio
                    
                    Cliente clienteAlterado = (Cliente) usuarioAlterado;
                    usuarioDAO.alterar(clienteAlterado);
                    break;
                } catch (DAOException ex) {
                    if( cont == 0)
                        throw new ServiceException( "Operação não concluida!" );
                }
            }
        
        return "OK";
	}

	@Override
	public String remover(Usuario usuario) throws ServiceException{

            int cont = 3;
            
            if (!usuario.getClass().equals(Cliente.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            Cliente cliente = (Cliente) usuario;
            
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    throw new ServiceException("Usuario não existente!");
                
                } catch(ServiceException ex){
                    if(ex.getMessage().equals("Usuario existente!")){
                        while (cont > 0){
                            cont--;
                            try {                                
                                usuarioDAO.remover(cliente);
                                break;
                            } catch (DAOException ex1) {
                                if(cont == 0)
                                    throw new ServiceException( "Operação não concluida!" );
                            }
                        }
                    }
                    else{
                        throw new ServiceException(ex.getMessage());
                    }                
                }

            return "OK";
	}

	@Override
	public String consultar(Usuario usuario) throws ServiceException{

            String mensagem = "";
            
            if (!usuario.getClass().equals(Cliente.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
            } catch(ServiceException ex){
                if(ex.getMessage().equals("Usuario existente!") ){
                    Cliente clienteResultado = (Cliente) usuarioDAO.consultar(usuario.getLogin());
                    if(clienteResultado != null)
                       return clienteResultado.toString();
                    else
                        mensagem = "Operacao invalida!";
                }
                else
                    mensagem = "Cliente não existe!";
            }
	    throw new ServiceException(mensagem);
	
        }

	@Override
	public List<String> consultarTodos() throws ServiceException {
            
            List<Cliente> clientes = new ArrayList<>();
            List<Usuario> usuarios = usuarioDAO.consultarTodos();
            
            if(usuarios.isEmpty()) 
                throw new ServiceException("Não há clientes!");
            
            for(Usuario usuario: usuarios){
                clientes.add((Cliente) usuario);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(Cliente cliente: clientes){
                retornos.add(cliente.toString());
            }
            
            return retornos;
            
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
            boolean entrou = false;
            List<String> clientesDados = this.consultarTodos();
            List<String> clientesResultado = new ArrayList<>();
            List<String> teste = new ArrayList<>();
            
            
            for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                    itParam.hasNext() && itKey.hasNext();) {
                String param = itParam.next();
                String key = itKey.next();
                for(String cliente: clientesDados){
                    String atributos[] = cliente.split(".");
                    for(String atributoValor : atributos){
                        if(atributoValor.matches(param)){
                            if(atributoValor.matches(key)){
                                teste.add(cliente);
                                break;
                            }
                        }
                    }    
                }
                
                if(!entrou && clientesResultado.isEmpty()){
                    entrou = true;
                    clientesResultado.addAll(teste);
                }
                else 
                    clientesResultado.retainAll(teste);
               
                teste.clear();
            }
            
            return clientesResultado;
            
	}
        
    @Override
    public List<String> ranquear(IRankingUsuarioStrategy rankingUsuario) {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public String verNotificacao(Usuario usuario) throws ServiceException {
        return usuario.getNoficacao();
    }

    @Override
    public void notificar(String notificacao, Usuario usuario) throws ServiceException {
        usuario.notificar(notificacao);
    }
   
}
