/* Author: Fabian Narvaez
 * Name: Table faculty
 * Last Modified: 2021-01-22
*/
CREATE EXTENSION IF NOT EXISTS pgcrypto;
DROP TABLE IF EXISTS Faculty;
Create table Faculty(
    Id bigint REFERENCES users (Id),
    SchoolCode varchar(10),
    SchoolDescription varchar(50),
    Office varchar(20),
    Extension int
);
/*Insert records to faculty table */
Insert into Faculty(Id, SchoolCode, SchoolDescription, Office, Extension)
values (100108655,'SET','School of Engineering & Technology', 'H-140', 1234);

Insert into Faculty(Id, SchoolCode, SchoolDescription, Office, Extension)
values (100792055,'SBT','School of Bussines and IT', 'H-150',4455);

Insert into Faculty(Id, SchoolCode, SchoolDescription, Office, Extension)
values (100799655,'SBT','School of Bussines and IT', 'E-130', 3124);