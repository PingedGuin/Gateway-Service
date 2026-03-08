package dev.dre.chatappserver.database.entitys;

import dev.dre.chatappserver.dtos.register.RegisterRequest;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_info")
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String gmail;

    @Column(nullable = false)
    private String password;

    public UserInfoEntity(RegisterRequest request) {
        this.username = request.getUsername();
        this.gmail = request.getEmail();
        this.password = request.getPassword();
    }
}