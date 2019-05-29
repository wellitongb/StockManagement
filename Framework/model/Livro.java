package model;

import java.util.ArrayList;
import java.util.Calendar;

import exception.ServiceException;

public class Livro extends Material{

	/// ATRIBUTO
	
    private String edicao;
    private int volume;
    private String editora;
    private String titulo;
    private String autor;
    private ArrayList<String> assunto;
    private Calendar dataDeLancamento;
    private int quantidadeDeTotalDeExemplares;
    private int quantidadeDeExemplaresEmprestados;
	
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
    
    public void setQuantidadeDeTotalDeExemplares(int quantidadeDeTotalDeExemplares) throws ServiceException {
        if(quantidadeDeTotalDeExemplares < this.quantidadeDeExemplaresEmprestados) 
            throw new ServiceException("Quantidade inválida de exemplares!");
        
        if(quantidadeDeTotalDeExemplares == this.quantidadeDeExemplaresEmprestados 
                && this.getStatus().equals(Status.NaoBloqueado))
            this.setEstadoLivro(EstadoLivro.ALUGADO);
        
        this.quantidadeDeTotalDeExemplares = quantidadeDeTotalDeExemplares;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public void setVolume(int volume) {
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

    public void setDataDeLancamento(Calendar dataDeLancamento){
        this.dataDeLancamento = dataDeLancamento;
    }

    public void setQuantidadeDeExemplaresEmprestados(int quantidadeDeExemplaresEmprestados) throws ServicoException {
         if(quantidadeDeExemplaresEmprestados < 0 && quantidadeDeExemplaresEmprestados > this.quantidadeDeTotalDeExemplares) 
            throw new ServicoException("Quantidade inválida de livros emprestados!");
         
         if(this.quantidadeDeTotalDeExemplares == quantidadeDeExemplaresEmprestados 
                && this.getEstadoLivro().equals(EstadoLivro.DISPONIVEL))
            this.setEstadoLivro(EstadoLivro.ALUGADO);
         
        this.quantidadeDeExemplaresEmprestados = quantidadeDeExemplaresEmprestados;
    }
    
    
    
    /// METODO
    
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
