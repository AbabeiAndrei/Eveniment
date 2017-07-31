/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.DataLayer;

import eveniment.DataLayer.exceptions.IllegalOrphanException;
import eveniment.DataLayer.exceptions.NonexistentEntityException;
import eveniment.Entities.Event;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import eveniment.Entities.Program;
import eveniment.Entities.Users;
import eveniment.Entities.EventItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Andrei
 */
public class EventJpaController implements Serializable {

    public EventJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Event event) {
        if (event.getEventItemCollection() == null) {
            event.setEventItemCollection(new ArrayList<EventItem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Program programId = event.getProgramId();
            if (programId != null) {
                programId = em.getReference(programId.getClass(), programId.getId());
                event.setProgramId(programId);
            }
            Users createdBy = event.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                event.setCreatedBy(createdBy);
            }
            Users forUser = event.getForUser();
            if (forUser != null) {
                forUser = em.getReference(forUser.getClass(), forUser.getId());
                event.setForUser(forUser);
            }
            Collection<EventItem> attachedEventItemCollection = new ArrayList<EventItem>();
            for (EventItem eventItemCollectionEventItemToAttach : event.getEventItemCollection()) {
                eventItemCollectionEventItemToAttach = em.getReference(eventItemCollectionEventItemToAttach.getClass(), eventItemCollectionEventItemToAttach.getId());
                attachedEventItemCollection.add(eventItemCollectionEventItemToAttach);
            }
            event.setEventItemCollection(attachedEventItemCollection);
            em.persist(event);
            if (programId != null) {
                programId.getEventCollection().add(event);
                programId = em.merge(programId);
            }
            if (createdBy != null) {
                createdBy.getEventCollection().add(event);
                createdBy = em.merge(createdBy);
            }
            if (forUser != null) {
                forUser.getEventCollection().add(event);
                forUser = em.merge(forUser);
            }
            for (EventItem eventItemCollectionEventItem : event.getEventItemCollection()) {
                Event oldEventIdOfEventItemCollectionEventItem = eventItemCollectionEventItem.getEventId();
                eventItemCollectionEventItem.setEventId(event);
                eventItemCollectionEventItem = em.merge(eventItemCollectionEventItem);
                if (oldEventIdOfEventItemCollectionEventItem != null) {
                    oldEventIdOfEventItemCollectionEventItem.getEventItemCollection().remove(eventItemCollectionEventItem);
                    oldEventIdOfEventItemCollectionEventItem = em.merge(oldEventIdOfEventItemCollectionEventItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Event event) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Event persistentEvent = em.find(Event.class, event.getId());
            Program programIdOld = persistentEvent.getProgramId();
            Program programIdNew = event.getProgramId();
            Users createdByOld = persistentEvent.getCreatedBy();
            Users createdByNew = event.getCreatedBy();
            Users forUserOld = persistentEvent.getForUser();
            Users forUserNew = event.getForUser();
            Collection<EventItem> eventItemCollectionOld = persistentEvent.getEventItemCollection();
            Collection<EventItem> eventItemCollectionNew = event.getEventItemCollection();
            List<String> illegalOrphanMessages = null;
            for (EventItem eventItemCollectionOldEventItem : eventItemCollectionOld) {
                if (!eventItemCollectionNew.contains(eventItemCollectionOldEventItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EventItem " + eventItemCollectionOldEventItem + " since its eventId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programIdNew != null) {
                programIdNew = em.getReference(programIdNew.getClass(), programIdNew.getId());
                event.setProgramId(programIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                event.setCreatedBy(createdByNew);
            }
            if (forUserNew != null) {
                forUserNew = em.getReference(forUserNew.getClass(), forUserNew.getId());
                event.setForUser(forUserNew);
            }
            Collection<EventItem> attachedEventItemCollectionNew = new ArrayList<EventItem>();
            for (EventItem eventItemCollectionNewEventItemToAttach : eventItemCollectionNew) {
                eventItemCollectionNewEventItemToAttach = em.getReference(eventItemCollectionNewEventItemToAttach.getClass(), eventItemCollectionNewEventItemToAttach.getId());
                attachedEventItemCollectionNew.add(eventItemCollectionNewEventItemToAttach);
            }
            eventItemCollectionNew = attachedEventItemCollectionNew;
            event.setEventItemCollection(eventItemCollectionNew);
            event = em.merge(event);
            if (programIdOld != null && !programIdOld.equals(programIdNew)) {
                programIdOld.getEventCollection().remove(event);
                programIdOld = em.merge(programIdOld);
            }
            if (programIdNew != null && !programIdNew.equals(programIdOld)) {
                programIdNew.getEventCollection().add(event);
                programIdNew = em.merge(programIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getEventCollection().remove(event);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getEventCollection().add(event);
                createdByNew = em.merge(createdByNew);
            }
            if (forUserOld != null && !forUserOld.equals(forUserNew)) {
                forUserOld.getEventCollection().remove(event);
                forUserOld = em.merge(forUserOld);
            }
            if (forUserNew != null && !forUserNew.equals(forUserOld)) {
                forUserNew.getEventCollection().add(event);
                forUserNew = em.merge(forUserNew);
            }
            for (EventItem eventItemCollectionNewEventItem : eventItemCollectionNew) {
                if (!eventItemCollectionOld.contains(eventItemCollectionNewEventItem)) {
                    Event oldEventIdOfEventItemCollectionNewEventItem = eventItemCollectionNewEventItem.getEventId();
                    eventItemCollectionNewEventItem.setEventId(event);
                    eventItemCollectionNewEventItem = em.merge(eventItemCollectionNewEventItem);
                    if (oldEventIdOfEventItemCollectionNewEventItem != null && !oldEventIdOfEventItemCollectionNewEventItem.equals(event)) {
                        oldEventIdOfEventItemCollectionNewEventItem.getEventItemCollection().remove(eventItemCollectionNewEventItem);
                        oldEventIdOfEventItemCollectionNewEventItem = em.merge(oldEventIdOfEventItemCollectionNewEventItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = event.getId();
                if (findEvent(id) == null) {
                    throw new NonexistentEntityException("The event with id " + id + " no longer exists.");
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
            Event event;
            try {
                event = em.getReference(Event.class, id);
                event.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The event with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<EventItem> eventItemCollectionOrphanCheck = event.getEventItemCollection();
            for (EventItem eventItemCollectionOrphanCheckEventItem : eventItemCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Event (" + event + ") cannot be destroyed since the EventItem " + eventItemCollectionOrphanCheckEventItem + " in its eventItemCollection field has a non-nullable eventId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Program programId = event.getProgramId();
            if (programId != null) {
                programId.getEventCollection().remove(event);
                programId = em.merge(programId);
            }
            Users createdBy = event.getCreatedBy();
            if (createdBy != null) {
                createdBy.getEventCollection().remove(event);
                createdBy = em.merge(createdBy);
            }
            Users forUser = event.getForUser();
            if (forUser != null) {
                forUser.getEventCollection().remove(event);
                forUser = em.merge(forUser);
            }
            em.remove(event);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Event> findEventEntities() {
        return findEventEntities(true, -1, -1);
    }

    public List<Event> findEventEntities(int maxResults, int firstResult) {
        return findEventEntities(false, maxResults, firstResult);
    }

    private List<Event> findEventEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Event.class));
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

    public Event findEvent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Event.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Event> rt = cq.from(Event.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
