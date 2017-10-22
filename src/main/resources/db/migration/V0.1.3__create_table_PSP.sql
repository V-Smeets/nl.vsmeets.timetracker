CREATE TABLE "PSP" COMMENT 'The PSPs' (
	"PSP_ID" IDENTITY
		COMMENT 'The generated primary key'
		PRIMARY KEY,
	"PROJECT_ID" INT8
		NOT NULL
		COMMENT 'The foreign key to the project'
		REFERENCES "PROJECT",
	"CODE" VARCHAR(3)
		NOT NULL
		COMMENT 'The 3 character PSP code',
	"NAME" VARCHAR(64)
		COMMENT 'The full name of the PSP',
	CONSTRAINT "PSP_UNQ_CODE"
		UNIQUE("PROJECT_ID", "CODE"));
