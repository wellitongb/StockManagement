package model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//Revisar classe!

@Entity
@Table (name = "Livro")
public class Livro extends Material{
	
	/// ATRIBUTO
	
	@Column
    private String edicao = "";
	@Column
	private int volume = 0;
	@Column
    private String editora = "";
	@Column
	private String titulo = "";
	@Column
    private String autor = "";
    private ArrayList<String> assunto;
	@Column
    private Data dataDeLancamento;
	@Column
    private int quantidadeDeTotalDeExemplares;
	@Column
    private int quantidadeDeExemplaresEmprestados;
	
    public Livro(){
    	super();
    }
    
    /// GETTERS E SETTERS
    
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
   
    /// METODO
    
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
	
}
