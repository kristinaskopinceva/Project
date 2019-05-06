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
id           INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT ,
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
office_id     INTEGER NOT NULL ,
first_name     VARCHAR(50) NOT NULL ,
second_name    VARCHAR(50) NOT NULL,
middle_name    VARCHAR(50),
position       VARCHAR(50) NOT NULL,
phone          VARCHAR(20),
citizenship_id INTEGER NOT NULL,
is_identified BOOLEAN NOT NULL);
CREATE INDEX IX_user_office_id ON user (office_id);
ALTER TABLE user ADD FOREIGN KEY (office_id) REFERENCES office (id);
CREATE INDEX IX_user_citizenship_id ON user (citizenship_id);
ALTER TABLE user ADD FOREIGN KEY (citizenship_id) REFERENCES country (id);

COMMENT ON TABLE user IS 'Пользователи';

CREATE TABLE IF NOT EXISTS doc_type(
id           INTEGER PRIMARY KEY NOT NULL,
version      INTEGER NOT NULL,
doc_code     INTEGER NOT NULL,
name         VARCHAR(150) NOT NULL);
COMMENT ON TABLE doc_type IS 'Типы документов';

CREATE TABLE IF NOT EXISTS doc(
  id             INTEGER PRIMARY KEY NOT NULL,
  version        INTEGER NOT NULL,
  user_id        INTEGER NOT NULL,
  id_code_type   INTEGER NOT NULL,
  number         VARCHAR(30),
  date           DATE
);
CREATE INDEX IX_doc_user_id ON doc (user_id);
ALTER TABLE doc ADD FOREIGN KEY (user_id) REFERENCES user (id);
CREATE INDEX IX_doc_id_code_type ON doc (id_code_type);
ALTER TABLE doc ADD FOREIGN KEY (id_code_type) REFERENCES doc_type (id);
COMMENT ON TABLE doc IS 'Документы сотрудников орг';

CREATE TABLE IF NOT EXISTS country(
id           INTEGER PRIMARY KEY NOT NULL,
version      INT NOT NULL,
code         INTEGER NOT NULL,
name         VARCHAR(50) NOT NULL);

COMMENT ON TABLE countries IS 'Страны';










