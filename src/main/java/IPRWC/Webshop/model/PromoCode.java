package IPRWC.Webshop.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Promocode")
public class PromoCode {
    @Id
    private Integer id;
    private Double discount;
    private String code;
}
