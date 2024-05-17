DROP TRIGGER IF EXISTS insertData_on_trip_after_insert;
DROP TRIGGER IF EXISTS insertData_on_reservation_after_insert;
DROP TRIGGER IF EXISTS insertData_on_event_after_insert;
DROP TRIGGER IF EXISTS insertData_on_travel_to_after_insert;
DROP TRIGGER IF EXISTS insertData_on_destination_after_insert;
DROP TRIGGER IF EXISTS updateData_on_trip_after_update;
DROP TRIGGER IF EXISTS updateData_on_reservation_after_update;
DROP TRIGGER IF EXISTS updateData_on_event_after_update;
DROP TRIGGER IF EXISTS updateData_on_travel_to_after_update;
DROP TRIGGER IF EXISTS updateData_on_destination_after_update;
DROP TRIGGER IF EXISTS deleteData_on_trip_after_delete;
DROP TRIGGER IF EXISTS deleteData_on_reservation_after_delete;
DROP TRIGGER IF EXISTS deleteData_on_event_after_delete;
DROP TRIGGER IF EXISTS deleteData_on_travel_to_after_delete;
DROP TRIGGER IF EXISTS deleteData_on_destination_after_delete;
DROP TRIGGER IF EXISTS not_accept_changes;
DROP TRIGGER IF EXISTS reduceSalary;

-- 3.1.4.1
-- INSERT

