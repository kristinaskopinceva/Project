CREATE TABLE IF NOT EXISTS organization(
id           INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
version      INTEGER NOT NULL,
name         VARCHAR(50) NOT NULL,
fullName     VARCHAR(100) NOT NULL,
inn          VARCHAR(9) NOT NULL,
kpp          VARCHAR(12) NOT NULL,
address      VARCHAR(150) NOT NULL,
phone        VARCHAR(20) NOT NULL,
isActive     BOOLEAN NOT NULL);

COMMENT ON TABLE Person IS 'Организация';

CREATE TABLE IF NOT EXISTS office (
id           INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
version      INTEGER NOT NULL,
orgid        INTEGER,
name         VARCHAR(50) NOT NULL,
address      VARCHAR(150)NOT NULL,
phone        VARCHAR(20) NOT NULL,
is_active    BOOLEAN NOT NULL,
FOREIGN KEY (orgId)REFERENCES `organization` (id));

COMMENT ON TABLE Person IS 'Офис';
CREATE INDEX IX_Org_Id ON office (orgid);

CREATE TABLE IF NOT EXISTS user (
id           INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
version      INTEGER NOT NULL,
officeId     INTEGER NOT NULL ,
firstName    VARCHAR(50) NOT NULL ,
secondName   VARCHAR(50) NOT NULL,
middleName   VARCHAR(50),
position     VARCHAR(50) NOT NULL,
phone        VARCHAR(20),
docCode      VARCHAR(20) NOT NULL,
docNumber    VARCHAR(30),
docDate      DATE,
citizenshipCode INTEGER NOT NULL,

isidentified BOOLEAN NOT NULL,
FOREIGN KEY (officeId) REFERENCES office (id),
FOREIGN KEY (docCode) REFERENCES docs (code),
FOREIGN KEY (citizenshipCode) REFERENCES countries (code));
CREATE INDEX IX_office_Id ON user(officeId);
CREATE INDEX IX_citizenship_Id ON user (citizenshipCode);

COMMENT ON TABLE Person IS 'Пользователи';

CREATE TABLE IF NOT EXISTS docs (
code         INTEGER PRIMARY KEY NOT NULL,
version      INTEGER NOT NULL,
name         VARCHAR(150) NOT NULL);

COMMENT ON TABLE Person IS 'Документы';

CREATE TABLE IF NOT EXISTS countries (
code         INTEGER PRIMARY KEY NOT NULL,
version      INT NOT NULL,
name         VARCHAR(50) NOT NULL);

COMMENT ON TABLE Person IS 'Страны';










