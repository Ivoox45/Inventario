/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.DetalleVenta;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author USER
 */
public class DetalleVentaJpaController implements Serializable {

    public DetalleVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public DetalleVentaJpaController() {
        emf = Persistence.createEntityManagerFactory("Inventario");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleVenta detalleVenta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta = detalleVenta.getVenta();
            if (venta != null) {
                venta = em.getReference(venta.getClass(), venta.getId());
                detalleVenta.setVenta(venta);
            }
            em.persist(detalleVenta);
            if (venta != null) {
                venta.getDetallesVenta().add(detalleVenta);
                venta = em.merge(venta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleVenta detalleVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleVenta persistentDetalleVenta = em.find(DetalleVenta.class, detalleVenta.getId());
            Venta ventaOld = persistentDetalleVenta.getVenta();
            Venta ventaNew = detalleVenta.getVenta();
            if (ventaNew != null) {
                ventaNew = em.getReference(ventaNew.getClass(), ventaNew.getId());
                detalleVenta.setVenta(ventaNew);
            }
            detalleVenta = em.merge(detalleVenta);
            if (ventaOld != null && !ventaOld.equals(ventaNew)) {
                ventaOld.getDetallesVenta().remove(detalleVenta);
                ventaOld = em.merge(ventaOld);
            }
            if (ventaNew != null && !ventaNew.equals(ventaOld)) {
                ventaNew.getDetallesVenta().add(detalleVenta);
                ventaNew = em.merge(ventaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleVenta.getId();
                if (findDetalleVenta(id) == null) {
                    throw new NonexistentEntityException("The detalleVenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleVenta detalleVenta;
            try {
                detalleVenta = em.getReference(DetalleVenta.class, id);
                detalleVenta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleVenta with id " + id + " no longer exists.", enfe);
            }
            Venta venta = detalleVenta.getVenta();
            if (venta != null) {
                venta.getDetallesVenta().remove(detalleVenta);
                venta = em.merge(venta);
            }
            em.remove(detalleVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleVenta> findDetalleVentaEntities() {
        return findDetalleVentaEntities(true, -1, -1);
    }

    public List<DetalleVenta> findDetalleVentaEntities(int maxResults, int firstResult) {
        return findDetalleVentaEntities(false, maxResults, firstResult);
    }

    private List<DetalleVenta> findDetalleVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleVenta.class));
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

    public DetalleVenta findDetalleVenta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleVenta> rt = cq.from(DetalleVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<DetalleVenta> obtenerDetallesPorVenta(Long idVenta) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT d FROM DetalleVenta d WHERE d.venta.id = :idVenta", DetalleVenta.class)
                    .setParameter("idVenta", idVenta)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<DetalleVenta> obtenerDetalleVentasPorIdsVentas(List<Long> idsVentas) {
        if (idsVentas == null || idsVentas.isEmpty()) {
            return new ArrayList<>();
        }

        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT d FROM DetalleVenta d WHERE d.venta.id IN :ids", DetalleVenta.class)
                    .setParameter("ids", idsVentas)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
