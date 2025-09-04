package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;

    // mappedBy = "customer" because you map it by what the field in the owning side is called (in this case Order is the owner and has a field name called "customer")
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Order> orders = new HashSet<>();

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Unidirectional
    public void addOrder(Order order) {
        orders.add(order);
        if (order != null){
            order.setCustomer(this);
        }
    }
}
