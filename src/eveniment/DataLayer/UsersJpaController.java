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
import eveniment.Entities.Event;
import eveniment.Entities.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Andrei
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getEventCollection() == null) {
            users.setEventCollection(new ArrayList<Event>());
        }
        if (users.getEventCollection1() == null) {
            users.setEventCollection1(new ArrayList<Event>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Event> attachedEventCollection = new ArrayList<Event>();
            for (Event eventCollectionEventToAttach : users.getEventCollection()) {
                eventCollectionEventToAttach = em.getReference(eventCollectionEventToAttach.getClass(), eventCollectionEventToAttach.getId());
                attachedEventCollection.add(eventCollectionEventToAttach);
            }
            users.setEventCollection(attachedEventCollection);
            Collection<Event> attachedEventCollection1 = new ArrayList<Event>();
            for (Event eventCollection1EventToAttach : users.getEventCollection1()) {
                eventCollection1EventToAttach = em.getReference(eventCollection1EventToAttach.getClass(), eventCollection1EventToAttach.getId());
                attachedEventCollection1.add(eventCollection1EventToAttach);
            }
            users.setEventCollection1(attachedEventCollection1);
            em.persist(users);
            for (Event eventCollectionEvent : users.getEventCollection()) {
                Users oldCreatedByOfEventCollectionEvent = eventCollectionEvent.getCreatedBy();
                eventCollectionEvent.setCreatedBy(users);
                eventCollectionEvent = em.merge(eventCollectionEvent);
                if (oldCreatedByOfEventCollectionEvent != null) {
                    oldCreatedByOfEventCollectionEvent.getEventCollection().remove(eventCollectionEvent);
                    oldCreatedByOfEventCollectionEvent = em.merge(oldCreatedByOfEventCollectionEvent);
                }
            }
            for (Event eventCollection1Event : users.getEventCollection1()) {
                Users oldForUserOfEventCollection1Event = eventCollection1Event.getForUser();
                eventCollection1Event.setForUser(users);
                eventCollection1Event = em.merge(eventCollection1Event);
                if (oldForUserOfEventCollection1Event != null) {
                    oldForUserOfEventCollection1Event.getEventCollection1().remove(eventCollection1Event);
                    oldForUserOfEventCollection1Event = em.merge(oldForUserOfEventCollection1Event);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getId());
            Collection<Event> eventCollectionOld = persistentUsers.getEventCollection();
            Collection<Event> eventCollectionNew = users.getEventCollection();
            Collection<Event> eventCollection1Old = persistentUsers.getEventCollection1();
            Collection<Event> eventCollection1New = users.getEventCollection1();
            List<String> illegalOrphanMessages = null;
            for (Event eventCollectionOldEvent : eventCollectionOld) {
                if (!eventCollectionNew.contains(eventCollectionOldEvent)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Event " + eventCollectionOldEvent + " since its createdBy field is not nullable.");
                }
            }
            for (Event eventCollection1OldEvent : eventCollection1Old) {
                if (!eventCollection1New.contains(eventCollection1OldEvent)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Event " + eventCollection1OldEvent + " since its forUser field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Event> attachedEventCollectionNew = new ArrayList<Event>();
            for (Event eventCollectionNewEventToAttach : eventCollectionNew) {
                eventCollectionNewEventToAttach = em.getReference(eventCollectionNewEventToAttach.getClass(), eventCollectionNewEventToAttach.getId());
                attachedEventCollectionNew.add(eventCollectionNewEventToAttach);
            }
            eventCollectionNew = attachedEventCollectionNew;
            users.setEventCollection(eventCollectionNew);
            Collection<Event> attachedEventCollection1New = new ArrayList<Event>();
            for (Event eventCollection1NewEventToAttach : eventCollection1New) {
                eventCollection1NewEventToAttach = em.getReference(eventCollection1NewEventToAttach.getClass(), eventCollection1NewEventToAttach.getId());
                attachedEventCollection1New.add(eventCollection1NewEventToAttach);
            }
            eventCollection1New = attachedEventCollection1New;
            users.setEventCollection1(eventCollection1New);
            users = em.merge(users);
            for (Event eventCollectionNewEvent : eventCollectionNew) {
                if (!eventCollectionOld.contains(eventCollectionNewEvent)) {
                    Users oldCreatedByOfEventCollectionNewEvent = eventCollectionNewEvent.getCreatedBy();
                    eventCollectionNewEvent.setCreatedBy(users);
                    eventCollectionNewEvent = em.merge(eventCollectionNewEvent);
                    if (oldCreatedByOfEventCollectionNewEvent != null && !oldCreatedByOfEventCollectionNewEvent.equals(users)) {
                        oldCreatedByOfEventCollectionNewEvent.getEventCollection().remove(eventCollectionNewEvent);
                        oldCreatedByOfEventCollectionNewEvent = em.merge(oldCreatedByOfEventCollectionNewEvent);
                    }
                }
            }
            for (Event eventCollection1NewEvent : eventCollection1New) {
                if (!eventCollection1Old.contains(eventCollection1NewEvent)) {
                    Users oldForUserOfEventCollection1NewEvent = eventCollection1NewEvent.getForUser();
                    eventCollection1NewEvent.setForUser(users);
                    eventCollection1NewEvent = em.merge(eventCollection1NewEvent);
                    if (oldForUserOfEventCollection1NewEvent != null && !oldForUserOfEventCollection1NewEvent.equals(users)) {
                        oldForUserOfEventCollection1NewEvent.getEventCollection1().remove(eventCollection1NewEvent);
                        oldForUserOfEventCollection1NewEvent = em.merge(oldForUserOfEventCollection1NewEvent);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Event> eventCollectionOrphanCheck = users.getEventCollection();
            for (Event eventCollectionOrphanCheckEvent : eventCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Event " + eventCollectionOrphanCheckEvent + " in its eventCollection field has a non-nullable createdBy field.");
            }
            Collection<Event> eventCollection1OrphanCheck = users.getEventCollection1();
            for (Event eventCollection1OrphanCheckEvent : eventCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Event " + eventCollection1OrphanCheckEvent + " in its eventCollection1 field has a non-nullable forUser field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
