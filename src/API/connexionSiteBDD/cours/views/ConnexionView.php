<!doctype html>
<html lang="fr">
	<head>
 		<meta charset="utf-8">
  		<title>ST - Connexion</title>
  		<link rel="stylesheet" href="style.css">
  		<script src="script.js"></script>
	</head>

	<body>
        <div id="container">
            <!-- zone de connexion -->
            
            <form action="http://m3104.iut-info-vannes.net/m3104_42/?page=user_connect" method="POST">
                <h1>Connexion</h1>

                <label><b>Adresse électronique</b></label>
                <input type="email" placeholder="Entrer votre adresse électronique" name="email" required><br>

                <label><b>Mot de passe</b></label>
                <input type="password" placeholder="Entrer le mot de passe" name="password" minlength=7 required><br>

                <input type="submit" id='submit' value='Se connecter'>
            </form>
        </div>
    </body>
</html>