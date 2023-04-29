Create table dbo.[User] (
    id int NOT NULL IDENTITY,
    firstname nvarchar(10) NOT NULL ,
    lastname nvarchar(10) NOT NULL ,
    password nvarchar(20) NOT NULL ,
    email nvarchar(20) NOT NULL ,
    age int NOT NULL ,
    clientCode nvarchar ,
    role nvarchar
)

