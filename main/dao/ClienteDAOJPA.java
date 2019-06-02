package dao;
  
import java.util.ArrayList;
import java.util.List;
  
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import exception.DAOException;
import model.Cliente;
import model.Usuario;

//Revisar classe!

public class ClienteDAOJPA implements IUsuarioDAO {
  
	 private static ClienteDAOJPA instance;
	 protected EntityManager entityManager;

     private ClienteDAOJPA() {
         entityManager = getEntityManager();
     }
     
     private EntityManager getEntityManager() {
    	 
    	 EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
         if (entityManager == null) {
                  entityManager = factory.createEntityManager();
         }

         return entityManager;
     }
     
     public static ClienteDAOJPA getInstance(){
    	 
    	 if (instance == null){
    		 instance = new ClienteDAOJPA();
    	 }
    
    	 return instance;
     }
       
     public void persist(Cliente cliente) throws DAOException {
    	 try {
    		 entityManager.getTransaction().begin();
	         entityManager.persist(cliente);
	         entityManager.getTransaction().commit();
    	 } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new DAOException( ex.getMessage() );
    	 }
     }
    
    @Override
 	public void adicionar(Usuario usuario) throws DAOException {
 		persist( (Cliente) usuario );
 	}

     public void merge(Cliente cliente) throws DAOException {
     
    	 try {
    		 entityManager.getTransaction().begin();
             entityManager.merge(cliente);
             entityManager.getTransaction().commit();
         } catch (Exception ex) {
             entityManager.getTransaction().rollback();
	         throw new DAOException( ex.getMessage() );
         }
     }

     @Override
     public void alterar(Usuario usuarioAlterado) throws DAOException {
    	 merge( (Cliente) usuarioAlterado);	
     }
     
     public void remove(Cliente cliente) throws DAOException {
    	 try {
    		 entityManager.getTransaction().begin();
             cliente = entityManager.find(Cliente.class, cliente.getIdUsuario() );
             entityManager.remove(cliente);
             entityManager.getTransaction().commit();
          } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
          }
      }
  
     @Override
     public void remover(Usuario usuario) throws DAOException {
    	 remove( (Cliente) usuario );
 	}
     
     public void removeById(String login) throws DAOException {
          try {
             Cliente cliente = getById(login);
             remove(cliente);
          } catch (Exception ex) {
        	  throw new DAOException( ex.getMessage() );
          }
     }
	
     private Cliente getById( String login) {
         return entityManager.find(Cliente.class, login);
     }

     @Override
     public Usuario consultar(String login) throws DAOException {

		Usuario cliente = getById( login );
		
		if( cliente == null )
			throw new DAOException("Funcionario nao encontrado");
		
		return cliente;
		
     }

     @SuppressWarnings("unchecked")
     public List<Cliente> findAll() {
    	 return entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
     }
      
     @Override
     public List<Usuario> consultarTodos() throws DAOException {
    	 
    	 List<Cliente> clientes = findAll();
    	 ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
		
    	 if( clientes == null )
    		 throw new DAOException("Funcionario nao encontrado");
		
    	 for( Cliente i : clientes) {
    		 usuarios.add(i);
    	 }
		
    	 return usuarios;
     }
  
}
	

