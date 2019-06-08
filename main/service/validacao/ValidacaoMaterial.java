package service.validacao;

import model.Material;
import exception.ServiceException;
import model.StatusSM;

public class ValidacaoMaterial {

	/// MÉTODOS

    /**
     * Método responsável por válidar todos os atributos da classe material!
     * @param material material que irá passar pela validação.  
     * @return Retorna uma String que representa a situação da validação. 
     * @throws ServiceException Dá suporte a indicação de problemas relacionados
     * a validação.
     */
    public String validacao(Material material) throws ServiceException{

        if(material.getId() < 0){
            throw new ServiceException("Material criado com ID errado!");
        }

        if((material.getStatus() == StatusSM.NaoBloqueado && 
                !material.getCausa().equals(" "))
                ||
                (material.getStatus() != StatusSM.NaoBloqueado &&
                material.getCausa().equals(" "))){
            throw new 
            ServiceException("Material possui status ou causa indevida!");

        }

        if(material.getDataEntrada() == null){
         throw new ServiceException("Material criado sem data de entrada!");
        }

        if(material.getQuantidade() < 0){
            throw new 
            ServiceException("Material criado com quantidade inválida!");
        }

        if(material.getValorUnitario() < 0){
            throw new 
            ServiceException("Material criado com valor unitário inválido!");
        }

        return "OK";
    }

}
