package com.app.user.data.entity;

import com.app.register.dtos.register.RegisterRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_info")
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "email", unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public UserInfoEntity(RegisterRequest request) {
        this.username = request.getUsername();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }
}