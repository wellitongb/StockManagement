package model;

import java.util.ArrayList;
import java.util.HashMap;

//Revisar classe!

public class Funcionario extends Usuario{
	 
	private HashMap<String, ArrayList<Boolean> > hMapRankingUsuario =  new HashMap<>();
	private float salario = 0;
	private StatusFuncionario statusFuncionario;
	
	public Funcionario( ){
        super();
    }
    
    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
    	this.salario = salario;
    }
    
    public void setHMapRankingUsuario(HashMap<String, ArrayList<Boolean> > hMapRankingUsuario){
        this.hMapRankingUsuario = hMapRankingUsuario;
    }
    
    public HashMap<String, ArrayList<Boolean> > getHMapRankingUsuario(){
        return this.hMapRankingUsuario;
    }
	
	@Override
	protected String ImplementYourToString() {
		String myObjectInString = "";
		
		myObjectInString+= " " +  String.valueOf(this.salario);
		myObjectInString+= " " + this.hMapRankingUsuario.toString();
		
		return myObjectInString;
	}

	public StatusFuncionario getStatusFuncionario() {
		return statusFuncionario;
	}

	public void setStatusFuncionario(StatusFuncionario statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}

}
