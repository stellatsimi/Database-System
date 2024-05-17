CREATE DATABASE IF NOT EXISTS travel_agency 
    CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE travel_agency ;

CREATE TABLE IF NOT EXISTS branch (
    br_code   INT(8)      PRIMARY KEY AUTO_INCREMENT NOT NULL, 
    br_street VARCHAR(30) NOT NULL,
    br_num    INT(4)      NULL DEFAULT NULL,
    br_city   VARCHAR(30) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS phones (
    ph_br_code INT(11)  NOT NULL,
    ph_number  CHAR(10) NOT NULL,
    
    CONSTRAINT phonesPK PRIMARY KEY (ph_br_code, ph_number),
    CONSTRAINT ph_fk_br FOREIGN KEY (ph_br_code)
        REFERENCES branch(br_code) 
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS worker (
    wrk_AT      CHAR(10)    NOT NULL PRIMARY KEY,
    wrk_name    VARCHAR(20) NOT NULL,
    WRK_lname   VARCHAR(20) NOT NULL,
    wrk_salary  FLOAT(7,2)  NOT NULL,
    wrk_br_code INT(11)     NOT NULL,
    
    CONSTRAINT wrk_fk_br FOREIGN KEY (wrk_br_code)
        REFERENCES branch(br_code) 
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS driver (
    drv_AT         CHAR(10)                 NOT NULL PRIMARY KEY,
    drv_license    ENUM('A', 'B', 'C', 'D') NOT NULL,
    drv_route      ENUM('LOCAL', 'ABROAD')  NOT NULL,
    drv_experience TINYINT(4)               NOT NULL,

    CONSTRAINT drv_fk_wrk FOREIGN KEY (drv_AT)
        REFERENCES worker(wrk_AT)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS guide (
    gui_AT CHAR(10) NOT NULL,
    gui_cv TEXT     NOT NULL,

    CONSTRAINT gui_fk_wrk FOREIGN KEY (gui_AT)
        REFERENCES worker(wrk_AT)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS admin (
    adm_AT      CHAR(10)                                          NOT NULL PRIMARY KEY,
    adm_type    ENUM('LOGISTICS', 'ADMINISTRATIVE', 'ACCOUNTING') NOT NULL,
    adm_diploma VARCHAR(200)                                      NOT NULL,

    CONSTRAINT adm_fk_wrk FOREIGN KEY (adm_AT)
        REFERENCES worker(wrk_AT)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS manages (
    mng_adm_AT CHAR(10) NOT NULL,
    mng_br_code INT(11) NOT NULL,

    CONSTRAINT mng_pk PRIMARY KEY (mng_adm_AT, mng_br_code),
    CONSTRAINT mng_fk_adm FOREIGN KEY (mng_adm_AT) REFERENCES admin(adm_AT) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT mng_fk_br FOREIGN KEY (mng_br_code) REFERENCES branch(br_code) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS trip (
    tr_id INT(11) NOT NULL AUTO_INCREMENT,
    tr_departure TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    tr_return TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    tr_maxseats TINYINT(4) NOT NULL,
    tr_cost FLOAT(7 , 2 ) NOT NULL,
    tr_br_code INT(11) NOT NULL,
    tr_gui_AT CHAR(10) NOT NULL,
    tr_drv_AT CHAR(10) NOT NULL,
    PRIMARY KEY (tr_id),
    CONSTRAINT tr_gui_AT_ FOREIGN KEY (tr_gui_AT)
        REFERENCES guide (gui_AT)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT tr_br_code_ FOREIGN KEY (tr_br_code)
        REFERENCES branch (br_code)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT tr_drv_AT_ FOREIGN KEY (tr_drv_AT)
        REFERENCES driver (drv_AT)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS event (
    ev_tr_id INT(11) NOT NULL,
    ev_start TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    ev_end TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    ev_descr TEXT,
    PRIMARY KEY (ev_tr_id , ev_start),
    CONSTRAINT ev_tr_id_ FOREIGN KEY (ev_tr_id)
        REFERENCES trip (tr_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS trip (
    tr_id INT(11) NOT NULL,
    tr_departure TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    tr_return TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    tr_maxseats TINYINT(4) NOT NULL,
    tr_cost FLOAT(7 , 2 ) NOT NULL,
    tr_br_code INT(11) NOT NULL,
    tr_gui_code INT(11) NOT NULL,
    tr_gui_AT CHAR(10) NOT NULL,
    tr_drv_AT CHAR(10) NOT NULL,
    PRIMARY KEY (tr_id),
    CONSTRAINT tr_id_ FOREIGN KEY (tr_id)
        REFERENCES event (ev_tr_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT tr_br_code_ FOREIGN KEY (tr_br_code)
        REFERENCES branch (br_code)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT tr_drv_AT_ FOREIGN KEY (tr_drv_AT)
        REFERENCES driver (drv_AT)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS languages (
    lng_gui_AT CHAR(10) NOT NULL,
    lng_language VARCHAR(30) NOT NULL,
    PRIMARY KEY (lng_gui_AT, lng_language),
    CONSTRAINT lng_gui_AT_ FOREIGN KEY (lng_gui_AT)
        REFERENCES guide (gui_AT)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS destination (
    dst_id INT(11) NOT NULL AUTO_INCREMENT,
    dst_name VARCHAR(50) NOT NULL DEFAULT 'UNKWOWN',
    dst_descr TEXT,
    dsrt_type ENUM('LOCAL', 'ABROAD'),
    dst_language VARCHAR(30) NOT NULL,
    dst_location INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (dst_id),
    CONSTRAINT dst_location_ FOREIGN KEY (dst_location)
        REFERENCES destination (dst_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS travel_to (
    to_tr_id INT(11) NOT NULL,
    to_dst_id INT(11) NOT NULL,
    to_arrival TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    to_departure TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    PRIMARY KEY (to_tr_id , to_dst_id),
    CONSTRAINT to_tr_id_ FOREIGN KEY (to_tr_id)
        REFERENCES trip (tr_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT to_dst_id_ FOREIGN KEY (to_dst_id)
        REFERENCES destination (dst_id)
        ON DELETE CASCADE ON UPDATE CASCADE   
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS reservation (
    res_tr_id INT(11) NOT NULL,
    res_seatnum TINYINT(4) NOT NULL,
    res_name VARCHAR(20) NOT NULL DEFAULT 'UNKNOWN',
    res_lname VARCHAR(20) NOT NULL DEFAULT 'UNKNOWN',
    res_isadult ENUM('ADULT', 'MINOR'),
    PRIMARY KEY (res_tr_id , res_seatnum),
    CONSTRAINT res_tr_id_ FOREIGN KEY (res_tr_id)
        REFERENCES trip (tr_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS it(
	IT_AT CHAR(10)  NOT NULL,
    password CHAR(10)  NOT NULL DEFAULT 'password',
    start_date DATE NOT NULL,
	end_date DATE NULL DEFAULT NULL,
	PRIMARY KEY(IT_AT),
    CONSTRAINT IT_at FOREIGN KEY (IT_AT)
        REFERENCES worker(wrk_AT)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS log (
    user_AT VARCHAR(20)                        NOT NULL,
    action  ENUM('INSERT', 'DELETE', 'UPDATE') NOT NULL,
    changes VARCHAR(200)                       NOT NULL,
    stamp   TIMESTAMP                          DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS offers (
    offer_id     INT        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    offer_start  DATE       NOT NULL,
    offer_finish DATE       NOT NULL,
    offer_cost   FLOAT(5,2) NOT NULL,
    offer_dst_id INT        NOT NULL,

    CONSTRAINT ofr_fk_dst FOREIGN KEY (offer_dst_id)
        REFERENCES destination(dst_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS reservation_offers (
    res_off_tr_id  INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    res_off_lname  VARCHAR(20) NOT NULL,
    res_off_name   VARCHAR(20) NOT NULL,
    res_off_id     INT         NOT NULL,
    res_off_depoit FLOAT(5,2)  NOT NULL,

    CONSTRAINT res_off_fk_off FOREIGN KEY (res_off_id)
        REFERENCES offers(offer_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;
