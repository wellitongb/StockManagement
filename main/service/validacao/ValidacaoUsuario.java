package service.validacao;

import exception.ServiceException;
import java.util.List;
import model.StatusSM;
import model.Usuario;

/**
 * 
 * @author wellitongb
 */
public class ValidacaoUsuario {

    /**
     *
     * @param usuario
     * @param usuarios
     * @return 
     * @throws ServiceException
     */
    public String validacao(Usuario usuario, List<Usuario> usuarios) throws ServiceException{
            
            verificacaoCaracterLogin(usuario.getLogin(), usuarios);            
            
            if(5 <= usuario.getSenha().length() && 
                    usuario.getSenha().length() <= 12 &&
                    !usuario.getSenha().matches("[a-zA-Z[0-9]]")){
                throw new 
                ServiceException("Senha inválida!");
            }
                        
            if(3 <= usuario.getSenha().length() &&
                    !usuario.getNome().matches("[a-zA-Z[0-9][ \\t\\n\\x0B\\f\\r]]")){
                throw new 
                ServiceException("Nome inválido!");
            }
                       
            
            if(usuario.getQuantidadeTentativasIncorretasDeAcesso() < 0 ||
               usuario.getQuantidadeTentativasIncorretasDeAcesso() > 2){
                throw new 
                ServiceException("Quantidade de tentativas incorretas "
                        + "de acesso inválida!");
            }
            
            
            if(usuario.getId() < 0){
                throw new ServiceException("Usuario criado com ID errado!");
            }
            
            
            if(usuario.getQuantidadeDeMovimentacoes() < 0){
                throw new ServiceException("Quantidade de movimentações"
                        + " inválida!");
            }
            
            if((usuario.getStatus() == StatusSM.NaoBloqueado && 
                    !usuario.getCausa().equals(" "))
                    ||
                    (usuario.getStatus() != StatusSM.NaoBloqueado &&
                    usuario.getCausa().equals(" "))){
                throw new 
                ServiceException("Usuario possui status ou causa indevida!");
            }
                      
            if(usuario.getNotificacoes()!= null && 
                    !usuario.getNotificacoes().isEmpty()){
                for(String notificacao: usuario.getNotificacoes()){
                    if(notificacao.length() <= 0 ||
                       !notificacao.contains("Notificacao:"))
                        throw new 
                        ServiceException("Usuario possui status ou causa indevida!");
                }
            }
            
            usuario.getObserverList();
            
            
            
            
            
            return "OK";
        }
        
        private void verificacaoCaracterLogin(String palavra, List<Usuario> usuarios) throws ServiceException{
         
            if(!palavra.matches("[a-zA-Z[0-9]]")){
                throw new 
                ServiceException("Login inválido!");
            }
            
            for (Usuario usuario : usuarios) {
                if(palavra.matches(usuario.getLogin()))
                    throw new 
                    ServiceException("Login inválido!");
            }
            
        }
        
}
