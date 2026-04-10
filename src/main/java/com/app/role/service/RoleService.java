package com.app.role.service;

import com.app.guild.service.GuildService;
import com.app.role.entity.RoleEntity;
import com.app.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final GuildService guildService;

    public RoleService(RoleRepository roleRepository, GuildService guildService) {
        this.roleRepository = roleRepository;
        this.guildService = guildService;
    }

    public List<RoleEntity> getAllRoles(Long guildId) {
        var guildInfo = guildService.getGuild(guildId);

        guildInfo.getRoles();

    }
}

