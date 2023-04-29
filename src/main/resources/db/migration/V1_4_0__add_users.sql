DROP TABLE dbo.[User];
GO

Create table dbo.[Users] (
                            id int NOT NULL IDENTITY,
                            firstname nvarchar(10) NOT NULL ,
                            lastname nvarchar(10) NOT NULL ,
                            password nvarchar(20) NOT NULL ,
                            email nvarchar(20) NOT NULL ,
                            age int NOT NULL ,
                            clientCode  nvarchar(20) NOT NULL ,
                            role nvarchar(20) NOT NULL
)

INSERT INTO Users (
                     firstname,
                     lastname,
                     password,
                     email,
                     age,
                     clientCode,
                     role
                     ) VALUES
                           ('foo','foo', 'foo', 'foo@gmail.com',23, 'DEL', 'ADMIN')

