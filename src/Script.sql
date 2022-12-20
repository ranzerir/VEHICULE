create table Avion(
    id serial primary key,
    matricule varchar(50),
    annee int,
    descriptions varchar(250),
    marque varchar(50)
);
create table Kilometrage(
    id int,
    dates date,
    debut real,
    fin real,
    foreign key (id) references Avion(id)
);

create table Entite(
    id serial primary key,
    email varchar(50),
    passwords varchar(50),
    token varchar(50),
    date_expiration timestamp
);

CREATE TABLE assurance(
    idAvion int references Avion(id),
    assurance varchar(50),
    date_payement date,
    date_expiration date,
    montant double precision

);


create or replace view v_Avion as select Avion.id,Avion.descriptions,Avion.marque,Avion.annee,Avion.matricule,Kilometrage.dates,Kilometrage.debut,Kilometrage.fin from Avion inner join Kilometrage on Avion.id=Kilometrage.id;




insert into Entite(email,passwords) values ('rakoto@gmail.com','123456');


insert into Avion(matricule,annee,descriptions,marque) values('TBL36',2002,'Des sièges confortables: Le confort des sièges dépend grandement des préférences de chacun. Par exemple, des appuis latéraux et un support lombaire sont indispensables pour certains, tandis que d''autres les trouvent incommodants.','TOYOTA');
insert into Avion(matricule,annee,descriptions,marque) values('TBA36',2002,'Une bonne visibilité: Ici, de minces montants A, B et C sont très utiles. Idem pour ce qui est d''une grande surface vitrée, de bons miroirs latéraux et de sièges ajustables en hauteur','PEUGEOT');
insert into Avion(matricule,annee,descriptions,marque) values('TBL35',2002,'Un lecteur CD et/ou une radio satellite dans la console: Pour tous ces petits moments où vous désirez éteindre la radio et choisir votre propre liste de chansons, un lecteur CD s''avère fort pratique, surtout un situé à même le tableau de bord','RENAULT');
insert into Avion(matricule,annee,descriptions,marque) values('TAN20',2002,'Des contrôles audio montés sur le volant: Il y a des fois où on ressent le besoin de jouer avec les basses ou de grimper le volume tout en étant confortablement assis derrière le volant','CITROEN');

insert into Kilometrage(id,dates,debut,fin) values(1,'2022-11-02',10,20000);
insert into Kilometrage(id,dates,debut,fin) values(1,'2022-11-02',20,40000);
insert into Kilometrage(id,dates,debut,fin) values(1,'2022-11-02',30,60000);

INSERT INTO Assurance values(1,'aro','2022-10-12',current_date,200000);
INSERT INTO Assurance values(2,'mama','2022-09-12','2022-12-31',300000);
INSERT INTO Assurance values(1,'aro','2022-10-12','2023-01-29',80000);
INSERT INTO Assurance values(2,'mama','2022-09-12','2023-03-30',300000);
