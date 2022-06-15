create table Author
(
    ID         int         not null
        primary key,
    First_Name varchar(20) null,
    Last_Name  varchar(20) null
);

create table Author_Book
(
    Author_ID int not null,
    Book_ID   int not null
);


create table Publishing_House
(
    ID   int         not null
        primary key,
    Name varchar(40) not null
);


create table Book
(
    ID                  int         not null
        primary key,
    Name                varchar(20) not null,
    Author_ID           int         null,
    Publishing_House_ID int         null
);

create table Faculty
(
    ID   int         not null
        primary key,
    Name varchar(20) null
);

create table Library_Worker
(
    ID         int         not null
        primary key,
    First_Name varchar(20) null,
    Last_Name  varchar(20) null,
    Adress     varchar(20) null
);

create table Student
(
    ID         int auto_increment
        primary key,
    First_Name varchar(20)  null,
    Last_Name  varchar(20)  null,
    Address    varchar(100) null,
    Group_ID   int          null,
    constraint student_Student_Group_ID_fk
        foreign key (Group_ID) references Student_Group (ID)
);

create table Student_Card
(
    ID            int      not null
        primary key,
    Opened_At     datetime not null,
    Close_At      datetime null,
    Student_ID    int      null,
    Required_At   datetime not null,
    Recieve_By_ID int      null,
    Book_ID       int      null,
    constraint Student_Card_Library_Worker_ID_fk
        foreign key (Recieve_By_ID) references Library_Worker (ID)
);

create table Student_Group
(
    ID         int         not null
        primary key,
    Name       varchar(20) null,
    Faculty_ID int         null,
    constraint Student_Group_Faculty_ID_fk
        foreign key (Faculty_ID) references Faculty (ID)
);

create table Teacher_Card
(
    ID             int         not null
        primary key,
    Opened_At      varchar(20) not null,
    Close_At       varchar(20) null,
    Teacher_ID     int         null,
    Required_At    varchar(20) not null,
    Received_By_ID int         null,
    constraint Teacher_Card_Library_Worker_ID_fk
        foreign key (Received_By_ID) references Library_Worker (ID),
    constraint Teacher_Card_Teacher_ID_fk
        foreign key (Teacher_ID) references Teacher (ID)
);

create table Teacher
(
    ID         int         not null
        primary key,
    First_Name varchar(20) null,
    Last_Name  varchar(20) null,
    Faculty_ID int         null,
    constraint Teacher_Faculty_ID_fk
        foreign key (Faculty_ID) references Faculty (ID)
);
