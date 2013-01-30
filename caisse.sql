-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 30 Janvier 2013 à 10:50
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `caisse`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE IF NOT EXISTS `article` (
  `id_art` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(128) NOT NULL,
  `description` text NOT NULL,
  `prix` float NOT NULL,
  `stock` int(11) NOT NULL,
  `idCategorie` int(11) NOT NULL,
  PRIMARY KEY (`id_art`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Contenu de la table `article`
--

INSERT INTO `article` (`id_art`, `nom`, `description`, `prix`, `stock`, `idCategorie`) VALUES
(1, 'Cafe', 'Décaféiné', 1.2, 150, 1),
(2, 'Croissant', 'Croissant au beurre', 0.6, 120, 2),
(3, 'Thé vert japon', 'En provenance de la région d’Uji est le plus célèbre. Située près de Kyoto, elle ne produit que 3% du thé vert japonais mais fournit les thés les plus extraordinaires par leurs arômes complexes. Par exemple le fameux Gyokuro “Perle de Rosée”.', 3, 120, 1),
(4, 'Thé noir chine', 'En provenance du Sichuan, la plus ancienne province productrice de thé.', 3, 100, 1),
(5, 'Èclair au chocolat', 'Au chocolat noir et crème patissière', 1.3, 80, 2),
(6, 'Pain au chocolat', 'Au chocolat noir et beurre', 0.7, 80, 2),
(7, 'Thé vert', 'En provenance de l''Ile Maurice.', 3, 80, 1),
(8, 'Fanta Orange', 'Canette de 33cl ', 1.5, 50, 3),
(9, 'Fanta Citron', 'Canette de 33cl.', 1.5, 50, 3),
(10, 'Nestea', 'Bouteille de 1,5 L', 2, 40, 3),
(11, 'Coca-Cola', 'Bouteille de 2L', 2.2, 20, 3),
(12, 'Fanta Zéro', 'Canette de 33cl.', 1.5, 50, 3),
(13, 'Coca-Cola Zero', 'Canette de 33 cl. ', 1.5, 50, 3),
(14, 'Coca-Cola Light', 'Canette de 33 cl.', 1.5, 52, 3),
(15, 'Sprite', 'Canette de 33 cl.', 1.5, 52, 3),
(16, 'Bière', 'Canette de 50cl.\r\nMarque : Jupiler', 1.9, 25, 4),
(17, 'Galette', 'Galettes au beurre faites maison', 0.3, 150, 6),
(18, 'Cookie', 'Ces cookies sont fourrés au Nutella.', 0.25, 150, 6),
(19, 'Bic', 'Bic standard de la marque BiC.', 0.5, 60, 5),
(20, 'Leffe', 'La Bière Leffe blonde est une bière de type abbaye à fermentation haute, conçue par le brasseur St Guibert à (Belgique).\r\nElle est disponible en bouteille de 25 cl. ', 2.6, 18, 4);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `id_cat` int(11) NOT NULL AUTO_INCREMENT,
  `nomCat` varchar(128) NOT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`id_cat`, `nomCat`) VALUES
(1, 'Boisson chaude'),
(2, 'viennoiserie'),
(3, 'Boisson gazeuse'),
(4, 'Boisson alcoolisée'),
(5, 'Fourniture'),
(6, 'Friandise');

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE IF NOT EXISTS `facture` (
  `id_fac` int(11) NOT NULL AUTO_INCREMENT,
  `idClient` int(11) NOT NULL,
  `dateFact` datetime NOT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`id_fac`),
  KEY `idClient` (`idClient`),
  KEY `id_fac` (`id_fac`),
  KEY `id_fac_2` (`id_fac`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(255) CHARACTER SET utf8 NOT NULL,
  `nom` varchar(128) CHARACTER SET utf8 NOT NULL,
  `prenom` varchar(128) CHARACTER SET utf8 NOT NULL,
  `adresse` varchar(128) CHARACTER SET utf8 NOT NULL,
  `localite` varchar(128) CHARACTER SET utf8 NOT NULL,
  `Ville` varchar(128) CHARACTER SET utf8 NOT NULL,
  `telephone` varchar(128) CHARACTER SET utf8 NOT NULL,
  `actif` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `mail`, `nom`, `prenom`, `adresse`, `localite`, `Ville`, `telephone`, `actif`) VALUES
(1, 'stephane13_89@hotmail.com', 'Bury', 'Stéphane', '8 rue des Paquerettes', '7140', 'Morlanwelz', '000/000000', 1),
(2, 'pgvc@hotmail.com', 'Pasbecq', 'Guillaume', '57 rue des Jonquilles', '7062', 'Soignie', '000/000000', 1),
(3, 'client8@hotmail.fr', 'Jurlon', 'Pierre', '79 rue de la Forêt', '7100', 'La Louviere', '000/000000', 1),
(4, 'client7@hotmail.com', 'Therasse', 'Pierre', '16 rue Haute', '7800', 'Ath', '000/000000', 1),
(5, 'client6@hotmail.com', 'Tricarico', 'Gianni', '24 rue Laurent', '7800', 'Ath', '000/000000', 1),
(6, 'client1@hotmail.fr', 'Petain', 'Thomas', '82 rue de la Station', '7800', 'Ath', '000/000000', 1),
(7, 'client2@hotmail.com', 'Lhoir', 'Olivier', '12 rue de la Vigne', '7800', 'Ath', '000/000000', 1),
(8, 'client3@gmail.com', 'Jacques', 'Etienne', '1 rue des Brasseurs', '7800', 'Ath', '000/000000', 1),
(9, 'client4@hotmail.fr', 'Legrain', 'Thomas', '21 rue Basse', '7800', 'Ath', '000/000000', 0),
(10, 'client5@hotmail.com', 'Bury', 'Sarah', '133 chaussée du Roeulx', '7800', 'Ath', '000/000000', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
