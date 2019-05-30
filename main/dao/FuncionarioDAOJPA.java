package dao;
  
import java.util.ArrayList;
import java.util.List;
  
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import exception.ServiceException;
import model.Funcionario;
import model.Usuario;

//Revisar classe!

public class FuncionarioDAOJPA implements IUsuarioDAO {
  
	 private static FuncionarioDAOJPA instance;
	 protected EntityManager entityManager;

     private FuncionarioDAOJPA() {
         entityManager = getEntityManager();
     }
     
     private EntityManager getEntityManager() {
    	 
    	 EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
         if (entityManager == null) {
                  entityManager = factory.createEntityManager();
         }

         return entityManager;
     }
     
     public static FuncionarioDAOJPA getInstance(){
    	 
    	 if (instance == null){
    		 instance = new FuncionarioDAOJPA();
    	 }
    
    	 return instance;
     }
  
     private void persist(Funcionario funcionario) throws ServiceException {
    	 try {
    		 entityManager.getTransaction().begin();
	         entityManager.persist(funcionario);
	         entityManager.getTransaction().commit();
    	 } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new ServiceException( ex.getMessage() );
    	 }
     }
  
 	@Override
 	public void adicionar(Usuario usuario) throws ServiceException {
 		persist( (Funcionario) usuario );
 	}
     
    private void merge(Funcionario funcionario) throws ServiceException {
     
    	try {
    		 entityManager.getTransaction().begin();
             entityManager.merge(funcionario);
             entityManager.getTransaction().commit();
         } catch (Exception ex) {
             entityManager.getTransaction().rollback();
	         throw new ServiceException( ex.getMessage() );
         }
    }

 	@Override
 	public void alterar(Usuario usuarioAlterado) throws ServiceException {
 		merge( (Funcionario) usuarioAlterado);
 	}
  
    private void remove(Funcionario funcionario) throws ServiceException {
    	try {
    		entityManager.getTransaction().begin();
            funcionario= entityManager.find(Funcionario.class, funcionario.getLogin() );
            entityManager.remove(funcionario);
             entityManager.getTransaction().commit();
         } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new ServiceException( ex.getMessage() );
         }
     }
  
    @Override
	public void remover(Usuario usuario) throws ServiceException {
		remove(  (Funcionario) usuario  );	
	}
    
     public void removeById(String login) throws ServiceException {
          try {
             Funcionario funcionario= getById(login);
             remove(funcionario);
          } catch (Exception ex) {
	            throw new ServiceException( ex.getMessage() );
          }
     }

     private Funcionario getById( String login) {
         return entityManager.find(Funcionario.class, login);
     }

	@Override
	public Usuario consultar( String login) throws ServiceException {
		
		Usuario funcionario = getById( login );
		
		if( funcionario == null )
			throw new ServiceException("Funcionario nao encontrado");
		
		return funcionario;
		
	}           
	 
    @SuppressWarnings("unchecked")
    private List<Funcionario> findAll() {
    	return entityManager.createQuery("FROM " + Funcionario.class.getName()).getResultList();
    }
	      
	@Override
	public List<Usuario> consultarTodos() throws ServiceException {
		
		List<Funcionario> funcionarios = findAll();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
		
		if( funcionarios == null )
			throw new ServiceException("Funcionario nao encontrado");
		
		for( Funcionario i : funcionarios) {
			usuarios.add(i);
		}
		
		return usuarios;
	}
  
}
	

