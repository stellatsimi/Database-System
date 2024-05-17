DROP PROCEDURE IF EXISTS addNewDriver;
DROP PROCEDURE IF EXISTS trip_details;
DROP PROCEDURE IF EXISTS deleteWorker;

-- 3.1.3.1
DELIMITER $
CREATE PROCEDURE addNewDriver(IN AT CHAR(10) , IN name VARCHAR(20), IN lastname VARCHAR(20),IN salary FLOAT(7,2), IN drv_license ENUM('A', 'B', 'C', 'D'), IN drv_route ENUM('LOCAL', 'ABROAD'), IN drv_experience TINYINT(4))     
BEGIN   
    DECLARE min INT;
    DECLARE branch INT(11);
    SET min=0;
    
    SELECT COUNT(wrk_AT) INTO min 
    FROM worker
    INNER JOIN branch on worker.wrk_br_code=branch.br_code
    INNER JOIN driver on worker.wrk_AT=driver.drv_AT
    GROUP BY worker.wrk_br_code
    ORDER BY count(wrk_AT) ASC
    LIMIT 0,1;
    
	SELECT br_code INTO branch
    FROM worker
    INNER JOIN branch on worker.wrk_br_code=branch.br_code
    INNER JOIN driver on worker.wrk_AT=driver.drv_AT
    GROUP BY worker.wrk_br_code
    ORDER BY min ASC
    LIMIT 0,1;
    
    INSERT INTO worker 
    VALUES(AT, name, lastname, salary, branch);
    
    INSERT INTO driver
    VALUES( AT, drv_license, drv_route, drv_experience);
END$
DELIMITER ;
# CALL addNewDriver('ΛΖ103412','Αλέκα','Λάλη',4582.78,'D','LOCAL',6);    
# SELECT * FROM worker;
# SELECT * FROM driver;    

-- 3.1.3.2
DELIMITER $$
CREATE PROCEDURE trip_details(
	IN branch_id_p INT,
    IN departure_date_p TIMESTAMP,
    IN arrival_date_p TIMESTAMP,
    OUT tr_cost_p FLOAT(7,2),
    OUT maxseats_p TINYINT,
    OUT reservations_p INT,
    OUT free_seats_p TINYINT,
    OUT drv_name_p VARCHAR(20),
    OUT drv_lname_p VARCHAR(20),
    OUT gui_name_p VARCHAR(20),
    OUT gui_lname_p VARCHAR(20),
    OUT departure_p TIMESTAMP,
    OUT arrival_p TIMESTAMP
)
BEGIN
	DECLARE finished INT DEFAULT 0;
    DECLARE guide CHAR(10);
    DECLARE driver CHAR(10);
    DECLARE cursor_trip_details CURSOR FOR
		SELECT t.tr_cost, t.tr_maxseats, COUNT(res.res_tr_id),
            t.tr_maxseats - COUNT(res.res_tr_id), t.tr_gui_AT, 
            t.tr_drv_AT, t.tr_departure, t.tr_return
        FROM trip t LEFT JOIN reservation res ON t.tr_id = res.res_tr_id
        WHERE t.tr_br_code = branch_id_p AND
			t.tr_departure BETWEEN departure_date_p AND arrival_date_p
		GROUP BY t.tr_id;
	
    DECLARE CONTINUE HANDLER FOR NOT FOUND
    SET finished = 1;
        
	OPEN cursor_trip_details;
    
    REPEAT
		FETCH cursor_trip_details 
        INTO tr_cost_p, maxseats_p, reservations_p, 
            free_seats_p, guide, driver, departure_p, arrival_p;
        IF finished = 0
        THEN
			SELECT wrk_name, wrk_lname
			INTO gui_name_p, gui_lname_p 
			FROM worker
			WHERE wrk_AT = guide;
			
			SELECT wrk_name, wrk_lname
			INTO drv_name_p, drv_lname_p 
			FROM worker
			WHERE wrk_AT = driver;
        END IF;
    UNTIL finished=1
    END REPEAT;
    
	CLOSE cursor_trip_details;
END$$
DELIMITER ;

-- 3.1.3.3
DELIMITER $
CREATE PROCEDURE deleteWorker(IN name VARCHAR(20), IN lastname VARCHAR(20))
BEGIN 
    DECLARE AT CHAR(10);
    DECLARE type ENUM('LOGISTICS', 'ADMINISTRATIVE', 'ACCOUNTING');
    
    SELECT wrk_AT INTO AT 
    FROM worker
    WHERE wrk_name=name AND WRK_lname=lastname; 
    
    SELECT adm_type INTO type
    FROM admin
    WHERE AT=adm_AT;
    
    IF(type='ADMINISTRATIVE')
	THEN
        SELECT 'THIS WORKER IS AN ADMINISTRATIVE AND CAN NOT BE DELETED'as warning;
    ELSE DELETE FROM admin WHERE AT=adm_AT;
		DELETE FROM worker WHERE AT=wrk_AT;
	END IF;
END$
DELIMITER ;

# SELECT * FROM admin;
# SELECT * FROM worker;
# CALL deleteWorker('Μήτσος','Παυλάτος');
# CALL deleteWorker('Μανωλία','Μπούρνοβα');

-- 3.1.3.4
DELIMITER $$
CREATE PROCEDURE returnCustomers(
	IN minAmount INT, 
    IN maxAmount INT
)
BEGIN
    SELECT CONCAT(res_off_name, " ", res_off_lname) AS name
    FROM reservation_offers 
    WHERE reservation_offers.res_off_depoit BETWEEN minAmount AND maxAmount;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetCustomerName (
	IN lastName varchar(50)
)
BEGIN
    SELECT CONCAT(res_off_lname, ' ', res_off_name) AS name, 
		res_off_id, 
        COUNT(*) as number
    FROM reservation_offers
    WHERE res_off_lname = lastName
    GROUP BY res_off_lname, res_off_name, res_off_id
    ORDER BY number DESC, res_off_id;
END $$
DELIMITER ;
