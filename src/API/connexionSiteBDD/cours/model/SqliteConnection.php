<?php
ini_set('display_errors', 'On');
error_reporting(E_ALL);

	class SqliteConnection {
		private static $instance;
		private $connection;

		public function __construct() {
			try {
				$this->connection = new PDO('sqlite:'.dirname(__FILE__)."/bdd_externe.db");
				$this->connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			}catch (PDOException $e){
				print "Erreur !: " . $e->getMessage() . "<br/>";
				die();
 			}
		}

		public static function getInstance() {
			if (self::$instance === null) {
				self::$instance = new SqliteConnection();
			}
			return self::$instance;
		}

		public function getConnection() {
			return $this->connection;
		}
	}
	
	SqliteConnection::getInstance()->getConnection();
?>
