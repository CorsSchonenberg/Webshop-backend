package IPRWC.Webshop.model;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Order")
public class Order {
    @Id
    private Integer id;
    private Integer productId;
    private Integer productAmount;
    private Integer userId;
}
