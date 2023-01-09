package IPRWC.Webshop.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "delivery")
public class Order {
    @Id
    private Integer id;
    private Integer productId;
    private Integer productAmount;
    private Integer userId;
}
