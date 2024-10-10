CREATE TABLE IF NOT EXISTS public.authors
(
    author_id text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default",
    contact_name text COLLATE pg_catalog."default",
    contact_email text COLLATE pg_catalog."default",
    contact_phone text COLLATE pg_catalog."default",
    CONSTRAINT authors_pkey PRIMARY KEY (author_id)
)

TABLESPACE pg_default;

ALTER TABLE public.authors
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.books
(
    book_id text COLLATE pg_catalog."default" NOT NULL,
    author_id text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    product_name text COLLATE pg_catalog."default" NOT NULL,
    book_type text COLLATE pg_catalog."default" NOT NULL,
    comment text COLLATE pg_catalog."default",
    CONSTRAINT books_pkey PRIMARY KEY (book_id),
    CONSTRAINT books_author_id_fkey FOREIGN KEY (author_id)
        REFERENCES public.authors (author_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.books
    OWNER to postgres;