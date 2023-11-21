--https://learn.microsoft.com/en-us/office/troubleshoot/access/database-normalization-description
CREATE TABLE IF NOT EXISTS user (
	user_id VARCHAR(320) NOT NULL,
	password VARCHAR(256) NOT NULL,
	company int NOT NULL,
	school VARCHAR(100) NOT NULL,
	name VARCHAR(10) NOT NULL,
	birth_date VARCHAR(8) NOT NULL,
	gender VARCHAR(1) NOT NULL,
	address VARCHAR(50) NOT NULL,
	height int NOT NULL,
	age_range_start int NOT NULL,
	age_range_end int NOT NULL,
    drinking int NOT NULL,
    smoking int NOT NULL,
    personality_type int NOT NULL,
    workout int NOT NULL,
    communication_style int NOT NULL,
	status int NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT user_user_id PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS user_photo (
	id BIGINT AUTO_INCREMENT,
	user_id VARCHAR(320) NOT NULL,
	photo VARCHAR(256) NOT NULL, -- can change data type and length
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT user_photo_id PRIMARY KEY (id),
	CONSTRAINT user_photo_user_id FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS user_interest (
	id BIGINT AUTO_INCREMENT,
	user_id VARCHAR(320) NOT NULL,
	interest int NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT user_interest_id PRIMARY KEY (id),
	CONSTRAINT user_interest_user_id FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS authentication (
    id BIGINT AUTO_INCREMENT,
	user_id VARCHAR(320) NOT NULL,
	auth_code VARCHAR(6) NOT NULL,
	type VARCHAR(15) NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT authentication_id PRIMARY KEY (id),
    CONSTRAINT authentication_user_id FOREIGN KEY (user_id) REFERENCES user(user_id)
);
CREATE TABLE IF NOT EXISTS purchase_history (
    id BIGINT AUTO_INCREMENT,
	user_id VARCHAR(320) NOT NULL,
	product VARCHAR(50) NOT NULL,
	price FLOAT NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT purchase_history_id PRIMARY KEY (id),
    CONSTRAINT purchase_history_user_id FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS inbox (
    id BIGINT AUTO_INCREMENT,
	last_message VARCHAR(300) NOT NULL,
	last_sent_id VARCHAR(320) NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT inbox_id PRIMARY KEY (id),
	CONSTRAINT inbox_last_sent_id FOREIGN KEY (last_sent_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS inbox_participant (
    id BIGINT AUTO_INCREMENT,
	user_id VARCHAR(320) NOT NULL,
	inbox_id BIGINT NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT inbox_participant_id PRIMARY KEY (id),
	CONSTRAINT inbox_participant_user_id FOREIGN KEY (user_id) REFERENCES user(user_id),
	CONSTRAINT inbox_participant_inbox_id FOREIGN KEY (inbox_id) REFERENCES inbox(id)
);

CREATE TABLE IF NOT EXISTS message (
    id BIGINT AUTO_INCREMENT,
	user_id VARCHAR(320) NOT NULL,
	inbox_id BIGINT NOT NULL,
	content VARCHAR(300) NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT message_id PRIMARY KEY (id),
    CONSTRAINT message_user_id FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT message_inbox_id FOREIGN KEY (inbox_id) REFERENCES inbox(id)
);

CREATE TABLE IF NOT EXISTS message_info (
    id BIGINT AUTO_INCREMENT,
    message_id BIGINT NOT NULL,
	receiver_id VARCHAR(320) NOT NULL,
	status CHAR NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT message_info_id PRIMARY KEY (id),
    CONSTRAINT message_info_sender_id FOREIGN KEY (sender_id) REFERENCES user(user_id),
    CONSTRAINT message_info_receiver_id FOREIGN KEY (receiver_id) REFERENCES user(user_id),
    CONSTRAINT message_info_message_id FOREIGN KEY (message_id) REFERENCES message(id)
);
CREATE TABLE IF NOT EXISTS fcm_token (
    id BIGINT AUTO_INCREMENT,
  	user_id VARCHAR(320) NOT NULL,
	fcm_token VARCHAR(256) NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT fcm_token_id PRIMARY KEY (id),
	CONSTRAINT fcm_token_user_id FOREIGN KEY (user_id) REFERENCES user(user_id)
);
/*
INSERT INTO dating.user
VALUES
('car22332@gmail.com', '393145b754601dda96e5e2847bad56d9', 0, '서경대학교'
, '김주영', '19950223', 'M', '경기도 김포시', '178', 20, 30, 0, 0, 0, 0, 0
, 1, 2, 3, 4, 5, 'photo1', 'photo2', 'photo3', 'photo4', 'photo5', 'photo6'
, 0, NOW(), NOW());
*/