DELIMITER $
CREATE TRIGGER insertData_on_trip_after_insert AFTER INSERT ON trip
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.tr_id,', ',
        NEW.tr_departure,', ',
        NEW.tr_return,', ',
        NEW.tr_maxseats,', ',
        NEW.tr_cost,', ',
        NEW.tr_br_code,', ',
        NEW.tr_gui_AT,', ',
        NEW.tr_drv_AT);
        
	INSERT INTO log
    VALUES(@USER, 'INSERT', changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER insertData_on_reservation_after_insert AFTER INSERT ON reservation
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.res_tr_id,', ',
        NEW.res_seatnum,', ',
        NEW.res_name,', ',
        NEW.res_lname,', ',
        NEW.res_isadult);
        
	INSERT INTO log
    VALUES(@USER, 'INSERT', changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER insertData_on_event_after_insert AFTER INSERT ON event
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.ev_tr_id,', ',
        NEW.ev_start,', ',
        NEW.ev_end,', ',
        NEW.ev_descr);
        
	INSERT INTO log
    VALUES(@USER, 'INSERT', changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER insertData_on_travel_to_after_insert AFTER INSERT ON travel_to
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.to_tr_id,', ',
        NEW.to_dst_id,', ',
        NEW.to_arrival,', ',
        NEW.to_departure);
        
	INSERT INTO log
    VALUES(@USER, 'INSERT', changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER insertData_on_destination_after_insert AFTER INSERT ON destination
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.dst_id,', ',
        NEW.dst_name,', ',
        NEW.dst_descr,', ',
        NEW.dsrt_type,', ',
        NEW.dst_language,', ',
        IF(NEW.dst_location IS NULL, "", NEW.dst_location));
        
	INSERT INTO log
    VALUES(@USER, 'INSERT', changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;

-- UPDATE

DELIMITER $
CREATE TRIGGER updateData_on_trip_after_update AFTER UPDATE ON trip
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.tr_id,', ',
        NEW.tr_departure,', ',
        NEW.tr_return,', ',
        NEW.tr_maxseats,', ',
        NEW.tr_cost,', ',
        NEW.tr_br_code,', ',
        NEW.tr_gui_AT,', ',
        NEW.tr_drv_AT);
        
	INSERT INTO log
    VALUES(@USER,'UPDATE',changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER updateData_on_reservation_after_update AFTER UPDATE ON reservation
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.res_tr_id,', ',
        NEW.res_seatnum,', ',
        NEW.res_name,', ',
        NEW.res_lname,', ',
        NEW.res_isadult);
        
	INSERT INTO log
    VALUES(CURRENT_USER(),'UPDATE',changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER updateData_on_event_after_update AFTER UPDATE ON event
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.ev_tr_id,', ',
        NEW.ev_start,', ',
        NEW.ev_end,', ',
        NEW.ev_descr);
        
	INSERT INTO log
    VALUES(CURRENT_USER(),'UPDATE',changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER updateData_on_travel_to_after_update AFTER UPDATE ON travel_to
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.to_tr_id,', ',
        NEW.to_dst_id,', ',
        NEW.to_arrival,', ',
        NEW.to_departure);
        
	INSERT INTO log
    VALUES(CURRENT_USER(),'UPDATE',changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER updateData_on_destination_after_update AFTER UPDATE ON destination
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(NEW.dst_id,', ',
        NEW.dst_name,', ',
        NEW.dst_descr,', ',
        NEW.dsrt_type,', ',
        NEW.dst_language,', ',
        NEW.dst_location);
        
	INSERT INTO log
    VALUES(CURRENT_USER(),'UPDATE',changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;

-- DELETE

DELIMITER $
CREATE TRIGGER deleteData_on_trip_after_delete AFTER DELETE ON trip
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(OLD.tr_id,', ',
        OLD.tr_departure,', ',
        OLD.tr_return,', ',
        OLD.tr_maxseats,', ',
        OLD.tr_cost,', ',
        OLD.tr_br_code,', ',
        OLD.tr_gui_AT,', ',
        OLD.tr_drv_AT);
        
	INSERT INTO log
    VALUES(CURRENT_USER(),'DELETE',changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER deleteData_on_reservation_after_delete AFTER DELETE ON reservation
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(OLD.res_tr_id,', ',
        OLD.res_seatnum,', ',
        OLD.res_name,', ',
        OLD.res_lname,', ',
        OLD.res_isadult);
        
	INSERT INTO log
    VALUES(@USER, 'DELETE', changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER deleteData_on_event_after_delete AFTER DELETE ON event
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(OLD.ev_tr_id,', ',
        OLD.ev_start,', ',
        OLD.ev_end,', ',
        OLD.ev_descr);
        
	INSERT INTO log
    VALUES(CURRENT_USER(),'DELETE',changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER deleteData_on_travel_to_after_delete AFTER DELETE ON travel_to
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(OLD.to_tr_id,', ',
        OLD.to_dst_id,', ',
        OLD.to_arrival,', ',
        OLD.to_departure);
        
	INSERT INTO log
    VALUES(CURRENT_USER(),'DELETE',changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;


DELIMITER $
CREATE TRIGGER deleteData_on_destination_after_delete AFTER DELETE ON destination
FOR EACH ROW
BEGIN 
    DECLARE changes VARCHAR(200);
    
    SET changes=
        CONCAT(OLD.dst_id,', ',
        OLD.dst_name,', ',
        OLD.dst_descr,', ',
        OLD.dsrt_type,', ',
        OLD.dst_language,', ',
        OLD.dst_location);
        
	INSERT INTO log
    VALUES(CURRENT_USER(),'DELETE',changes, CURRENT_TIMESTAMP());
END$
DELIMITER ;

-- 3.1.4.2
DELIMITER $$
CREATE TRIGGER not_accept_changes
BEFORE UPDATE ON trip
FOR EACH ROW
BEGIN
    DECLARE reservations INT;

    SELECT COUNT(res.res_tr_id)
    INTO reservations
    FROM trip t LEFT JOIN reservation res 
	    ON t.tr_id = res.res_tr_id
    WHERE t.tr_id = OLD.tr_id;

    IF reservations <> 0
    THEN
        SET NEW.tr_departure = OLD.tr_departure,
            NEW.tr_return = OLD.tr_return,
            NEW.tr_cost = OLD.tr_cost;
    END IF;
END$$
DELIMITER ;
/*
SELECT tr_cost 
FROM travel_agency.reservation res JOIN trip t 
	ON res.res_tr_id = t.tr_id
WHERE res_tr_id = 22; # 7

UPDATE trip SET tr_cost = 1;

SELECT tr_cost 
FROM travel_agency.reservation res RIGHT JOIN trip t 
	ON res.res_tr_id = t.tr_id
WHERE tr_id = 22; # 7
*/
-- 3.1.4.3
DELIMITER $
CREATE TRIGGER reduceSalary BEFORE UPDATE ON worker
FOR EACH ROW 
BEGIN 
    DECLARE diff FLOAT(7,2);
    SET diff=0.0;
    SET diff=NEW.wrk_salary-OLD.wrk_salary;
    
    IF(diff<0)
    THEN 
        SET NEW.wrk_salary = OLD.wrk_salary;
	END IF;
END $
DELIMITER ;
/*
INSERT INTO worker VALUES('ΧΨ109234', 'Πόπη', 'Παπακαλιάτη',5000.32,15);
SELECT * FROM worker;
UPDATE worker
SET wrk_salary=4000.00
WHERE wrk_AT='ΧΨ109234';

UPDATE worker
SET wrk_salary=6000.00
WHERE wrk_AT='ΧΨ109234';
*/
