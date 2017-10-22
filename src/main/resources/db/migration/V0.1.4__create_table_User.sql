CREATE TABLE "USER" COMMENT 'The users' (
	"USER_ID" IDENTITY
		COMMENT 'The generated primary key'
		PRIMARY KEY,
	"NAME" VARCHAR(8)
		NOT NULL
		COMMENT 'The full name of the user'
		UNIQUE);
