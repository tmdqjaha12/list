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
cateItemId = 5,
displayStatus = 1,
title = '알고리즘을 시작합니다.',
`body` = '';

# 게시물 테이블 확인
SELECT *
FROM article;

# 
SELECT * 
FROM article
WHERE displayStatus = 1 AND cateItemId = 2
ORDER BY id 
DESC LIMIT 0, 5;


SELECT * 
FROM article
WHERE 1
AND title = '자바를';
AND displayStatus = 1;

SELECT * 
FROM article 
WHERE `title`
LIKE '%자바%'; OR '%알고%'; // (전방일치) 
> SELECT * FROM sample WHERE TEXT LIKE '%SQL%'; // (중간일치) 
https://gmlwjd9405.github.io/2019/04/25/db-SQL-select.html
# page
SELECT COUNT(*) FROM article WHERE cateItemId = 1;
# int itemsInAPage = 5; //한 페이지에 나오는 게시물 수
# int LimitFrom = (page - 1) * itemsInAPage;
# totalPage = Math.ceil((double)totalCount / itemsInAPage);
# <a href="${pageContext.request.contextPath}/s/article?/cateItemId={$param.cateItemId}&page=<%=i%>