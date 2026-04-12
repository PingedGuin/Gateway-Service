package com.app.guild.permission.data;

import lombok.Getter;

@Getter
public enum Permission {

    // ===== General =====
    VIEW_CHANNEL(1L << 0),
    SEND_MESSAGES(1L << 1),
    READ_MESSAGE_HISTORY(1L << 2),
    MANAGE_MESSAGES(1L << 3),

    // ===== Server =====
    MANAGE_SERVER(1L << 4),
    KICK_MEMBERS(1L << 5),
    BAN_MEMBERS(1L << 6),
    ADMINISTRATOR(1L << 7),

    // ===== Channels =====
    MANAGE_CHANNELS(1L << 8),
    CREATE_CHANNELS(1L << 9),

    // ===== Roles =====
    MANAGE_ROLES(1L << 10),
    ASSIGN_ROLES(1L << 11),

    // ===== Voice =====
    CONNECT(1L << 12),
    SPEAK(1L << 13),
    MUTE_MEMBERS(1L << 14),
    DEAFEN_MEMBERS(1L << 15),

    // ===== Advanced =====
    MENTION_EVERYONE(1L << 16),
    USE_EXTERNAL_EMOJIS(1L << 17);

    private final long bit;

    Permission(long bit) {
        this.bit = bit;
    }

    public boolean has(long permissions) {
        return (permissions & bit) == bit;
    }

    public static long add(long base, Permission permission) {
        return base | permission.bit;
    }

    public static long remove(long base, Permission permission) {
        return base & ~permission.bit;
    }
}