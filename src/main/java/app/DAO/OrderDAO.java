package app.DAO;

import app.DTO.OrderTotalDTO;
import app.entities.Order;
import app.entities.OrderLine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OrderDAO implements IDAO<Order, Integer> {
    private final EntityManagerFactory emf;

    public OrderDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public Order create(Order order) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
        }
        return order;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public boolean delete(Order order) {
        return false;
    }

    @Override
    public Order find(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Order order = em.find(Order.class, id);
            em.getTransaction().commit();
            return order;
        }
    }

    @Override
    public List<Order> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
            List<Order> orders = query.getResultList();
            em.getTransaction().commit();
            return orders;
        }
    }
    public List<OrderTotalDTO>  getOrderTotalDTO() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<OrderTotalDTO> query = em.createQuery(
                    "SELECT new app.DTO.OrderTotalDTO(o.id, SUM(ol.quantity * p.price)) " +
                            "FROM Order o " +
                            "JOIN o.orderLines ol " +
                            "JOIN ol.product p " +
                            "GROUP BY o.id",
                    OrderTotalDTO.class
            );
            return query.getResultList();
        }
    }
}
