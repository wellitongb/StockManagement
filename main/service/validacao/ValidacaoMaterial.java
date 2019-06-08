package service.validacao;

import model.Material;
import exception.ServiceException;
import model.StatusSM;

public class ValidacaoMaterial {

	/// MÉTODOS

	public String validacao(Material material) throws ServiceException{
            
            if(material.getIdMaterial() < 0){
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
