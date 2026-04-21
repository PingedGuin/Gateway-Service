package com.app.member.entity;

import com.app.role.entity.RoleEntity;
import com.app.user.data.entity.UserInfoEntity;
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

    private String username;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<RoleEntity> roles;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfoEntity userInfo;
}