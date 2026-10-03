
INSERT INTO security (approve_invoice, create_clients, create_person, edit_clients) VALUES (true, true, true, true);

INSERT INTO person (first_name, last_name, login_name, password, current, security_id) VALUES ('Ted', 'Pet', 'tedpet', '3368', true, 1);


INSERT INTO security (approve_invoice, create_clients, create_person, edit_clients) VALUES (false, true, true, true);

INSERT INTO person (first_name, last_name, login_name, password, current, security_id) VALUES ('Bill', 'Simpson', 'bilsim', '4004', true, 2);

INSERT INTO security (approve_invoice, create_clients, create_person, edit_clients) VALUES (false, false, true, true);

INSERT INTO person (first_name, last_name, login_name, password, current, security_id) VALUES ('Sally', 'Anne', 'salann', '1234', true, 2);

INSERT INTO vendor (vendor_name, password, current, person_id) VALUES ('A&P Tea Co.', '1234', true, 3);


INSERT INTO preference (name, value) VALUES ('attachment.word.maxWidth', '300px');
INSERT INTO preference (name, value) VALUES ('attachment.word.maxHeight', '100px');
INSERT INTO preference (name, value) VALUES ('attachment.word.maxBlocks', '30');
INSERT INTO preference (name, value) VALUES ('attachment.word.maxTableRows', '10');
INSERT INTO preference (name, value) VALUES ('attachment.sheet.maxWidth', '100%');
INSERT INTO preference (name, value) VALUES ('attachment.sheet.maxRows', '20');
INSERT INTO preference (name, value) VALUES ('attachment.sheet.maxCols', '10');
INSERT INTO preference (name, value) VALUES ('attachment.pdf.dpi', '100');
INSERT INTO preference (name, value) VALUES ('attachment.pdf.maxWidth', '100%');
INSERT INTO preference (name, value) VALUES ('attachment.pdf.maxHeight', '100px');
INSERT INTO preference (name, value) VALUES ('attachment.image.maxWidth', '100%');
INSERT INTO preference (name, value) VALUES ('attachment.image.maxHeight', '300px');

