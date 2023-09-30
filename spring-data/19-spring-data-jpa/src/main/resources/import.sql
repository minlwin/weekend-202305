
		insert into address(street,township,city) values('No.D-36,Between 27×28th Sts,Between 84×85th Sts','Chan Aye Thar Zan','Mandalay');
		insert into address(street,township,city) values('No.3, 56th Street Quarter (7)','Hlaing','Yangon');
		insert into address(street,township,city) values('19st,Between 80x81 sts','Aungmyaytharzan','Mandalay');
		insert into address(street,township,city) values('Between 35x36 sts,Between 84x85 sts','Maharaungmyay','Mandalay');
		insert into address(street,township,city) values('41st,Between 75x76 sts ','Maharaungmyay','Mandalay');


		insert into customer (name,address_id,phone,email) values ('mahar swe',1,'02-57494','maharswe@gmail.com');
		insert into customer (name,address_id,phone,email) values ('Andrew',2,'02-522494','andrewnyar@gmail.com');
		insert into customer (name,address_id,phone,email) values ('Sophia',2,'02-7694','sophia@gmail.com');
		insert into customer (name,address_id,phone,email) values ('William',2,'01-234343','William@gmail.com');
		insert into customer (name,address_id,phone,email) values ('John',2,'01-993423333','John@gmail.com');
		insert into customer (name,address_id,phone,email) values ('Arnel',2,'02-3453453','Arnel@gmail.com');
		insert into customer (name,address_id,phone,email) values ('Upul Sampah',3,'02-645323','UpuSampahanlin@gmail.com');
	    insert into customer (name,address_id,phone,email) values ('banyar',4,'02-828342','banyar@gmail.com');
		insert into customer (name,address_id,phone,email) values ('san lin',5,'02-22333345','sanlinanlin@gmail.com');

		insert into category(name) values ('Diary');
		insert into category(name) values ('Snacks');
		insert into category(name) values ('Drinks');
		insert into category(name) values ('Fruits');
		insert into category(name) values ('Vegetables');
		insert into category(name) values ('Cleaning');
		insert into category(name) values('Others');

	
		insert into product (name,dt_price,ws_price,category_id) values('Milk',2500,2000,1);
		insert into product (name,dt_price,ws_price,category_id) values('Eggs',2200,2000,1);
		insert into product (name,dt_price,ws_price,category_id) values('Butter',4000,3800,1);
		insert into product (name,dt_price,ws_price,category_id) values('Cheese',3000,2500,1);
		insert into product (name,dt_price,ws_price,category_id) values('Yogurt',1200,1000,1);
		insert into product (name,dt_price,ws_price,category_id) values('Candy',700,500,2);
		insert into product (name,dt_price,ws_price,category_id) values('Dried Fruit',1500,1000,2);
		insert into product (name,dt_price,ws_price,category_id) values('Popcorn',800,500,2);
		insert into product (name,dt_price,ws_price,category_id) values('Sunflower Seeds',1200,1100,2);
		insert into product (name,dt_price,ws_price,category_id) values('Tiger Beer',2500,2200,3);
		insert into product (name,dt_price,ws_price,category_id) values('Su Coffee',3500,3300,3);
		insert into product (name,dt_price,ws_price,category_id) values('Lemon Juice',3200,3000,3);
		insert into product (name,dt_price,ws_price,category_id) values('Red Bull',1000,700,3);
		insert into product (name,dt_price,ws_price,category_id) values('Red level',42000,40000,3);
		insert into product (name,dt_price,ws_price,category_id) values('Super Tea',300,250,3);
		insert into product (name,dt_price,ws_price,category_id) values('Aye thar yar Wine',4500,4000,3);
		insert into product (name,dt_price,ws_price,category_id) values('Apple',1500,1300,4);
		insert into product (name,dt_price,ws_price,category_id) values('Avocados',1500,1200,4);
		insert into product (name,dt_price,ws_price,category_id) values('Cherries',2000,1700,4);
		insert into product (name,dt_price,ws_price,category_id) values('Grapefruit',1500,1300,4);
		insert into product (name,dt_price,ws_price,category_id) values('Watermelon',2000,1800,4);
		insert into product (name,dt_price,ws_price,category_id) values('Broccoli',2000,1750,5);
		insert into product (name,dt_price,ws_price,category_id) values('Chilies',500,400,5);
		insert into product (name,dt_price,ws_price,category_id) values('Onions',3200,3000,5);
		insert into product (name,dt_price,ws_price,category_id) values('Potatoes',1000,1000,5);
		insert into product (name,dt_price,ws_price,category_id) values('Tomatos',1200,1100,5);
		insert into product (name,dt_price,ws_price,category_id) values('Air fresher',4000,3800,6);
		insert into product (name,dt_price,ws_price,category_id) values('Garbage bag',700,500,6);
		insert into product (name,dt_price,ws_price,category_id) values('Glass cleaner',1300,1200,6);
		insert into product (name,dt_price,ws_price,category_id) values('Dish soap',500,400,6);
		insert into product (name,dt_price,ws_price,category_id) values('Laundry soap',2200,2000,6);
	--	insert into product (name,dt_price,ws_price,category_id) values('Body Wash soap',2200,2000,null);

	
		insert into sales(customer_id,sale_date) values (1,'20220112');
		insert into sales(customer_id,sale_date) values (2,'20220405');
		insert into sales(customer_id,sale_date) values (3,'20220405');
		insert into sales(customer_id,sale_date) values (4,'20230222');
		insert into sales(customer_id,sale_date) values (5,'20230303');
		insert into sales(customer_id,sale_date) values (6,'20230411');
		insert into sales(customer_id,sale_date) values (6,'20230510');
		insert into sales(customer_id,sale_date) values (5,'20230512');
		insert into sales(customer_id,sale_date) values (3,'20230512');
		insert into sales(customer_id,sale_date) values (4,'20230512');
		insert into sales(customer_id,sale_date) values (7,'20230604');	
		insert into sales(customer_id,sale_date) values (8,'20230620');			

		insert into sale_details (product_id,sales_id,qty,total) values (1,1,3,7500);
		insert into sale_details (product_id,sales_id,qty,total) values (3,1,2,8000);
		insert into sale_details (product_id,sales_id,qty,total) values (5,1,5,6000);

		insert into sale_details (product_id,sales_id,qty,total) values (11,2,1,3500);
		insert into sale_details (product_id,sales_id,qty,total) values (12,2,2,6400);
		insert into sale_details (product_id,sales_id,qty,total) values (13,2,2,2000);

		insert into sale_details (product_id,sales_id,qty,total) values (1,3,3,7500);
		insert into sale_details (product_id,sales_id,qty,total) values (3,3,2,8000);
		insert into sale_details (product_id,sales_id,qty,total) values (5,3,5,6000);

		insert into sale_details (product_id,sales_id,qty,total) values (1,4,3,7500);
		insert into sale_details (product_id,sales_id,qty,total) values (3,4,2,8000);
		insert into sale_details (product_id,sales_id,qty,total) values (2,4,5,11000);

		insert into sale_details (product_id,sales_id,qty,total) values (22,5,3,6000);
		insert into sale_details (product_id,sales_id,qty,total) values (1,5,2,5000);
		insert into sale_details (product_id,sales_id,qty,total) values (30,5,3,6600);

		insert into sale_details (product_id,sales_id,qty,total) values (1,6,3,7500);
		insert into sale_details (product_id,sales_id,qty,total) values (3,6,2,8000);
		insert into sale_details (product_id,sales_id,qty,total) values (5,6,5,6000);

		insert into sale_details (product_id,sales_id,qty,total) values (11,7,1,3500);
		insert into sale_details (product_id,sales_id,qty,total) values (12,7,2,6400);


		insert into sale_details (product_id,sales_id,qty,total) values (1,8,3,7500);
		insert into sale_details (product_id,sales_id,qty,total) values (3,8,2,8000);
		insert into sale_details (product_id,sales_id,qty,total) values (5,8,5,6000);

		insert into sale_details (product_id,sales_id,qty,total) values (1,9,3,7500);

		insert into sale_details (product_id,sales_id,qty,total) values (22,10,3,6000);
		insert into sale_details (product_id,sales_id,qty,total) values (1,10,2,5000);
		insert into sale_details (product_id,sales_id,qty,total) values (30,10,3,6600);

		insert into sale_details (product_id,sales_id,qty,total) values (22,11,3,6000);
		insert into sale_details (product_id,sales_id,qty,total) values (1,11,2,5000);
		insert into sale_details (product_id,sales_id,qty,total) values (19,11,3,6000);

		insert into sale_details (product_id,sales_id,qty,total) values (22,12,3,6000);
		insert into sale_details (product_id,sales_id,qty,total) values (1,12,2,5000);
		insert into sale_details (product_id,sales_id,qty,total) values (30,12,3,6600);

