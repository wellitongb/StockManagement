package dao;
  
import java.util.ArrayList;
import java.util.List;
  
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import exception.ServiceException;
import model.Livro;
import model.Material;

//Revisar classe!

public class LivroDAOJPA implements IMaterialDAO {
  
	 private static LivroDAOJPA instance;
	 protected EntityManager entityManager;

     private LivroDAOJPA() {
         entityManager = getEntityManager();
     }
     
     private EntityManager getEntityManager() {
    	 
    	 EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
         if (entityManager == null) {
                  entityManager = factory.createEntityManager();
         }

         return entityManager;
     }
     
     public static LivroDAOJPA getInstance(){
    	 
    	 if (instance == null){
    		 instance = new LivroDAOJPA();
    	 }
    
    	 return instance;
     }
  
     private void persist(Livro livro) throws ServiceException {
    	 try {
    		 entityManager.getTransaction().begin();
	         entityManager.persist(livro);
	         entityManager.getTransaction().commit();
    	 } catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            throw new ServiceException( ex.getMessage() );
    	 }
     }
  
 	@Override
 	public void adicionar(Material material) throws ServiceException {
 		persist( (Livro) material );
 	}
     
    private void merge(Livro livro) throws ServiceException {
     
    	try {
    		 entityManager.getTransaction().begin();
             entityManager.merge(livro);
             entityManager.getTransaction().commit();
         } catch (Exception ex) {
             entityManager.getTransaction().rollback();
	         throw new ServiceException( ex.getMessage() );
         }
    }

 	@Override
 	public void alterar(Material materialAlterado) throws ServiceException {
 		merge( (Livro) materialAlterado);
 	}
  
    private void remove(Livro livro) {
    	try {
    		entityManager.getTransaction().begin();
            livro= entityManager.find(Livro.class, livro.getIdMaterial() );
            entityManager.remove(livro);
             entityManager.getTransaction().commit();
         } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
         }
     }
  
    @Override
	public void remover(Material material) throws ServiceException {
		remove( (Livro) material );	
	}
    
     public void removeById(final int id) {
          try {
             Livro livro= getById(id);
             remove(livro);
          } catch (Exception ex) {
             ex.printStackTrace();
          }
     }


     private Livro getById(final int id) {
         return entityManager.find(Livro.class, id);
     }

	@Override
	public Material consultar(long idMaterial) throws ServiceException {
		
		Material livro = getById( (int)idMaterial );
		
		if( livro == null )
			throw new ServiceException("Livro nao encontrado");
		
		return livro;
		
	}           
	 
    @SuppressWarnings("unchecked")
    private List<Livro> findAll() {
    	return entityManager.createQuery("FROM " + Livro.class.getName()).getResultList();
    }
	      
	@Override
	public List<Material> consultarTodos() throws ServiceException {
		
		List<Livro> livros = findAll();
		ArrayList<Material> materiais = new ArrayList<Material>(); 
		
		if( livros == null )
			throw new ServiceException("Livro nao encontrado");
		
		for( Livro i : livros) {
			materiais.add(i);
		}
		
		return materiais;
	}
  
}
	

