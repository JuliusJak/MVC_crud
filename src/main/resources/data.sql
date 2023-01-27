DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id int AUTO_INCREMENT primary key,
    username varchar(255),

    Titel varchar(255),
    Beskrivning varchar(255),
    kategori varchar(255),
    Pris int,
    created_at date,
    created_by varchar(32)
);



INSERT INTO users (username, Titel, Beskrivning, kategori, Pris, created_at, created_by) VALUES ("bob", "jobb", "installering av lampa", "installation", 200, now(), "CONSOLE");
INSERT INTO users (username, Titel, Beskrivning, kategori, Pris, created_at, created_by) VALUES ("bob", "jobb", "installering av el", "installation", 500, "2022-02-19", "CONSOLE");
INSERT INTO users (username, Titel, Beskrivning, kategori, Pris, created_at, created_by) VALUES ("Linus", "Övertid", "övertid på kontoret", "övertid", 600, now(), "CONSOLE");
INSERT INTO users (username, Titel, Beskrivning, kategori, Pris, created_at, created_by) VALUES ("Bertil", "Övertid", "övertid på kontoret", "övertid", 600, now(), "CONSOLE");
INSERT INTO users (username, Titel, Beskrivning, kategori, Pris, created_at, created_by) VALUES ("Gertrude", "Övertid", "övertid på kontoret", "övertid", 600, now(), "CONSOLE");
INSERT INTO users (username, Titel, Beskrivning, kategori, Pris, created_at, created_by) VALUES ("Todd", "Övertid", "övertid på kontoret", "övertid", 600, now(), "CONSOLE");