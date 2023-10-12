create database if not exists jukeboxdb;
use jukeboxdb;
create table songs(
id int primary key,
song_name varchar(50),
song_genre varchar(50),
album_artist varchar(50),
duration double,
path_url varchar(100));
show tables;
drop table songs;
insert into songs values(101,"aradhya","love","vijay",4.56,"src\\main\\java\\resources\\songs\\Aradhya.wav");
insert into songs values(102,"Djtillu","pop","ram",3.06,"src\\main\\java\\resources\\songs\\DJTillu.wav");
insert into songs values(103,"heeriye","love","mitraz",3.19,"src\\main\\java\\resources\\songs\\Heeriye.wav");
insert into songs values(104,"lavenderhaze","pop","tylor",3.22,"src\\main\\java\\resources\\songs\\LavenderHaze.wav");
insert into songs values(105,"SuperMachi","party","DSP",4.38,"src\\main\\java\\resources\\SuperMachi.wav");
insert into songs values(106,"Bullet","party","haripriya",4.02,"src\\main\\java\\resources\\songs\\Bullet.wav");
select * from songs;
create table playlist(
playlist_id int ,
playlist_name varchar(30),
id int,
foreign key(id) references songs(id));
show tables;
insert into playlist values(1,"fav",103);
insert into playlist values(2,"latest",101);
select * from playlist;
create table login(
username Varchar(30),
password varchar(20),
mailid varchar(40),
contactno int);
insert into login values("sandhya","1234","abc@gmail.com","123456789");
