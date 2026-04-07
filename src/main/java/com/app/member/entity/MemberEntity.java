package com.app.member.entity;

import com.app.role.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
public class MemberEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "guild_id", nullable = false)
    private String guildId;

    @Transient
    private RoleEntity everyoneRole;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roles;
}