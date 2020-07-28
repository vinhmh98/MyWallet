package vn.itsol.MyWallet.entity.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "user_id")
    private long user_id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_Number")
    private long phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "path_ava")
    private String path_ava;

}
