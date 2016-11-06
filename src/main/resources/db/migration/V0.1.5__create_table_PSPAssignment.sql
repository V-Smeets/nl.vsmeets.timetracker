CREATE TABLE "PSP_ASSIGNMENT" COMMENT 'The PSP assignments' (
	"PSP_ASSIGNMENT_ID" IDENTITY
		COMMENT 'The generated primary key'
		PRIMARY KEY,
	"PSP_ID" INT8
		NOT NULL
		COMMENT 'The foreign key to the PSP'
		REFERENCES "PSP",
	"USER_ID" INT8
		NOT NULL
		COMMENT 'The foreign key to the user'
		REFERENCES "USER",
	"START_DATE" DATE
		NOT NULL
		COMMENT 'The first date of the assignment (inclusive)',
	"END_DATE" DATE
		NOT NULL
		COMMENT 'The last date of the assignment (inclusive)',
	CONSTRAINT "PSP_ASSIGNMENT_UNQ_PSP_ID"
		UNIQUE("PSP_ID", "USER_ID", "START_DATE"),
	CONSTRAINT "PSP_ASSIGNMENT_UNQ_END_DATE"
		CHECK "START_DATE" <= "END_DATE");
