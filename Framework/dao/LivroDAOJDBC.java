package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.ServiceException;
import model.Livro;
import model.Material;

public class LivroDAOJDBC implements IMaterialDAO {

	private static Connection conn = Conexao.getConnection(); /** Conexao com o banco de dados  */

	/**
	 * Realiza a insercao de um novo livro no banco de dados ( INSERT ) 
	 * @param	livro	Livro a ser inserido
	 * @throws	ServiceException
	 * @see 	{@link ServiceException} 
	 */
	@Override
	public void adicionar(Material material) throws ServiceException {
		
		Livro livro = (Livro) material;
		
		String insertSQL = "INSERT INTO Livro VALUES ( default, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(insertSQL);
			pst.setString(1, livro.getCpf() );
			pst.setString(2, livro.getNome() );
			pst.setString(3, livro.getEmail() );
			
			pst.executeUpdate();
			conn.commit();
			pst.close();
			
		}catch( SQLException e) {
			System.err.println();
			throw new ServiceException("Erro na insercao de livro");
		}	
			
		throw new ServiceException("Insercao de livro bem sucedida");
	
	}
		
	/**
	 * Realiza a consulta de livros por meio do cpf no banco de dados ( SELECT )
	 * @param	cpf	CPF do livro 
	 * @return	Livro correspondente ao cpf 
	 * @throws	ServiceException
	 * @see		{@link ServiceException}
	 */
	@Override
	public Material consultar(long idMaterial) throws ServiceException {
	
		String selectSQL = "SELECT * FROM Livro WHERE login = ? ";
		Livro livro = new Livro();
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(selectSQL);
			
			while( rs.next() ) {
				livro.setCpf(	rs.getString(1) );
				livro.setNome( rs.getString(2)); 
				livro.setEmail( rs.getString(3) );
				
			}
		}catch(SQLException e) {
			throw new ServiceException("Erro na consulta de livro");
		}	
		
		return livro;
	}
	
	/**
	 * Realiza a alteracao de dados de determinado livro por meio do cpf no banco de dados ( UPDATE )
	 * @param	livro	Livro a ser alterado
	 * @throws	ServiceException
	 * @see		{@link ServiceException}	
	 */
	@Override
	public void alterar(Material materialAlterado) throws ServiceException {
		
		Livro livro = (Livro) materialAlterado;
		
		String updateSQL = "UPDATE Livro SET email = ? WHERE cpf = ? ";
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(updateSQL);
			pst.setString(1, livro.getEmail() );
			pst.setString(2, livro.getCpf());
			pst.execute();
			conn.commit();
			pst.close();
			
		}catch(SQLException e) {
			System.err.println( e.getMessage() );
			throw new ServiceException("Erro na alteracao de dados de livro");
		}	
			
	}
	
	/**
	 * Realiza a remocao de determinado livro por meio do cpf no banco de dados ( DELETE )
	 * @param 	livro		Livro a ser removido
	 * @throws 	ServiceException
	 * @see		{@link ServiceException}
	 */
	@Override
	public void remover(Material material) throws ServiceException {

		Livro livro = (Livro) material;
		
		String deleteSQL = "DELETE FROM Livro WHERE login = ?";
		
		try {
		
			PreparedStatement pst = conn.prepareStatement(deleteSQL);
			pst.setString(1, livro.getLogin());
			pst.execute();
			conn.commit();
			pst.close();
		
		}catch(SQLException e) {
			throw new ServiceException("Erro na remocao de livro!");
		}	
		
		throw new ServiceException("Livro removido com com sucesso!");

	}
	
	/**
	 * Imprime um lista contendo todos os livros cadastrados no banco de dados  ( SELECT * )
	 * @throws 	ServiceException
	 * @see		{@link ServiceException} 
	 */
	@Override
	public List<Material> consultarTodos() throws ServiceException {
	
		String consultaSQL = "SELECT * FROM CLIENTE";
		List<Livro> livros = new ArrayList<Livro>();
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(consultaSQL);
				
			while( rs.next() ) {
				Livro livro = new Livro( rs.getString(1),
												rs.getString(2), 
												rs.getString(3));

				livros.add(livro);
			}
			
		}catch(SQLException e) {
			throw new ServiceException("Erro em lista todos os livros cadastrados");
		}
		
		return livros;
	
	}
	
	/**
	 * Fechando a conexao com o banco de dados
	 * @throws 	ServiceException
	 * @see		{@link ServiceException} 
	 */
	public void desconectar() throws ServiceException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new ServiceException("Erro na desconexao");
		}
		
		throw new ServiceException("Desconexao bem sucedida");
	}

	

}
