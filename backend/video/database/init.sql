CREATE TABLE objects (
    objectId integer PRIMARY KEY,
    name varchar(50),
    width integer,
    depth integer,
    height integer
);

INSERT INTO objects VALUES(0, 'chair', 47, 45, 70);
INSERT INTO objects VALUES(0, 'desk', 100, 100, 100);
