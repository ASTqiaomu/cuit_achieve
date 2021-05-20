DROP DATABASE
IF EXISTS db_cuit_achievement;

CREATE DATABASE db_cuit_achievement DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;

USE db_cuit_achievement;

DROP TABLE IF EXISTS t_admin;
CREATE TABLE t_admin (
    adminId INT AUTO_INCREMENT PRIMARY KEY,
    adminName NVARCHAR(16) NOT NULL,
    adminPassword NVARCHAR(16) NOT NULL,
    -- 权限等级，可选(0,1,2)，0最高
    adminLevel INT NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

INSERT INTO t_admin VALUES (1, 'admin', 'admin', 0);
INSERT INTO t_admin VALUES (2, 'tom', 'tom', 1);
INSERT INTO t_admin VALUES (3, 'rose', 'rose', 2);

DROP TABLE IF EXISTS t_college;
CREATE TABLE t_college (
    collegeId INT AUTO_INCREMENT PRIMARY KEY,
    collegeName NVARCHAR(32) NOT NULL UNIQUE,
    collegeDesc NVARCHAR(512) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

INSERT INTO t_college VALUES (1, '计算机学院', null);
INSERT INTO t_college VALUES (2, '大气科学学院', null);
INSERT INTO t_college VALUES (3, '管理学院', null);
INSERT INTO t_college VALUES (4, '资源环境学院', null);
INSERT INTO t_college VALUES (5, '物流学院', null);
INSERT INTO t_college VALUES (6, '应用数学学院', null);
INSERT INTO t_college VALUES (7, '电子工程学院', null);
INSERT INTO t_college VALUES (8, '统计学院', null);
INSERT INTO t_college VALUES (9, '控制工程学院', null);
INSERT INTO t_college VALUES (10, '文化艺术学院', null);
INSERT INTO t_college VALUES (11, '通信工程学院', null);
INSERT INTO t_college VALUES (12, '外国语学院', null);
INSERT INTO t_college VALUES (13, '马克思主义学院', null);
INSERT INTO t_college VALUES (14, '软件工程学院', null);
INSERT INTO t_college VALUES (15, '工程实践中心', null);
INSERT INTO t_college VALUES (16, '网络空间安全学院', null);
INSERT INTO t_college VALUES (17, '计算中心', null);
INSERT INTO t_college VALUES (18, '光电工程学院', null);
INSERT INTO t_college VALUES (19, '体育部', null);
INSERT INTO t_college VALUES (20, '区块链产业学院', null);

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userName NVARCHAR(16) NOT NULL UNIQUE,
    userPassword NVARCHAR(16) NOT NULL,
    userTrueName NVARCHAR(16) NOT NULL,
    userScore INT CHECK(userScore>=0) DEFAULT 0,
    userSex NVARCHAR(1) CHECK(userSex IN ('男','女')) NOT NULL,
    userPhone NVARCHAR(16) DEFAULT NULL,
    collegeId INT REFERENCES t_college(collegeId)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

INSERT INTO t_user VALUES (1, 'zhangsan', 'zhangsan', '张三', 0, '男', '13012345678', 1);
INSERT INTO t_user VALUES (2, 'bingbing', 'bingbing', '冰冰', 0, '女', '19736314456', 12);
INSERT INTO t_user VALUES (3, 'lisi', 'lisi', '李四', 0, '男', '13975683588', 7);
INSERT INTO t_user VALUES (4, 'laowang', 'laowang', '老王', 0, '男', '14687027623', 9);

DROP TABLE IF EXISTS t_log_login;
CREATE TABLE t_log_login (
    logId INT AUTO_INCREMENT PRIMARY KEY,
    loginName NVARCHAR(16) NOT NULL,
    loginIp NVARCHAR(16) NOT NULL,
    loginDate DATETIME DEFAULT NOW()
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO t_log_login VALUES (1, 'admin', '127.0.0.1', '2021-05-20 17:39:35');

DROP TABLE IF EXISTS t_result_type;
CREATE TABLE t_result_type (
    typeId INT AUTO_INCREMENT PRIMARY KEY,
    typeName NVARCHAR(32) NOT NULL UNIQUE,
    typeDesc NVARCHAR(512) DEFAULT NULL,
    -- 成果类型绩效分
    typeScore INT CHECK(typeScore>=0) DEFAULT 0
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

INSERT INTO t_result_type VALUES (1, '科研项目', '有研究、实际应用意义或者获得过省级或国家级奖项的科研项目，即可申请', 6);
INSERT INTO t_result_type VALUES (2, '课程授课', '经过教务处批准后在校授课，即可申请', 1);
INSERT INTO t_result_type VALUES (3, '授课全勤', '课程授课过程中，教师除公事请假外全勤，即可申请', 1);
INSERT INTO t_result_type VALUES (4, '任职辅导员', '任职辅导员，即可申请', 4);
INSERT INTO t_result_type VALUES (5, '任职课程组组长', '任职课程组组长，即可申请', 4);
INSERT INTO t_result_type VALUES (6, '策划大型活动', '策划了校级及以上的大型活动（如校运会），即可申请', 3);
INSERT INTO t_result_type VALUES (7, '参与大型活动', '参与了校级及以上的大型活动（如校运会当裁判），即可申请', 2);
INSERT INTO t_result_type VALUES (8, '毕业设计指导', '指导在校学生完成毕业设计，即可申请', 2);
INSERT INTO t_result_type VALUES (9, '参赛指导', '指导在校学生完成校级及以上比赛，即可申请', 1);

DROP TABLE IF EXISTS t_result;
CREATE TABLE t_result (
    resId INT AUTO_INCREMENT PRIMARY KEY,
    -- 提交人
    userId INT REFERENCES t_user(userId),
    resName NVARCHAR(64) NOT NULL,
    resDesc NVARCHAR(512) DEFAULT NULL,
    -- 成果提交日期
    resDate DATETIME DEFAULT now(),
    -- 成果状态，0提交，1通过，2拒绝
    resStatus INT CHECK(resStatus IN (0,1,2)) NOT NULL,
    typeId INT REFERENCES t_result_type(typeId),
    typeName NVARCHAR(32)	REFERENCES t_result_type(typeName),
    -- 成果图片
    resImg NVARCHAR(256) DEFAULT NULL,
    -- 成果材料
    resFile NVARCHAR(256) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

INSERT INTO t_result VALUES (1, 1, '智能气象收集与分析系统', '基于大数据的智能气象收集与分析系统', '2020-01-06 10:43:16', 1, 1, '科研项目', null, null);
INSERT INTO t_result VALUES (2, 2, 'C语言程序设计1A', '教学班：自动化202', '2020-02-04 17:28:40', 1, 2, '课程授课', null, null);
INSERT INTO t_result VALUES (3, 2, 'C语言程序设计1A', '教学班：自动化202', '2020-06-12 20:03:52', 1, 3, '授课全勤', null, null);
INSERT INTO t_result VALUES (4, 3, '2017级计算机学院辅导员', '任职2017级计算机学院辅导员', '2021-03-06 14:02:03', 1, 4, '任职辅导员', null, null);
INSERT INTO t_result VALUES (5, 4, '指导XXX完成毕设', '指导2017012345完成毕业设计', '2021-04-22 16:44:01', 0, 8, '毕业设计指导', null, null);

DROP TABLE IF EXISTS t_log_verify;
CREATE TABLE t_log_verify (
    verifyId INT AUTO_INCREMENT PRIMARY KEY,
    resId INT REFERENCES t_result(resId),
    -- 审核状态，0申请，1通过，2拒绝
    verifyType INT CHECK(verifyType IN (0,1,2)) NOT NULL,
    verifyDesc NVARCHAR(512) DEFAULT NULL,
    adminId INT REFERENCES t_admin(adminId),
    verifyDate datetime DEFAULT now()
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

DROP TRIGGER IF EXISTS tr_log_verify_insert;
CREATE TRIGGER tr_log_verify_insert
AFTER INSERT
ON t_log_verify
FOR EACH ROW
BEGIN
    -- 如果审核通过
    IF (NEW.verifyType=1) THEN
        -- 更新绩效分
        UPDATE t_user
        SET userScore=userScore+(
        	SELECT typeScore FROM t_result_type WHERE typeId=(
                SELECT typeId FROM t_result WHERE resId=NEW.resId
            )
        )
        WHERE userId=(SELECT userId FROM t_result WHERE resId=NEW.resId);
    END IF;
END;

INSERT INTO t_log_verify VALUES (1,1,2,'无证明材料',1,'2020-01-06 12:43:16');
INSERT INTO t_log_verify VALUES (2,1,1,'通过',1,'2020-01-06 14:43:16');
INSERT INTO t_log_verify VALUES (3,2,1,'通过',2,'2020-02-05 17:28:40');
INSERT INTO t_log_verify VALUES (4,3,1,'通过',2,'2020-06-13 20:03:52');
INSERT INTO t_log_verify VALUES (5,4,1,'通过',3,'2021-03-06 16:02:03');