package service;

import java.util.List;

import service.ranqueamento.IRankingMaterialStrategy;
import dao.LivroJpaController;
import exception.DAOException;
import exception.ServiceException;
import model.Material;
import model.tiposMaterial.Livro;
import service.validacao.ValidacaoFuncionario;
import service.validacao.ValidacaoLivro;

public class LivroService extends MaterialService{

    /// CONSTRUTOR *******************************************************************************

    LivroService(){
        this.materialDAO = LivroJpaController.getInstance();
        this.validacaoMaterial = new ValidacaoLivro();
    }

    @Override
    public String adicionar(Material material) {

        Livro livro = (Livro) material;

        try {	
                materialDAO.adicionar(livro);
        }catch( DAOException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

    @Override
    public String alterar(Material material, Material materialAlterado) {

        Livro livro = (Livro) material;

        try {	
                materialDAO.alterar(livro);
        }catch( DAOException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

    @Override
    public String remover(Material material) {
        Livro livro = (Livro) material;

        try {	
                materialDAO.remover(livro);
        }catch( DAOException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

    @Override
    public String consultar(Material material) {
//
//            try {	
//                    Livro livro = (Livro) materialDAO.consultar(livro.getId());
//            }catch( DAOException serExc ) {
//                    return serExc.getMessage();
//            }

        return "OK";
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
    public List<String> ranquear(IRankingMaterialStrategy rankingMaterial) {
            // TODO Auto-generated method stub
            return null;
    }


    @Override
    public String validacao(Material material) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
