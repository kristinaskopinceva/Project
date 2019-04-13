CREATE TABLE IF NOT EXISTS organization(
id           INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
version      INTEGER NOT NULL,
name         VARCHAR(50) NOT NULL,
full_name    VARCHAR(100) NOT NULL,
inn          VARCHAR(9) NOT NULL,
kpp          VARCHAR(12) NOT NULL,
address      VARCHAR(150) NOT NULL,
phone        VARCHAR(20) NOT NULL,
is_active    BOOLEAN NOT NULL);

COMMENT ON TABLE organization IS 'Организация';

CREATE TABLE IF NOT EXISTS office (
id           INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
version      INTEGER NOT NULL,
org_id       INTEGER NOT NULL ,
name         VARCHAR(50) NOT NULL,
address      VARCHAR(150)NOT NULL,
phone        VARCHAR(20) NOT NULL,
is_active    BOOLEAN NOT NULL);
CREATE INDEX IX_office_org_id ON office (org_id);
ALTER TABLE office ADD FOREIGN KEY (org_id) REFERENCES organization (id);

COMMENT ON TABLE office IS 'Офис';


CREATE TABLE IF NOT EXISTS user (
id            INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
version       INTEGER NOT NULL,
office_id      INTEGER NOT NULL ,
first_name    VARCHAR(50) NOT NULL ,
second_name   VARCHAR(50) NOT NULL,
middle_name   VARCHAR(50),
position      VARCHAR(50) NOT NULL,
phone         VARCHAR(20),
doc_code      INTEGER NOT NULL,
doc_number    VARCHAR(30),
doc_date      DATE,
citizenship_code INTEGER NOT NULL,
is_identified BOOLEAN NOT NULL);
CREATE INDEX IX_user_office_id ON user (office_id);
ALTER TABLE user ADD FOREIGN KEY (office_id) REFERENCES office (id);

CREATE INDEX IX_user_doc_code ON user (doc_code);
ALTER TABLE user ADD FOREIGN KEY (doc_code) REFERENCES docs (code);

CREATE INDEX IX_user_citizenship_code ON user (citizenship_code);
ALTER TABLE user ADD FOREIGN KEY (citizenship_code) REFERENCES countries (code);

COMMENT ON TABLE user IS 'Пользователи';

CREATE TABLE IF NOT EXISTS docs (
code         INTEGER PRIMARY KEY NOT NULL,
version      INTEGER NOT NULL,
name         VARCHAR(150) NOT NULL);

COMMENT ON TABLE docs IS 'Документы';

CREATE TABLE IF NOT EXISTS countries (
code         INTEGER PRIMARY KEY NOT NULL,
version      INT NOT NULL,
name         VARCHAR(50) NOT NULL);

COMMENT ON TABLE countries IS 'Страны';










