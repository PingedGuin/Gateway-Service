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

    @EmbeddedId
    private MemberId id;

    private String username;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<RoleEntity> roles;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserInfoEntity userInfo;
}