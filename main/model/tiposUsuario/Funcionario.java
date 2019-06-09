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

    /// MÉTODOS **********************************************************************************
    
    @Override
    protected String ImplementYourToString() {
        String myObjectInString = "";

        myObjectInString+= " " +  this.statusFuncionario.toString();
        myObjectInString+= " " +  String.valueOf(this.salario);

        return myObjectInString;
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
                
        return !((this.id == null && other.id != null) || 
                (!this.causa.equals(other.causa)) ||
                (!this.login.equals(other.login)) ||
                (!this.nome.equals(other.nome)) ||
                (!this.notificacoes.equals(other.notificacoes)) ||
                (!this.observerList.equals(other.observerList)) ||
                (this.quantidadeDeMovimentacoes != other.quantidadeDeMovimentacoes) ||
                (this.quantidadeTentativasIncorretasDeAcesso != other.quantidadeTentativasIncorretasDeAcesso) ||
                (!this.senha.equals(other.senha)) ||
                (!this.status.equals(other.status)) ||
                (!this.id.equals(other.id)) ||
                (this.salario != other.salario) ||
                (!this.statusFuncionario.equals(other.statusFuncionario))
                );
    }

}
