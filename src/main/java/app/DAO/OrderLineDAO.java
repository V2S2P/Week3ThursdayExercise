package app.DAO;

import app.entities.OrderLine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class OrderLineDAO implements IDAO<OrderLine, Integer> {
    private final EntityManagerFactory emf;

    public OrderLineDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public OrderLine create(OrderLine orderLine) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(orderLine);
            em.getTransaction().commit();
        }
        return orderLine;
    }

    @Override
    public boolean update(OrderLine orderLine) {
        return false;
    }

    @Override
    public boolean delete(OrderLine orderLine) {
        return false;
    }

    @Override
    public OrderLine find(Integer id) {
        return null;
    }

    @Override
    public List<OrderLine> getAll() {
        return List.of();
    }
}
