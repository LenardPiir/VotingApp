CREATE TABLE IF NOT EXISTS "result" (
    "id" SERIAL PRIMARY KEY,
    "animal" varchar,
    "number" int
);

insert into result (animal, number) values ('Dog', 0), ('Cat', 0);