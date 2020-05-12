
insert into category(title) values ("Education");
insert into category(title) values ("Novel");
insert into category(title) values ("Literature");
insert into category(title) values ("Science");
insert into category(title) values ("History");
 
insert into subcategory(category_id, title) values (1, "High School");
insert into subcategory(category_id, title) values (1, "Self Development");
insert into subcategory(category_id, title) values (2, "Horror");
insert into subcategory(category_id, title) values (2, "Adventure"); -- cat ıd 5
insert into subcategory(category_id, title) values (3, "English Literary");
insert into subcategory(category_id, title) values (3, "Russian Literary");
insert into subcategory(category_id, title) values (4, "Engineering");
insert into subcategory(category_id, title) values (4, "Astronomy");
insert into subcategory(category_id, title) values (4, "Biology");
insert into subcategory(category_id, title) values (5, "World History");
insert into subcategory(category_id, title) values (5, "Ottoman history");
 


insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('H.P. Lovecraft',5, 1928, 'The Call of Cthulhu', 1291239123, 'https://i.dr.com.tr/cache/136x136-0/originals/0001796506001-1.jpg','Atlas', 'Fascinating story !!', 2,23);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Dan Brown',5, 2001, 'Lost Symbol', 1291264743, 'https://i.dr.com.tr/cache/154x170-0/originals/0000000317832-1.jpg','Gold', 'Amazing story !!', 5,29.99);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Dan Brown',5, 2003, 'Angels and Deamons', 1671264743, 'https://i.dr.com.tr/cache/154x170-0/originals/0000000151552-1.jpg','Gold', 'Interesting story !!', 10,24.50);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Stephan King',4, 1998, 'It', 9871264743, 'https://i.dr.com.tr/cache/154x170-0/originals/0000000706011-1.jpg','Gold', 'Horrible story !!', 3,19);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Alein Kentigerna',4, 2015, 'Hallucinations', 1671264743, 'https://i.dr.com.tr/cache/154x170-0/originals/0000000434749-1.jpg','Panama', 'Good story !!', 7,24);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Kolektif',2, 2019, 'Geometri 0', 1291999123, 'https://i.dr.com.tr/cache/154x170-0/originals/0001779881001-1.jpg','Karekok', 'Learn quickly !!', 11,23);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Orhan Kutay',2, 2019, 'TYT Fizik Deneme', 1273294123, 'https://i.dr.com.tr/cache/154x170-0/originals/0001863577001-1.jpg','Delta', 'To Win !!', 6,26.87);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Eckhart Tolle',3, 2018, 'The Power of Now', 8888294123, 'https://i.dr.com.tr/cache/136x136-0/originals/0001818159001-1.jpg','Hodder', 'Improve yourself !!', 9,35);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Sarah Harvey',3, 2019, 'Kaizen', 7777294123, 'https://i.dr.com.tr/cache/600x600-0/originals/0001844661001-1.jpg','Bluebird', 'For japons !!', 8,10);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('George Orwell',6, 1990, '1984', 1291966773, 'https://i.dr.com.tr/cache/136x136-0/originals/0000000064038-1.jpg','Can', ' Distopik', 1,23);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('George Orwell',6, 1985, 'Animal Farm', 12732900775, 'https://i.dr.com.tr/cache/136x136-0/originals/0000000105409-1.jpg','Can', 'Utopik', 17,26.87);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Tolstoy',7, 2006, 'What Men Live By', 124545456773, 'https://i.dr.com.tr/cache/136x136-0/originals/0001870178001-1.jpg','Platanus', ' masterpiece', 3,23);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Dostoyevski',7, 2007, 'Crime and Punishment', 1289898900775, 'https://i.dr.com.tr/cache/136x136-0/originals/0001869884001-1.jpg','Platanus', 'masterpiece', 5,26.87);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Kolektif',8, 2009, 'Studies In Engineering', 124919256773, 'https://i.dr.com.tr/cache/136x136-0/originals/0001791602001-1.jpg','Night', 'information for studies', 25,238);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Kolektif',8, 2010, 'Computer Engineering', 12898810063, 'https://i.dr.com.tr/cache/136x136-0/originals/0001694291001-1.jpg','DaisyScience', 'easy to learn', 71,265.87);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Eric Simon',10, 2000, 'Biology The Core', 66666656773, 'https://i.dr.com.tr/cache/136x136-0/originals/0000000639975-1.jpg','Night', 'information about cell', 2,250);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Abidin Temizer',11, 1999, 'Glimpses Of Balkans', 12495555444, 'https://i.dr.com.tr/cache/136x136-0/originals/0001700399001-1.jpg','Night', 'balkans culturucal history', 6,23);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Dorling Kinder',11, 1987, 'Who Chanched History ', 2255337788, 'https://i.dr.com.tr/cache/136x136-0/originals/0001837504001-1.jpg','DK Publish', 'amazing scientists', 19,26);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Ilber Ortayli',12, 1950, 'Ottoman Studies', 9988776655, 'https://i.dr.com.tr/cache/136x136-0/originals/0001807565001-1.jpg','Kronik Kitap', 'ottomon story', 4,23);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Halil Inancik',12, 1955, 'The Ottoman Empire', 7744990033, 'https://i.dr.com.tr/cache/136x136-0/originals/0001741995001-1.jpg','Kronik Kitap', 'ottoman story', 3,26);
