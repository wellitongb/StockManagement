package service;

import service.validacao.ValidacaoMaterial;
import java.util.List;

import dao.LivroDAOJPA;
import model.Material;
import model.tiposMaterial.Livro;
import exception.ServiceException;

public class LivroService extends MaterialService{

	LivroService(){
		this.materialDAO = new LivroDAOJPA();
	}
	
	@Override
	public String adicionar(Material material) {

		Livro livro = (Livro) material;
		
		try {	
			materialDAO.adicionar(livro);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String alterar(Material material, Material materialAlterado) {
		
		Livro livro = (Livro) material;
		
		try {	
			materialDAO.alterar(livro);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String remover(Material material) {
		Livro livro = (Livro) material;
		
		try {	
			materialDAO.remover(livro);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String consultar(Material material) {
		
		try {	
			Livro livro = (Livro) materialDAO.consultar(livro.getIdMaterial());
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
		

	}

	@Override
	public List<String> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bloquear(Material material, BloqueioMaterial bloqueioMaterial, String causa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> ranquear(IRankingMaterialStrategy rankingMaterial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validacao(Material material, ValidacaoMaterial validacaoMaterial) {
		// TODO Auto-generated method stub
		return null;
	}

}
