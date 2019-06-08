package model.tiposUsuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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

    private HashMap<String, ArrayList<Boolean> > hMapRankingUsuario =  new HashMap<>();

   
    /// CONSTRUTOR *******************************************************************************
    
    public Funcionario( ){
        super();
    }
    
    /// GETTERS E SETTERS ************************************************************************
       
    public float getSalario() {
        return salario;
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

    public void setHMapRankingUsuario(HashMap<String, ArrayList<Boolean> > hMapRankingUsuario){
        this.hMapRankingUsuario = hMapRankingUsuario;
    }
    
    public HashMap<String, ArrayList<Boolean> > getHMapRankingUsuario(){
        return this.hMapRankingUsuario;
    }
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected String ImplementYourToString() {
        String myObjectInString = "";

        myObjectInString+= " " +  String.valueOf(this.salario);
        myObjectInString+= " " + this.hMapRankingUsuario.toString();

        return myObjectInString;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cliente[ id=" + id + " ]";
    }

}
