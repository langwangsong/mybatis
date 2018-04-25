CREATE TABLE country(
	id INT NOT NULL AUTO_INCREMENT,
	countryname VARCHAR(255),
	countrycode VARCHAR(255),
	PRIMARY KEY(id)
);
INSERT INTO country(countryname,countrycode)
	VALUES('中国','CN'),
	('美国','US'),
	('俄罗斯','RU'),
	('英国','GB'),
	('法国','FR');
SELECT * FROM country