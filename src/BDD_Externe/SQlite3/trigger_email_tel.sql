DROP TRIGGER trig_email_tel;

CREATE TRIGGER trig_email_tel BEFORE INSERT ON Client
FOR EACH ROW
WHEN NEW.telClient IS NULL AND NEW.emailClient IS NULL 
BEGIN
	SELECT RAISE(ABORT,'Vous devez renseigner au moins le numéro de téléphone ou l adresse mail du client.');
   
END;
