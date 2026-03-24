package dev.dre.GetwayService.database.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_sessions")
public class UserSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String sessionId;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp expiresAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.expiresAt = new Timestamp(System.currentTimeMillis() + 3600000);
        this.sessionId = java.util.UUID.randomUUID().toString();
    }
    public String getExpiresAt() {
        return expiresAt.toString();
    }
    public UserSessionEntity(String userId,boolean active) {
        this.userId = userId;
        this.active = active;
    }

}