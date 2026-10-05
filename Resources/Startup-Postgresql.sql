
INSERT INTO security (approve_invoice, create_clients, create_person, edit_clients) VALUES (true, true, true, true);

INSERT INTO person (first_name, last_name, login_name, password, current, administrator, security_id) VALUES ('Ted', 'Pet', 'tedpet', '3368', true, true, 1);


INSERT INTO security (approve_invoice, create_clients, create_person, edit_clients) VALUES (false, true, true, true);

INSERT INTO person (first_name, last_name, login_name, password, current, administrator, security_id) VALUES ('Bill', 'Simpson', 'bilsim', '4004', true, false, 2);

INSERT INTO security (approve_invoice, create_clients, create_person, edit_clients) VALUES (false, false, true, true);

INSERT INTO person (first_name, last_name, login_name, password, current, administrator, security_id) VALUES ('Sally', 'Anne', 'salann', '1234', true, false, 3);

INSERT INTO vendor (vendor_name, password, current, login_name, person_id) VALUES ('A&P Tea Co.', '1234', true, 'billy', 3);
INSERT INTO vendor (vendor_name, password, current, login_name, person_id) VALUES ('Number Two', '3368', true, 'no2', 1);
INSERT INTO vendor (vendor_name, password, current, login_name, person_id) VALUES ('Number three', '3368', true, 'no3', 3);


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

INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxWidth', '300px', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxHeight', '100px', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxBlocks', '30', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxTableRows', '10', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.sheet.maxWidth', '100%', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.sheet.maxRows', '20', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.sheet.maxCols', '10', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.pdf.dpi', '100', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.pdf.maxWidth', '100%', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.pdf.maxHeight', '100px', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.image.maxWidth', '100%', '1');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.image.maxHeight', '300px', '1');

INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxWidth', '300px',  '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxHeight', '100px', '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxBlocks', '30',    '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxTableRows', '10', '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.sheet.maxWidth', '100%',  '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.sheet.maxRows', '20',     '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.sheet.maxCols', '10',     '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.pdf.dpi', '100',          '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.pdf.maxWidth', '100%',    '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.pdf.maxHeight', '100px',  '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.image.maxWidth', '100%',  '2');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.image.maxHeight', '300px', '2');

INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxWidth', '300px',  '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxHeight', '100px', '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxBlocks', '30',    '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.word.maxTableRows', '10', '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.sheet.maxWidth', '100%',  '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.sheet.maxRows', '20',     '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.sheet.maxCols', '10',     '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.pdf.dpi', '100',          '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.pdf.maxWidth', '100%',    '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.pdf.maxHeight', '100px',  '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.image.maxWidth', '100%',  '3');
INSERT INTO preference (name, value, person_id) VALUES ('attachment.image.maxHeight', '300px', '3');
