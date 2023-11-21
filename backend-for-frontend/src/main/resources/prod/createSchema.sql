--DROP TABLE user;
CREATE TABLE IF NOT EXISTS user (
	--id BIGINT AUTO_INCREMENT,
	user_id VARCHAR(320) NOT NULL,
	password VARCHAR(256) NOT NULL,
	company VARCHAR(50) NOT NULL,
	school VARCHAR(100) NOT NULL,
	name VARCHAR(10) NOT NULL,
	birth_date VARCHAR(8) NOT NULL,
	gender VARCHAR(1) NOT NULL,
	address VARCHAR(50) NOT NULL,
	height int NOT NULL,
	drinking_capacity VARCHAR(15) NOT NULL,
	smoking VARCHAR(1) NOT NULL,
	mbti VARCHAR(4) NOT NULL,
	age_range_start int NOT NULL,
	age_range_end int NOT NULL,
	status VARCHAR(10) NOT NULL,
	photo1 VARCHAR(256) NOT NULL,
	photo2 VARCHAR(256) NOT NULL,
	photo3 VARCHAR(256) NOT NULL,
	photo4 VARCHAR(256) NOT NULL,
	photo5 VARCHAR(256) NOT NULL,
	photo6 VARCHAR(256) NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT user_user_id PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS authentication (
    id BIGINT AUTO_INCREMENT,
	user_id VARCHAR(320) NOT NULL,
	auth_code VARCHAR(6) NOT NULL,
	email_type VARCHAR(15) NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT authentication_id PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS purchase_history (
    id BIGINT AUTO_INCREMENT,
	user_id VARCHAR(320) NOT NULL,
	product VARCHAR(50) NOT NULL,
	price FLOAT NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT purchase_history_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS company (
	email_domain VARCHAR(255) NOT NULL,
	company_name VARCHAR(50) NOT NULL,
	CONSTRAINT company_email_domain PRIMARY KEY (email_domain)
);

CREATE TABLE IF NOT EXISTS message (
    id BIGINT AUTO_INCREMENT,
	sender_id VARCHAR(320) NOT NULL,
	receiver_id VARCHAR(320) NOT NULL,
	content VARCHAR(300) NOT NULL,
	status VARCHAR(15) NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT message_id PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS fcm_token (
	user_id VARCHAR(320) NOT NULL,
	fcm_token VARCHAR(320) NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL,
	CONSTRAINT fcm_token_id PRIMARY KEY (user_id)
);
/*
INSERT INTO dating.user
VALUES
('car22332@gmail.com', '393145b754601dda96e5e2847bad56d9', '토스', '서경대학교'
, '김주영1', '19950223', 'M', '경기도 김포시', 178, '1'
, 'N', 'INFP', 20, 30, 'APPROVED', 'photo1', 'photo2', 'photo3', 'photo4'
, 'photo5', 'photo6', NOW(), NOW()),
('car223321@gmail.com', '393145b754601dda96e5e2847bad56d9', '토스', '서경대학교'
, '김주영2', '19950223', 'M', '경기도 김포시', 178, '1'
, 'N', 'INFP', 20, 30, 'APPROVED', 'photo1', 'photo2', 'photo3', 'photo4'
, 'photo5', 'photo6', NOW(), NOW()),
('car223322@gmail.com', '393145b754601dda96e5e2847bad56d9', '토스', '서경대학교'
, '김주영3', '19950223', 'M', '경기도 김포시', 178, '1'
, 'N', 'INFP', 20, 30, 'APPROVED', 'photo1', 'photo2', 'photo3', 'photo4'
, 'photo5', 'photo6', NOW(), NOW()),
('car2233223@gmail.com', '393145b754601dda96e5e2847bad56d9', '토스', '서경대학교'
, '김주영4', '19950223', 'M', '경기도 김포시', 178, '1'
, 'N', 'INFP', 20, 30, 'APPROVED', 'photo1', 'photo2', 'photo3', 'photo4'
, 'photo5', 'photo6', NOW(), NOW()),

('car1@gmail.com', '393145b754601dda96e5e2847bad56d9', '토스', '서경대학교'
, '여자1', '19960223', 'F', '경기도 김포시', 178, '1'
, 'N', 'INFP', 20, 30, 'APPROVED', 'photo1', 'photo2', 'photo3', 'photo4'
, 'photo5', 'photo6', NOW(), NOW()),
('car2@gmail.com', '393145b754601dda96e5e2847bad56d9', '토스', '서경대학교'
, '여자2', '19970223', 'F', '경기도 김포시', 178, '1'
, 'N', 'INFP', 20, 30, 'APPROVED', 'photo1', 'photo2', 'photo3', 'photo4'
, 'photo5', 'photo6', NOW(), NOW()),
('car3@gmail.com', '393145b754601dda96e5e2847bad56d9', '토스', '서경대학교'
, '여자3', '19980223', 'F', '경기도 김포시', 178, '1'
, 'N', 'INFP', 20, 30, 'APPROVED', 'photo1', 'photo2', 'photo3', 'photo4'
, 'photo5', 'photo6', NOW(), NOW()),
('car4@gmail.com', '393145b754601dda96e5e2847bad56d9', '토스', '서경대학교'
, '여자4', '19990223', 'F', '경기도 김포시', 178, '1'
, 'N', 'INFP', 20, 30, 'APPROVED', 'photo1', 'photo2', 'photo3', 'photo4'
, 'photo5', 'photo6', NOW(), NOW())
*/
/*

INSERT INTO company
VALUES('navercorp.com', '네이버')
,('gmarket.com', '지마켓')
,('coupang.com', '쿠팡')
*/