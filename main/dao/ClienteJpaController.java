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
import model.tiposUsuario.Cliente;
import exception.DAOException;
import java.util.ArrayList;
import model.Usuario;

/**
 * Classe controladora JPA do modelo Cliente
 * @see IUsuarioDAO
 * @see Usuario
 */
public class ClienteJpaController implements Serializable, IUsuarioDAO {

    /// ATRIBUTOS ********************************************************************************
    
    private static ClienteJpaController instance;
    private EntityManager entityManager;


    /// CONSTRUTOR *******************************************************************************

    private ClienteJpaController() {
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
    
    public static ClienteJpaController getInstance(){
    	 
        if (instance == null){
            instance = new ClienteJpaController();
        }
    
        return instance;
    }
    
    public int getClienteCount() {
    
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /// MÉTODOS **********************************************************************************
    
    // INSERÇÃO
    public void create(Cliente cliente) throws DAOException {
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
        create( (Cliente) usuario ); 
    }

    // ALTERAÇÃO
    public void edit(Cliente cliente)  throws DAOException {
     
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
        edit( (Cliente) usuarioAlterado ); 
    }

    // REMOÇÃO
    public void destroy(Cliente cliente)throws DAOException {
        try {
            entityManager.getTransaction().begin();
            cliente = entityManager.find(Cliente.class, cliente.getId() );
            entityManager.remove(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
    }

    @Override
    public void remover(Usuario usuario) throws DAOException {
        destroy((Cliente) usuario ); 
    }
    
    // CONSULTA
    public Cliente findCliente(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, login);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Usuario consultar(String login) throws DAOException {
        Cliente cliente = findCliente(login);
        
        if( cliente == null )
            throw new DAOException("Funcionario nao encontrado");
		
        return cliente;
    }
    
    // CONSULTA TODOS
    private List<Cliente> findClienteEntities() {
    	 return entityManager.createQuery("FROM " + Cliente.class.getName() ).getResultList();
     }
    
    @Override
    public List<Usuario> consultarTodos() throws DAOException {
        
        List<Cliente> clientes = findClienteEntities();
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
		
        if( clientes == null )
            throw new DAOException("Funcionario nao encontrado");
		
        for( Cliente i : clientes) {
            usuarios.add(i);
        }
		
        return usuarios;    
    }
    
}
