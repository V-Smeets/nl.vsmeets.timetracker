CREATE TABLE "COUNTRY" COMMENT 'The countries' (
	"COUNTRY_ID" IDENTITY
		COMMENT 'The generated primary key'
		PRIMARY KEY,
	"CODE" VARCHAR(2)
		NOT NULL
		COMMENT 'The ISO 3166-1 2 letter country code'
		UNIQUE);
