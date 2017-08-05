
package eveniment.DataLayer;

import eveniment.DataLayer.exceptions.NonexistentEntityException;
import eveniment.Entities.Period;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PeriodJpaController implements Serializable {

    public PeriodJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Period period) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(period);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Period period) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            period = em.merge(period);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = period.getId();
                if (findPeriod(id) == null) {
                    throw new NonexistentEntityException("The period with id " + id + " no longer exists.");
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
            Period period;
            try {
                period = em.getReference(Period.class, id);
                period.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The period with id " + id + " no longer exists.", enfe);
            }
            em.remove(period);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Period> findPeriodEntities() {
        return findPeriodEntities(true, -1, -1);
    }

    public List<Period> findPeriodEntities(int maxResults, int firstResult) {
        return findPeriodEntities(false, maxResults, firstResult);
    }

    private List<Period> findPeriodEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Period.class));
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

    public Period findPeriod(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Period.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Period> rt = cq.from(Period.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public float getPrice(int day, int month, int year) {
        BigDecimal maxPrice = new BigDecimal(0f);
        
        Date date = new GregorianCalendar(year, month - 1, day).getTime();
        
        for(Period per : findPeriodEntities())
            if(per.getFrom().before(date) && per.getTo().after(date) && per.getPrice().compareTo(maxPrice) > 0)
                maxPrice = per.getPrice();
        
        return maxPrice.floatValue();
    }
}
