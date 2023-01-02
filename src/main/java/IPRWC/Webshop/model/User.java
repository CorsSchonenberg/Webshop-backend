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
public class User {
    @Id
    private long id;
    private String email;
    private String password;

}
