/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import java.util.ArrayList;
import java.util.Map;
import model.Data;
import model.Usuario;
import model.tiposUsuario.Cliente;

/**
 * Representa uma validacao do usuario cliente
 */
public class ValidacaoCliente extends ValidacaoUsuario{
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Usuario usuario) throws ServiceException {
        if ( !usuario.getClass().equals(Cliente.class) ) 
            throw new ServiceException("Tipo de usuário inválido!");
        
        Cliente cliente = (Cliente) usuario;
        
        if(cliente.getRankingCliente().size() != 5 ||
           cliente.getRankingInt() < 0 || cliente.getRankingInt() > 5){
            throw new ServiceException("ranking de cliente passado é invalido!");
        }
                        
        
        if(cliente.getHMapId_DataDeEmprestimoLivros() == null){
            throw new ServiceException("Lista ID - Data De Empréstimo inválida!");
        }
        else{
            for(Map.Entry<String, ArrayList<Data>> objeto : 
                    cliente.getHMapId_DataDeEmprestimoLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                ArrayList<Data> listaDatasEmprestimos = objeto.getValue();
                
                if(listaDatasEmprestimos == null || listaDatasEmprestimos.isEmpty())
                    throw new ServiceException("Lista ID - Data De Empréstimo inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(cliente.getHMapId_RankingLivros() == null){
            throw new ServiceException("Lista ID - Ranking de Livros inválida!");
        }
        else{
            for(Map.Entry<String, ArrayList<Boolean>> objeto : 
                    cliente.getHMapId_RankingLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                ArrayList<Boolean> RankingLivro = objeto.getValue();
                
                if(RankingLivro == null || RankingLivro.size() == 5)
                    throw new ServiceException("Lista ID - Ranking de Livros inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(cliente.getLivrosAlugados() == null){
            throw new ServiceException("Lista ID de Livros Alugados inválida!");
        }
        
        if(cliente.getNumeroDevolucoes() < 0)
            throw new ServiceException("Número de devoluções inválido!");
        
        if(cliente.getNumeroEmprestimos() < 0)
            throw new ServiceException("Número de emprestimos inválido!");
        
        if(cliente.getNumeroLivrosPendentes() < 0 && 
           cliente.getNumeroLivrosPendentes() > cliente.getNumeroEmprestimos())
            throw new ServiceException("Número de emprestimos inválido!");
        
    }
    
}
