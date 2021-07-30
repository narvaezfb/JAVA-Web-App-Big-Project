/* Author: Fabian Narvaez
 * Name: Table Users
 * Last Modified: 2021-01-22
*/
/*Create table users and import encryption */
CREATE EXTENSION IF NOT EXISTS pgcrypto;
DROP TABLE IF EXISTS Users;
create table Users (
    Id bigint primary key,
    Password varchar(40) not null,
	FirstName varchar(128),
	LastName varchar (128),
    EmailAddress varchar (255),
    LastAccess Date,
    EnrolDate Date,
    Enabled boolean,
    Type varchar(1) 
);

/*Insert records to the table 
 * Students:
 */
Insert into Users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
values (100736775, ENCODE(DIGEST('Ecuador','sha1'), 'hex'), 'Fabian', 'Narvaez', 'narvaezfb4@hotmail.com', '2021-01-17', '2021-01-17','t','s');

Insert into Users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
values (100737777, ENCODE(DIGEST('password2','sha1'), 'hex'), 'Yustina', 'Bouls', 'yustina@hotmail.com', '2021-01-18', '2021-01-18','t','s');

Insert into Users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
values (100111111, ENCODE(DIGEST('password','sha1'), 'hex'), 'Mike', 'Jones', 'mike@hotmail.com', '2021-01-17', '2021-01-17','t','s');
/*
 * Faculty:
 */
Insert into Users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
values (100108655, ENCODE(DIGEST('faculty','sha1'), 'hex'), 'Stephen', 'Forbes', 'stephen@hotmail.com', '2021-01-19', '2021-01-19','t','f');

Insert into Users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
values (100792055, ENCODE(DIGEST('faculty2','sha1'), 'hex'), 'James', 'Bonds', 'james@hotmail.com', '2021-01-20', '2021-01-20','t','f');

Insert into Users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
values (100799655, ENCODE(DIGEST('faculty3','sha1'), 'hex'), 'Jhon', 'Abruzzi', 'Jhon@hotmail.com', '2021-01-21', '2021-01-21','t','f');
