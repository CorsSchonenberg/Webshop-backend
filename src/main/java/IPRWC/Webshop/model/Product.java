package IPRWC.Webshop.model;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class Product {
    @Id
    private int id;
    private String url;
    private double price;
    private String description;
}
