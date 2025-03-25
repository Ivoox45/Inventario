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
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import logica.DetalleVenta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.Venta;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author USER
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public VentaJpaController() {
        emf = Persistence.createEntityManagerFactory("Inventario");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        if (venta.getDetallesVenta() == null) {
            venta.setDetallesVenta(new ArrayList<DetalleVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetalleVenta> attachedDetallesVenta = new ArrayList<DetalleVenta>();
            for (DetalleVenta detallesVentaDetalleVentaToAttach : venta.getDetallesVenta()) {
                detallesVentaDetalleVentaToAttach = em.getReference(detallesVentaDetalleVentaToAttach.getClass(), detallesVentaDetalleVentaToAttach.getId());
                attachedDetallesVenta.add(detallesVentaDetalleVentaToAttach);
            }
            venta.setDetallesVenta(attachedDetallesVenta);
            em.persist(venta);
            for (DetalleVenta detallesVentaDetalleVenta : venta.getDetallesVenta()) {
                Venta oldVentaOfDetallesVentaDetalleVenta = detallesVentaDetalleVenta.getVenta();
                detallesVentaDetalleVenta.setVenta(venta);
                detallesVentaDetalleVenta = em.merge(detallesVentaDetalleVenta);
                if (oldVentaOfDetallesVentaDetalleVenta != null) {
                    oldVentaOfDetallesVentaDetalleVenta.getDetallesVenta().remove(detallesVentaDetalleVenta);
                    oldVentaOfDetallesVentaDetalleVenta = em.merge(oldVentaOfDetallesVentaDetalleVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getId());
            List<DetalleVenta> detallesVentaOld = persistentVenta.getDetallesVenta();
            List<DetalleVenta> detallesVentaNew = venta.getDetallesVenta();
            List<String> illegalOrphanMessages = null;
            for (DetalleVenta detallesVentaOldDetalleVenta : detallesVentaOld) {
                if (!detallesVentaNew.contains(detallesVentaOldDetalleVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleVenta " + detallesVentaOldDetalleVenta + " since its venta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DetalleVenta> attachedDetallesVentaNew = new ArrayList<DetalleVenta>();
            for (DetalleVenta detallesVentaNewDetalleVentaToAttach : detallesVentaNew) {
                detallesVentaNewDetalleVentaToAttach = em.getReference(detallesVentaNewDetalleVentaToAttach.getClass(), detallesVentaNewDetalleVentaToAttach.getId());
                attachedDetallesVentaNew.add(detallesVentaNewDetalleVentaToAttach);
            }
            detallesVentaNew = attachedDetallesVentaNew;
            venta.setDetallesVenta(detallesVentaNew);
            venta = em.merge(venta);
            for (DetalleVenta detallesVentaNewDetalleVenta : detallesVentaNew) {
                if (!detallesVentaOld.contains(detallesVentaNewDetalleVenta)) {
                    Venta oldVentaOfDetallesVentaNewDetalleVenta = detallesVentaNewDetalleVenta.getVenta();
                    detallesVentaNewDetalleVenta.setVenta(venta);
                    detallesVentaNewDetalleVenta = em.merge(detallesVentaNewDetalleVenta);
                    if (oldVentaOfDetallesVentaNewDetalleVenta != null && !oldVentaOfDetallesVentaNewDetalleVenta.equals(venta)) {
                        oldVentaOfDetallesVentaNewDetalleVenta.getDetallesVenta().remove(detallesVentaNewDetalleVenta);
                        oldVentaOfDetallesVentaNewDetalleVenta = em.merge(oldVentaOfDetallesVentaNewDetalleVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = venta.getId();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetalleVenta> detallesVentaOrphanCheck = venta.getDetallesVenta();
            for (DetalleVenta detallesVentaOrphanCheckDetalleVenta : detallesVentaOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venta (" + venta + ") cannot be destroyed since the DetalleVenta " + detallesVentaOrphanCheckDetalleVenta + " in its detallesVenta field has a non-nullable venta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Venta> obtenerTodasLasVentas() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Venta> query = em.createQuery("SELECT v FROM Venta v", Venta.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void ActualizarVenta(Venta venta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(venta); // Actualiza la venta en la BD
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Venta> filtrarPorFechas(Date fechaInicio, Date fechaFin) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin", Venta.class)
                    .setParameter("inicio", fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .setParameter("fin", fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Venta> obtenerVentasPorRango(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Venta> query = em.createQuery(
                    "SELECT v FROM Venta v WHERE v.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY v.fecha DESC",
                    Venta.class
            );
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
