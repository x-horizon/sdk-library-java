CREATE TABLE geometry_test
(
    id            BIGINT   NOT NULL,
    location_info GEOMETRY NULL,
    PRIMARY KEY (id)
);

INSERT INTO geometry_test(id, location_info)
VALUES (1, st_geometryfromtext('POINT (10 10)'));
INSERT INTO geometry_test(id, location_info)
VALUES (2, st_geometryfromtext('LINESTRING (0 0, 10 10, 20 20)'));
INSERT INTO geometry_test(id, location_info)
VALUES (3, st_geometryfromtext('POLYGON((40 100, 40 20, 120 20, 120 100, 40 100))'));

SELECT id, st_astext(location_info) AS location_info
FROM geometry_test;