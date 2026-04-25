ALTER TABLE public.user_info
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE public.user_info
    ADD CONSTRAINT user_info_gmail_unique UNIQUE (gmail);

ALTER TABLE public.user_info
    ADD CONSTRAINT user_info_username_unique UNIQUE (username);
ALTER TABLE public.user_info
    ADD COLUMN status VARCHAR(20) DEFAULT 'OFFLINE';
