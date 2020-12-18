DROP TRIGGER trig_email_tel;
DELIMITER |
CREATE TRIGGER trig_email_tel
BEFORE INSERT ON Client
FOR EACH ROW

BEGIN
   
    IF NEW.telClient IS NULL 
    AND NEW.emailClient IS NULL 
    	THEN
    		SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = 'Vous devez renseigner au moins le numéro de téléphone ou l adresse mail du client.';
    END IF;
END |
DELIMITER;
