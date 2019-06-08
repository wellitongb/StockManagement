/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import model.Material;
import model.tiposMaterial.Livro;

/**
 *
 * @author wellitongb
 */
public class ValidacaoLivro extends ValidacaoMaterial{

    @Override
    protected void validacaoImplementacao(Material material) throws ServiceException {
        
        if ( !material.getClass().equals(Livro.class) ) 
            throw new ServiceException("Tipo de material inválido!");
        
        Livro livro = (Livro) material;
                
        for(String Assunto: livro.getAssunto()){
            boolean falhou = true;
            for(String tiposDeAssunto : livro.tiposDeAssuntos){
                if(Assunto.equals(tiposDeAssunto)) falhou = false;
            }
            if(falhou)
                throw new ServiceException("Tipo de assunto inválido!");
        }
                
        if(3 <= livro.getAutor().length() &&
           livro.getAutor().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
           !livro.getAutor().matches("[a-zA-Z[0-9][ \\t\\n\\x0B\\f\\r]]")){
            throw new 
                ServiceException("Autor inválido!");
        }
        
//        if(material.getDataDeLancamento() == null){
//         throw new ServiceException("Material criado sem data de entrada!");
//        }
        
        if(!(livro.getEdicao().length() > 0) &&
             livro.getEdicao().matches("[0-9][º]")){
            throw new ServiceException("Edição inválida!");
        }

        if(!(livro.getEditora().length() > 0) &&
             livro.getEditora().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
             livro.getEditora().substring(0, 1).matches("[0-9]") &&
             livro.getEditora().substring(0, 1).matches("[a-z]")){
            throw new ServiceException("Editora inválida!");
        }
        
        if(livro.getQuantidadeDeExemplaresEmprestados() < 0) 
            throw new ServiceException("Quantidade inválida de exemplares emprestados!");
        
        if(livro.getQuantidadeDeTotalDeExemplares() < 
                livro.getQuantidadeDeExemplaresEmprestados()) 
	    throw new ServiceException("Quantidade inválida de exemplares!");
                        
        if(!(livro.getTitulo().length() > 0) &&
             livro.getTitulo().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
             livro.getTitulo().substring(0, 1).matches("[0-9]") &&
             livro.getTitulo().substring(0, 1).matches("[a-z]")){
            throw new ServiceException("Titulo inválida!");
        }
                
        if(livro.getVolume() < 0){
            throw new ServiceException("Quantidade de volume inválida!");
        }
        
    }
        
}
