INSERT INTO User (id, login, name, password, securityLevel_id) VALUES (1, 'petrovna', 'Petrovna', '1234', 1);
INSERT INTO User (id, login, name, password, securityLevel_id) VALUES (2, 'admin', 'Admin', 'admin', 4);
INSERT INTO User (id, login, name, password, securityLevel_id) VALUES (3, 'user', 'User', 'user', 2);
INSERT INTO User (id, login, name, password, securityLevel_id) VALUES (4, 'usersec', 'Secret user', 'user', 3);

INSERT INTO UserGroup (id, name) VALUES (1, 'all');
INSERT INTO UserGroup (id, name) VALUES (2, 'work');
INSERT INTO UserGroup (id, name) VALUES (3, 'staff');
INSERT INTO UserGroup (id, name) VALUES (4, 'administration');

INSERT INTO UserGroup_User (UserGroup_id, users_id) VALUES (1, 1);
INSERT INTO UserGroup_User (UserGroup_id, users_id) VALUES (1, 2);
INSERT INTO UserGroup_User (UserGroup_id, users_id) VALUES (1, 3);
INSERT INTO UserGroup_User (UserGroup_id, users_id) VALUES (1, 4);

INSERT INTO UserGroup_User (UserGroup_id, users_id) VALUES (2, 2);
INSERT INTO UserGroup_User (UserGroup_id, users_id) VALUES (2, 3);
INSERT INTO UserGroup_User (UserGroup_id, users_id) VALUES (2, 4);

INSERT INTO UserGroup_User (UserGroup_id, users_id) VALUES (3, 1);

INSERT INTO UserGroup_User (UserGroup_id, users_id) VALUES (4, 2);