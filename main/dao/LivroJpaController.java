/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/// CLASSES PRÓPRIAS
import model.tiposMaterial.Livro;
import exception.DAOException;
import java.util.ArrayList;
import model.Material;

/**
 * Classe controladora JPA do modelo Livro
 * @see IMaterialDAO
 * @see Material
 */
public class LivroJpaController implements Serializable, IMaterialDAO {

    /// ATRIBUTOS ********************************************************************************
    
    private static LivroJpaController instance;
    private EntityManager entityManager;


    /// CONSTRUTOR *******************************************************************************

    private LivroJpaController() {
        this.entityManager = getEntityManager();
    }

    /// GETTERS **********************************************************************************
        
    public EntityManager getEntityManager() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");

        if (entityManager == null) {
          entityManager = factory.createEntityManager();
        }

         return entityManager;        
        
    }
    
    public static LivroJpaController getInstance(){
    	 
        if (instance == null){
            instance = new LivroJpaController();
        }
    
        return instance;
    }
    
    public int getLivroCount() {
    
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Livro> rt = cq.from(Livro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /// MÉTODOS **********************************************************************************
    
    // INSERÇÃO
    public void create(Livro livro) throws DAOException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(livro);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
     }

    @Override
    public void adicionar(Material material) throws DAOException {
        create( (Livro) material ); 
    }

    // ALTERAÇÃO
    public void edit(Livro livro)  throws DAOException {
     
    	try {
            entityManager.getTransaction().begin();
            entityManager.merge(livro);
            entityManager.getTransaction().commit();
         } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
         }
     }

    @Override
    public void alterar(Material materialAlterado) throws DAOException {
        edit( (Livro) materialAlterado ); 
    }

    // REMOÇÃO
    public void destroy(Livro livro)throws DAOException {
        try {
            entityManager.getTransaction().begin();
            livro = entityManager.find(Livro.class, livro.getId() );
            entityManager.remove(livro);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
    }

    @Override
    public void remover(Material material) throws DAOException {
        destroy((Livro) material ); 
    }
    
    // CONSULTA
    public Livro findLivro(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Livro.class, login);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Material consultar(String login) throws DAOException {
        Livro livro = findLivro(login);
        
        if( livro == null )
            throw new DAOException("Livro nao encontrado");
		
        return livro;
    }
    
    // CONSULTA TODOS
    private List<Livro> findLivroEntities() {
    	 return entityManager.createQuery("FROM " + Livro.class.getName() ).getResultList();
     }
    
    @Override
    public List<Material> consultarTodos() throws DAOException {
        
        List<Livro> livros = findLivroEntities();
    	ArrayList<Material> materials = new ArrayList<Material>(); 
		
        if( livros == null )
            throw new DAOException("Livro nao encontrado");
		
        for( Livro i : livros) {
            materials.add(i);
        }
		
        return materials;    
    }
    
}
