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
import eveniment.Entities.ProgramProducts;
import java.util.ArrayList;
import java.util.Collection;
import eveniment.Entities.EventItem;
import eveniment.Entities.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Andrei
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) {
        if (product.getProgramProductsCollection() == null) {
            product.setProgramProductsCollection(new ArrayList<ProgramProducts>());
        }
        if (product.getEventItemCollection() == null) {
            product.setEventItemCollection(new ArrayList<EventItem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ProgramProducts> attachedProgramProductsCollection = new ArrayList<ProgramProducts>();
            for (ProgramProducts programProductsCollectionProgramProductsToAttach : product.getProgramProductsCollection()) {
                programProductsCollectionProgramProductsToAttach = em.getReference(programProductsCollectionProgramProductsToAttach.getClass(), programProductsCollectionProgramProductsToAttach.getProgramProductsPK());
                attachedProgramProductsCollection.add(programProductsCollectionProgramProductsToAttach);
            }
            product.setProgramProductsCollection(attachedProgramProductsCollection);
            Collection<EventItem> attachedEventItemCollection = new ArrayList<EventItem>();
            for (EventItem eventItemCollectionEventItemToAttach : product.getEventItemCollection()) {
                eventItemCollectionEventItemToAttach = em.getReference(eventItemCollectionEventItemToAttach.getClass(), eventItemCollectionEventItemToAttach.getId());
                attachedEventItemCollection.add(eventItemCollectionEventItemToAttach);
            }
            product.setEventItemCollection(attachedEventItemCollection);
            em.persist(product);
            for (ProgramProducts programProductsCollectionProgramProducts : product.getProgramProductsCollection()) {
                Product oldProductOfProgramProductsCollectionProgramProducts = programProductsCollectionProgramProducts.getProduct();
                programProductsCollectionProgramProducts.setProduct(product);
                programProductsCollectionProgramProducts = em.merge(programProductsCollectionProgramProducts);
                if (oldProductOfProgramProductsCollectionProgramProducts != null) {
                    oldProductOfProgramProductsCollectionProgramProducts.getProgramProductsCollection().remove(programProductsCollectionProgramProducts);
                    oldProductOfProgramProductsCollectionProgramProducts = em.merge(oldProductOfProgramProductsCollectionProgramProducts);
                }
            }
            for (EventItem eventItemCollectionEventItem : product.getEventItemCollection()) {
                Product oldProductIdOfEventItemCollectionEventItem = eventItemCollectionEventItem.getProductId();
                eventItemCollectionEventItem.setProductId(product);
                eventItemCollectionEventItem = em.merge(eventItemCollectionEventItem);
                if (oldProductIdOfEventItemCollectionEventItem != null) {
                    oldProductIdOfEventItemCollectionEventItem.getEventItemCollection().remove(eventItemCollectionEventItem);
                    oldProductIdOfEventItemCollectionEventItem = em.merge(oldProductIdOfEventItemCollectionEventItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getId());
            Collection<ProgramProducts> programProductsCollectionOld = persistentProduct.getProgramProductsCollection();
            Collection<ProgramProducts> programProductsCollectionNew = product.getProgramProductsCollection();
            Collection<EventItem> eventItemCollectionOld = persistentProduct.getEventItemCollection();
            Collection<EventItem> eventItemCollectionNew = product.getEventItemCollection();
            List<String> illegalOrphanMessages = null;
            for (ProgramProducts programProductsCollectionOldProgramProducts : programProductsCollectionOld) {
                if (!programProductsCollectionNew.contains(programProductsCollectionOldProgramProducts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProgramProducts " + programProductsCollectionOldProgramProducts + " since its product field is not nullable.");
                }
            }
            for (EventItem eventItemCollectionOldEventItem : eventItemCollectionOld) {
                if (!eventItemCollectionNew.contains(eventItemCollectionOldEventItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EventItem " + eventItemCollectionOldEventItem + " since its productId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ProgramProducts> attachedProgramProductsCollectionNew = new ArrayList<ProgramProducts>();
            for (ProgramProducts programProductsCollectionNewProgramProductsToAttach : programProductsCollectionNew) {
                programProductsCollectionNewProgramProductsToAttach = em.getReference(programProductsCollectionNewProgramProductsToAttach.getClass(), programProductsCollectionNewProgramProductsToAttach.getProgramProductsPK());
                attachedProgramProductsCollectionNew.add(programProductsCollectionNewProgramProductsToAttach);
            }
            programProductsCollectionNew = attachedProgramProductsCollectionNew;
            product.setProgramProductsCollection(programProductsCollectionNew);
            Collection<EventItem> attachedEventItemCollectionNew = new ArrayList<EventItem>();
            for (EventItem eventItemCollectionNewEventItemToAttach : eventItemCollectionNew) {
                eventItemCollectionNewEventItemToAttach = em.getReference(eventItemCollectionNewEventItemToAttach.getClass(), eventItemCollectionNewEventItemToAttach.getId());
                attachedEventItemCollectionNew.add(eventItemCollectionNewEventItemToAttach);
            }
            eventItemCollectionNew = attachedEventItemCollectionNew;
            product.setEventItemCollection(eventItemCollectionNew);
            product = em.merge(product);
            for (ProgramProducts programProductsCollectionNewProgramProducts : programProductsCollectionNew) {
                if (!programProductsCollectionOld.contains(programProductsCollectionNewProgramProducts)) {
                    Product oldProductOfProgramProductsCollectionNewProgramProducts = programProductsCollectionNewProgramProducts.getProduct();
                    programProductsCollectionNewProgramProducts.setProduct(product);
                    programProductsCollectionNewProgramProducts = em.merge(programProductsCollectionNewProgramProducts);
                    if (oldProductOfProgramProductsCollectionNewProgramProducts != null && !oldProductOfProgramProductsCollectionNewProgramProducts.equals(product)) {
                        oldProductOfProgramProductsCollectionNewProgramProducts.getProgramProductsCollection().remove(programProductsCollectionNewProgramProducts);
                        oldProductOfProgramProductsCollectionNewProgramProducts = em.merge(oldProductOfProgramProductsCollectionNewProgramProducts);
                    }
                }
            }
            for (EventItem eventItemCollectionNewEventItem : eventItemCollectionNew) {
                if (!eventItemCollectionOld.contains(eventItemCollectionNewEventItem)) {
                    Product oldProductIdOfEventItemCollectionNewEventItem = eventItemCollectionNewEventItem.getProductId();
                    eventItemCollectionNewEventItem.setProductId(product);
                    eventItemCollectionNewEventItem = em.merge(eventItemCollectionNewEventItem);
                    if (oldProductIdOfEventItemCollectionNewEventItem != null && !oldProductIdOfEventItemCollectionNewEventItem.equals(product)) {
                        oldProductIdOfEventItemCollectionNewEventItem.getEventItemCollection().remove(eventItemCollectionNewEventItem);
                        oldProductIdOfEventItemCollectionNewEventItem = em.merge(oldProductIdOfEventItemCollectionNewEventItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getId();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
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
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ProgramProducts> programProductsCollectionOrphanCheck = product.getProgramProductsCollection();
            for (ProgramProducts programProductsCollectionOrphanCheckProgramProducts : programProductsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the ProgramProducts " + programProductsCollectionOrphanCheckProgramProducts + " in its programProductsCollection field has a non-nullable product field.");
            }
            Collection<EventItem> eventItemCollectionOrphanCheck = product.getEventItemCollection();
            for (EventItem eventItemCollectionOrphanCheckEventItem : eventItemCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the EventItem " + eventItemCollectionOrphanCheckEventItem + " in its eventItemCollection field has a non-nullable productId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
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

    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
