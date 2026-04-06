package com.app.channel.Entity;

import com.app.member.entity.MemberOverride;
import com.app.role.entity.RoleOverride;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "channels")
@Getter
@Setter
public class ChannelEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoleOverride> roleOverrides;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberOverride> memberOverrides;

    @Column(nullable = false)
    private String guildId;
}