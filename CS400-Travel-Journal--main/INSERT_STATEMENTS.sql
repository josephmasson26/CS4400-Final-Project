INSERT INTO account (username, email, fname, lname, password)
VALUES ('acandelmo3', 'email123@gmail.com', 'Anthony', 'Candelmo', 'secretpassword'),
	   ('bluu', 'cactor@fan.org', 'Joseph', 'Masson', 'ilovemoney'),
	   ('epreez', 'josephfan123@gmail.com', 'Isaac', 'Moran', 'awesomesauce3'),
	   ('RegentDart75702', 'lorenzoballing@fortnite.com', 'Lorenzo', 'Gastaldi', 'unhackable'),
	('jmasson6', 'jmasson6@gatech.edu', 'Joseph', 'Masson', 'hahauwish'),
       ('AvoidingSloth37', 'foodforthought@me.com', 'Suraj', 'Shekar', 'ilikespheres');
       
INSERT INTO admin (username, sDate)
VALUES ('acandelmo3', '1967-01-01'),
	   ('epreez', '2023-07-03');
       
INSERT INTO user (username, memDate, privLvl)
VALUES ('bluu', '2020-06-30', 'private'),
	   ('RegentDart75702', '2021-09-23', 'public'),
	('jmasson6', '2023-07-04', 'public'),
       ('AvoidingSloth37', '1969-12-25', 'public');

	

INSERT INTO city (cityName, country)
VALUES ('Tokyo', 'Japan'),
	   ('Delhi', 'India'),
       ('Shanghai', 'China'),
       ('Sao Paulo', 'Brazil'),
       ('Mexico City', 'Mexico'),
       ('Cairo', 'Egypt'),
       ('Dhaka', 'Bangladesh'),
       ('Atlanta', 'United States'),
       ('Toronto', 'Canada'),
       ('Buenos Aires', 'Argentina');
       


INSERT INTO category(catName, aUsername) 
VALUES ('Cultural', 'epreez'), ('Specialty Museums', 'epreez'), 
	('Walking Tour', 'epreez'), 
	('Architectural', 'epreez'), ('Landmark', 'epreez'), 
	('Park', 'epreez'), ('Theaters', 'epreez'), 
	('Market', 'epreez');

INSERT INTO can_be_in(catName, siteName, city, country) 
	VALUES ('Specialty Museums', 'World of Coke', 'Atlanta', 'United States'), 
	('Landmark', 'Red Fort', 'Delhi', 'India'),
		('Park', 'Parque Ibirapuera', 'Sao Paulo', 'Brazil');


INSERT INTO trip(username, tripName, sDate, eDate) 
VALUES ('bluu', 'Trip to Japan', '2021-06-03', '2021-06-10'), 
	('bluu', 'Trip to Paris', '2023-06-11', '2023-06-13'), 
	('RegentDart75702', 'Trip to Atlanta', '2022-12-03', '2022-12-25'),
	('RegentDart75702', 'Trip to Sao Paulo', '2022-10-15', '2023-10-15');

INSERT INTO journal_entry(username, entryDate, locName, country, city, privLvl, note, rating) 
	VALUES ('jmasson6', '2023-07-04', 'World of Coke', 'United States', 'Atlanta', 'public', 'I LOVE COKE!!!!!', '3'), 
	('jmasson6', '2023-06-05', 'Shanghai', 'China', 'Shanghai', 'private', 'BING CHILLING IN SHANGHAI TODAY!', 5),
	('AvoidingSloth37', '2020-01-01', 'Tokyo', 'Japan', 'Tokyo', 'public', '', '1'),
    	('AvoidingSloth37', '2022-03-15', 'Palacio de Bellas Artes', 'Mexico', 'Mexico City', 'private', '', '4');

INSERT INTO bans(uUsername, aUsername) 
	VALUES ('bluu', 'acandelmo3');

INSERT INTO can_flag(username, flaggedUser, flagDate, entryDate, locName, city, country, reason) 
	VALUES ('RegentDart75702', 'jmasson6', '2023-07-04', '2023-06-05', 'Shanghai', 'Shanghai', 'China', 'Harassment');


INSERT INTO trip(username, tripName, sDate, eDate) 
	VALUES ('jmasson6', 'Trip to Sao Paulo', '2022-04-01', '2022-04-08');
INSERT INTO journal_entry(username, entryDate, locName, country, city, privLvl, note, rating) 
	VALUES ('jmasson6', '2022-04-08', 'Parque Ibirapuera', 'Brazil', 'Sao Paulo', 'public', 'I wen to Parque Ibirapuera', 5);
INSERT INTO part_of(username, locName, entryDate, country, city, tripName) VALUES
	('jmasson6', 'Parque Ibirapuera', '2022-04-08', 'Brazil', 'Sao Paulo', 'Trip to Sao Paulo');
    
    
INSERT INTO reasons_lookup
VALUES (0, ""),
		(1, "Off Topic"),
        (2, "Explicit Language"),
        (3, "Off Topic, Explicit Language"),
        (4, "Harassment"),
        (5, "Harassment, Off Topic"),
        (6, "Harassment, Explicit Language"),
        (7, "Harassment, Explicit Language, Off Topic");


       
       
