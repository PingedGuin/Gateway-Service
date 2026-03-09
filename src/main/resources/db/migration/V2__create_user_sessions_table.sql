CREATE TABLE IF NOT EXISTS public.user_sessions
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    user_id
    VARCHAR
(
    255
) NOT NULL,
    session_id VARCHAR
(
    255
) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    expires_at TIMESTAMP NOT NULL
    );