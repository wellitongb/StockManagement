package model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

//Revisar classe!

@Entity
@Table( name = "Funcionario")
public class Funcionario extends Usuario{
	
	/// ATRIBUTOS
	
	@Column(nullable = true)
	private float salario = 0;
	
	@Enumerated(EnumType.STRING)
	private StatusFuncionario statusFuncionario;
	
	private HashMap<String, ArrayList<Boolean> > hMapRankingUsuario =  new HashMap<>();
	
	public Funcionario( ){
        super();
    }
    
	/// GETTERS E SETTERS
	
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
	
    /// METODOS
    
	@Override
	protected String ImplementYourToString() {
		String myObjectInString = "";
		
		myObjectInString+= " " +  String.valueOf(this.salario);
		myObjectInString+= " " + this.hMapRankingUsuario.toString();
		
		return myObjectInString;
	}
	
}
