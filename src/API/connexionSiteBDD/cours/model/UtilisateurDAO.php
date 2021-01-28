<?php
require_once('SqliteConnection.php');

class UtilisateurDAO {
    private static $dao;

    public function __construct() {}

    public final static function getInstance() {
       if(!isset(self::$dao)) {
           self::$dao = new UtilisateurDAO();
       }
       return self::$dao;
    }

    public final function findAll(){
       $dbc = SqliteConnection::getInstance()->getConnection();
       $query = "select * from Utilisateur";
       $stmt = $dbc->query($query);
       $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Utilisateur');
       return $results;
    }

    public final function insert($request){
       if($request instanceof Utilisateur){
          $dbc = SqliteConnection::getInstance()->getConnection();
          // prepare the SQL statement
          $query = "insert into Utilisateur(email, nom, prenom, dateNaissance, sexe, taille, poids, motDePasse) values (:email,:nom,:prenom,:dateNaissance,:sexe,:taille,:poids,:motDePasse)";
          $stmt = $dbc->prepare($query);

          // bind the paramaters
          $stmt->bindValue(':email',$request['email'],PDO::PARAM_STR);
          $stmt->bindValue(':nom',$request['username'],PDO::PARAM_STR);
          $stmt->bindValue(':prenom',$request['firstname'],PDO::PARAM_STR);
          $stmt->bindValue(':dateNaissance',$request['birthday'],PDO::PARAM_STR);
          $stmt->bindValue(':sexe',$request['gender'],PDO::PARAM_STR);
          $stmt->bindValue(':taille',$request['height'],PDO::PARAM_STR);
          $stmt->bindValue(':poids',$request['weight'],PDO::PARAM_STR);
          $stmt->bindValue(':motDePasse',$request['password'],PDO::PARAM_STR);

          // execute the prepared statement
          $stmt->execute();
      }
    }

    public function delete($request){ 
      if ($request instanceof Utilisateur){
        $dbc = SqliteConnection::getInstance()->getConnection();

        $userEmail = $request['email'];
        // prepare the SQL statement
        $query = "delete from Utilisateur where email = '". $userEmail . "'";
        $stmt = $dbc->prepare($query);

        //execute the query
        $stmt->execute();
      }
    }

    public function update($request){
      if ($request instanceof Utilisateur){
        $dbc = SqliteConnection::getInstance()->getConnection();

        $userEmail = $request['email'];
        // prepare the SQL statement
        $query = "UPDATE utilisateur SET nom=:nom, prenom=:prenom, dateNaissance=:dateNaissance, sexe=:sexe, taille=:taille, poids=:poids, motDePasse=:motDePasse WHERE email = '". $userEmail. "'";
       
        $stmt = $dbc->prepare($query);
        
        // bind the paramaters
        $stmt->bindValue(':nom',$request['username'],PDO::PARAM_STR);
        $stmt->bindValue(':prenom',$request['firstname'],PDO::PARAM_STR);
        $stmt->bindValue(':dateNaissance',$request['birthday'],PDO::PARAM_STR);
        $stmt->bindValue(':sexe',$request['gender'],PDO::PARAM_STR);
        $stmt->bindValue(':taille',$request['height'],PDO::PARAM_STR);
        $stmt->bindValue(':poids',$request['weight'],PDO::PARAM_STR);
        $stmt->bindValue(':motDePasse',$request['password'],PDO::PARAM_STR);
          
        //execute the query
        $stmt->execute();
      } 
    }
}
?>
