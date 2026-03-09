CREATE TABLE IF NOT EXISTS public.user_info
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    username
    VARCHAR
(
    255
) NOT NULL,
    gmail VARCHAR
(255) NOT NULL,
    password VARCHAR
(
255
) NOT NULL
    );