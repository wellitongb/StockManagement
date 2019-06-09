package model.tiposMaterial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/// CLASSES PRÓPRIAS
import model.Data;
import model.Material;

//Revisar classe!

/**
 * Representa um livro
 * @see Material
 */
@Entity
@Table (name = "Livro")
public class Livro extends Material implements Serializable{
    
   
    
/// ATRIBUTOS ********************************************************************************
    
    public final String tiposDeAssuntos[];
    
    @Column(nullable = true)
    private String edicao = "";
	
    @Column(nullable = true)
    private int volume = 0;
	
    @Column(nullable = true)
    private String editora = "";
	
    @Column(nullable = false)
    private String titulo = "";
	
    @Column(nullable = false)
    private String autor = "";
    
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Data dataDeLancamento;
    
    @Column(nullable = true)
    private int quantidadeDeTotalDeExemplares;
    
    @Column(nullable = true)
    private int quantidadeDeExemplaresEmprestados;
	
    @OneToMany    
    private ArrayList<String> assunto;
    
    /// CONSTRUTOR *******************************************************************************
    
    public Livro(){
        this.tiposDeAssuntos = new String[]{"Ficção", "Comédia", "Drama", "Apolalipse Zumbi", 
            "Horror", "Terror", "Poesia", "Conto", "Biográfia", "AutoBiográfia", 
            "Suspense", "Paródia", "Tragédia"};
    }
    
    /// GETTERS E SETTERS ************************************************************************
       
    public String getEdicao() {
        return this.edicao;
    }

    public int getVolume() {
        return this.volume;
    }

    public String getEditora() {
        return this.editora;
    }

    public ArrayList<String> getAssunto() {
        return this.assunto;
    }
       
    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }


    public String getDataDeLancamento() {
        return this.dataDeLancamento.toString();
    }
    
    public int getQuantidadeDeExemplaresEmprestados(){
        return this.quantidadeDeExemplaresEmprestados;
    }
    
    public int getQuantidadeDeTotalDeExemplares() {
        return this.quantidadeDeTotalDeExemplares;
    }
    
    public void setQuantidadeDeTotalDeExemplares(int quantidadeDeTotalDeExemplares) {
        this.quantidadeDeTotalDeExemplares = quantidadeDeTotalDeExemplares;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public void setVolume(int volume) {
        if( volume > 0 )
        	this.volume = volume;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAssunto(ArrayList<String> assunto) {
        this.assunto = assunto;
    }

    public void setDataDeLancamento(Data dataDeLancamento){
        this.dataDeLancamento = dataDeLancamento;
    }
    
    public void setDataDeLancamento(int dia, int mes, int ano){
        this.dataDeLancamento.set(dia, mes, ano);
    }

    public void setQuantidadeDeExemplaresEmprestados(int quantidadeDeExemplaresEmprestados) {
        this.quantidadeDeExemplaresEmprestados = quantidadeDeExemplaresEmprestados;
    }
   
    /// MÉTODOS **********************************************************************************
        
    @Override
    protected String ImplementYourToString() {
        String myObjectInString = "";
		
        myObjectInString+= " " + this.autor;
        myObjectInString+= " " + this.edicao;
        myObjectInString+= " " + this.editora;
        myObjectInString+= " " + String.valueOf(this.quantidadeDeExemplaresEmprestados);
        myObjectInString+= " " + String.valueOf(this.quantidadeDeTotalDeExemplares);
        myObjectInString+= " " + this.titulo;
        myObjectInString+= " " + String.valueOf(this.volume);
        myObjectInString+= " " + this.assunto.toString();
        myObjectInString+= " " + this.dataDeLancamento.toString();

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
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        return !((this.id == null && other.id != null) || 
                (!this.causa.equals(other.causa)) ||
                (!this.dataEntrada.equals(other.dataEntrada)) ||
                (!this.assunto.equals(other.assunto)) ||
                (!this.observerList.equals(other.observerList)) ||
                (this.quantidade != other.quantidade) ||
                (this.quantidadeDeExemplaresEmprestados != other.quantidadeDeExemplaresEmprestados) ||
                (!this.edicao.equals(other.edicao)) ||
                (!this.editora.equals(other.editora)) ||
                (!this.status.equals(other.status)) ||
                (!this.id.equals(other.id)) ||
                (this.quantidadeDeTotalDeExemplares != other.quantidadeDeTotalDeExemplares) ||
                (!this.autor.equals(other.autor)) ||
                (!this.dataDeLancamento.equals(other.dataDeLancamento)) ||
                (!this.titulo.equals(other.titulo)) ||
                (this.valorUnitario != other.valorUnitario) ||
                (this.volume != other.volume)
                );
    }

}
