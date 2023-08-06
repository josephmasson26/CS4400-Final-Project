CREATE TABLE account (
	username VARCHAR(20),
    email VARCHAR(45) UNIQUE NOT NULL,
    fName VARCHAR(20) NOT NULL,
    lName VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    
    PRIMARY KEY (username)
);

CREATE TABLE user (
	username VARCHAR(20),
    memDate DATE NOT NULL,
    privLvl VARCHAR(7) NOT NULL,
    
    CHECK (privLvl = 'public' OR privLVL = 'private'),
    PRIMARY KEY (username),
    FOREIGN KEY (username) REFERENCES account(username)
		ON UPDATE RESTRICT
		ON DELETE CASCADE
);

CREATE TABLE admin (
	username VARCHAR(20),
    sDate DATE NOT NULL,
    
    PRIMARY KEY (username),
    FOREIGN KEY (username) REFERENCES account(username)
		ON UPDATE RESTRICT
		ON DELETE CASCADE
);

CREATE TABLE bans (
	uUsername VARCHAR(20),
    aUsername  VARCHAR(20) NOT NULL,
    
    
    PRIMARY KEY (uUsername),
    FOREIGN KEY (uUsername) REFERENCES user(username)
		ON UPDATE RESTRICT
        ON DELETE CASCADE,
	FOREIGN KEY (aUsername) REFERENCES admin(username)
		ON UPDATE RESTRICT
		ON DELETE CASCADE
);



CREATE TABLE city (
	cityName VARCHAR(20),
    country VARCHAR(20),
    
    PRIMARY KEY (cityName, country)
);


CREATE TABLE category (
	catName VARCHAR(20),
    aUsername VARCHAR(20) NOT NULL,
    
    PRIMARY KEY (catName),
    FOREIGN KEY (aUsername) REFERENCES admin(username)
		ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

CREATE TABLE can_be_in (
	catName VARCHAR(20),
    siteName VARCHAR(45),
    city VARCHAR(20),
    country VARCHAR(20),
    
    PRIMARY KEY (catName, siteName, city, country),
    FOREIGN KEY (catName) REFERENCES category(catName)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE journal_entry (
	username VARCHAR(20),
    entryDate DATE,
    locName VARCHAR(45),
    country VARCHAR(20),
    city VARCHAR(20) NOT NULL,
    privLvl VARCHAR(7) NOT NULL,
    note VARCHAR(500),
    rating TINYINT UNSIGNED,
    
    CHECK (privLvl = 'public' OR privLVL = 'private'),
    CHECK (rating >= 1 AND rating <= 5),
    CHECK (note is NOT NULL OR rating is NOT NULL),
    PRIMARY KEY (username, entryDate, locName, country),
    FOREIGN KEY (username) REFERENCES user(username)
		ON UPDATE RESTRICT
        ON DELETE CASCADE
);

CREATE TABLE trip (
	username VARCHAR(20),
    tripName VARCHAR(40),
    sDate DATE NOT NULL,
    eDate DATE NOT NULL,
    
    CHECK (sDate <= eDate),
    PRIMARY KEY (username, tripName),
    FOREIGN KEY (username) REFERENCES user(username)
		ON UPDATE RESTRICT
        ON DELETE CASCADE
);

CREATE TABLE can_flag (
	username VARCHAR(20),
    flaggedUser VARCHAR(20),
    flagDate DATE NOT NULL,
    entryDate DATE NOT NULL,
    locName VARCHAR(45) NOT NULL,
    city VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
	reason VARCHAR (100),
	note VARCHAR(500),
    
    PRIMARY KEY (username, flaggedUser, locName),
    FOREIGN KEY (username) REFERENCES user(username)
		ON UPDATE RESTRICT
        ON DELETE CASCADE,
	FOREIGN KEY (flaggedUser, entryDate, locName, country) REFERENCES
		journal_entry(username, entryDate, locName, country)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE part_of (
    username VARCHAR(20),
    locName VARCHAR(45),
    entryDate DATE,
    country VARCHAR(20),
    city VARCHAR(20) NOT NULL,
    tripName VARCHAR(20) NOT NULL,
    PRIMARY KEY (username , locName , entryDate , country),
    FOREIGN KEY (username)
        REFERENCES user (username)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (username , entryDate , locName , country)
        REFERENCES journal_entry (username , entryDate , locName , country)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (username , tripName)
        REFERENCES trip (username , tripName)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE reasons_lookup (
	reasonID INT,
    reasonString VARCHAR(40) NOT NULL,
    
    PRIMARY KEY (reasonID)
    -- binary value ID based on reason combo selected 1-7
);

CREATE VIEW avg_rating AS
SELECT city, country, AVG(rating) AS average
FROM journal_entry
WHERE city = city AND country = country AND privLvl = "public"
GROUP BY city, country
ORDER BY average;
