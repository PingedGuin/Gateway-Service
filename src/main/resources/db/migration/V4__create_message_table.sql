CREATE TABLE public.message
(
    id         BIGSERIAL PRIMARY KEY,
    message_id VARCHAR(255) NOT NULL UNIQUE,
    content    TEXT,
    guild_id   VARCHAR(255),
    channel_id VARCHAR(255),
    user_id    BIGINT       NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);