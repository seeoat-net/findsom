//유저 별 알람 리스트 조회 쿼리
SELECT
    NT.notificationID,
    INFO.nickname,
    CMTY.title,
    CMTY.idx AS content_idx,
    CMT.content,
    CMT.response_to AS commentID
FROM
    notification AS NT
INNER JOIN
    UserInfo AS INFO ON NT.writer_idx = INFO.userID
INNER JOIN
    community AS CMTY ON NT.content_idx = CMTY.idx
INNER JOIN
    comment AS CMT ON NT.notiTypeID = CMT.userID
WHERE
    NT.userID = ?
ORDER BY
    NT.notificationID DESC;
