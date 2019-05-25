INSERT INTO country (version, code, name)
VALUES ('0', '643', 'Российская Федерация');

INSERT INTO country (version, code, name)
VALUES ('0', '380', 'Украина');

INSERT INTO organization (version, name, full_name, inn, kpp, address, phone, is_active)
VALUES ('0', 'Магнит', 'ЗАО "Тандер"', '2310031475', '997350001', 'Краснодар, ул. Солнечная, 15/5',
        '+7 (861) 200-01-01', 'true');

INSERT INTO organization (version, name, full_name, inn, kpp, address, phone, is_active)
VALUES ('0', 'Санги Стиль', 'ООО "Русский стиль-97"', '2310033521', '231001001', 'Краснодар, ул.им Щорса, д.50,',
        '+7 (861) 547-22-22', 'false');

INSERT INTO organization (version, name, full_name, inn, kpp, address, phone, is_active)
VALUES ('0', 'АШАН', 'ООО "АШАН"', '7703270067', '876543210', 'Сочи, улица Бабушкина, д. 9 ', '+7 (8622) 589-45-45',
        true);

INSERT INTO office (version, org_id, name, address, phone, is_active)
VALUES ('0', '1', 'Офис Магнит ГК', 'Краснодар', '+7(861) 456-00-00', 'true');

INSERT INTO office (version, org_id, name, address, phone, is_active)
VALUES ('0', '1', 'Офис Магнит Сочи', 'Сочи', '+7 (862) 657-55-56', 'true');

INSERT INTO office (version, org_id, name, address, phone, is_active)
VALUES ('0', '1', 'Офис Магнит Ростов', 'г. Ростов-на-Дону, ул. Малое Зеленое Кольцо, 5', '+7 (863)-333 25 -96',
        'true');

INSERT INTO office (version, org_id, name, address, phone, is_active)
VALUES ('0', '2', 'Офис Русский стиль Краснодар', 'г. Краснодар,ул.Уральская, 75', '+7 (861) 458-87-99', 'false');

INSERT INTO office (version, org_id, name, address, phone, is_active)
VALUES ('0', '2', 'Офис Русский стиль Самара', 'г. Самара, ул.Ленина, 3', '+7 (846) 458-87-99', 'false');

INSERT INTO office (version, org_id, name, address, phone, is_active)
VALUES ('0', '3', 'Офис АШАН ГК', 'г. Сочи, микрорайон Донская, ул. Новая Заря 7', '+7 (862) 455-33-99', 'true');

INSERT INTO user (version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id,
                  is_identified)
VALUES ('0', '1', 'Иван', 'Иванов', 'Иванович', 'Оператор', '+7 (789) 966-07-98', '1', 'true');

INSERT INTO user (version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id,
                  is_identified)
VALUES ('0', '2', 'Петр', 'Петров', 'Иванович', 'Шеф', '+7 (895) 956-09-96', '2', 'true');

INSERT INTO user (version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id,
                  is_identified)
VALUES ('0', '3', 'Николай', 'Петренко', 'Иванович', 'Рук.сектора', '+7 (548) 958-79-99', '1', 'true');

INSERT INTO user (version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id,
                  is_identified)
VALUES ('0', '4', 'Нина', 'Крылова', 'Сергеевна', 'Бухгалтер', '+7 (358) 958-79-99', '1', 'true');

INSERT INTO user (version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id,
                  is_identified)
VALUES ('0', '5', 'Нина', 'Крылова', 'Сергеевна', 'Бухгалтер', '+7 (358) 958-79-99', '1', 'true');

INSERT INTO user (version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id,
                  is_identified)
VALUES ('0', '5', 'Елена', 'Иванова', 'Сергеевна', 'Шеф', '+7 (358) 958-79-99', '2', 'true');

INSERT INTO user (version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id,
                  is_identified)
VALUES ('0', '6', 'Александр', 'Уткин', 'Валериевич', 'Администратор', '+7 (099) 966-76-66', '1', 'true');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '03', 'Свидетельство о рождении');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '07', 'Военный билет');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '08', 'Временное удостоверение, выданное взамен военного билета');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '10', 'Паспорт иностранного гражданина');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '11', 'Свидетельство о рассмотрении ходатайства о признании лица');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '12', 'Вид на жительство в Российской Федерации');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '13', 'Удостоверение беженца');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '15', 'Разрешение на временное проживание в Российской Федерации');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '18', 'Свидетельство о предоставлении временного убежища на территории');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '21', 'Паспорт гражданина Российской Федерации');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '24', 'Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO doc_type (version, doc_code, name)
VALUES ('0', '91', 'Инные документы');

INSERT INTO doc(version, user_id, id_code_type, number, date)
VALUES (0, '1', '10', '0308458156', '2018-07-30');
INSERT INTO doc(version, user_id, id_code_type, number, date)
VALUES (0, '1', '10', '0308458156', '2010-07-30');
INSERT INTO doc (version, user_id, id_code_type, number, date)
VALUES (0, '1', '10', '03085478', '2015-07-30');
INSERT INTO doc (version, user_id, id_code_type, number, date)
VALUES (0, '1', '10', '030845778', '2016-07-30');
INSERT INTO doc (version, user_id, id_code_type, number, date)
VALUES (0, '1', '10', '0308455556', '2017-07-30');
INSERT INTO doc (version, user_id, id_code_type, number, date)
VALUES (0, '1', '10', '030845546', '2014-07-30');
INSERT INTO doc (version, user_id, id_code_type, number, date)
VALUES (0, '1', '10', '030877777', '2011-07-30');

