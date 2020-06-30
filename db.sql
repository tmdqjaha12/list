# brX

# 캐릭터SET 설정
SET NAMES utf8mb4;

# DB 생성
DROP DATABASE IF EXISTS site36;
CREATE DATABASE site36;
USE site36;

# 카테고리
CREATE TABLE cateItem (
    id INT (10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    `name` CHAR(100) NOT NULL UNIQUE
);

# 카테고리 아이템 4~5개 

INSERT INTO cateItem
SET regDate = NOW(),
`name` = 'JAVA';

INSERT INTO cateItem
SET regDate = NOW(),
`name` = 'JavaScript';

INSERT INTO cateItem
SET regDate = NOW(),
`name` = 'DB';

INSERT INTO cateItem
SET regDate = NOW(),
`name` = 'Spring';

INSERT INTO cateItem
SET regDate = NOW(),
`name` = 'Algorithm';


# 카테고리 확인
SELECT *
FROM cateItem;

# 게시물 테이블 생성
DROP TABLE IF EXISTS article;
CREATE TABLE article (
    id INT (10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    cateItemId INT(10) UNSIGNED NOT NULL,
    displayStatus TINYINT(1) UNSIGNED NOT NULL,
    `title` CHAR(200) NOT NULL,
    `body` TEXT NOT NULL
);

# 게시물 추가
INSERT INTO article SET
regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
title = '자바를 시작합니다.',
`body` = '';

# 게시물 테이블 확인
SELECT *
FROM article;