

-- insert to customers
-- already exists
insert into customers(userid, firstname,middleinitial,lastname,phonenumber,address,city,state,zipcode,accountnumber) values
(110000,'John','K','Forsyth','3563520035','7714 Chapel Ave.','Whitestone','NY',11357,1353600),
(110001,'Jason','A','Dyer','(651) 472-8861','4 South Shore Dr.','Vincentown','NJ',8088,1354000),
(110002,'Anna','A','King','(704) 760-7614','489 Riverview Lane','State College','PA',16801,1354400),
(110003,'Wanda','A','Reid','(663) 844-6410','12 Princeton Road','Georgetown','SC',29440,1354800),
(110004,'Edward','A','Parsons','(591) 713-3770','7498 Main Court','Petersburg','VA',23803,1355200),
(110005,'Stewart','A','Turner','(589) 480-1297','984 Valley St.','Yuma','AZ',85365,1355600),
(110006,'Nicholas','A','Miller','(837) 669-4495','396 Jones St.','Hartsville','SC',29550,1356000),
(110007,'Gabrielle','G','Stewart','(458) 483-2620','7655 Johnson Dr.','Dorchester Center','MA',2124,1356400),
(110008,'Sebastian','G','Ferguson','(794) 384-8135','848 Locust Lane' ,'Fort Lauderdale','FL',33308,1356800),
(110009,'Angela','G','Chapman','(303) 625-1941','193 Glendale St.','Sevierville','TN',37876,1357200),
(110010,'Rose','G','Martin','(775) 594-7490','2 Water Lane','Orland Park','IL',60462,1357600),
(110011,'Anthony','G','Stewart','(711) 358-9550','57 3rd Street','Lockport','NY',14094,1358000),
(110012,'Connor','G','Mackenzie','(944) 982-0977','68 Summer Drive','Coventry','RI',2816,1358400),
(110013,'Bella','G','Piper','(695) 374-0867','158 Bay St.','Rockledge','FL',32955,1358800),
(110014,'Alan','G','Sanderson','(284) 254-1020','104 York Dr.','Saratoga Springs','NY',12866,1359200),
(110015,'Carl','G','Hughes','(564) 332-6351','9110 James Avenue','Lake Villa','IL',60046,1359600),
(110016,'Lillian','F','Greene','(304) 783-9909','9657 Randall Mill St.', 'Valdosta','GA',31601,1360000),
(110017,'Jason','m','Murray','(604) 693-6402','7286 High Point St.' ,'Romeoville','IL',60446,1360400),
(110018,'Gavin','N','Lewis','(644) 567-5529','87 E. Wall Drive','Arvada','CO',80003,1360800),
(110019,'Maria','A','Black','(727) 295-7367','9199 Golden Star Ave.', 'Southampton','PA',18966,1361200),
(110020,'Warren','R','Cornish','(301) 232-7709','686 Bowman Avenue','Allentown','PA',18102,1361600),
(110021,'Penelope','D','Robertson','(350) 436-7025','406 Jones Street','Lorton','VA',22079,1362000),
(110022,'Dorothy','G','Russell','(995) 350-8929','19 E. Kingston Ave.', 'Depew','NY',14043,1362400),
(110023,'Keith','P','Sutherland','(507) 231-9154','91 Glen Creek Avenue' ,'Mcallen','TX',78501,1362800),
(110024,'Gavin','T','Ball','(356) 939-0067','8454 Big Rock Cove Dr.' ,'Ypsilanti','MI',48197,1363200),
(110025,'Jake','J','Slater','(639) 556-0339','8564 Arlington Ave.' ,'Tacoma','WA',98444,1363600),
(110026,'Joanne','Q','Tucker','(434) 867-8265','98 Magnolia Street' ,'Largo','FL',33771,1364000),
(110027,'Ruth','S','MacDonald','(400) 796-7324','572 Pacific Avenue' ,'Belleville','NJ',7109,1364400),
(110028,'Molly','U','Chapman','(427) 540-4961','9665 Trout Road' ,'Newport News','VA',23601,1364800),
(110029,'Jonathan','K','Harris','(805) 458-6990','80 North Lower River Lane','Villa Park','IL',60181,1365200),
(110030,'Rachel','W','Wallace','(909) 565-7445','4 North Warren St. ','Suitland','MD',20746,1365600)
;


-- assign them accounts and running balances
-- already exists

insert into accounts (accountId, runningBalance, accountNumber) values
(3656,2500,1353600),
(3756,500,1354000),
(3856,10000,1354400),
(2121,3653,1354800),
(3121,1245,1355200),
(1250,1201,1355600),
(1350,1457,1356000),
(1450,147,1356400),
(1550,0.25,1356800),
(1650,256,1357200),
(1750,145,1357600),
(1850,147,1358000),
(1950,147,1358400),
(2050,34587,1358800),
(2150,4578,1359200),
(2250,9896,1359600),
(2350,47156,1360000),
(2450,963,1360400),
(3450,7896,1360800),
(4400,18966,1361200),
(4687,18102,1361600),
(5600,22079,1362000),
(5700,14043,1362400),
(5900,78501,1362800),
(1402,48197,1363200),
(7896,98444,1363600),
(78963,33771,1364000),
(1789,7109,1364400),
(7880,23601,1364800),
(1478,60181,1365200),
(36360,20746,1365600);