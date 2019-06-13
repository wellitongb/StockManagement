package model.tiposUsuario;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/// CLASSES PRÓPRIAS
import exception.ServiceException;
import java.io.Serializable;
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
    private HashMap<String, ArrayList<Data> >  hMapId_DataDeEmprestimoLivros;    
    
    @OneToMany    
    private HashMap<String, Integer > hMapId_RankingLivros; 
    
    @OneToMany    
    private ArrayList<String> livrosAlugados;
    
    @Column(nullable = true)
    private Integer rankingCliente;

    /// CONSTRUTOR *******************************************************************************
    
    public Cliente(){
        super();
        this.livrosAlugados = new ArrayList<>();
        this.hMapId_RankingLivros = new HashMap<>();
        this.hMapId_DataDeEmprestimoLivros = new HashMap<>();
//        this.rankingCliente = new ArrayList<>();
//        for(int i = 0; i < 5;i++)
//            this.rankingCliente.add(false);
    }
    
    /// GETTERS E SETTERS ************************************************************************
                
    public Integer getRankingCliente(){
        return this.rankingCliente;
    }

//    public int getRankingInt(){
//        int cont = 0;
//        for(boolean nivel : this.getRankingCliente() ){
//            
//            if(nivel) 
//                cont++;
//            else break;
//        }
//        return cont;
//    }
    
    public void setRankingCliente(Integer rankingCliente ){
        this.rankingCliente = rankingCliente;
    }
//    public void setRankingClienteInt( int valor) throws ServiceException{        
//        if(valor < 0 || valor >5) throw new ServiceException("ranking de cliente passado é invalido!");
//        else {
//            this.rankingCliente.set(0, false);
//            this.rankingCliente.set(1, false);
//            this.rankingCliente.set(2, false);
//            this.rankingCliente.set(3, false);
//            this.rankingCliente.set(4, false);
//            for(int i = 0; i < valor; i++){
//                this.rankingCliente.set(i, true);
//            }
//        }
//    }
    
//    public void setHMapId_RankingLivros(HashMap<String, Integer > 
//            hMapId_RankingLivros){
//        this.hMapId_RankingLivros = hMapId_RankingLivros;
//    }
    
//    public void setHMapId_DataDeEmprestimoLivros(HashMap<String, ArrayList<Data> > 
//           hMapId_DataDeEmprestimoLivros){
//        this.hMapId_DataDeEmprestimoLivros = hMapId_DataDeEmprestimoLivros;
//    }
    
    public HashMap<String, Integer > getHMapId_RankingLivros(){
        return this.hMapId_RankingLivros;
    }
    
    public HashMap<String, ArrayList<Data> > getHMapId_DataDeEmprestimoLivros(){
        return this.hMapId_DataDeEmprestimoLivros;
    }
     

    public int getNumeroEmprestimos() {
        return this.numeroEmprestimos;
    }

    public void setNumeroEmprestimos(int numeroEmprestimos) throws ServiceException {
//        if(livrosAlugados.size() == numeroEmprestimos) 
//            throw new ServiceException("Valor de numero de emprestimos invalido!");
        this.numeroEmprestimos = numeroEmprestimos;
    }

    public int getNumeroDevolucoes() {
        return this.numeroDevolucoes;
    }

    public void setNumeroDevolucoes(int numeroDevolucoes) throws ServiceException {
//        if(livrosAlugados.size() >= numeroDevolucoes) 
//            throw new ServiceException("Valor de numero de devoluções invalido!");
        this.numeroDevolucoes = numeroDevolucoes;
    }
    
    public int getNumeroLivrosPendentes(){
        return this.numeroLivrosPendentes;
    }
    
    public void setNumeroLivrosPendentes(int numeroLivrosPendentes){
        this.numeroLivrosPendentes = numeroLivrosPendentes;
    }
    
    public ArrayList<String> getLivrosAlugados() {
        return this.livrosAlugados;
    }
    
