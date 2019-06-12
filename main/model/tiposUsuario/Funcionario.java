package model.tiposUsuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/// CLASSES PRÓPRIAS
import model.Usuario;

//Revisar classe!

/**
 * Representa um funcionário
 * @see Usuario
 */
@Entity
@Table( name = "Funcionario")
public class Funcionario extends Usuario implements Serializable {
	
    /// ATRIBUTOS ********************************************************************************
    
    @Column(nullable = true)
    private float salario = 0;

    @Enumerated(EnumType.STRING)
    private StatusFuncionario statusFuncionario;

   
    /// CONSTRUTOR *******************************************************************************
    
    public Funcionario(StatusFuncionario status){
        super();
        this.statusFuncionario = status;
    }
        
    /// GETTERS E SETTERS ************************************************************************
       
    public float getSalario() {
        return this.salario;
    }

    public void setSalario(float salario) {
    	this.salario = salario;
    }
    
    public StatusFuncionario getStatusFuncionario() {
        return statusFuncionario;
    }

    public void setStatusFuncionario(StatusFuncionario statusFuncionario) {
        this.statusFuncionario = statusFuncionario;
    }

    /// MÉTODOS **********************************************************************************
    
    @Override
    protected String implementYourToString() {
        return "salario&" + this.salario + 
               ", statusFuncionario&" + this.statusFuncionario;
    }

    @Override
    protected int implementYourHashCode() {
        int hash = 0;
        hash += ( this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    protected boolean implementYourEquals(Object object) {
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
                
        return !((this.salario != other.salario) ||
                (!this.statusFuncionario.equals(other.statusFuncionario))
                );
    }

}
