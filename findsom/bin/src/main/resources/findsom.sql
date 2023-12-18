
DROP SEQUENCE Sequence_commentID;

CREATE SEQUENCE Sequence_commentID
	INCREMENT BY 1
	START WITH 1;

DROP SEQUENCE Sequence_findPostID;

CREATE SEQUENCE Sequence_findPostID
	INCREMENT BY 2
	START WITH 1;

DROP SEQUENCE Sequence_freePostID;

CREATE SEQUENCE Sequence_freePostID
	INCREMENT BY 2
	START WITH 2;

DROP SEQUENCE Sequence_messageID;

CREATE SEQUENCE Sequence_messageID
	INCREMENT BY 1
	START WITH 1;

DROP SEQUENCE Sequence_notificationID;

CREATE SEQUENCE Sequence_notificationID
	INCREMENT BY 1
	START WITH 1;

DROP SEQUENCE Sequence_userID;

CREATE SEQUENCE Sequence_userID
	INCREMENT BY 1
	START WITH 1;

ALTER TABLE NotiInfo
DROP CONSTRAINT R_22;

ALTER TABLE NotiInfo
DROP CONSTRAINT R_29;

ALTER TABLE NotiInfo
DROP CONSTRAINT R_41;

ALTER TABLE NotiInfo
DROP CONSTRAINT R_43;

ALTER TABLE NotiInfo
DROP CONSTRAINT R_25;

ALTER TABLE MessageInfo
DROP CONSTRAINT R_27;

ALTER TABLE MessageInfo
DROP CONSTRAINT R_28;

ALTER TABLE MessageInfo
DROP CONSTRAINT R_32;

ALTER TABLE MessageInfo
DROP CONSTRAINT R_33;

ALTER TABLE CommentInfo
DROP CONSTRAINT R_11;

ALTER TABLE CommentInfo
DROP CONSTRAINT R_34;

ALTER TABLE CommentInfo
DROP CONSTRAINT R_35;

ALTER TABLE FreeBoardPost
DROP CONSTRAINT R_17;

ALTER TABLE FindBoardPost
DROP CONSTRAINT R_9;

ALTER TABLE AuthenticationFile
DROP CONSTRAINT R_7;

ALTER TABLE LifePatterns
DROP CONSTRAINT R_6;

ALTER TABLE UserInfo
DROP CONSTRAINT R_20;

ALTER TABLE NotiInfo
DROP PRIMARY KEY CASCADE  DROP INDEX;

ALTER TABLE MessageInfo
DROP PRIMARY KEY CASCADE  DROP INDEX;

ALTER TABLE CommentInfo
DROP PRIMARY KEY CASCADE  DROP INDEX;

ALTER TABLE FreeBoardPost
DROP PRIMARY KEY CASCADE  DROP INDEX;

ALTER TABLE FindBoardPost
DROP PRIMARY KEY CASCADE  DROP INDEX;

ALTER TABLE AuthenticationFile
DROP PRIMARY KEY CASCADE  DROP INDEX;

ALTER TABLE LifePatterns
DROP PRIMARY KEY CASCADE  DROP INDEX;

ALTER TABLE UserInfo
DROP PRIMARY KEY CASCADE  DROP INDEX;

ALTER TABLE Room
DROP PRIMARY KEY CASCADE  DROP INDEX;

DROP INDEX XPKNotificationBox;

DROP INDEX XIF2NotificationBox;

DROP INDEX XIF7NotificationBox;

DROP INDEX XIF8NotificationBox;

DROP INDEX XIF9NotificationBox;

DROP INDEX XIF5NotificationBox;

DROP TABLE NotiInfo CASCADE CONSTRAINTS PURGE;

DROP INDEX XPKMessageInfo;

DROP INDEX XIF3MessageInfo;

DROP INDEX XIF4MessageInfo;

DROP INDEX XIF5MessageInfo;

DROP INDEX XIF6MessageInfo;

DROP TABLE MessageInfo CASCADE CONSTRAINTS PURGE;

DROP INDEX XPKComment;

DROP INDEX XIF2Comment;

DROP INDEX XIF3Comment;

DROP INDEX XIF4Comment;

DROP TABLE CommentInfo CASCADE CONSTRAINTS PURGE;

DROP INDEX XPKFreeBoardPost;

DROP INDEX XIF1FreeBoardPost;

DROP TABLE FreeBoardPost CASCADE CONSTRAINTS PURGE;

DROP INDEX XPKFindBoardPost;

