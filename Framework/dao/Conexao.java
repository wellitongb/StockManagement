package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conecta com o banco de dados 
 * @author 	Samuel Lucas
 * @version 0.0.1
 */
public class Conexao {
	
	/// PARTE VARIAVEL (PostgresSQL)
	private static String url = "jdbc:postgresql://localhost/curso_java"; /** Url para acesso ao banco de dados  */
	private static String user = "postgres"; /** Nome do usuario conectado ao banco de dados  */
	private static String password = "sandan123"; /** Senha do usuario conectado ao banco de dados  */
	
	/// PARTE FIXA
	private static Connection conn; /** Conexao com o banco  */
	
	/**
	 * Construtor privado devido estar sendo utilizado o padrao de projeto SINGLETON
	 */
	private Conexao() { /** VAZIO */ }
	
	/**
	 * Metodo get do atributo conn
	 * @return	Conexao com o banco de dados 
	 * @see #createConnection() 
	 */
	public static Connection getConnection() {
		
		if( conn == null) {
			try {
				createConnection();
			}catch (SQLException e) {
				System.err.println( e.getMessage() );
			}
		}
		return conn;
		
	}
	
	/**
	 * Cria a conexao com o banco de dados 
	 * @throws SQLException
	 */
	private static void createConnection() throws SQLException {
		
		conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		System.out.println("Conexao com o BD efetuada com sucesso!");
	
	}
	
	/**
	 * Mostra na tela as informacoes relativas ao banco de dados utilizado
	 * @throws SQLException
	 */
	public static void mostrarMetaInfoBD() throws SQLException {
		
		DatabaseMetaData meta = conn.getMetaData(); 
		String fabricanteBD = meta.getDatabaseProductName();
		String versaoBD = meta.getDatabaseProductVersion();
	
		System.out.println( "Fabricante:" + fabricanteBD + " Versao:" + versaoBD);
		
	}
}
