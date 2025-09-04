package app;

import app.DAO.CustomerDAO;
import app.DAO.OrderDAO;
import app.DAO.OrderLineDAO;
import app.DAO.ProductDAO;
import app.config.HibernateConfig;
import app.entities.Customer;
import app.entities.Order;
import app.entities.OrderLine;
import app.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        try (EntityManager em = emf.createEntityManager()) {
            CustomerDAO customerDAO = new CustomerDAO(emf);
            ProductDAO productDAO = new ProductDAO(emf);
            OrderDAO orderDAO = new OrderDAO(emf);
            OrderLineDAO orderLineDAO = new OrderLineDAO(emf);

            //Create a Customer
            Customer customer = Customer.builder()
                            .name("Valdemar")
                            .email("valdmear@gmail.com")
                            .build();
            Customer customer2 = Customer.builder()
                            .name("Oliver")
                            .email("oliver@gmail.com")
                            .build();
            customerDAO.create(customer);
            customerDAO.create(customer2);

            //Find a Customer
            customerDAO.find(customer.getId());

            //Get all Customers
            customerDAO.getAll().forEach(System.out::println);

            //Create a Product
            Product product = Product.builder()
                            .name("Fodbold")
                            .description("Bold til fodbold")
                            .price(200.0)
                            .build();
            Product product2 = Product.builder()
                    .name("Sten")
                    .description("Sten til at kaste")
                    .price(200.0)
                    .build();

            productDAO.create(product);
            productDAO.create(product2);

            //Find a Product
            productDAO.find(product.getId());

            //Create an Order and add it to a Customer
            Order order =  Order.builder()
                    .created(LocalDate.now())
                    .build();
            orderDAO.create(order);
            customer.addOrder(order);
            customerDAO.update(customer);

            Order order2 =  Order.builder()
                    .created(LocalDate.now())
                    .build();
            orderDAO.create(order2);
            customer.addOrder(order2);
            customerDAO.update(customer);

            //Create an OrderLine for a specific Product, and add it to an Order
            OrderLine orderLine = OrderLine.builder()
                    .quantity(1)
                    .product(product)
                    .build();
            order.addOrderLine(orderLine);
            customer.addOrder(order);
            customerDAO.update(customer);

            OrderLine orderLine2 = OrderLine.builder()
                    .quantity(1)
                    .product(product2)
                    .build();
            order.addOrderLine(orderLine2);
            customer.addOrder(order);
            customerDAO.update(customer);

            //Find all Orders, for a specific Customer
           Customer customerOrders = customerDAO.find(customer.getId());
           Set<Order> orderSet =  customerOrders.getOrders();
           orderSet.forEach(System.out::println);

           //Find the total price of an Order
            orderDAO.getOrderTotalDTO().forEach(System.out::println);

        }
    }
}