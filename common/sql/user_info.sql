CREATE TABLE user_info(
  user_id   int8  PRIMARY KEY,
  user_name VARCHAR(32),
  user_tel  VARCHAR(11)
);
CREATE TABLE consumer(
threat_level int8,
create_time VARCHAR(255),
sip VARCHAR (255),
dip VARCHAR (255),
user_id int8 PRIMARY KEY,
user_name VARCHAR(32),
user_tel VARCHAR (11)
);
CREATE SEQUENCE consumer_sequence INCREMENT 1 MINVALUE 1 NO MAXVALUE  OWNED BY consumer.user_id