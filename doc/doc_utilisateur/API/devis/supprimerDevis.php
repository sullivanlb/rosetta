<?php 
      header("Access-Control-Allow-Methods: GET, POST, OPTIONS, DELETE");
      header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("../BDD_Externe_Connexion.php");

    include("../objets/Devis.php");
    include("../dao/DevisDAO.php");
    include("../objets/AppartientCD.php");
    include("../dao/AppartientCDDAO.php");
    include("../objets/AppartientDC.php");
    include("../dao/AppartientDCDAO.php");
    include("../objets/AppartientDP.php");
    include("../dao/AppartientDPDAO.php");
    
    $connexion = new BDD_Externe_Connexion();

    if ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
        $index = basename($_SERVER['REQUEST_URI']);
        
        // Suppression du devis
        $daoDevis = new DevisDAO();
        $devis = new Devis();
        $devis = $daoDevis->unElement($index);
        $daoDevis->suppression($devis[0]);

        // Suppression des liaisons avec ses clients
        $daoAppartientCD = new AppartientCDDAO();
        $appartientCD = new AppartientCD();
        $appartientCD = $daoAppartientCD->unElement($devis[0]);
        foreach ($appartientCD as $liaison) {
            $daoAppartientCD->suppression($liaison);
        }

        // Suppression des liaisons avec ses composants
        $daoAppartientDC = new AppartientDCDAO();
        $appartientDC = new AppartientDC();
        $appartientDC = $daoAppartientDC->unElement($devis[0]);
        foreach ($appartientDC as $liaison) {
            $daoAppartientDC->suppression($liaison);
        }

        // Suppression des liaisons avec ses packs
        $daoAppartientDP = new AppartientDPDAO();
        $appartientDP = new AppartientDP();
        $appartientDP = $daoAppartientSP->unElement($devis[0]);
        foreach ($appartientDP as $liaison) {
            $daoAppartientDP->suppression($liaison);
        }
    }
?>