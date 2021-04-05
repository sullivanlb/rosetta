-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql-rosettabdd.alwaysdata.net
-- Généré le :  lun. 05 avr. 2021 à 02:16
-- Version du serveur :  10.5.8-MariaDB
-- Version de PHP :  7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `rosettabdd_rosetta`
--

-- --------------------------------------------------------

--
-- Structure de la table `appartientcd`
--

CREATE TABLE `appartientcd` (
  `unClient` int(10) NOT NULL,
  `unDevis` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `appartientdc`
--

CREATE TABLE `appartientdc` (
  `unDevis` int(10) NOT NULL,
  `unComposant` int(10) NOT NULL,
  `quantite` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `appartientdp`
--

CREATE TABLE `appartientdp` (
  `unDevis` int(10) NOT NULL,
  `unPack` int(10) NOT NULL,
  `quantite` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `appartientpc`
--

CREATE TABLE `appartientpc` (
  `unPack` int(10) NOT NULL,
  `unComposant` int(10) NOT NULL,
  `quantite` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `appartientsc`
--

CREATE TABLE `appartientsc` (
  `unScenario` int(10) NOT NULL,
  `unComposant` int(10) NOT NULL,
  `quantite` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `appartientsp`
--

CREATE TABLE `appartientsp` (
  `unScenario` int(10) NOT NULL,
  `unPack` int(10) NOT NULL,
  `quantite` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `appartientsq`
--

CREATE TABLE `appartientsq` (
  `unScenario` int(10) NOT NULL,
  `uneQuestion` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `nomClient` tinytext NOT NULL,
  `prenomClient` tinytext NOT NULL,
  `adresseClient` tinytext DEFAULT NULL,
  `emailClient` tinytext DEFAULT NULL,
  `telClient` tinytext DEFAULT NULL,
  `sexeClient` tinytext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `composant`
--

CREATE TABLE `composant` (
  `idComposant` int(11) NOT NULL,
  `nomComposant` tinytext NOT NULL,
  `uniteComposant` tinytext NOT NULL,
  `prixComposant` decimal(10,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `connexion`
--

CREATE TABLE `connexion` (
  `login` varchar(100) NOT NULL,
  `mdp` tinytext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `devis`
--

CREATE TABLE `devis` (
  `idDevis` int(11) NOT NULL,
  `nomDevis` text DEFAULT NULL,
  `descriptionDevis` text DEFAULT NULL,
  `dureeDevis` text DEFAULT NULL,
  `dateEditionDevis` text DEFAULT NULL,
  `dateTravauxDevis` text DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pack`
--

CREATE TABLE `pack` (
  `idPack` int(11) NOT NULL,
  `nomPack` tinytext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `idQuestion` int(11) NOT NULL,
  `nomQuestion` tinytext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `scenario`
--

CREATE TABLE `scenario` (
  `idScenario` int(11) NOT NULL,
  `nomScenario` tinytext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `appartientcd`
--
ALTER TABLE `appartientcd`
  ADD PRIMARY KEY (`unClient`,`unDevis`);

--
-- Index pour la table `appartientdc`
--
ALTER TABLE `appartientdc`
  ADD PRIMARY KEY (`unDevis`,`unComposant`);

--
-- Index pour la table `appartientdp`
--
ALTER TABLE `appartientdp`
  ADD PRIMARY KEY (`unDevis`,`unPack`);

--
-- Index pour la table `appartientpc`
--
ALTER TABLE `appartientpc`
  ADD PRIMARY KEY (`unPack`,`unComposant`);

--
-- Index pour la table `appartientsc`
--
ALTER TABLE `appartientsc`
  ADD PRIMARY KEY (`unScenario`,`unComposant`);

--
-- Index pour la table `appartientsp`
--
ALTER TABLE `appartientsp`
  ADD PRIMARY KEY (`unScenario`,`unPack`);

--
-- Index pour la table `appartientsq`
--
ALTER TABLE `appartientsq`
  ADD PRIMARY KEY (`unScenario`,`uneQuestion`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`);

--
-- Index pour la table `composant`
--
ALTER TABLE `composant`
  ADD PRIMARY KEY (`idComposant`);

--
-- Index pour la table `connexion`
--
ALTER TABLE `connexion`
  ADD PRIMARY KEY (`login`);

--
-- Index pour la table `devis`
--
ALTER TABLE `devis`
  ADD PRIMARY KEY (`idDevis`);

--
-- Index pour la table `pack`
--
ALTER TABLE `pack`
  ADD PRIMARY KEY (`idPack`);

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`idQuestion`);

--
-- Index pour la table `scenario`
--
ALTER TABLE `scenario`
  ADD PRIMARY KEY (`idScenario`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;

--
-- AUTO_INCREMENT pour la table `composant`
--
ALTER TABLE `composant`
  MODIFY `idComposant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;

--
-- AUTO_INCREMENT pour la table `devis`
--
ALTER TABLE `devis`
  MODIFY `idDevis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;

--
-- AUTO_INCREMENT pour la table `pack`
--
ALTER TABLE `pack`
  MODIFY `idPack` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;

--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `idQuestion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;

--
-- AUTO_INCREMENT pour la table `scenario`
--
ALTER TABLE `scenario`
  MODIFY `idScenario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
