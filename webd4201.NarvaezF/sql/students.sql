/* Author: Fabian Narvaez
 * Name: Table students
 * Last Modified: 2021-01-22
*/
CREATE EXTENSION IF NOT EXISTS pgcrypto;
DROP TABLE IF EXISTS Students;
Create table Students(
    Id bigint REFERENCES Users (Id),
    ProgramCode varchar(50),
    ProgramDescription varchar(50),
    Year int
);
/*Insert records to students table */
Insert into Students(Id, ProgramCode, ProgramDescription, Year)
values (100736775,'CPA','Computer Programming and Analysis', 2);

Insert into Students(Id, ProgramCode, ProgramDescription, Year)
values (100737777,'CSTY','Computer System Technology', 3);

Insert into Students(Id, ProgramCode, ProgramDescription, Year)
values (100111111,'CSTY','Computer System Technology', 3);