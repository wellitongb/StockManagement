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
import model.tiposUsuario.Funcionario;
import exception.DAOException;
import java.util.ArrayList;
import model.Usuario;

/**
 * Classe controladora JPA do modelo Funcionario
 * @see IUsuarioDAO
 * @see Usuario
 */
public class FuncionarioJpaController implements Serializable, IUsuarioDAO {

    /// ATRIBUTOS ********************************************************************************
    
    private static FuncionarioJpaController instance;
    private EntityManager entityManager;


    /// CONSTRUTOR *******************************************************************************

    private FuncionarioJpaController() {
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
    
    public static FuncionarioJpaController getInstance(){
    	 
        if (instance == null){
            instance = new FuncionarioJpaController();
        }
    
        return instance;
    }
    
    public int getFuncionarioCount() {
    
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /// MÉTODOS **********************************************************************************
    
    // INSERÇÃO
    public void create(Funcionario funcionario) throws DAOException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(funcionario);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
     }

    @Override
    public void adicionar(Usuario usuario) throws DAOException {
        create( (Funcionario) usuario ); 
    }

    // ALTERAÇÃO
    public void edit(Funcionario funcionario)  throws DAOException {
     
    	try {
            entityManager.getTransaction().begin();
            entityManager.merge(funcionario);
            entityManager.getTransaction().commit();
         } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
         }
     }

    @Override
    public void alterar(Usuario usuarioAlterado) throws DAOException {
        edit( (Funcionario) usuarioAlterado ); 
    }

    // REMOÇÃO
    public void destroy(Funcionario funcionario)throws DAOException {
        try {
            entityManager.getTransaction().begin();
            funcionario = entityManager.find(Funcionario.class, funcionario.getId() );
            entityManager.remove(funcionario);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
    }

    @Override
    public void remover(Usuario usuario) throws DAOException {
        destroy((Funcionario) usuario ); 
    }
    
    // CONSULTA
    public Funcionario findFuncionario(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, login);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Usuario consultar(String login){// throws DAOException {
        Funcionario funcionario = findFuncionario(login);
        
//        if( funcionario == null )
//            throw new DAOException("Funcionario nao encontrado");
		
        return funcionario;
    }
    
    // CONSULTA TODOS
    private List<Funcionario> findFuncionarioEntities() {
    	 return entityManager.createQuery("FROM " + Funcionario.class.getName() ).getResultList();
     }
    
    @Override
    public List<Usuario> consultarTodos(){
        
        List<Funcionario> funcionarios = findFuncionarioEntities();
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
		
        if( funcionarios == null )
            return usuarios;
            
        for( Funcionario i : funcionarios) {
            usuarios.add(i);
        }
		
        return usuarios;    
    }
    
}
