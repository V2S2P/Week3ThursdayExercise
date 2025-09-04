package app.DAO;

import app.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomerDAO implements IDAO<Customer,Integer> {
    private final EntityManagerFactory emf;

    public CustomerDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Customer create(Customer customer) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        }
        return customer;
    }

    @Override
    public boolean update(Customer customer) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(customer);
            em.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean delete(Customer customer) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(customer);
            em.getTransaction().commit();
        }
        return true;
    }

    @Override
    public Customer find(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            em.getTransaction().commit();
            System.out.println("Customer found with id " + id + ": " + customer.getName() + " " + customer.getEmail());
            return customer;
        }
    }

    @Override
    public List<Customer> getAll() {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            List<Customer> customers = query.getResultList();
            em.getTransaction().commit();

            return customers;
        }
    }
}
