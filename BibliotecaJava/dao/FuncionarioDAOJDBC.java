package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.ServiceException;
import model.Cliente;
import model.Funcionario;
import model.Usuario;

public class FuncionarioDAOJDBC implements IUsuarioDAO{
	
	private static Connection conn = Conexao.getConnection(); /** Conexao com o banco de dados  */
	
	/**
	 * Realiza a insercao de um novo funcionario no banco de dados ( INSERT ) 
	 * @param	funcionario	Funcionario a ser inserido
	 * @throws	ServiceException
	 * @see 	{@link ServiceException} 
	 */
	@Override
	public void adicionar(Usuario usuario) throws ServiceException {
		
		Funcionario funcionario = (Funcionario) usuario;
		
		String insertSQL = "INSERT INTO Funcionario VALUES ( default, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(insertSQL);
			pst.setString(1, funcionario.getNome() );
			pst.setString(2, funcionario.getLogin() );
			pst.setString(3, funcionario.getSenha() );
			pst.setString(4, funcionario.getStatus().toString() );
			pst.setInt(5, funcionario.getQuantidadeDeMovimentacoes() );
			pst.setString(6, funcionario.getCausa() );
			pst.setDouble(7, funcionario.getSalario() );
			
			pst.executeUpdate();
			conn.commit();
			pst.close();
			
		}catch( SQLException e) {
			System.err.println();
			throw new ServiceException("Erro na insercao de funcionario");
		}	
			
		throw new ServiceException("Insercao de funcionario bem sucedida");
	
	}
		
	/**
	 * Realiza a consulta de funcionarios por meio do cpf no banco de dados ( SELECT )
	 * @param	cpf	CPF do funcionario 
	 * @return	Funcionario correspondente ao cpf 
	 * @throws	ServiceException
	 * @see		{@link ServiceException}
	 */
	@Override
	public Usuario consultar(String login) throws ServiceException {
		String selectSQL = "SELECT * FROM Funcionario WHERE login = ? ";
		Funcionario funcionario = new Funcionario();
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(selectSQL);
			
			funcionario.setNome( rs.getString(1) );
			funcionario.setLogin( rs.getString(2));
			funcionario.setSenha( rs.getString(3) );
			funcionario.setStatus( rs.getString(4) ); /// CONVERTER PARA O ENUM
			funcionario.setQuantidadeDeMovimentacoes( rs.getInt(5) );	
			funcionario.setCausa( rs.getString(6));
			
		}catch(SQLException e) {
			throw new ServiceException("Erro na consulta de funcionario");
		}	
		
		return funcionario;
	}
	
	/**
	 * Realiza a alteracao de dados de determinado funcionario por meio do cpf no banco de dados ( UPDATE )
	 * @param	funcionario	Funcionario a ser alterado
	 * @throws	ServiceException
	 * @see		{@link ServiceException}	
	 */
	@Override
	public void alterar(Usuario usuarioAlterado) throws ServiceException {
		
		Funcionario funcionario = (Funcionario) usuarioAlterado;
		
		String updateSQL = "UPDATE Funcionario SET email = ? WHERE cpf = ? ";
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(updateSQL);
			
			pst.setString(1, funcionario.getNome() );
			pst.setString(2, funcionario.getLogin() );
			pst.setString(3, funcionario.getSenha() );
			pst.setString(4, funcionario.getStatus().toString() );
			pst.setInt(5, funcionario.getQuantidadeDeMovimentacoes() );
			pst.setString(6, funcionario.getCausa() );

			pst.execute();
			conn.commit();
			pst.close();
			
		}catch(SQLException e) {
			System.err.println( e.getMessage() );
			throw new ServiceException("Erro na alteracao de dados de funcionario");
		}	
			
	}
	
	/**
	 * Realiza a remocao de determinado funcionario por meio do cpf no banco de dados ( DELETE )
	 * @param 	funcionario		Funcionario a ser removido
	 * @throws 	ServiceException
	 * @see		{@link ServiceException}
	 */
	@Override
	public void remover(Usuario usuario) throws ServiceException {

		Funcionario funcionario = (Funcionario) usuario;
		
		String deleteSQL = "DELETE FROM Funcionario WHERE login = ?";
		
		try {
		
			PreparedStatement pst = conn.prepareStatement(deleteSQL);

			pst.setString(1, funcionario.getNome() );
			pst.setString(2, funcionario.getLogin() );
			pst.setString(3, funcionario.getSenha() );
			pst.setString(4, funcionario.getStatus().toString() );
			pst.setInt(5, funcionario.getQuantidadeDeMovimentacoes() );
			pst.setString(6, funcionario.getCausa() );
			
			pst.execute();
			conn.commit();
			pst.close();
		
		}catch(SQLException e) {
			throw new ServiceException("Erro na remocao de funcionario!");
		}	
		
		throw new ServiceException("Funcionario removido com com sucesso!");

	}
	
	/**
	 * Imprime um lista contendo todos os funcionarios cadastrados no banco de dados  ( SELECT * )
	 * @throws 	ServiceException
	 * @see		{@link ServiceException} 
	 */
	@Override
	public List<Usuario> consultarTodos() throws ServiceException {
	
		String consultaSQL = "SELECT * FROM CLIENTE";
		List<Usuario> funcionarios = new ArrayList<Usuario>();
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(consultaSQL);
				
			while( rs.next() ) {
				Funcionario funcionario = new Funcionario( );
				
				funcionario.setNome( rs.getString(1) );
				funcionario.setLogin( rs.getString(2));
				funcionario.setSenha( rs.getString(3) );
				funcionario.setStatus( rs.getString(4) ); /// CONVERTER PARA O ENUM
				funcionario.setQuantidadeDeMovimentacoes( rs.getInt(5) );

				funcionarios.add(funcionario);
			}
			
		}catch(SQLException e) {
			throw new ServiceException("Erro em lista todos os funcionarios cadastrados");
		}
		
		return funcionarios;
	
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
