create table Vehicule(
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
    foreign key (id) references Vehicule(id)
);

create table Entite(
    id serial primary key,
    email varchar(50),
    passwords varchar(50),
    token varchar(50),
    date_expiration timestamp
);
create or replace view v_vehicule as select Vehicule.id,Vehicule.descriptions,Vehicule.marque,Vehicule.annee,Vehicule.matricule,Kilometrage.dates,Kilometrage.debut,Kilometrage.fin from Vehicule inner join Kilometrage on Vehicule.id=Kilometrage.id;




insert into Entite(email,passwords) values ('rakoto@gmail.com','123456');


insert into Vehicule(matricule,annee,descriptions,marque) values('TBL36',2002,'Des sièges confortables: Le confort des sièges dépend grandement des préférences de chacun. Par exemple, des appuis latéraux et un support lombaire sont indispensables pour certains, tandis que d''autres les trouvent incommodants.','TOYOTA');
insert into Vehicule(matricule,annee,descriptions,marque) values('TBA36',2002,'Une bonne visibilité: Ici, de minces montants A, B et C sont très utiles. Idem pour ce qui est d''une grande surface vitrée, de bons miroirs latéraux et de sièges ajustables en hauteur','PEUGEOT');
insert into Vehicule(matricule,annee,descriptions,marque) values('TBL35',2002,'Un lecteur CD et/ou une radio satellite dans la console: Pour tous ces petits moments où vous désirez éteindre la radio et choisir votre propre liste de chansons, un lecteur CD s''avère fort pratique, surtout un situé à même le tableau de bord','RENAULT');
insert into Vehicule(matricule,annee,descriptions,marque) values('TAN20',2002,'Des contrôles audio montés sur le volant: Il y a des fois où on ressent le besoin de jouer avec les basses ou de grimper le volume tout en étant confortablement assis derrière le volant','CITROEN');

insert into Kilometrage(id,dates,debut,fin) values(1,'2022-11-02',10,20000);
insert into Kilometrage(id,dates,debut,fin) values(1,'2022-11-02',20,40000);
insert into Kilometrage(id,dates,debut,fin) values(1,'2022-11-02',30,60000);
