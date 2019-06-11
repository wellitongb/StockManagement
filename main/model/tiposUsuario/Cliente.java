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
import java.util.Map;
import javax.persistence.OneToMany;
import model.Usuario;
import model.Data;

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
    
    @Column(nullable = true)
    private int numeroLivrosPendentes = 0;

    @OneToMany    
    private HashMap<String, ArrayList<Data> >  hMapId_DataDeEmprestimoLivros = new HashMap<>();    
    
    @OneToMany    
    private HashMap<String, ArrayList<Boolean> > hMapId_RankingLivros = new HashMap<>(); 
    
    @OneToMany    
    private ArrayList<String> livrosAlugados = new ArrayList<>();
    
    @OneToMany    
    private ArrayList<Boolean> rankingCliente;

    /// CONSTRUTOR *******************************************************************************
    
    public Cliente(){ 
        super();
        this.rankingCliente = new ArrayList<>();
        for(int i = 0; i < 5;i++)
            this.rankingCliente.add(false);
    }
    
    /// GETTERS E SETTERS ************************************************************************
            
     
    public int getNumeroLivrosPendentes(){
        return this.numeroLivrosPendentes;
    }
    
    public void setNumeroLivrosPendentes(int numeroLivrosPendentes){
        this.numeroLivrosPendentes = numeroLivrosPendentes;
    }
    
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
    /*
    public void setRankingCliente( ArrayList<Boolean> rankingCliente ){
        this.rankingCliente.clear();
        this.rankingCliente = rankingCliente;
    }
    */
    public void setRankingClienteInt( int valor) throws ServiceException{        
        if(valor < 0 || valor >5) throw new ServiceException("ranking de cliente passado é invalido!");
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
            hMapId_RankingLivros){
        this.hMapId_RankingLivros = hMapId_RankingLivros;
    }
    
    public void setHMapId_DataDeEmprestimoLivros(HashMap<String, ArrayList<Data> > 
           hMapId_DataDeEmprestimoLivros){
        this.hMapId_DataDeEmprestimoLivros = hMapId_DataDeEmprestimoLivros;
    }
    
    public HashMap<String, ArrayList<Boolean> > getHMapId_RankingLivros(){
        return this.hMapId_RankingLivros;
    }
    
    public HashMap<String, ArrayList<Data> > getHMapId_DataDeEmprestimoLivros(){
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
    
    
    public ArrayList<String> getLivrosAlugados() {
        return this.livrosAlugados;
    }
    
    public void setLivrosAlugados(ArrayList<String> livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    /// MÉTODOS **********************************************************************************
        
    @Override
    protected String ImplementYourToString() {		
        String myObjectInString = "";

        myObjectInString+= "."+ "HMapId_DataDeEmprestimoLivros:" +"|";
        
        for(Map.Entry<String, ArrayList<Data>> objeto : 
            this.getHMapId_DataDeEmprestimoLivros().entrySet()) {
                myObjectInString+= "(" + objeto.getKey() + "," + 
                        objeto.getValue() + "),";
                
            }
        //myObjectInString+= "|";

        myObjectInString+= "." + "HMapId_RankingLivros:" +"|";
        
        for(Map.Entry<String, ArrayList<Boolean>> objeto : 
            this.getHMapId_RankingLivros().entrySet()) {
                myObjectInString+= "(" + objeto.getKey() + "," + 
                        objeto.getValue() + "), ";
                
            }
        //myObjectInString+= "|";
        

        myObjectInString+= "."  + "LivrosAlugados:" + "{";
        for(String LivroID: this.getLivrosAlugados())
            myObjectInString+= LivroID + ",";
        //myObjectInString+= "}";
        
        myObjectInString+= "." + "NumeroDevolucoes:" + 
                String.valueOf(this.numeroDevolucoes);
        myObjectInString+= "." + "NumeroEmprestimos:" + 
                String.valueOf(this.numeroEmprestimos);
        
        myObjectInString+= "." + "RankingCliente:" + "{";
        for(boolean valor: this.rankingCliente)
            myObjectInString+= valor + ",";
        //myObjectInString+= "}";    
        

        return myObjectInString;
    } 
        
        
    @Override
    public int implementYourHashCode() {
        int hash = 0;
        hash += ( this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean implementYourEquals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
                
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
                (this.livrosAlugados != other.livrosAlugados) ||
                (this.numeroDevolucoes != other.numeroDevolucoes) ||
                (this.numeroEmprestimos != other.numeroEmprestimos) ||
                (this.numeroLivrosPendentes != other.numeroLivrosPendentes) ||
                (!this.rankingCliente.equals(other.rankingCliente)) ||
                (!this.hMapId_DataDeEmprestimoLivros.equals(other.hMapId_DataDeEmprestimoLivros)) ||
                (!this.hMapId_RankingLivros.equals(other.hMapId_RankingLivros))
                );
    }
        
}
