package IPRWC.Webshop.model;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Product")
public class Product {
    @Id
    private Integer id;
    private String url;
    private Double price;
    private String description;
}
