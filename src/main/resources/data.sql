INSERT INTO organization (version, name, fullname, inn, kpp, address, phone, isactive) VALUES ('0', 'Магнит', 'ЗАО "Тандер"', '2310031475', '997350001', 'Краснодар, ул. Солнечная, 15/5', '+7 (861) 200-01-01', 'true');

INSERT INTO organization (version, name, fullname, inn, kpp, address, phone, isactive) VALUES ('0', 'Санги Стиль', 'ООО "Русский стиль-97"', '2310033521', '231001001', 'Краснодар, ул.им Щорса, д.50,', '+7 (861) 547-22-22', 'false');

INSERT INTO organization (version, name, fullname, inn, kpp, address, phone, isactive) VALUES ('0', 'АШАН', 'ООО "АШАН"', '7703270067' , '876543210', 'Сочи, улица Бабушкина, д. 9 ', '+7 (8622) 589-45-45', true);

INSERT INTO office (version, orgid, name, address, phone, is_active) VALUES ('0', '1', 'Офис Магнит ГК', 'Краснодар', '+7(861) 456-00-00', 'true');

INSERT INTO office (version, orgid, name, address, phone, is_active) VALUES ('0', '1', 'Офис Магнит Сочи', 'Сочи', '+7 (862) 657-55-56', 'true');

INSERT INTO office (version, orgid, name, address, phone, is_active) VALUES ('0', '1', 'Офис Магнит Ростов', 'г. Ростов-на-Дону, ул. Малое Зеленое Кольцо, 5', '+7 (863)-333 25 -96', 'true');

INSERT INTO office (version, orgid, name, address, phone, is_active) VALUES ('0', '2', 'Офис Русский стиль Краснодар', 'г. Краснодар,ул.Уральская, 75', '+7 (861) 458-87-99', 'false');

INSERT INTO office (version, orgid, name, address, phone, is_active) VALUES ('0', '2', 'Офис Русский стиль Самара', 'г. Самара, ул.Ленина, 3', '+7 (846) 458-87-99', 'false');

INSERT INTO office (version, orgid, name, address, phone, is_active) VALUES ('0', '3', 'Офис АШАН ГК', 'г. Сочи, микрорайон Донская, ул. Новая Заря 7' ,'+7 (862) 455-33-99', 'true');

INSERT INTO user (version, officeid, firstName, secondName, middleName, position, phone, docCode, docNumber, docDate, citizenshipCode, isidentified) VALUES ('0', '1', 'Иван', 'Иванов', 'Иванович', 'Оператор', '+7 (789) 966-07-98', '21', '0305 546546', '2019-02-01', '643', 'true');

INSERT INTO user (version, officeid, firstName, secondName, middleName, position, phone, docCode, docNumber, docDate, citizenshipCode, isidentified) VALUES ('0', '2', 'Петр', 'Петров', 'Иванович', 'Шеф', '+7 (895) 956-09-96', '07', '0456 544524', '2018-02-22', '380', 'true');

INSERT INTO user (version, officeid, firstName, secondName, middleName, position, phone, docCode, docNumber, docDate, citizenshipCode, isidentified) VALUES ('0', '3', 'Николай', 'Петренко', 'Иванович', 'Рук.сектора', '+7 (548) 958-79-99', '03', '08545666', '2017-11-22', '643', 'true');

INSERT INTO user (version, officeid, firstName, secondName, middleName, position, phone, docCode, docNumber, docDate, citizenshipCode, isidentified) VALUES ('0', '4', 'Нина', 'Крылова', 'Сергеевна', 'Бухгалтер', '+7 (358) 958-79-99', '21', '0203 544888', '2010-11-22', '643', 'true');

INSERT INTO user (version, officeid, firstName, secondName, middleName, position, phone, docCode, docNumber, docDate, citizenshipCode, isidentified) VALUES ('0', '5', 'Нина', 'Крылова', 'Сергеевна', 'Бухгалтер', '+7 (358) 958-79-99', '21', '0203 544888', '2010-03-22', '643', 'true');

INSERT INTO user (version, officeid, firstName, secondName, middleName, position, phone, docCode, docNumber, docDate, citizenshipCode, isidentified) VALUES ('0', '5', 'Елена', 'Иванова', 'Сергеевна', 'Шеф', '+7 (358) 958-79-99', '12', '054-544888', '2010-11-22', '380', 'true');

INSERT INTO user (version, officeid, firstName, secondName, middleName, position, phone, docCode, docNumber, docDate, citizenshipCode, isidentified) VALUES ('0', '6', 'Александр', 'Уткин', 'Валериевич', 'Администратор', '+7 (099) 966-76-66', '07', '054544888', '2015-11-22', '643', 'true');

INSERT INTO docs (code, version, name) VALUES ('03', '0', 'Свидетельство о рождении');

INSERT INTO docs (code, version, name) VALUES ('07', '0', 'Военный билет');

INSERT INTO docs (code, version, name) VALUES ('08', '0', 'Временное удостоверение, выданное взамен военного билета');

INSERT INTO docs (code, version, name) VALUES ('10', '0', 'Паспорт иностранного гражданина');

INSERT INTO docs (code, version, name) VALUES ('11', '0', 'Свидетельство о рассмотрении ходатайства о признании лица');

INSERT INTO docs (code, version, name) VALUES ('12', '0', 'Вид на жительство в Российской Федерации');

INSERT INTO docs (code, version, name) VALUES ('13', '0', 'Удостоверение беженца');

INSERT INTO docs (code, version, name) VALUES ('15', '0', 'Разрешение на временное проживание в Российской Федерации');

INSERT INTO docs (code, version, name) VALUES ('18', '0', 'Свидетельство о предоставлении временного убежища на территории');

INSERT INTO docs (code, version, name) VALUES ('21', '0', 'Паспорт гражданина Российской Федерации');

INSERT INTO docs (code, version, name) VALUES ('24', '0', 'Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO docs (code, version, name) VALUES ('91', '0', 'Инные документы');

INSERT INTO countries (code, version, name) VALUES ('643', '0', 'Российская Федерация');

INSERT INTO countries (code, version, name) VALUES ('380', '0', 'Украина');

