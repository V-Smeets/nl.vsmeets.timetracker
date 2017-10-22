CREATE TABLE "TIME_ENTRY" COMMENT 'The time entries' (
	"TIME_ENTRY_ID" IDENTITY
		COMMENT 'The generated primary key'
		PRIMARY KEY,
	"PSP_ASSIGNMENT_ID" INT8
		NOT NULL
		COMMENT 'The foreign key to the PSP assignment'
		REFERENCES "PSP_ASSIGNMENT",
	"DATE" DATE
		NOT NULL
		COMMENT 'The date of this entry',
	"DURATION" INTEGER
		NOT NULL
		COMMENT 'The duration in seconds',
	"COMMENT" VARCHAR(32)
		COMMENT 'The comment of this entry',
	CONSTRAINT "TIME_ENTRY_UNQ_DATE"
		UNIQUE("PSP_ASSIGNMENT_ID", "DATE"));
