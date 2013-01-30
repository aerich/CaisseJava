-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mer 01 Février 2012 à 19:02
-- Version du serveur: 5.1.53
-- Version de PHP: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


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
  `idCatégorie` int(11) NOT NULL,
  PRIMARY KEY (`id_art`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Contenu de la table `article`
--

INSERT INTO `article` (`id_art`, `nom`, `description`, `prix`, `stock`, `idCatégorie`) VALUES
(1, 'Soutien-gorge Vicereine', '                    Original, luxueux et très en vogue, voilà les atouts de ce soutien-gorge push-up qui disposent d''un rembourrage amovible et d''armatures au niveau des bonnets. Ils sont en microfibre extensible unie sur le bas superposée d''un empiècement en maille imprimée donnant l''impression d''un col de chemise, le reste des bonnets étant en voile transparent superposé de dentelle très luxueuse. La partie haute bordée d''un fin ruban de satin est mise en valeur par un drapé d''organza froncé et volanté. Une mini cravate est présente à l''entre-bonnets, des petits noeuds sont présents au devant des bretelles réglables et amovibles. Les côtés sont entièrement en dentelle et la fermeture se fait au dos par agrafes multipositions.\r\n\r\n                    ', 49.45, 15, 1),
(2, 'Soutien-gorge Muscat', 'Offrez-vous le luxe avec ce soutien-gorge mis en valeur par une dentelle brodée de fils argentés d''une extrême qualité. Les bonnets qui se font simples sont en microfibre et disposent d''un rembourrage amovible et d''armatures. Les bretelles sont réglables et amovibles, ce modèle est livré avec deux paires de bretelles, l''une noire, l''autre transparente. Il peut se porter sous une tenue de soirée grâce au collier de cristaux de Swarovski placé sur le haut et qui s''attache derrière la nuque par une attache. La fermeture se fait au dos par agrafes multipositions.\r\n\r\nMatière : 33% coton, 25% polyamide, 16% polyuréthanne, 10% polyester, 8% nylon, 7% élasthanne, 1% lurex', 60, 4, 1),
(3, 'Soutien-gorge Duchess', 'Ce soutien-gore push-up est en dentelle transparente sur les bonnets qui disposent d''un rembourrage amovible et d''armatures, un noeud de satin est présent à l''entre-bonnets que l''on retrouve au devant des bretelles réglables et amovibles. Les côtés avec stabilisateurs sont en voile et la fermeture se fait au dos par agrafes multipositions.\r\n\r\nMatière : 43% polyamide, 26% coton, 14% polyester, 12% polyuréthanne, 4% polyéthylène, 1% elasthanne', 35, 8, 1),
(4, ' Soutien-gorge Marquise 2', 'De très grande qualité, ce soutien-gorge push-up à armatures allie voile plié sur la partie basse de ses bonnets qui disposent d''un rembourrage amovible, et dentelle sur la partie haute. L''entre-bonnets est mis en valeur par un noeud de satin que l''on retrouve au devant des bretelles réglables et amovibles qui sont surpiquées d''un liseré fantaisie. Les côtés sont en luxueuse dentelle et la fermeture se fait au dos par agrafes mutlipositions.\r\n\r\nMatière : 33% coton, 32% nylon, 14% polyuréthanne, 9% polyester, 8% polyéthylène, 3% élasthanne, 1% polyamide', 35, 3, 1),
(5, 'Ensemble Claire', 'Original et sexy, cet ensemble en voile comprend un soutien-gorge redresse-seins mis en valeur par un important noeud à l''entre-bonnets. Disposant d''armatures, il s''attache au dos par agrafes multipositions et les bretelles sont extensibles. Une culotte basse mis en valeur par de nombreuses ficelles et par un important noeud de satin au dos s''ajoute au soutien-gorge.\r\n\r\nMatière : 80% polyamide, 20% elasthanne,', 49, 10, 5),
(6, 'Robe et string Calipso', 'Envie de pimenter votre quotidien ? Cette robe à porter aussi bien en tenue de soirée que pour un moment d''intimité est mise en valeur au devant par une transparence soulignée de motifs brillants. En microfibre, elle vous apportera le confort en plus de l''élégance. A nouer derrière la nuque, elle est accompagnée d''un string ficelle assorti. Modèle emballé dans un sachet d''organza.\r\n\r\nMatière : 88% polyester, 12% elasthanne', 32, 10, 5),
(7, 'Body ouvert anastasia', 'Esprit ultra coquin avec ce body ouvert en dentelle, les bonnets à armatures sont entièrement ouverts, leur partie haute est surpiquée d''un galon de dentelle que l''on retrouve sur les bretelles et ils sont parés d''un noeud de satin à leur centre. Entièrement extensible, ce body est accompagné d''une paire de longs gants en microfibre. Modèle vendu dans un sachet d''organza.\r\n\r\nMatière : 88% polyester, 12% elasthanne\r\n    ', 42, 9, 5),
(8, 'Body string Denise', 'Body string ficelle en dentelle délicate au devant ouvert, il s''attache derrière la nuque et au dos. \r\nMatière : 88% polyester, 12% elasthanne\r\n    ', 23, 10, 5),
(9, 'Maillot de bain Daniela Turquoise', 'Maillot de bain deux pièces à armatures, ce modèle aux motifs fleuris dispose de bonnets rembourrés de mousse pouvant être retirée. Il se noue au dos et dans le cou. Le slip est doublé au devant, et le dos se fait simple. \r\nMatière : 80% polyamide, 20% elasthanne', 29, 7, 3),
(10, 'Nuisette Lea', 'Matière : non', 49, 25, 4),
(11, 'Nuisette Tree of Wisdom', 'Matière : non', 59, 6, 4),
(12, ' Nuisette Crazy Me', 'Matière : non', 50, 8, 4),
(13, 'Peignoir de bain Wera', 'Chaud, élégant ce peignoir vous comblera par sa beauté, il dispose d''une ceinture sous passants et d''un col châle bicolore qui lui apporte toute son originalité. L''épaisseur du tissu de ce peignoir est de 240g/m2, ce modèle se décline dans un panel de couleur magnifique pastels et très distinguées. \r\nMatière : 100% polyester  ', 42, 14, 4),
(14, 'Déshabillé et string Maggie', 'A la fois chic et sexy, ce déshabillé en satin vous accompagnera dans tous vos moments de détente mais aussi de séduction. Accompagné d''un string ficelle assorti, il se noue par une ceinture sous passants. Modèle emballé dans un sachet d''organza.\r\n\r\nMatière : 97% polyester, 3% elasthanne', 35, 31, 4),
(15, 'Soutien-gorge Anais', 'Matière : non', 49, 4, 6),
(16, 'Bustier Marquise', 'Offrez-vous le luxe pour cette journée inoubliable avec ce bustier en dentelle d''une extrême qualité, les bonnets de forme push-up à armatures avec rembourrage amovible laissent place à du voile sur leur partie haute, un noeud de satin étant placé à leur centre que l''on retrouve au bas du modèle. Un empiècement de voile légèrement plié est présent au devant, des liserés de fils brodés traversent le modèle au centre ainsi que sur chacun des bonnets. Des baleines sont présentes sur le contour du modèle pour un excellent maintien, les jarretelles sont réglables et amovibles et les bretelles qui sont réglables sont en voile doublé au devant d''où le charme de ce modèle. Les côtés et le dos sont en voile et la fermeture se fait au par agrafes multipositions.\r\n\r\nMatière : 36% polyamide, 31% nylon, 16% coton, 7% polyuréthane, 6% elasthanne, 4% polyester', 65, 12, 6),
(17, 'Guêpière Caprice', 'Offrez-vous une magnifique poitrine avec cette guêpière qui allie microfibre satinée et voile brodé sur le devant où se fondent des empiècements de dentelle. Les bonnets de forme push-up à armatures sont en microfibre accompagnée de dentelle sur leur partie basse et un noeud de satin est placé à leur centre, ils disposent d''un rembourrage amovible. Les bretelles bordées par des motifs fantaisie sont réglables mais non amovibles. Les jarretelles parées de noeud de satin au devant sont réglables et amovibles. Elle se passe comme un top.\r\n\r\nMatière : 85% polyamide, 15% elasthanne', 36, 18, 6),
(18, 'Collant Malena', 'En microfibre ce collant fantaisie de 60 deniers est opaque et utilise la technologie 3D dans la plus grande qualité de recouvrement de fil. Un gousset en coton à l''entrejambe est présent sur les tailles 3 et 4. Un renforcement invisible au niveau des orteils et des coutures plates vous assurent un confort exceptionnel.\r\n \r\nMatière : 81% polyamide, 18% elasthanne, 1% coton  ', 6, 4, 6),
(19, 'Guêpière et string Chantal', 'En satin cette guêpière sexy à armatures est recouverte de voile brodé au devant et dispose de baleines sur son contour pour vous offrir un maintien parfait, au dos un laçage de satin vous permettra de l’ajuster parfaitement à votre silhouette. Les bretelles sont réglables ainsi que les jarretelles et un string ficelle vient la compléter. \r\nMatière : 88% polyester, 12% elasthanne', 37, 7, 2),
(20, 'Porte-jarretelles Lilac', 'Compléter votre parure avec ce porte-jarretelles en microfibre, il est orné de laçages de satin au devant qui sont bordés de dentelle. Les jarretelles sont réglables.\r\n\r\nMatière : 90% polyamide, 10% elasthanne ', 15, 5, 2),
(21, 'Porte-jarretelles Reina', 'Alliance de microfibre imprimée et dentelle brodée de fils pailletés sur cet original porte-jarretelles. Il est mis en valeur par des petits noeuds de satin au devant et sur les jarretelles qui sont réglables. Le dos est réglable et se ferme par une petite attache.\r\n\r\nMatière : 51% polyamide, 32%polyester, 9% lurex, 8% elasthanne', 29, 21, 2),
(22, 'Porte-jarretelles Milady', 'Matière : 50% coton, 50% polyester', 25, 10, 2),
(23, 'Porte-jarretelles Dame', '       Originalité et élégance à l''état pur sur ce porte-jarretelles en voile, il est brodé dur les côtés et un laçage de satin vient souligner la beauté de ce modèle au devant. La partie basse est bordée d''un voile légèrement plié sur lequel sont apposés des noeuds de satin. Les jarretelles sont réglables et la fermeture se fait par un système d''agrafes multipositions placé au dos qui est en voile.\r\n\r\nMatière : 77% polyamide, 8% elasthanne, 8% coton, 7% viscose', 35, 10, 2),
(24, 'Maillot de bain Felicita Noir par Verano', '  Tendance très jeune pour ce maillot de bain deux pièces à nouer au dos et dans le cou par des lanières, ses bonnets sont rembourrés de mousse qui peut être retirée. Le shorty uni est traversé par un bandeau sous passants orné d''un anneau au devant. Il est doublé au devant.\r\n\r\nMatière : 80% polyamide, 20% elasthanne,', 30.95, 6, 3),
(25, 'test2', 'essai', 45.5, 5, 1),
(26, 'Maillot de bain Emma par Verano', 'Maillot de bain deux pièces de forme bandeau à nouer dans le cou, la fermeture se fait au dos par une attache. Les bonnets à motifs disposent d''un rembourrage en mousse pouvant être retiré et les côtés disposent de stabilisateur pour un maintien optimal. Le slip uni est doublé au devant.\r\n\r\nMatière : 80% polyamide, 20% elasthanne,   ', 28.95, 17, 3),
(27, 'Maillot de bain Nadiya par Verano', '         Ce maillot de bain 1 pièce affinera votre silhouette de façon spectaculaire grâce aux liserés fantaisies traversant le devant de ce maillot qui est parfaitement opaque, paré de mousse les bonnets mettront votre poitrine en valeur en lui donnant une forme galbée et ronde. Les bretelles larges pour vous assurer un très bon maintien pour les poitrines généreuses sont également réglables. \r\nMatière : 80% polyamide, 20% elasthanne,', 34.95, 9, 3),
(28, 'Test', 'test', 45.1, 12, 1),
(29, 'Test', 'test', 45.1, 12, 1);

-- --------------------------------------------------------

--
-- Structure de la table `catégorie`
--

CREATE TABLE IF NOT EXISTS `catégorie` (
  `id_cat` int(11) NOT NULL AUTO_INCREMENT,
  `nomCat` varchar(128) NOT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `catégorie`
--

INSERT INTO `catégorie` (`id_cat`, `nomCat`) VALUES
(1, 'soutiens-gorge'),
(2, 'porte-jarretelles'),
(3, 'maillots de bain'),
(4, 'lingerie de nuit'),
(5, 'ensembles sexy'),
(6, 'lingerie marriage');

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE IF NOT EXISTS `facture` (
  `id_fac` int(11) NOT NULL AUTO_INCREMENT,
  `idClient` int(11) NOT NULL,
  `dateFact` datetime NOT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`id_fac`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `facture`
--

INSERT INTO `facture` (`id_fac`, `idClient`, `dateFact`, `total`) VALUES
(1, 1, '2012-01-24 19:42:29', 210),
(2, 1, '2012-01-24 19:43:02', 198),
(3, 1, '2012-01-24 19:53:19', 49),
(4, 1, '2012-01-25 10:30:55', 126),
(5, 1, '2012-01-27 13:15:22', 115),
(6, 6, '2012-01-27 13:36:40', 465),
(7, 8, '2012-01-27 14:08:06', 99),
(8, 1, '2012-01-27 16:07:06', 401);

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
(1, 'mbiloute@hotmail.fr', 'deraes', 'Wesley', '21 rue Basse', '7100', 'belgique', '000/000000', 1),
(2, 'user1@hotmail.fr', 'User1', 'user1', 'user', 'la louvière', 'belgique', '000/000000', 1),
(3, 'mbiloute2@hotmail.fr', 'de raes', 'Wesley', '21 rue Basse', '7100', 'La Louviere', '000/000000', 1),
(4, 'mbiloute3@hotmail.fr', 'de raes', 'Wesley', '21 rue Basse', '7100', 'La Louviere', '000/000000', 1),
(5, 'mbiloute4@hotmail.fr', 'de raes', 'Wesley', '21 rue Basse', '7100', 'La Louviere', '000/000000', 1),
(6, 'clien1t@hotmail.fr', 'DE RAES', 'Wesley', '21 rue Basse', '7100', 'La Louviere', '000/000000', 1),
(7, 'clien2t@hotmail.fr', 'DE RAES', 'Wesley', '21 rue Basse', '7100', 'La Louviere', '000/000000', 1),
(8, 'clien3t@hotmail.fr', 'DE RAES', 'Wesley', '21 rue Basse', '7100', 'La Louviere', '000/000000', 1),
(9, 'clien4t@hotmail.fr', 'DE RAES', 'Wesley', '21 rue Basse', '7100', 'La Louviere', '000/000000', 0),
(10, 'mbiloute6@hotmail.fr', 'DE RAES', 'Wesley', '21 rue Basse', '7100', 'La Louviere', '000/000000', 1);
