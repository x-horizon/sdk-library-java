DROP
    DATABASE IF EXISTS test_mqtt_server;

CREATE
    DATABASE test_mqtt_server PRECISION 'ms' KEEP 3650 DURATION 10 BUFFER 16;

USE
test_mqtt_server;

DROP TABLE if EXISTS junction_signal_step;
DROP TABLE if EXISTS junction_traffic_flow;
DROP TABLE if EXISTS junction_status;


CREATE
STABLE junction_signal_step (
    create_time TIMESTAMP,
    info INT
) TAGS (
    junction_id INT
);

CREATE TABLE junction_signal_step_001 USING junction_signal_step
(
    junction_id
) TAGS
(
    001
);

CREATE
STABLE junction_traffic_flow (
    create_time TIMESTAMP,
    info INT
) TAGS (
    junction_id INT
);

CREATE TABLE junction_traffic_flow_001 USING junction_traffic_flow
(
    junction_id
) TAGS
(
    001
);

CREATE
STABLE junction_status (
    create_time TIMESTAMP,
    info INT
) TAGS (
    junction_id INT
);

CREATE TABLE junction_status_001 USING junction_status
(
    junction_id
) TAGS
(
    001
);