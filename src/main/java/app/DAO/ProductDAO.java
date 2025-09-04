package app.DAO;

import app.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ProductDAO implements IDAO<Product, Integer> {
    private final EntityManagerFactory emf;

    public ProductDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Product create(Product product) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        }
        return product;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public Product find(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            em.getTransaction().commit();
            System.out.println("product found with id: " + id + " " + product.toString());
            return product;
        }
    }

    @Override
    public List<Product> getAll() {
        return List.of();
    }
}
