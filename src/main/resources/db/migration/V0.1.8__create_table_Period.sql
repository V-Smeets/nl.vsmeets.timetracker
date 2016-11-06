CREATE TABLE "PERIOD" COMMENT 'The periods' (
	"PERIOD_ID" IDENTITY
		COMMENT 'The generated primary key'
		PRIMARY KEY,
	"TASK_ID" INT8
		NOT NULL
		COMMENT 'The foreign key to the task'
		REFERENCES "TASK",
	"START_TIME" TIME
		NOT NULL
		COMMENT 'The start time. (inclusive)',
	"END_TIME" TIME
		NOT NULL
		COMMENT 'The end time. (exclusive)',
	CONSTRAINT "PERIOD_UNQ_START_TIME"
		UNIQUE("TASK_ID", "START_TIME"),
	CONSTRAINT "PERIOD_CHK_START_TIME"
		CHECK "START_TIME" < "END_TIME");
