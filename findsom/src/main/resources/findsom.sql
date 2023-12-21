
CREATE SEQUENCE Sequence_commentID
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE Sequence_findPostID
	INCREMENT BY 2
	START WITH 1;

CREATE SEQUENCE Sequence_freePostID
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE Sequence_messageID
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE Sequence_notificationID
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE Sequence_userID
	INCREMENT BY 1
	START WITH 1;

CREATE TABLE Comment
(
	commentID            VARCHAR2(18)  NOT NULL ,
	content              VARCHAR2(100)  NULL ,
	commentDate          DATE  NULL ,
<<<<<<< HEAD
	userID               VARCHAR2(18)  NOT NULL ,
	postID               VARCHAR2(18)  NULL 
=======
	userID               VARCHAR2(100)  NOT NULL ,
	findpostID           NUMBER(4)  NULL ,
	freepostID           NUMBER(4)  NULL 
>>>>>>> origin/develop
);

CREATE UNIQUE INDEX XPKComment ON Comment
(commentID   ASC);

ALTER TABLE Comment
	ADD CONSTRAINT  XPKComment PRIMARY KEY (commentID);

CREATE TABLE FindBoardPost
(
<<<<<<< HEAD
	postID               VARCHAR2(18)  NOT NULL ,
	isAnonymous          VARCHAR2(1)  NULL ,
	title                VARCHAR2(20)  NULL ,
	prefer               VARCHAR2(100)  NULL ,
	userID               VARCHAR2(18)  NOT NULL ,
	content              VARCHAR2(500)  NULL 
=======
	findpostID           NUMBER(4)  NOT NULL ,
	isAnonymous          VARCHAR2(10)  NULL ,
	title                VARCHAR2(20)  NULL ,
	prefer               VARCHAR2(100)  NULL ,
	userID               VARCHAR2(100)  NOT NULL ,
	mycontent            VARCHAR2(500)  NULL ,
	matecontent          VARCHAR2(500)  NULL 
>>>>>>> origin/develop
);

CREATE UNIQUE INDEX XPKFindBoardPost ON FindBoardPost
(postID   ASC);

ALTER TABLE FindBoardPost
	ADD CONSTRAINT  XPKFindBoardPost PRIMARY KEY (postID);

CREATE TABLE FreeBoardPost
(
	postID               VARCHAR2(18)  NOT NULL ,
	title                VARCHAR2(50)  NULL ,
<<<<<<< HEAD
	userID               VARCHAR2(18)  NOT NULL ,
	isAnonymous          VARCHAR(1)  NULL ,
	contents             VARCHAR2(500)  NULL ,
=======
	userID               VARCHAR2(100)  NOT NULL ,
	isAnonymous          VARCHAR(10)  NULL ,
	content              VARCHAR2(500)  NULL ,
>>>>>>> origin/develop
	category             VARCHAR2(10)  NULL 
);

CREATE UNIQUE INDEX XPKFreeBoardPost ON FreeBoardPost
(postID   ASC);

ALTER TABLE FreeBoardPost
	ADD CONSTRAINT  XPKFreeBoardPost PRIMARY KEY (postID);

CREATE TABLE MessageInfo
(
	messageID            VARCHAR2(18)  NOT NULL ,
	messageText          VARCHAR2(600)  NULL ,
	createAt             DATE  NULL ,
	recognizeID          VARCHAR2(18)  NULL ,
<<<<<<< HEAD
	senderID             VARCHAR2(18)  NOT NULL ,
	receiverID           VARCHAR2(18)  NOT NULL ,
	postID               VARCHAR2(18)  NULL 
=======
	senderID             VARCHAR2(100)  NOT NULL ,
	receiverID           VARCHAR2(100)  NOT NULL ,
	freepostID           NUMBER(4)  NULL ,
	findpostID           NUMBER(4)  NULL 
>>>>>>> origin/develop
);

CREATE UNIQUE INDEX XPKMessageInfo ON MessageInfo
(messageID   ASC);

ALTER TABLE MessageInfo
	ADD CONSTRAINT  XPKMessageInfo PRIMARY KEY (messageID);

CREATE TABLE NoiseInfo
(
	roomInfo             VARCHAR2(18)  NOT NULL ,
	count                NUMBER(4)  NULL 
);

CREATE UNIQUE INDEX XPKNoiseInfo ON NoiseInfo
(roomInfo   ASC);

ALTER TABLE NoiseInfo
	ADD CONSTRAINT  XPKNoiseInfo PRIMARY KEY (roomInfo);

