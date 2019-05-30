package model;

import java.util.ArrayList;
import java.util.HashMap;
import exception.ServiceException;

//Revisar classe!

public class Cliente extends Usuario {
	
	/// ATRIBUTOS
	
	private int numeroEmprestimos = 0;
	private int numeroDevolucoes = 0;
	private HashMap<String,String>  hMapId_DataDeEmprestimoLivros = new HashMap<>();    
    private HashMap<String, ArrayList<Boolean> > hMapId_RankingLivros = new HashMap<>(); 
    private ArrayList<Livro> livrosAlugados = new ArrayList<>();
    private ArrayList<Boolean> rankingCliente = new ArrayList<>();
	
    /// CONSTRUTORES 
    
    public Cliente(){
    	
    	super();
    	
    }
    
    /// GETTERS E SETTERS
    
    public ArrayList<Boolean> getRankingCliente(){
        return this.rankingCliente;
    }

    public int getRankingInt(){
        int cont = 0;
        for(boolean nivel : this.getRankingCliente()){
            if(nivel) cont++;
            else break;
        }
        return cont;
    }
    
    public void setRankingCliente( ArrayList<Boolean> rankingCliente ){
        this.rankingCliente.clear();
        this.rankingCliente = rankingCliente;
    }
    
    public void setRankingClienteInt( int valor) throws ServiceException{        
        if(valor < 0 || valor >5) throw new ServiceException("Valor do ranking do cliente inválido!");
        else {
            this.rankingCliente.set(0, false);
            this.rankingCliente.set(1, false);
            this.rankingCliente.set(2, false);
            this.rankingCliente.set(3, false);
            this.rankingCliente.set(4, false);
            for(int i = 0; i < valor; i++){
                this.rankingCliente.set(i, true);
            }
        }
    }
    
    public void setHMapId_RankingLivros(HashMap<String, ArrayList<Boolean> > 
            hMapId_DataDeEmprestimoLivros){
        this.hMapId_RankingLivros = hMapId_DataDeEmprestimoLivros;
    }
    
    public void setHMapId_DataDeEmprestimoLivros(HashMap<String, String> 
           hMapId_DataDeEmprestimoLivros){
        this.hMapId_DataDeEmprestimoLivros = hMapId_DataDeEmprestimoLivros;
    }
    
    public HashMap<String, ArrayList<Boolean> > getHMapId_RankingLivros(){
        return this.hMapId_RankingLivros;
    }
    
    public HashMap<String, String> getHMapId_DataDeEmprestimoLivros(){
        return this.hMapId_DataDeEmprestimoLivros;
    }
     

    public int getNumeroEmprestimos() {
        return this.numeroEmprestimos;
    }

    public void setNumeroEmprestimos(int numeroEmprestimos) throws ServiceException {
        if(livrosAlugados.size() > numeroEmprestimos) throw new ServiceException("Valor de numero de emprestimos invalido!");
        this.numeroEmprestimos = numeroEmprestimos;
    }

    public int getNumeroDevolucoes() {
        return this.numeroDevolucoes;
    }

    public void setNumeroDevolucoes(int numeroDevolucoes) throws ServiceException {
        if(livrosAlugados.size() > numeroDevolucoes) throw new ServiceException("Valor de numero de devoluções invalido!");
        this.numeroDevolucoes = numeroDevolucoes;
    }
    
    
    public ArrayList<Livro> getLivrosAlugados() {
        return this.livrosAlugados;
    }
    
    public void setLivrosAlugados(ArrayList<Livro> livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    /// METODOS
    
	@Override
	protected String ImplementYourToString() {		
		String myObjectInString = "";
		
		myObjectInString+= " " +  this.getHMapId_DataDeEmprestimoLivros().toString();
		myObjectInString+= " " + this.getHMapId_RankingLivros();
		myObjectInString+= " " + this.getLivrosAlugados();
		myObjectInString+= " " + String.valueOf(this.numeroDevolucoes);
		myObjectInString+= " " + String.valueOf(this.numeroEmprestimos);
		myObjectInString+= " " + this.rankingCliente.toString();
		
		return myObjectInString;
	} 
}
