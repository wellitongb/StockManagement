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
import model.Usuario;

/**
 * JDBC do CRUD Cliente 
 * @author	Samuel Lucas
 * @version	0.0.2
 */
public class ClienteDAOJDBC implements IUsuarioDAO{

	private static Connection conn = Conexao.getConnection(); /** Conexao com o banco de dados  */
	
	/**
	 * Realiza a insercao de um novo cliente no banco de dados ( INSERT ) 
	 * @param	cliente	Cliente a ser inserido
	 * @throws	ServiceException
	 * @see 	{@link ServiceException} 
	 */
	@Override
	public void adicionar(Usuario usuario) throws ServiceException {
		
		Cliente cliente = (Cliente) usuario;
		
		String insertSQL = "INSERT INTO Cliente VALUES ( default, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(insertSQL);
			pst.setString(1, cliente.getCpf() );
			pst.setString(2, cliente.getNome() );
			pst.setString(3, cliente.getEmail() );
			
			pst.executeUpdate();
			conn.commit();
			pst.close();
			
		}catch( SQLException e) {
			System.err.println();
			throw new ServiceException("Erro na insercao de cliente");
		}	
			
		throw new ServiceException("Insercao de cliente bem sucedida");
	
	}
		
	/**
	 * Realiza a consulta de clientes por meio do cpf no banco de dados ( SELECT )
	 * @param	cpf	CPF do cliente 
	 * @return	Cliente correspondente ao cpf 
	 * @throws	ServiceException
	 * @see		{@link ServiceException}
	 */
	@Override
	public Usuario consultar(String login) throws ServiceException {
		String selectSQL = "SELECT * FROM Cliente WHERE login = ? ";
		Cliente cliente = new Cliente();
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(selectSQL);
			
			while( rs.next() ) {
				cliente.setCpf(	rs.getString(1) );
				cliente.setNome( rs.getString(2)); 
				cliente.setEmail( rs.getString(3) );
				
			}
		}catch(SQLException e) {
			throw new ServiceException("Erro na consulta de cliente");
		}	
		
		return cliente;
	}
	
	/**
	 * Realiza a alteracao de dados de determinado cliente por meio do cpf no banco de dados ( UPDATE )
	 * @param	cliente	Cliente a ser alterado
	 * @throws	ServiceException
	 * @see		{@link ServiceException}	
	 */
	@Override
	public void alterar(Usuario usuarioAlterado) throws ServiceException {
		
		Cliente cliente = (Cliente) usuarioAlterado;
		
		String updateSQL = "UPDATE Cliente SET email = ? WHERE cpf = ? ";
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(updateSQL);
			pst.setString(1, cliente.getEmail() );
			pst.setString(2, cliente.getCpf());
			pst.execute();
			conn.commit();
			pst.close();
			
		}catch(SQLException e) {
			System.err.println( e.getMessage() );
			throw new ServiceException("Erro na alteracao de dados de cliente");
		}	
			
	}
	
	/**
	 * Realiza a remocao de determinado cliente por meio do cpf no banco de dados ( DELETE )
	 * @param 	cliente		Cliente a ser removido
	 * @throws 	ServiceException
	 * @see		{@link ServiceException}
	 */
	@Override
	public void remover(Usuario usuario) throws ServiceException {

		Cliente cliente = (Cliente) usuario;
		
		String deleteSQL = "DELETE FROM Cliente WHERE login = ?";
		
		try {
		
			PreparedStatement pst = conn.prepareStatement(deleteSQL);
			pst.setString(1, cliente.getLogin());
			pst.execute();
			conn.commit();
			pst.close();
		
		}catch(SQLException e) {
			throw new ServiceException("Erro na remocao de cliente!");
		}	
		
		throw new ServiceException("Cliente removido com com sucesso!");

	}
	
	/**
	 * Imprime um lista contendo todos os clientes cadastrados no banco de dados  ( SELECT * )
	 * @throws 	ServiceException
	 * @see		{@link ServiceException} 
	 */
	@Override
	public List<Usuario> consultarTodos() throws ServiceException {
	
		String consultaSQL = "SELECT * FROM CLIENTE";
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(consultaSQL);
				
			while( rs.next() ) {
				Cliente cliente = new Cliente( rs.getString(1),
												rs.getString(2), 
												rs.getString(3));

				clientes.add(cliente);
			}
			
		}catch(SQLException e) {
			throw new ServiceException("Erro em lista todos os clientes cadastrados");
		}
		
		return clientes;
	
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