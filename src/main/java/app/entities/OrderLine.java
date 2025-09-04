package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int quantity;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    public OrderLine(int quantity, Product product) {
        this.quantity = quantity;
    }
}
