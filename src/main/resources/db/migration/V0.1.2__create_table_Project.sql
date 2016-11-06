CREATE TABLE "PROJECT" COMMENT 'The projects' (
	"PROJECT_ID" IDENTITY
		COMMENT 'The generated primary key'
		PRIMARY KEY,
	"COUNTRY_ID" INT8
		NOT NULL
		COMMENT 'The foreign key to the country'
		REFERENCES "COUNTRY",
	"CODE" VARCHAR(6)
		NOT NULL
		COMMENT 'The 6 character project code',
	"NAME" VARCHAR(64)
		COMMENT 'The full name of the project',
	CONSTRAINT "PROJECT_UNQ_CODE"
		UNIQUE("COUNTRY_ID", "CODE"));
