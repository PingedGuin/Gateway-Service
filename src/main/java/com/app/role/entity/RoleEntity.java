package com.app.role.entity;

import com.app.guild.member.entity.MemberEntity;
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
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long permissions;

    @ManyToMany(mappedBy = "roles")
    private List<MemberEntity> memberEntities;
}