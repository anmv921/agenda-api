-- Table: public.contacto

-- DROP TABLE IF EXISTS public.contacto;

CREATE TABLE IF NOT EXISTS public.contacto
(
    id integer NOT NULL DEFAULT nextval('contacto_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    favorito boolean,
    nome character varying(255) COLLATE pg_catalog."default",
    foto oid,
    CONSTRAINT contacto_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.contacto
    OWNER to postgres;
