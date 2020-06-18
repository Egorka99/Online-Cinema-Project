alter sequence author_author_id_seq restart;
alter sequence book_book_id_seq restart;
alter sequence bookmark_bookmark_id_seq restart;
alter sequence history_history_id_seq restart;
alter sequence user_user_id_seq restart;

DELETE FROM author;
DELETE FROM book; 
DELETE FROM "user"; 
DELETE FROM history;
DELETE FROM bookmark;
 
INSERT INTO public.author (
author_id, firstname, lastname, dob) VALUES (
'1'::integer, 'Steven '::character varying(255), 'King'::character varying(255), '2012-01-01'::timestamp without time zone)
 returning author_id; 
 
INSERT INTO public."user" (
nickname, password, isblocked, isadmin) VALUES (
'Sashka'::character varying(255), '12345'::character varying(255), false::boolean, false::boolean)
 returning user_id; 

INSERT INTO public.book (
 bookname, releaseyear, isbn, publisher, pagecount, author_id) VALUES (
'Java 8'::character varying(255), '2014'::integer, '0000000-123213'::character varying(255), 'Astra'::character varying(255), '569'::integer, '1'::integer)
 returning book_id;

INSERT INTO public.bookmark (
user_id, book_id, pagenumber) VALUES (
'1'::integer, '1'::integer, '333'::integer)
 returning bookmark_id; 

INSERT INTO public."user" (
nickname, password, isblocked, isadmin) VALUES (
'Chugun'::character varying(255), '54321'::character varying(255), false::boolean, false::boolean)
 returning user_id; 

INSERT INTO public.history (
user_id, actiontext) VALUES (
'1'::integer, 'add book to bookmark'::character varying(255))
 returning history_id;