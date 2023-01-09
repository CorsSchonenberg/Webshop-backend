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
@Table(name = "Promocode")
public class PromoCode {
    @Id
    private Integer id;
    private Double discount;
    private String code;
}