-- sum,avg,min,max
-- select sum(total) from sale_details;
-- select sales_id,sum(total) as totals from sale_details group by sales_id;
-- -- avg
-- select avg(dt_price) from product;
-- select min(dt_price) from product;
-- select max(dt_price) from product;

-- query test
-- join
-- select * from product p left join category c on p.category_id = c.id
-- 	where c.name = 'Snacks';

-- -- find product and qty in select customer
-- select c.name,s.sale_date,p.name as product,sd.qty,sd.total from sale_details sd
-- 	join sales s on sd.sales_id = s.id
-- 	join product p on sd.product_id = p.id
-- 	join customer c on s.customer_id = c.id 
-- 	where c.name = 'Arnel';

-- -- select sum total by customer  
-- select c.name,sum(total) as total from sale_details sd 
-- 	join sales s on sd.sales_id = s.id 
-- 	join product p on sd.product_id = p.id 
-- 	join customer c on s.customer_id = c.id  
-- 	where c.name = 'Arnel';

-- -- select sum total by sale_date using group by and order by
-- select s.sale_date saleDate,sum(sd.total) as total from sale_details sd
-- 	join sales s on sd.sales_id = s.id 
-- 	where s.sale_date between '2023-01-07' and '2023-05-30' 
-- 	group by saleDate
-- 	order by total desc;


-- -- select product by township and date
-- select a.township,p.name as product,p.dt_price as price,sum(sd.qty) as totalQty from sale_details sd
-- 	join sales s on sd.sales_id = s.id
-- 	join customer c on s.customer_id = c.id
-- 	join address a on c.address_id = a.id
-- 	join product p on sd.product_id = p.id
-- 	where a.township = 'Hlaing' and s.sale_date = '20230512' 
-- 	group by a.township,p.name,p.dt_price
--     having price < 2500;



















