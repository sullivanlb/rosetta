<!doctype html>
<html lang="fr">
	<head>
 		<meta charset="utf-8">
  		<title>ST - Modification du profil</title>
  		<link rel="stylesheet" href="style.css">
  		<script src="script.js"></script>
	</head>

	<body>
        <div id="container">
            <!-- zone de modification du profil -->
            
            <form action="verification.php" method="POST">
                <h1>Mise à jour du profil</h1>

                <label><b>Nom</b></label>
                <input type="text" placeholder="Entrer votre nom" name="username" pattern="[A-Za-z]+" required><br>

                <label><b>Prénom</b></label>
                <input type="text" placeholder="Entrer votre prénom" name="firstname" pattern="[A-Za-z]+" required><br>

                <label><b>Date de naissance</b></label><br>
                <input type="date" placeholder="Entrer votre date de naissance" name="birthday" min="1900-01-01" required><br>

                <br>

                <label><b>Sexe</b></label>
                <input type="radio" id="male" name="gender" value="male" required>
                <label>Homme</label>
                <input type="radio" id="female" name="gender" value="female" required>
                <label>Femme</label>
                <input type="radio" id="other" name="gender" value="other" required>
                <label>Autre</label><br>

                <label><b>Taille</b></label>
                <input type="number" name="heigth" min=0 required>
                <label>cm</label><br>

                <label><b>Poids</b></label>
                <input type="number" name="weight" min=0 required>
                <label>kg</label><br>

                <label><b>Adresse électronique</b></label>
                <input type="email" placeholder="Entrer votre adresse électronique" name="email" required><br>

                <label><b>Mot de passe</b></label>
                <input type="password" placeholder="Entrer le mot de passe" name="password" minlength=7 required><br>

                <input type="submit" id='submit' value='Valider'>
            </form>
        </div>
    </body>
</html>