CREATE TABLE UserInfo
(
<<<<<<< HEAD
	userID               VARCHAR2(18)  NOT NULL ,
	email                VARCHAR2(30)  NULL ,
	password             VARCHAR2(20)  NULL ,
	name                 VARCHAR2(10)  NULL ,
	nickname             VARCHAR2(10)  NULL ,
	authentication       VARCHAR2(1)  DEFAULT 0  NULL ,
	roomInfo             VARCHAR2(18)  NULL ,
	isRecruite           VARCHAR2(10)  NULL 
=======
   userID               VARCHAR2(100)  NOT NULL ,
   email                VARCHAR2(30)  NULL ,
   password             VARCHAR2(20)  NULL ,
   phone                CHAR(18)  NULL ,
   name                 VARCHAR2(50)  NULL ,
   nickname             VARCHAR2(10)  NULL ,
   authentication       VARCHAR2(20)  DEFAULT 0  NULL ,
   isRecruite           VARCHAR2(10)  NULL ,
   roomInfo             VARCHAR2(18)  NULL 
>>>>>>> origin/develop
);

CREATE UNIQUE INDEX XPKUserInfo ON UserInfo
(userID   ASC);

ALTER TABLE UserInfo
	ADD CONSTRAINT  XPKUserInfo PRIMARY KEY (userID);

CREATE TABLE Notification
(
<<<<<<< HEAD
	notificationID       CHAR(18)  NOT NULL ,
	userID               VARCHAR2(18)  NOT NULL ,
	notiType             VARCHAR2(18)  NULL ,
	notiTypeID           VARCHAR2(18)  NULL ,
	isChecked            VARCHAR2(2)  NULL ,
	commentID            VARCHAR2(18)  NULL ,
	messageID            VARCHAR2(18)  NULL ,
	postID               VARCHAR2(18)  NULL 
=======
	authenticationFile   BLOB  NULL ,
	userID               VARCHAR2(100)  NOT NULL 
>>>>>>> origin/develop
);

CREATE UNIQUE INDEX XPKNotificationBox ON Notification
(notificationID   ASC);

ALTER TABLE Notification
	ADD CONSTRAINT  XPKNotificationBox PRIMARY KEY (notificationID);

CREATE TABLE LifePatterns
(
   userID               VARCHAR2(100)  NOT NULL ,
   lifePattern          VARCHAR2(20)  NULL 
);

<<<<<<< HEAD
CREATE UNIQUE INDEX XPKLifePatterns ON LifePatterns
(userID   ASC);

ALTER TABLE LifePatterns
	ADD CONSTRAINT  XPKLifePatterns PRIMARY KEY (userID);

CREATE TABLE AuthenticationFile
(
	authenticationFile   BLOB  NULL ,
	userID               VARCHAR2(18)  NOT NULL 
=======
CREATE TABLE Notification
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
>>>>>>> origin/develop
);

CREATE UNIQUE INDEX XPKAuthenticationFile ON AuthenticationFile
(userID   ASC);

ALTER TABLE AuthenticationFile
	ADD CONSTRAINT  XPKAuthenticationFile PRIMARY KEY (userID);

ALTER TABLE Comment
	ADD (
CONSTRAINT R_11 FOREIGN KEY (userID) REFERENCES UserInfo (userID));

ALTER TABLE Comment
	ADD (
CONSTRAINT R_34 FOREIGN KEY (postID) REFERENCES FindBoardPost (postID) ON DELETE SET NULL);

ALTER TABLE Comment
	ADD (
CONSTRAINT R_35 FOREIGN KEY (postID) REFERENCES FreeBoardPost (postID) ON DELETE SET NULL);

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
CONSTRAINT R_32 FOREIGN KEY (postID) REFERENCES FreeBoardPost (postID) ON DELETE SET NULL);

ALTER TABLE MessageInfo
	ADD (
CONSTRAINT R_33 FOREIGN KEY (postID) REFERENCES FindBoardPost (postID) ON DELETE SET NULL);

ALTER TABLE UserInfo
	ADD (
CONSTRAINT R_20 FOREIGN KEY (roomInfo) REFERENCES NoiseInfo (roomInfo) ON DELETE SET NULL);

ALTER TABLE Notification
	ADD (
CONSTRAINT R_22 FOREIGN KEY (commentID) REFERENCES Comment (commentID) ON DELETE SET NULL);

ALTER TABLE Notification
	ADD (
CONSTRAINT R_25 FOREIGN KEY (messageID) REFERENCES MessageInfo (messageID) ON DELETE SET NULL);

ALTER TABLE Notification
	ADD (
CONSTRAINT R_26 FOREIGN KEY (userID) REFERENCES UserInfo (userID));

ALTER TABLE Notification
	ADD (
CONSTRAINT R_29 FOREIGN KEY (postID) REFERENCES FindBoardPost (postID) ON DELETE SET NULL);

ALTER TABLE LifePatterns
	ADD (
CONSTRAINT R_6 FOREIGN KEY (userID) REFERENCES UserInfo (userID));

ALTER TABLE AuthenticationFile
	ADD (
CONSTRAINT R_7 FOREIGN KEY (userID) REFERENCES UserInfo (userID));
