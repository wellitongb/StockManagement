package dao;
  
import java.util.List;
  
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import exception.ServiceException;
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
  
     public Cliente getById(final int id) {
         return entityManager.find(Cliente.class, id);
     }	
            
 
     @SuppressWarnings("unchecked")
     public List<Cliente> findAll() {
    	 return entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
     }
      
     
     public void persist(Cliente cliente) throws ServiceException {
    	 try {
    		 entityManager.getTransaction().begin();
	         entityManager.persist(cliente);
	         entityManager.getTransaction().commit();
    	 } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new ServiceException( ex.getMessage() );
    	 }
     }
    
    @Override
 	public void adicionar(Usuario usuario) throws ServiceException {
 		persist( (Cliente) usuario );
 	}

     public void merge(Cliente cliente) throws ServiceException {
     
    	 try {
    		 entityManager.getTransaction().begin();
             entityManager.merge(cliente);
             entityManager.getTransaction().commit();
         } catch (Exception ex) {
             entityManager.getTransaction().rollback();
	         throw new ServiceException( ex.getMessage() );
         }
     }
  
     public void remove(Cliente cliente) throws ServiceException {
    	 try {
    		 entityManager.getTransaction().begin();
             cliente = entityManager.find(Cliente.class, cliente.getIdUsuario() );
             entityManager.remove(cliente);
             entityManager.getTransaction().commit();
          } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new ServiceException( ex.getMessage() );
          }
      }
  
     @Override
     public void remover(Usuario usuario) throws ServiceException {
    	 remove( (Cliente) usuario );
 	}
     
     public void removeById(final int id) throws ServiceException {
          try {
             Cliente cliente = getById(id);
             remove(cliente);
          } catch (Exception ex) {
        	  throw new ServiceException( ex.getMessage() );
          }
     }

	
	@Override
	public void alterar(Usuario usuarioAlterado) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public Usuario consultar(String login) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> consultarTodos() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
  
}
	