DROP INDEX XIF1FindBoardPost;

DROP TABLE FindBoardPost CASCADE CONSTRAINTS PURGE;

DROP INDEX XPKAuthenticationFile;

DROP INDEX XIF1AuthenticationFile;

DROP TABLE AuthenticationFile CASCADE CONSTRAINTS PURGE;

DROP INDEX XPKLifePatterns;

DROP INDEX XIF1LifePatterns;

DROP TABLE LifePatterns CASCADE CONSTRAINTS PURGE;

DROP INDEX XPKUserInfo;

DROP INDEX XIF1UserInfo;

DROP TABLE UserInfo CASCADE CONSTRAINTS PURGE;

DROP INDEX XPKNoiseInfo;

DROP TABLE Room CASCADE CONSTRAINTS PURGE;

CREATE TABLE CommentInfo
(
	commentID            NUMBER(4)  NOT NULL ,
	content              VARCHAR2(100)  NULL ,
	commentDate          DATE  NULL ,
	userID               VARCHAR2(100)  NOT NULL ,
	findpostID           NUMBER(4)  NULL ,
	freepostID           NUMBER(4)  NULL 
);

CREATE UNIQUE INDEX XPKComment ON CommentInfo
(commentID   ASC);

ALTER TABLE CommentInfo
	ADD CONSTRAINT  XPKComment PRIMARY KEY (commentID);

CREATE TABLE FindBoardPost
(
	findpostID           NUMBER(4)  NOT NULL ,
	isAnonymous          VARCHAR2(10)  NULL ,
	title                VARCHAR2(20)  NULL ,
	prefer               VARCHAR2(100)  NULL ,
	mycontent            VARCHAR2(500)  NULL ,
	matecontent          VARCHAR2(500)  NULL ,
    userID               VARCHAR2(100)  NOT NULL 
);

CREATE UNIQUE INDEX XPKFindBoardPost ON FindBoardPost
(findpostID   ASC);

ALTER TABLE FindBoardPost
	ADD CONSTRAINT  XPKFindBoardPost PRIMARY KEY (findpostID);

CREATE TABLE FreeBoardPost
(
	freepostID           NUMBER(4)  NOT NULL ,
	title                VARCHAR2(50)  NULL ,
	userID               VARCHAR2(100)  NOT NULL ,
	isAnonymous          VARCHAR(10)  NULL ,
	content              VARCHAR2(500)  NULL ,
	category             VARCHAR2(10)  NULL 
);

CREATE UNIQUE INDEX XPKFreeBoardPost ON FreeBoardPost
(freepostID   ASC);

ALTER TABLE FreeBoardPost
	ADD CONSTRAINT  XPKFreeBoardPost PRIMARY KEY (freepostID);

CREATE TABLE MessageInfo
(
	messageID            NUMBER(4)  NOT NULL ,
	messageText          VARCHAR2(600)  NULL ,
	createAt             DATE  NULL ,
	senderID             VARCHAR2(100)  NOT NULL ,
	receiverID           VARCHAR2(100)  NOT NULL ,
	freepostID           NUMBER(4)  NULL ,
	findpostID           NUMBER(4)  NULL 
);

CREATE UNIQUE INDEX XPKMessageInfo ON MessageInfo
(messageID   ASC);

ALTER TABLE MessageInfo
	ADD CONSTRAINT  XPKMessageInfo PRIMARY KEY (messageID);

CREATE TABLE Room
(
	roomInfo             VARCHAR2(18)  NOT NULL ,
	count                VARCHAR2(18)  NULL 
);

CREATE UNIQUE INDEX XPKNoiseInfo ON Room
(roomInfo   ASC);

ALTER TABLE Room
	ADD CONSTRAINT  XPKNoiseInfo PRIMARY KEY (roomInfo);

CREATE TABLE UserInfo
(
   userID               VARCHAR2(18)  NOT NULL ,
   email                VARCHAR2(30)  NULL ,
   password             VARCHAR2(20)  NULL ,
   phone                CHAR(18)  NULL ,
   name                 VARCHAR2(50)  NULL ,
   nickname             VARCHAR2(10)  NULL ,
   authentication       VARCHAR2(20)  DEFAULT 0  NULL ,
   isRecruite           VARCHAR2(10)  NULL ,
   roomInfo             VARCHAR2(18)  NULL 
);

CREATE UNIQUE INDEX XPKUserInfo ON UserInfo
(userID   ASC);

