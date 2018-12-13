--
-- PostgreSQL database dump
--

-- Dumped from database version 10.6 (Ubuntu 10.6-1.pgdg18.04+1)
-- Dumped by pg_dump version 10.6 (Ubuntu 10.6-1.pgdg18.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--
-- Name: actions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actions (
    id uuid NOT NULL,
    action character varying(32) NOT NULL,
    occurred_on timestamp without time zone NOT NULL,
    user_id uuid NOT NULL
);


ALTER TABLE public.actions OWNER TO postgres;

--
-- Name: pictures; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pictures (
    id character varying(64) NOT NULL,
    picture bytea NOT NULL
);


ALTER TABLE public.pictures OWNER TO postgres;

--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id uuid NOT NULL,
    created_on timestamp without time zone NOT NULL,
    description text,
    product_name character varying(32) NOT NULL,
    quantity integer NOT NULL,
    serial_number character varying(4) NOT NULL,
    project_id uuid NOT NULL,
    status_id uuid NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_pictures; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products_pictures (
    product_id uuid NOT NULL,
    picture_id character varying(64) NOT NULL
);


ALTER TABLE public.products_pictures OWNER TO postgres;

--
-- Name: projects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projects (
    id uuid NOT NULL,
    company_name character varying(32) NOT NULL,
    created_on timestamp without time zone NOT NULL,
    project_manager character varying(32) NOT NULL,
    project_name character varying(32) NOT NULL,
    status_id uuid NOT NULL
);


ALTER TABLE public.projects OWNER TO postgres;

--
-- Name: statuses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.statuses (
    id uuid NOT NULL,
    status character varying(32) NOT NULL
);


ALTER TABLE public.statuses OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    email character varying(32) NOT NULL,
    first_name character varying(32) NOT NULL,
    last_name character varying(32),
    password character varying(32) NOT NULL,
    "position" character varying(32) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: actions actions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actions
    ADD CONSTRAINT actions_pkey PRIMARY KEY (id);


--
-- Name: pictures pictures_picture_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pictures
    ADD CONSTRAINT pictures_picture_key UNIQUE (picture);


--
-- Name: pictures pictures_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pictures
    ADD CONSTRAINT pictures_pkey PRIMARY KEY (id);


--
-- Name: products_pictures products_pictures_picture_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_pictures
    ADD CONSTRAINT products_pictures_picture_id_key UNIQUE (picture_id);


--
-- Name: products_pictures products_pictures_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_pictures
    ADD CONSTRAINT products_pictures_pkey PRIMARY KEY (product_id, picture_id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: projects projects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT projects_pkey PRIMARY KEY (id);


--
-- Name: statuses statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.statuses
    ADD CONSTRAINT statuses_pkey PRIMARY KEY (id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: products fk2aua82opnqei9ygk6q9mic5y4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk2aua82opnqei9ygk6q9mic5y4 FOREIGN KEY (status_id) REFERENCES public.statuses(id);


--
-- Name: products fkdjd5tux3sl0urf5yyat0fnqo7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkdjd5tux3sl0urf5yyat0fnqo7 FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: actions fkfsg3bsx5riuhmr7ilvqyuylpe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actions
    ADD CONSTRAINT fkfsg3bsx5riuhmr7ilvqyuylpe FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: products_pictures fkh3amnci4cl7xcl1al140xw79e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_pictures
    ADD CONSTRAINT fkh3amnci4cl7xcl1al140xw79e FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: projects fkhn7q52kcupyn8gnkqpg1wdgh1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT fkhn7q52kcupyn8gnkqpg1wdgh1 FOREIGN KEY (status_id) REFERENCES public.statuses(id);


--
-- Name: products_pictures fkloucf8ggy74nmdej2jmvttvi4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_pictures
    ADD CONSTRAINT fkloucf8ggy74nmdej2jmvttvi4 FOREIGN KEY (picture_id) REFERENCES public.pictures(id);


--
-- PostgreSQL database dump complete
--

