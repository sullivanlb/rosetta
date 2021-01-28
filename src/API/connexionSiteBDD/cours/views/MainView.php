<!doctype html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
        <title>ST - Accueil</title>
        <link rel="stylesheet" href="style.css">
        <script src="script.js"></script>
	</head>
	
	<body>
		<?php echo $_SESSION['message']; ?>

		<div id="container">
            <!-- page d'accueil -->
            
            <form action="http://m3104.iut-info-vannes.net/m3104_42/?page=user_add_form" method="POST" enctype="multipart/form-data">
            	<h1>Page d'accueil</h1>
                <input type="submit" id='submit' value='Créer un compte'>
            </form>

            <form action="http://m3104.iut-info-vannes.net/m3104_42/?page=user_connect_form" method="POST" enctype="multipart/form-data">
                <input type="submit" id='submit' value='Se Connecter'>
            </form>

             <form action="http://m3104.iut-info-vannes.net/m3104_42/?page=user_disconnect" method="POST" enctype="multipart/form-data">
                <input type="submit" id='submit' value='Se Déconnecter'>
            </form>
        </div>
	</body>
</html>