ALTER TABLE UserInfo
	ADD CONSTRAINT  XPKUserInfo PRIMARY KEY (userID);

CREATE TABLE AuthenticationFile
(
	authenticationFile   BLOB  NULL ,
	userID               VARCHAR2(100)  NOT NULL 
);

CREATE UNIQUE INDEX XPKAuthenticationFile ON AuthenticationFile
(userID   ASC);

ALTER TABLE AuthenticationFile
	ADD CONSTRAINT  XPKAuthenticationFile PRIMARY KEY (userID);
    
CREATE TABLE LifePatterns
(
   userID               VARCHAR2(18)  NOT NULL ,
   lifePattern          VARCHAR2(20)  NULL 
);

CREATE TABLE NotiInfo
(
	notificationID       NUMBER(4)  NOT NULL ,
	notiType             VARCHAR2(18)  NULL ,
	notiTypeID           VARCHAR2(18)  NULL ,
	isChecked            VARCHAR2(2)  NULL ,
	commentID            NUMBER(4)  NULL ,
	messageID            NUMBER(4)  NULL ,
	postID               NUMBER(4)  NULL ,
	receiverID           VARCHAR2(100)  NOT NULL ,
	senderID             VARCHAR2(100)  NOT NULL 
);

CREATE UNIQUE INDEX XPKNotificationBox ON NotiInfo
(notificationID   ASC);

ALTER TABLE NotiInfo
	ADD CONSTRAINT  XPKNotificationBox PRIMARY KEY (notificationID);

ALTER TABLE CommentInfo
	ADD (
CONSTRAINT R_11 FOREIGN KEY (userID) REFERENCES UserInfo (userID));

ALTER TABLE CommentInfo
	ADD (
CONSTRAINT R_34 FOREIGN KEY (findpostID) REFERENCES FindBoardPost (findpostID) ON DELETE SET NULL);

ALTER TABLE CommentInfo
	ADD (
CONSTRAINT R_35 FOREIGN KEY (freepostID) REFERENCES FreeBoardPost (freepostID) ON DELETE SET NULL);

ALTER TABLE FindBoardPost
	ADD (
CONSTRAINT R_9 FOREIGN KEY (userID) REFERENCES UserInfo (userID));

ALTER TABLE FreeBoardPost
	ADD (
CONSTRAINT R_17 FOREIGN KEY (userID) REFERENCES UserInfo (userID));

ALTER TABLE MessageInfo
	ADD (
CONSTRAINT R_27 FOREIGN KEY (senderID) REFERENCES UserInfo (userID));

ALTER TABLE MessageInfo
	ADD (
CONSTRAINT R_28 FOREIGN KEY (receiverID) REFERENCES UserInfo (userID));

ALTER TABLE MessageInfo
	ADD (
CONSTRAINT R_32 FOREIGN KEY (freepostID) REFERENCES FreeBoardPost (freepostID) ON DELETE SET NULL);

ALTER TABLE MessageInfo
	ADD (
CONSTRAINT R_33 FOREIGN KEY (findpostID) REFERENCES FindBoardPost (findpostID) ON DELETE SET NULL);

ALTER TABLE UserInfo
	ADD (
CONSTRAINT R_20 FOREIGN KEY (roomInfo) REFERENCES Room (roomInfo) ON DELETE SET NULL);

ALTER TABLE AuthenticationFile
	ADD (
CONSTRAINT R_7 FOREIGN KEY (userID) REFERENCES UserInfo (userID));

ALTER TABLE LifePatterns
	ADD (
CONSTRAINT R_6 FOREIGN KEY (userID) REFERENCES UserInfo (userID));

ALTER TABLE NotiInfo
	ADD (
CONSTRAINT R_22 FOREIGN KEY (commentID) REFERENCES CommentInfo (commentID) ON DELETE SET NULL);

ALTER TABLE NotiInfo
	ADD (
CONSTRAINT R_29 FOREIGN KEY (postID) REFERENCES FindBoardPost (findpostID) ON DELETE SET NULL);

ALTER TABLE NotiInfo
	ADD (
CONSTRAINT R_41 FOREIGN KEY (receiverID) REFERENCES UserInfo (userID));

ALTER TABLE NotiInfo
	ADD (
CONSTRAINT R_43 FOREIGN KEY (senderID) REFERENCES UserInfo (userID));

ALTER TABLE NotiInfo
	ADD (
CONSTRAINT R_25 FOREIGN KEY (messageID) REFERENCES MessageInfo (messageID) ON DELETE SET NULL);
