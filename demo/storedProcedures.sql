DELIMITER //
 CREATE PROCEDURE CountByOrderLevel(IN orderLevel INT,
 OUT total INT)
   BEGIN
    SELECT count(pno)
		INTO total
	FROM parts
	WHERE olevel = orderLevel;
   END //
 DELIMITER ;
 
 DELIMITER //
 CREATE PROCEDURE GetPartLevel(in  partNumber int(11), 
    out partLevel  varchar(10))
   BEGIN
   DECLARE priceLevel double;
 
    SELECT price INTO priceLevel
    FROM parts
    WHERE pno = partNumber;
 
    IF priceLevel > 20 THEN
    SET partLevel = 'HIGH';
    ELSEIF (priceLevel <= 20 AND priceLevel >= 15) THEN
        SET partLevel = 'MEDIUM';
    ELSEIF priceLevel < 15 THEN
        SET partLevel = 'LOW';
    END IF;
   END //
 DELIMITER ;


 CALL CountByOrderLevel(20, @total);
SELECT @total;

call GetPartLevel(10800, @level);
select @level;
