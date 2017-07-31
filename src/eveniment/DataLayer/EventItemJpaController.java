/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.DataLayer;

import eveniment.DataLayer.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import eveniment.Entities.Event;
import eveniment.Entities.EventItem;
import eveniment.Entities.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Andrei
 */
public class EventItemJpaController implements Serializable {

    public EventItemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EventItem eventItem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Event eventId = eventItem.getEventId();
            if (eventId != null) {
                eventId = em.getReference(eventId.getClass(), eventId.getId());
                eventItem.setEventId(eventId);
            }
            Product productId = eventItem.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getId());
                eventItem.setProductId(productId);
            }
            em.persist(eventItem);
            if (eventId != null) {
                eventId.getEventItemCollection().add(eventItem);
                eventId = em.merge(eventId);
            }
            if (productId != null) {
                productId.getEventItemCollection().add(eventItem);
                productId = em.merge(productId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EventItem eventItem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventItem persistentEventItem = em.find(EventItem.class, eventItem.getId());
            Event eventIdOld = persistentEventItem.getEventId();
            Event eventIdNew = eventItem.getEventId();
            Product productIdOld = persistentEventItem.getProductId();
            Product productIdNew = eventItem.getProductId();
            if (eventIdNew != null) {
                eventIdNew = em.getReference(eventIdNew.getClass(), eventIdNew.getId());
                eventItem.setEventId(eventIdNew);
            }
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getId());
                eventItem.setProductId(productIdNew);
            }
            eventItem = em.merge(eventItem);
            if (eventIdOld != null && !eventIdOld.equals(eventIdNew)) {
                eventIdOld.getEventItemCollection().remove(eventItem);
                eventIdOld = em.merge(eventIdOld);
            }
            if (eventIdNew != null && !eventIdNew.equals(eventIdOld)) {
                eventIdNew.getEventItemCollection().add(eventItem);
                eventIdNew = em.merge(eventIdNew);
            }
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getEventItemCollection().remove(eventItem);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getEventItemCollection().add(eventItem);
                productIdNew = em.merge(productIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eventItem.getId();
                if (findEventItem(id) == null) {
                    throw new NonexistentEntityException("The eventItem with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventItem eventItem;
            try {
                eventItem = em.getReference(EventItem.class, id);
                eventItem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventItem with id " + id + " no longer exists.", enfe);
            }
            Event eventId = eventItem.getEventId();
            if (eventId != null) {
                eventId.getEventItemCollection().remove(eventItem);
                eventId = em.merge(eventId);
            }
            Product productId = eventItem.getProductId();
            if (productId != null) {
                productId.getEventItemCollection().remove(eventItem);
                productId = em.merge(productId);
            }
            em.remove(eventItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EventItem> findEventItemEntities() {
        return findEventItemEntities(true, -1, -1);
    }

    public List<EventItem> findEventItemEntities(int maxResults, int firstResult) {
        return findEventItemEntities(false, maxResults, firstResult);
    }

    private List<EventItem> findEventItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EventItem.class));
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

    public EventItem findEventItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EventItem.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EventItem> rt = cq.from(EventItem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
