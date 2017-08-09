/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.DataLayer;

import eveniment.DataLayer.exceptions.NonexistentEntityException;
import eveniment.DataLayer.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import eveniment.Entities.Product;
import eveniment.Entities.Program;
import eveniment.Entities.ProgramProducts;
import eveniment.Entities.ProgramProductsPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Andrei
 */
public class ProgramProductsJpaController implements Serializable {

    public ProgramProductsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramProducts programProducts) throws PreexistingEntityException, Exception {
        if (programProducts.getProgramProductsPK() == null) {
            programProducts.setProgramProductsPK(new ProgramProductsPK());
        }
        programProducts.getProgramProductsPK().setProgramId(programProducts.getProgram().getId());
        programProducts.getProgramProductsPK().setProductId(programProducts.getProduct().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product product = programProducts.getProduct();
            if (product != null) {
                product = em.getReference(product.getClass(), product.getId());
                programProducts.setProduct(product);
            }
            Program program = programProducts.getProgram();
            if (program != null) {
                program = em.getReference(program.getClass(), program.getId());
                programProducts.setProgram(program);
            }
            em.persist(programProducts);
            if (product != null) {
                product.getProgramProductsCollection().add(programProducts);
                product = em.merge(product);
            }
            if (program != null) {
                program.getProgramProductsCollection().add(programProducts);
                program = em.merge(program);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramProducts(programProducts.getProgramProductsPK()) != null) {
                throw new PreexistingEntityException("ProgramProducts " + programProducts + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProgramProducts programProducts) throws NonexistentEntityException, Exception {
        programProducts.getProgramProductsPK().setProgramId(programProducts.getProgram().getId());
        programProducts.getProgramProductsPK().setProductId(programProducts.getProduct().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramProducts persistentProgramProducts = em.find(ProgramProducts.class, programProducts.getProgramProductsPK());
            Product productOld = persistentProgramProducts.getProduct();
            Product productNew = programProducts.getProduct();
            Program programOld = persistentProgramProducts.getProgram();
            Program programNew = programProducts.getProgram();
            if (productNew != null) {
                productNew = em.getReference(productNew.getClass(), productNew.getId());
                programProducts.setProduct(productNew);
            }
            if (programNew != null) {
                programNew = em.getReference(programNew.getClass(), programNew.getId());
                programProducts.setProgram(programNew);
            }
            programProducts = em.merge(programProducts);
            if (productOld != null && !productOld.equals(productNew)) {
                productOld.getProgramProductsCollection().remove(programProducts);
                productOld = em.merge(productOld);
            }
            if (productNew != null && !productNew.equals(productOld)) {
                productNew.getProgramProductsCollection().add(programProducts);
                productNew = em.merge(productNew);
            }
            if (programOld != null && !programOld.equals(programNew)) {
                programOld.getProgramProductsCollection().remove(programProducts);
                programOld = em.merge(programOld);
            }
            if (programNew != null && !programNew.equals(programOld)) {
                programNew.getProgramProductsCollection().add(programProducts);
                programNew = em.merge(programNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProgramProductsPK id = programProducts.getProgramProductsPK();
                if (findProgramProducts(id) == null) {
                    throw new NonexistentEntityException("The programProducts with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProgramProductsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramProducts programProducts;
            try {
                programProducts = em.getReference(ProgramProducts.class, id);
                programProducts.getProgramProductsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programProducts with id " + id + " no longer exists.", enfe);
            }
            Product product = programProducts.getProduct();
            if (product != null) {
                product.getProgramProductsCollection().remove(programProducts);
                product = em.merge(product);
            }
            Program program = programProducts.getProgram();
            if (program != null) {
                program.getProgramProductsCollection().remove(programProducts);
                program = em.merge(program);
            }
            em.remove(programProducts);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProgramProducts> findProgramProductsEntities() {
        return findProgramProductsEntities(true, -1, -1);
    }

    public List<ProgramProducts> findProgramProductsEntities(int maxResults, int firstResult) {
        return findProgramProductsEntities(false, maxResults, firstResult);
    }

    private List<ProgramProducts> findProgramProductsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProgramProducts.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProgramProducts findProgramProducts(ProgramProductsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramProducts.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramProductsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProgramProducts> rt = cq.from(ProgramProducts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    

    public List<ProgramProducts> findProgramProductsEntities(int programId) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();      
            CriteriaQuery cq = builder.createQuery();      
            Root root = cq.from(ProgramProducts.class);
            cq.select(root).where(builder.and(builder.equal(root.get("program_id"), programId)));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
