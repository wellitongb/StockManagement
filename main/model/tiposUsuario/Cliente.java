package model.tiposUsuario;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/// CLASSES PRÓPRIAS
import model.tiposMaterial.Livro;
import exception.ServiceException;
import java.io.Serializable;
import javax.persistence.OneToMany;
import model.Usuario;

//Revisar classe!

/**
 * Representa um cliente
 * @see Usuario
 */
@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario implements Serializable {
	
    /// ATRIBUTOS ********************************************************************************
    
    @Column(nullable = true)
    private int numeroEmprestimos = 0;

    @Column(nullable = true)
    private int numeroDevolucoes = 0;

    @OneToMany    
    private HashMap<String,String>  hMapId_DataDeEmprestimoLivros = new HashMap<>();    
    
    @OneToMany    
    private HashMap<String, ArrayList<Boolean> > hMapId_RankingLivros = new HashMap<>(); 
    
    @OneToMany    
    private ArrayList<Livro> livrosAlugados = new ArrayList<>();
    
    @OneToMany    
    private ArrayList<Boolean> rankingCliente = new ArrayList<>();

    /// CONSTRUTOR *******************************************************************************
    
    public Cliente(){ 
        super(); 
    }
    
    /// GETTERS E SETTERS ************************************************************************
            
    public ArrayList<Boolean> getRankingCliente(){
        return this.rankingCliente;
    }

    public int getRankingInt(){
        int cont = 0;
        for(boolean nivel : this.getRankingCliente() ){
            
            if(nivel) 
                cont++;
            else break;
        }
        return cont;
    }
    
    public void setRankingCliente( ArrayList<Boolean> rankingCliente ){
        this.rankingCliente.clear();
        this.rankingCliente = rankingCliente;
    }
    
    public void setRankingClienteInt( int valor) throws ServiceException{        
        if(valor < 0 || valor >5) throw new ServiceException("Valor do ranking do cliente inv�lido!");
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
        if(livrosAlugados.size() > numeroDevolucoes) throw new ServiceException("Valor de numero de devolu��es invalido!");
        this.numeroDevolucoes = numeroDevolucoes;
    }
    
    
    public ArrayList<Livro> getLivrosAlugados() {
        return this.livrosAlugados;
    }
    
    public void setLivrosAlugados(ArrayList<Livro> livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    /// MÉTODOS **********************************************************************************
        
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cliente[ id=" + id + " ]";
    }

        
}