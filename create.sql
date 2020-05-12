create schema if not exists BOOKSTORE; 
 
create table if not exists Category(
	id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(50)
);
    
create table if not exists Subcategory(
	id int AUTO_INCREMENT PRIMARY KEY,
    category_id int default null, 
    title varchar(255) , 
    foreign key(category_id) references Category(id) on update cascade on delete cascade
);

create table if not exists Book(  
	id int AUTO_INCREMENT PRIMARY KEY, 
    subcategory_id integer default 1, 
	author_name varchar(255),
	publish_year int,
    title varchar(255),
    isbn varchar(20),
    image_url varchar(2048),
    publisher_name varchar(255),
    added_date DateTime DEFAULT CURRENT_TIMESTAMP,
    subjects varchar(600),
    count int,
    price float,
    foreign key(subcategory_id) references Subcategory(id) on update cascade on delete set null
);
    
    
create table if not exists Campaign( 
	id int AUTO_INCREMENT PRIMARY KEY,
	book_id int not null,
    discount_percentage float,
    note varchar(600),
    image_url varchar(2048),
    foreign key (book_id) references Book(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DELIMITER $$ 
       -- DEFAULT ROW FOR Subcategory --
		insert into subcategory(title) values ('Campaign');

	create trigger deleteSubcategoryTrigger after delete on subcategory
		for each row
	   begin
		   update book set subcategory_id=1 where subcategory_id is null ;
	   end$$ 
       
	create trigger deleteCategoryTrigger after delete on category
		for each row
	   begin
		   update Book set subcategory_id=1 where subcategory_id is null ;
	   end$$
       
DELIMITER ;