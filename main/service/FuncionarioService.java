package service;

import dao.FuncionarioJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import service.raqueamento.IRankingUsuarioStrategy;
import java.util.List;

import model.Usuario;
import model.tiposUsuario.Funcionario;
import service.validacao.ValidacaoFuncionario;

public class FuncionarioService extends UsuarioService {
    
        /// CONSTRUTOR *******************************************************************************

        FuncionarioService(){
            this.usuarioDAO = FuncionarioJpaController.getInstance();
            this.validacaoUsuario = new ValidacaoFuncionario();
        }    
    
    
	/// MÉTODOS **********************************************************************************

    @Override
    public String adicionar(Usuario usuario) throws ServiceException{

        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                Funcionario funcionario = (Funcionario) usuario;
                usuarioDAO.adicionar(funcionario);
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
                    Funcionario funcionarioAlterado = (Funcionario) usuarioAlterado;
                    usuarioDAO.alterar(funcionarioAlterado);
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
            
            if (!usuario.getClass().equals(Funcionario.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            Funcionario funcionario = (Funcionario) usuario;
            
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    throw new ServiceException("Usuario não existente!");
                
                } catch(ServiceException ex){
                    if(ex.getMessage().equals("Usuario existente!")){
                        while (cont > 0){
                            cont--;
                            try {                                
                                usuarioDAO.remover(funcionario);
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
            
            if (!usuario.getClass().equals(Funcionario.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
            } catch(ServiceException ex){
                if(ex.getMessage().equals("Usuario existente!") ){
                    Funcionario funcionarioResultado = (Funcionario) usuarioDAO.consultar(usuario.getLogin());
                    if(funcionarioResultado != null)
                       return funcionarioResultado.toString();
                    else
                        mensagem = "Operacao invalida!";
                }
                else
                    mensagem = "Funcionario não existe!";
            }
	    throw new ServiceException(mensagem);
	
        }

	@Override
	public List<String> consultarTodos() throws ServiceException {
            
            List<Funcionario> funcionarios = new ArrayList<>();
            List<Usuario> usuarios = usuarioDAO.consultarTodos();
            
            if(usuarios.isEmpty()) 
                throw new ServiceException("Não há funcionarios!");
            
            for(Usuario usuario: usuarios){
                funcionarios.add((Funcionario) usuario);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(Funcionario funcionario: funcionarios){
                retornos.add(funcionario.toString());
            }
            
            return retornos;
            
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
            boolean entrou = false;
            List<String> funcionariosDados = this.consultarTodos();
            List<String> funcionariosResultado = new ArrayList<>();
            List<String> teste = new ArrayList<>();
            
            
            for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                    itParam.hasNext() && itKey.hasNext();) {
                String param = itParam.next();
                String key = itKey.next();
                for(String funcionario: funcionariosDados){
                    String atributos[] = funcionario.split(".");
                    for(String atributoValor : atributos){
                        if(atributoValor.matches(param)){
                            if(atributoValor.matches(key)){
                                teste.add(funcionario);
                                break;
                            }
                        }
                    }    
                }
                
                if(!entrou && funcionariosResultado.isEmpty()){
                    entrou = true;
                    funcionariosResultado.addAll(teste);
                }
                else 
                    funcionariosResultado.retainAll(teste);
               
                teste.clear();
            }
            
            return funcionariosResultado;
            
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
