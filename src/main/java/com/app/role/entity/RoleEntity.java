package com.app.role.entity;

import com.app.guild.data.Entity.GuildEntity;
import com.app.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long roleId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long permission;

    @ManyToMany(mappedBy = "roles")
    private List<MemberEntity> members;

    @Column(nullable = false)
    private int position;

    @ManyToOne
    @JoinColumn(name = "guild_id", nullable = false)
    private GuildEntity guild;
}