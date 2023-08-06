-- DO NOT RUN THESE STATEMENTS! ONLY FOR PHASE 3 SUBMISSION. --

-- CS 4400 Phase 3

-- Query 1 - Checked

SELECT username, password
FROM account
WHERE username = 'jmasson6' AND password = 'hahauwish' AND NOT EXISTS(SELECT uUsername FROM bans WHERE username = uUsername);

-- Query 2 - Checked
INSERT INTO account (username, email, fName, lName, password)
VALUES('grandzo', 'grandzo@gmail.com', 'Grand', 'Zo', 'grandzo');

-- if adminButton = true:
	INSERT INTO admin (username, sDate)
	VALUES('grandzo', '2023-07-18');
-- else:]
INSERT INTO account (username, email, fName, lName, password)
	VALUES ('ssll', 'ssll@gmail.com', 'SS', 'LL', 'SS');
INSERT INTO user (username, memDate, privLvl)
	VALUES('ssll', '2023-07-18', 'private');


-- Query 3 is not SQL
	
-- Query 4 Shows on the User Settings Screen data about the user Joseph Masson - checked

SELECT Fname
FROM account
WHERE username = 'jmasson6';

SELECT Lname
FROM account
WHERE username = 'jmasson6';

SELECT email
FROM account
WHERE username = 'jmasson6';

SELECT password
FROM account
WHERE username = 'jmasson6';

-- Query 5 - checked

DELETE FROM journal_entry
WHERE username = 'ssll';
DELETE FROM account
WHERE username = 'ssll';
DELETE FROM User
WHERE username = 'ssll';

-- Query 6 -checked

UPDATE account
SET Fname = 'Jason', Lname = 'Mason', email = 'josephhmasson@gmail.com', password = '12345'
WHERE username = 'jmasson6';

-- Query 7 -checked

INSERT INTO trip
VALUES ("jmasson6", "Journey to the Earth", "2022-01-01", "2023-01-01");

-- Query 8 - checked
SELECT *
FROM avg_rating;

-- Query 9 - checked

SELECT  *
FROM avg_rating
WHERE city = "Sao Paulo";

-- Query 10 - checked

SELECT *
FROM avg_rating;

-- Query 11 - Checked

SELECT entryDate, Rating, Note
FROM journal_entry
WHERE privLvl = 'public' AND city = 'Tokyo';

-- Query 12 - Checked

SELECT city, entryDate, rating, note
FROM journal_entry AS JE
WHERE JE.entryDate = '2020-01-01';

-- Query 13 - Checked

INSERT INTO can_flag(username, flaggedUser, flagDate, entryDate, locName, city, country, reason, note)
VALUES ('bluu', 'jmasson6', '2023-07-18', '2023-06-05', 'Shanghai', 'Shanghai', 'China', 'Harassment', 'BING CHILLING IN SHANGHAI TODAY!');

-- Query 14 - Checked

SELECT privLvl
FROM user
WHERE username = 'jmasson6';
	
-- Query 15 - checked

INSERT INTO journal_entry(username, entryDate, locName, country, city, privLvl, note, rating)
VALUES('jmasson6', '2023-07-18', 'Atlanta', 'United States',  'Atlanta', 'public', '', '5');	

-- Query 16 - checked

SELECT tripName
     	FROM trip
      	WHERE username = 'jmasson6';
        
-- Query 17 - checked

SELECT entryDate, city, country, rating, note
FROM journal_entry AS JE
WHERE JE.username = 'jmasson6' AND JE.entryDate BETWEEN '2022-01-01' AND '2023-01-01'
ORDER BY JE.entryDate;

-- Query 18 - checked

SELECT entryDate, city, country, rating, note
FROM journal_entry AS JE
WHERE JE.username = 'jmasson6'
ORDER BY JE.entryDate;

-- Query 19 -checked

SELECT entryDate, city, country, rating, note
FROM journal_entry AS JE
WHERE JE.username = 'jmasson6'
ORDER BY JE.city;

-- Query 20 - checked

-- ** On click entry **
SELECT city, entryDate, rating, note
FROM journal_entry
WHERE username = 'jmasson6'
	AND entryDate = '2023-06-05'
	AND city = 'Shanghai'
	AND country = 'China';

-- Query 21 - checked

-- ** When delete the entry **
DELETE FROM journal_entry
WHERE username = 'jmasson6'
	AND entryDate = '2022-04-08'
	AND city = 'Sao Paulo'
	AND country = 'Brazil';

-- Query 22 - checked

SELECT flaggedUser, city, note, reason, username
FROM can_flag;

-- Query 23 is not an SQL query


-- Query 24 - checked
SELECT JE.city, JE.entryDate, JE.rating, JE.note, CF.username, CF.flaggedUser
FROM can_flag AS CF, journal_entry AS JE
WHERE CF.username = 'bluu' AND CF.flaggedUser = 'jmasson6'
	AND JE.username = CF.flaggedUser
	AND JE.entryDate = CF.entryDate
	AND JE.locName = CF.locName
	AND JE.country = CF.country;


-- Query 25
DELETE FROM can_flag
WHERE username = 'bluu'
AND flaggedUser = 'jmasson6'
AND entryDate = '2023-06-05'
AND locName = 'Shanghai'
AND country = 'China';

-- Query 26 - checked
DELETE FROM can_flag
WHERE username = 'RegentDart75702'
AND flaggedUser = 'jmasson6'
AND entryDate = '2023-06-05'
AND locName = 'Shanghai'
AND country = 'China';

DELETE FROM journal_entry AS JE
WHERE JE.username = 'jmasson6'
	AND entryDate = '2023-06-05'
	AND locName = 'Shanghai'
	AND country = 'China';
	

	
-- Query 27

-- ***** UNFINISHED *****	
DELETE FROM journal_entry
WHERE username = 'jmasson6';

INSERT INTO bans(uUsername, aUsername)
VALUES ('jmasson6', 'grandzo');

