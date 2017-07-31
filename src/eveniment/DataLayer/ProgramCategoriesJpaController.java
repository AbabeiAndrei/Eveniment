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
import eveniment.Entities.Program;
import eveniment.Entities.ProgramCategories;
import eveniment.Entities.ProgramCategoriesPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Andrei
 */
public class ProgramCategoriesJpaController implements Serializable {

    public ProgramCategoriesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramCategories programCategories) throws PreexistingEntityException, Exception {
        if (programCategories.getProgramCategoriesPK() == null) {
            programCategories.setProgramCategoriesPK(new ProgramCategoriesPK());
        }
        programCategories.getProgramCategoriesPK().setProgramId(programCategories.getProgram().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Program program = programCategories.getProgram();
            if (program != null) {
                program = em.getReference(program.getClass(), program.getId());
                programCategories.setProgram(program);
            }
            em.persist(programCategories);
            if (program != null) {
                program.getProgramCategoriesCollection().add(programCategories);
                program = em.merge(program);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramCategories(programCategories.getProgramCategoriesPK()) != null) {
                throw new PreexistingEntityException("ProgramCategories " + programCategories + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProgramCategories programCategories) throws NonexistentEntityException, Exception {
        programCategories.getProgramCategoriesPK().setProgramId(programCategories.getProgram().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramCategories persistentProgramCategories = em.find(ProgramCategories.class, programCategories.getProgramCategoriesPK());
            Program programOld = persistentProgramCategories.getProgram();
            Program programNew = programCategories.getProgram();
            if (programNew != null) {
                programNew = em.getReference(programNew.getClass(), programNew.getId());
                programCategories.setProgram(programNew);
            }
            programCategories = em.merge(programCategories);
            if (programOld != null && !programOld.equals(programNew)) {
                programOld.getProgramCategoriesCollection().remove(programCategories);
                programOld = em.merge(programOld);
            }
            if (programNew != null && !programNew.equals(programOld)) {
                programNew.getProgramCategoriesCollection().add(programCategories);
                programNew = em.merge(programNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProgramCategoriesPK id = programCategories.getProgramCategoriesPK();
                if (findProgramCategories(id) == null) {
                    throw new NonexistentEntityException("The programCategories with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProgramCategoriesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramCategories programCategories;
            try {
                programCategories = em.getReference(ProgramCategories.class, id);
                programCategories.getProgramCategoriesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programCategories with id " + id + " no longer exists.", enfe);
            }
            Program program = programCategories.getProgram();
            if (program != null) {
                program.getProgramCategoriesCollection().remove(programCategories);
                program = em.merge(program);
            }
            em.remove(programCategories);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProgramCategories> findProgramCategoriesEntities() {
        return findProgramCategoriesEntities(true, -1, -1);
    }

    public List<ProgramCategories> findProgramCategoriesEntities(int maxResults, int firstResult) {
        return findProgramCategoriesEntities(false, maxResults, firstResult);
    }

    private List<ProgramCategories> findProgramCategoriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProgramCategories.class));
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

    public ProgramCategories findProgramCategories(ProgramCategoriesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramCategories.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramCategoriesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProgramCategories> rt = cq.from(ProgramCategories.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
