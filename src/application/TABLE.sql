CREATE TABLE FX_BOARD
(
	BOARD_ID NUMBER NOT NULL,
	BOARD_TITLE VARCHAR(100) NOT NULL,
	BOARD_WRITER VARCHAR(10) NOT NULL,
	BOARD_DATE DATE NOT NULL,
	BOARD_CONTENT LONG,
	CONSTRAINT PK_FX_BOARD PRIMARY KEY (BOARD_ID)
);


INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'aaa','me',SYSDATE,'AAA');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'bbb','me',SYSDATE,'BBB');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'ccc','me',SYSDATE,'CCC');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'ddd','me',SYSDATE,'DDD');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'eee','me',SYSDATE,'EEE');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'fff','me',SYSDATE,'FFF');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'ggg','me',SYSDATE,'GGG');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'hhh','me',SYSDATE,'HHH');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'iii','me',SYSDATE,'III');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'jjj','me',SYSDATE,'JJJ');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'kkk','me',SYSDATE,'KKK');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'lll','me',SYSDATE,'LLL');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'mmm','me',SYSDATE,'MMM');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'nnn','me',SYSDATE,'NNN');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'ooo','me',SYSDATE,'OOO');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'ppp','me',SYSDATE,'PPP');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'qqq','me',SYSDATE,'QQQ');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'rrr','me',SYSDATE,'RRR');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'sss','me',SYSDATE,'SSS');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'ttt','me',SYSDATE,'TTT');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'uuu','me',SYSDATE,'UUU');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'vvv','me',SYSDATE,'VVV');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'www','me',SYSDATE,'WWW');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'xxx','me',SYSDATE,'XXX');
INSERT INTO FX_BOARD (BOARD_ID, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)
VALUES ((SELECT NVL(MAX(BOARD_ID),0)+1 FROM FX_BOARD), 'yyy','me',SYSDATE,'YYY');