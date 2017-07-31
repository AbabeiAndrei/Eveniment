/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.DataLayer;

import eveniment.DataLayer.exceptions.IllegalOrphanException;
import eveniment.DataLayer.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import eveniment.Entities.ProgramCategories;
import java.util.ArrayList;
import java.util.Collection;
import eveniment.Entities.Event;
import eveniment.Entities.Program;
import eveniment.Entities.ProgramProducts;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Andrei
 */
public class ProgramJpaController implements Serializable {

    public ProgramJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Program program) {
        if (program.getProgramCategoriesCollection() == null) {
            program.setProgramCategoriesCollection(new ArrayList<ProgramCategories>());
        }
        if (program.getEventCollection() == null) {
            program.setEventCollection(new ArrayList<Event>());
        }
        if (program.getProgramProductsCollection() == null) {
            program.setProgramProductsCollection(new ArrayList<ProgramProducts>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ProgramCategories> attachedProgramCategoriesCollection = new ArrayList<ProgramCategories>();
            for (ProgramCategories programCategoriesCollectionProgramCategoriesToAttach : program.getProgramCategoriesCollection()) {
                programCategoriesCollectionProgramCategoriesToAttach = em.getReference(programCategoriesCollectionProgramCategoriesToAttach.getClass(), programCategoriesCollectionProgramCategoriesToAttach.getProgramCategoriesPK());
                attachedProgramCategoriesCollection.add(programCategoriesCollectionProgramCategoriesToAttach);
            }
            program.setProgramCategoriesCollection(attachedProgramCategoriesCollection);
            Collection<Event> attachedEventCollection = new ArrayList<Event>();
            for (Event eventCollectionEventToAttach : program.getEventCollection()) {
                eventCollectionEventToAttach = em.getReference(eventCollectionEventToAttach.getClass(), eventCollectionEventToAttach.getId());
                attachedEventCollection.add(eventCollectionEventToAttach);
            }
            program.setEventCollection(attachedEventCollection);
            Collection<ProgramProducts> attachedProgramProductsCollection = new ArrayList<ProgramProducts>();
            for (ProgramProducts programProductsCollectionProgramProductsToAttach : program.getProgramProductsCollection()) {
                programProductsCollectionProgramProductsToAttach = em.getReference(programProductsCollectionProgramProductsToAttach.getClass(), programProductsCollectionProgramProductsToAttach.getProgramProductsPK());
                attachedProgramProductsCollection.add(programProductsCollectionProgramProductsToAttach);
            }
            program.setProgramProductsCollection(attachedProgramProductsCollection);
            em.persist(program);
            for (ProgramCategories programCategoriesCollectionProgramCategories : program.getProgramCategoriesCollection()) {
                Program oldProgramOfProgramCategoriesCollectionProgramCategories = programCategoriesCollectionProgramCategories.getProgram();
                programCategoriesCollectionProgramCategories.setProgram(program);
                programCategoriesCollectionProgramCategories = em.merge(programCategoriesCollectionProgramCategories);
                if (oldProgramOfProgramCategoriesCollectionProgramCategories != null) {
                    oldProgramOfProgramCategoriesCollectionProgramCategories.getProgramCategoriesCollection().remove(programCategoriesCollectionProgramCategories);
                    oldProgramOfProgramCategoriesCollectionProgramCategories = em.merge(oldProgramOfProgramCategoriesCollectionProgramCategories);
                }
            }
            for (Event eventCollectionEvent : program.getEventCollection()) {
                Program oldProgramIdOfEventCollectionEvent = eventCollectionEvent.getProgramId();
                eventCollectionEvent.setProgramId(program);
                eventCollectionEvent = em.merge(eventCollectionEvent);
                if (oldProgramIdOfEventCollectionEvent != null) {
                    oldProgramIdOfEventCollectionEvent.getEventCollection().remove(eventCollectionEvent);
                    oldProgramIdOfEventCollectionEvent = em.merge(oldProgramIdOfEventCollectionEvent);
                }
            }
            for (ProgramProducts programProductsCollectionProgramProducts : program.getProgramProductsCollection()) {
                Program oldProgramOfProgramProductsCollectionProgramProducts = programProductsCollectionProgramProducts.getProgram();
                programProductsCollectionProgramProducts.setProgram(program);
                programProductsCollectionProgramProducts = em.merge(programProductsCollectionProgramProducts);
                if (oldProgramOfProgramProductsCollectionProgramProducts != null) {
                    oldProgramOfProgramProductsCollectionProgramProducts.getProgramProductsCollection().remove(programProductsCollectionProgramProducts);
                    oldProgramOfProgramProductsCollectionProgramProducts = em.merge(oldProgramOfProgramProductsCollectionProgramProducts);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Program program) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Program persistentProgram = em.find(Program.class, program.getId());
            Collection<ProgramCategories> programCategoriesCollectionOld = persistentProgram.getProgramCategoriesCollection();
            Collection<ProgramCategories> programCategoriesCollectionNew = program.getProgramCategoriesCollection();
            Collection<Event> eventCollectionOld = persistentProgram.getEventCollection();
            Collection<Event> eventCollectionNew = program.getEventCollection();
            Collection<ProgramProducts> programProductsCollectionOld = persistentProgram.getProgramProductsCollection();
            Collection<ProgramProducts> programProductsCollectionNew = program.getProgramProductsCollection();
            List<String> illegalOrphanMessages = null;
            for (ProgramCategories programCategoriesCollectionOldProgramCategories : programCategoriesCollectionOld) {
                if (!programCategoriesCollectionNew.contains(programCategoriesCollectionOldProgramCategories)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProgramCategories " + programCategoriesCollectionOldProgramCategories + " since its program field is not nullable.");
                }
            }
            for (Event eventCollectionOldEvent : eventCollectionOld) {
                if (!eventCollectionNew.contains(eventCollectionOldEvent)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Event " + eventCollectionOldEvent + " since its programId field is not nullable.");
                }
            }
            for (ProgramProducts programProductsCollectionOldProgramProducts : programProductsCollectionOld) {
                if (!programProductsCollectionNew.contains(programProductsCollectionOldProgramProducts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProgramProducts " + programProductsCollectionOldProgramProducts + " since its program field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ProgramCategories> attachedProgramCategoriesCollectionNew = new ArrayList<ProgramCategories>();
            for (ProgramCategories programCategoriesCollectionNewProgramCategoriesToAttach : programCategoriesCollectionNew) {
                programCategoriesCollectionNewProgramCategoriesToAttach = em.getReference(programCategoriesCollectionNewProgramCategoriesToAttach.getClass(), programCategoriesCollectionNewProgramCategoriesToAttach.getProgramCategoriesPK());
                attachedProgramCategoriesCollectionNew.add(programCategoriesCollectionNewProgramCategoriesToAttach);
            }
            programCategoriesCollectionNew = attachedProgramCategoriesCollectionNew;
            program.setProgramCategoriesCollection(programCategoriesCollectionNew);
            Collection<Event> attachedEventCollectionNew = new ArrayList<Event>();
            for (Event eventCollectionNewEventToAttach : eventCollectionNew) {
                eventCollectionNewEventToAttach = em.getReference(eventCollectionNewEventToAttach.getClass(), eventCollectionNewEventToAttach.getId());
                attachedEventCollectionNew.add(eventCollectionNewEventToAttach);
            }
            eventCollectionNew = attachedEventCollectionNew;
            program.setEventCollection(eventCollectionNew);
            Collection<ProgramProducts> attachedProgramProductsCollectionNew = new ArrayList<ProgramProducts>();
            for (ProgramProducts programProductsCollectionNewProgramProductsToAttach : programProductsCollectionNew) {
                programProductsCollectionNewProgramProductsToAttach = em.getReference(programProductsCollectionNewProgramProductsToAttach.getClass(), programProductsCollectionNewProgramProductsToAttach.getProgramProductsPK());
                attachedProgramProductsCollectionNew.add(programProductsCollectionNewProgramProductsToAttach);
            }
            programProductsCollectionNew = attachedProgramProductsCollectionNew;
            program.setProgramProductsCollection(programProductsCollectionNew);
            program = em.merge(program);
            for (ProgramCategories programCategoriesCollectionNewProgramCategories : programCategoriesCollectionNew) {
                if (!programCategoriesCollectionOld.contains(programCategoriesCollectionNewProgramCategories)) {
                    Program oldProgramOfProgramCategoriesCollectionNewProgramCategories = programCategoriesCollectionNewProgramCategories.getProgram();
                    programCategoriesCollectionNewProgramCategories.setProgram(program);
                    programCategoriesCollectionNewProgramCategories = em.merge(programCategoriesCollectionNewProgramCategories);
                    if (oldProgramOfProgramCategoriesCollectionNewProgramCategories != null && !oldProgramOfProgramCategoriesCollectionNewProgramCategories.equals(program)) {
                        oldProgramOfProgramCategoriesCollectionNewProgramCategories.getProgramCategoriesCollection().remove(programCategoriesCollectionNewProgramCategories);
                        oldProgramOfProgramCategoriesCollectionNewProgramCategories = em.merge(oldProgramOfProgramCategoriesCollectionNewProgramCategories);
                    }
                }
            }
            for (Event eventCollectionNewEvent : eventCollectionNew) {
                if (!eventCollectionOld.contains(eventCollectionNewEvent)) {
                    Program oldProgramIdOfEventCollectionNewEvent = eventCollectionNewEvent.getProgramId();
                    eventCollectionNewEvent.setProgramId(program);
                    eventCollectionNewEvent = em.merge(eventCollectionNewEvent);
                    if (oldProgramIdOfEventCollectionNewEvent != null && !oldProgramIdOfEventCollectionNewEvent.equals(program)) {
                        oldProgramIdOfEventCollectionNewEvent.getEventCollection().remove(eventCollectionNewEvent);
                        oldProgramIdOfEventCollectionNewEvent = em.merge(oldProgramIdOfEventCollectionNewEvent);
                    }
                }
            }
            for (ProgramProducts programProductsCollectionNewProgramProducts : programProductsCollectionNew) {
                if (!programProductsCollectionOld.contains(programProductsCollectionNewProgramProducts)) {
                    Program oldProgramOfProgramProductsCollectionNewProgramProducts = programProductsCollectionNewProgramProducts.getProgram();
                    programProductsCollectionNewProgramProducts.setProgram(program);
                    programProductsCollectionNewProgramProducts = em.merge(programProductsCollectionNewProgramProducts);
                    if (oldProgramOfProgramProductsCollectionNewProgramProducts != null && !oldProgramOfProgramProductsCollectionNewProgramProducts.equals(program)) {
                        oldProgramOfProgramProductsCollectionNewProgramProducts.getProgramProductsCollection().remove(programProductsCollectionNewProgramProducts);
                        oldProgramOfProgramProductsCollectionNewProgramProducts = em.merge(oldProgramOfProgramProductsCollectionNewProgramProducts);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = program.getId();
                if (findProgram(id) == null) {
                    throw new NonexistentEntityException("The program with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Program program;
            try {
                program = em.getReference(Program.class, id);
                program.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The program with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ProgramCategories> programCategoriesCollectionOrphanCheck = program.getProgramCategoriesCollection();
            for (ProgramCategories programCategoriesCollectionOrphanCheckProgramCategories : programCategoriesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Program (" + program + ") cannot be destroyed since the ProgramCategories " + programCategoriesCollectionOrphanCheckProgramCategories + " in its programCategoriesCollection field has a non-nullable program field.");
            }
            Collection<Event> eventCollectionOrphanCheck = program.getEventCollection();
            for (Event eventCollectionOrphanCheckEvent : eventCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Program (" + program + ") cannot be destroyed since the Event " + eventCollectionOrphanCheckEvent + " in its eventCollection field has a non-nullable programId field.");
            }
            Collection<ProgramProducts> programProductsCollectionOrphanCheck = program.getProgramProductsCollection();
            for (ProgramProducts programProductsCollectionOrphanCheckProgramProducts : programProductsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Program (" + program + ") cannot be destroyed since the ProgramProducts " + programProductsCollectionOrphanCheckProgramProducts + " in its programProductsCollection field has a non-nullable program field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(program);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Program> findProgramEntities() {
        return findProgramEntities(true, -1, -1);
    }

    public List<Program> findProgramEntities(int maxResults, int firstResult) {
        return findProgramEntities(false, maxResults, firstResult);
    }

    private List<Program> findProgramEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Program.class));
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

    public Program findProgram(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Program.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Program> rt = cq.from(Program.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
