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
@Table(name = "person")
public class User {
    @Id
    private Integer id;
    private String email;
    private String password;
    private boolean isAdmin = false;
    private String address;

}
