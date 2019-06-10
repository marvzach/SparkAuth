--
-- PostgreSQL database dump
--

-- Dumped from database version 10.8 (Ubuntu 10.8-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.8 (Ubuntu 10.8-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: users; Type: TABLE; Schema: public; Owner: moringa
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying,
    password character varying,
    username character varying
);


ALTER TABLE public.users OWNER TO moringa;

--
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: moringa
--

CREATE SEQUENCE public.animals_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.animals_id_seq OWNER TO moringa;

--
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: moringa
--

ALTER SEQUENCE public.animals_id_seq OWNED BY public.users.id;


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.animals_id_seq'::regclass);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: moringa
--

COPY public.users (id, email, password, username) FROM stdin;
\.


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: moringa
--

SELECT pg_catalog.setval('public.animals_id_seq', 5, true);


--
-- Name: users animals_pkey; Type: CONSTRAINT; Schema: public; Owner: moringa
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

