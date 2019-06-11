package service;

import service.raqueamento.IRankingUsuarioStrategy;
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

            if (!usuario.getClass().equals(Cliente.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            Cliente cliente = (Cliente) usuario;
            
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    throw new ServiceException("Usuario não existente!");
                
                } catch(ServiceException ex){
                    if(ex.getMessage().equals("Usuario existente!"))
                        try {
                            usuarioDAO.remover(cliente);
                        } catch (DAOException ex1) {
                            throw new ServiceException( "Operacao invalida" );
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
    public String verNotificacao() {
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
