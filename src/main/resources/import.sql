-- INSERT INTO am_user_privilege(user_id, role_id) VALUES(2,1);
INSERT INTO users(username, password) VALUES('Intenso', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.');
INSERT INTO users(username, password) VALUES('Rafix', 'password');

INSERT INTO roles(role, user_id) VALUES('US_ROLE_USER', 1)
INSERT INTO roles(role, user_id) VALUES('US_ROLE_ADMIN', 1)

INSERT INTO roles(role, user_id) VALUES('US_ROLE_USER', 2
)