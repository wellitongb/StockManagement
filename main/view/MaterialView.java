/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.MaterialControl;
import model.Material;
import model.Usuario;

/**
 * Representa uma tela de material genérico
 * @see MaterialControl
 */
public abstract class MaterialView {

    /// ATRIBUTOS ********************************************************************************
    
    protected MaterialControl materialControl;
    protected Usuario usuario;
    protected Material material;

    /// CONSTRUTOR *******************************************************************************
    
     public MaterialView( ){ /** vazio */  }
    
    /// MÉTODOS **********************************************************************************
    
     /**
      * Apresenta a tela de material
      */
    public abstract void apresentacao();
     
}
