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
        
        if(cliente.getRankingCliente() < 0 || cliente.getRankingCliente() >5) 
            throw new ServiceException("ranking de cliente é invalido!");
        
                        
        
        if(cliente.getHMapId_DataDeEmprestimoLivros() == null||
           (cliente.getHMapId_DataDeEmprestimoLivros().size() > cliente.getLivrosAlugados().size()) ){
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
        
        
        if(cliente.getHMapId_RankingLivros() == null ||
           (cliente.getHMapId_RankingLivros().size() > cliente.getLivrosAlugados().size()) ){
            throw new ServiceException("Lista ID - Ranking de Livros inválida!");
        }
        else{
            for(Map.Entry<String, Integer> objeto : 
                    cliente.getHMapId_RankingLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                Integer RankingLivro = objeto.getValue();
                
                if(RankingLivro == null || (RankingLivro < 0 && RankingLivro > 5))
                    throw new ServiceException("Lista ID - Ranking de Livros inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(cliente.getLivrosAlugados() == null){
            throw new ServiceException("Lista ID de Livros Alugados inválida!");
        }
        
        if(cliente.getNumeroDevolucoes() < 0 && 
           cliente.getNumeroDevolucoes() > cliente.getLivrosAlugados().size())
            throw new ServiceException("Número de devoluções inválido!");
        
        if(cliente.getNumeroLivrosPendentes() < 0 && 
           cliente.getNumeroLivrosPendentes() > 
                cliente.getLivrosAlugados().size())
            throw new ServiceException("Número de livros pendentes inválido!");
        
        if(cliente.getNumeroEmprestimos() < 0 &&
           cliente.getNumeroEmprestimos() != cliente.getLivrosAlugados().size() &&
           cliente.getNumeroEmprestimos() != (cliente.getNumeroDevolucoes() + 
                cliente.getNumeroLivrosPendentes()) )
            throw new ServiceException("Número de emprestimos inválido!");
        
    }
    
}
