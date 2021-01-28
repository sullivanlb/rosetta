<?php
    ini_set('display_errors', 'On');
    error_reporting(E_ALL);
    session_start();
    require ('controllers/ApplicationController.php');

    $controller = ApplicationController::getInstance()->getController($_REQUEST);
    if($controller != null){
        include "controllers/$controller.php";
        (new $controller())->handle($_REQUEST);
    }

    $view = ApplicationController::getInstance()->getView($_REQUEST);
    if($view != null){
        include "views/$view.php";
    }
?>