//    public void setLivrosAlugados(ArrayList<String> livrosAlugados) {
//        this.livrosAlugados = livrosAlugados;
//    }

    /// MÉTODOS **********************************************************************************
        
    
    public void adicionarDataDeEmprestimo(String idLivro, Data dataDeEmprestimo) throws ServiceException{
        ArrayList<Data> dataEmprestimos = this.hMapId_DataDeEmprestimoLivros.get(idLivro);
        
        if(this.hMapId_DataDeEmprestimoLivros.get(idLivro) != null){
            if(dataEmprestimos.contains(dataDeEmprestimo)){
                throw new ServiceException("Já há essa data de emprestimo!");
            }
            else{
                dataEmprestimos.add(dataDeEmprestimo);
                this.hMapId_DataDeEmprestimoLivros.put(idLivro, dataEmprestimos);
            }
        }
        else{
            dataEmprestimos = new ArrayList<>();
            dataEmprestimos.add(dataDeEmprestimo);
            this.hMapId_DataDeEmprestimoLivros.put(idLivro, dataEmprestimos);
        }
    }
    
    public void removerDataDeEmprestimo(String idLivro, Data dataDeEmprestimo) throws ServiceException{
        ArrayList<Data> dataEmprestimos = this.hMapId_DataDeEmprestimoLivros.get(idLivro);
        
        if(this.hMapId_DataDeEmprestimoLivros.get(idLivro) != null){
            if(dataEmprestimos.contains(dataDeEmprestimo)){
                dataEmprestimos.remove(dataDeEmprestimo);
                if(dataEmprestimos.isEmpty())
                    this.hMapId_DataDeEmprestimoLivros.remove(idLivro);
                else
                    this.hMapId_DataDeEmprestimoLivros.put(idLivro, dataEmprestimos);
                return;
            }
        }        
        
        throw new ServiceException("Não há essa data de emprestimo!");
    }
    
    public void adicionarRankingLivro(String idLivro, int ranking) throws ServiceException{
        Integer valor = this.hMapId_RankingLivros.get(idLivro);
        
        if(this.hMapId_RankingLivros.get(idLivro) != null){
            if(ranking != valor){
                if(ranking >= 0 && ranking <= 5)
                    this.hMapId_RankingLivros.put(idLivro, valor);
                else
                    throw new ServiceException("Ranking do livro é inválido!");
            }
        }
        else{
            if(ranking >= 0 && ranking <= 5){
                valor = ranking;
                this.hMapId_RankingLivros.put(idLivro, valor);
            }
            else
                throw new ServiceException("Ranking do livro é inválido!");
        }
    }
    
    public void alterarRankingLivro(String idLivro, int ranking) throws ServiceException{
        adicionarRankingLivro(idLivro, ranking);
    }
    
    public void removerRankingLivro(String idLivro) throws ServiceException{
        Integer valor = this.hMapId_RankingLivros.get(idLivro);
        
        if(valor != null){
            this.hMapId_RankingLivros.remove(idLivro);
            return;
        }
        
        throw new ServiceException("Não há ranking para esse livro!");
    }
    
    public void adicionarLivroAlugado(String idLivro) throws ServiceException{
        if(this.livrosAlugados.contains(idLivro))
            throw new ServiceException("Livro já consta como alugado!");
        else
            this.livrosAlugados.add(idLivro);
    }
    
    public void RemoverLivroAlugado(String idLivro) throws ServiceException{
        if(!this.livrosAlugados.remove(idLivro))
            throw new ServiceException("Esse livro não consta como alugado!");
    }
    
    @Override
    protected String implementYourToString() {
        return "numeroEmprestimos&" + numeroEmprestimos + 
               ", numeroDevolucoes&" + numeroDevolucoes + 
               ", numeroLivrosPendentes&" + numeroLivrosPendentes + 
               ", hMapId_DataDeEmprestimoLivros&" + hMapId_DataDeEmprestimoLivros +
               ", hMapId_RankingLivros&" + hMapId_RankingLivros + 
               ", livrosAlugados&" + livrosAlugados + 
               ", rankingCliente&" + rankingCliente;
    }
        
        
    @Override
    protected int implementYourHashCode() {
        int hash = 0;
        hash += ( this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    protected boolean implementYourEquals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
                
        return !((this.livrosAlugados != other.livrosAlugados) ||
                 (this.numeroDevolucoes != other.numeroDevolucoes) ||
                 (this.numeroEmprestimos != other.numeroEmprestimos) ||
                 (this.numeroLivrosPendentes != other.numeroLivrosPendentes) ||
                 (!this.rankingCliente.equals(other.rankingCliente)) ||
                 (!this.hMapId_DataDeEmprestimoLivros.equals(other.hMapId_DataDeEmprestimoLivros)) ||
                 (!this.hMapId_RankingLivros.equals(other.hMapId_RankingLivros)));
    }
        
}
