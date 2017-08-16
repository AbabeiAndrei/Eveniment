-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.19-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table eveniment.category: ~0 rows (approximately)
DELETE FROM `category`;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `name`, `row_state`) VALUES
	(1, 'Mancare', 'Created'),
	(2, 'Bar', 'Created'),
	(3, 'Minibar', 'Created'),
	(4, 'Dans', 'Created');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping data for table eveniment.event: ~6 rows (approximately)
DELETE FROM `event`;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;

-- Dumping data for table eveniment.event_item: ~3 rows (approximately)
DELETE FROM `event_item`;
/*!40000 ALTER TABLE `event_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_item` ENABLE KEYS */;

-- Dumping data for table eveniment.period: ~0 rows (approximately)
DELETE FROM `period`;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
/*!40000 ALTER TABLE `period` ENABLE KEYS */;

-- Dumping data for table eveniment.product: ~2 rows (approximately)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `name`, `description`, `price`, `rate`, `category_id`, `row_state`) VALUES
	(1, 'Meniu 1', 'Aperitiv, Peste, Traditional, Principal', 280.00, 'ByPersons', 1, 'Created'),
	(2, 'Meniu 2', 'Aperitiv, Peste, Traditional, Principal', 300.00, 'ByPersons', 1, 'Created'),
	(3, 'Meniu 3', 'Cruton Foie gras cu ceapă caramelizată\r\nBărcuță Duet de Somon cu Biban de mare, mix de legume și sos de șofran\r\nSărmăluțe în foi de varză\r\nMușchiuleț de vită cu smochine și sos de rodie, ruladă de curcan fantezie', 350.00, 'ByPersons', 1, 'Created'),
	(4, 'Open Bar', 'Apă minerală/plată ( Borsec la sticlă)\r\nSucuri gama Pepsi\r\nSucuri naturale Pfanner\r\nCafea espresso\r\nCeai\r\nVin Domeniile Tohani (Sauvignon Blanc, Moșia Tohani Roze)\r\nVin Roșu La Querciola\r\nBere Heineken\r\nWhisky (J.Walker, Jim Beam)\r\nGin\r\nCampari\r\nMartini Alb/Roșu\r\nVodka Absolut', 1000.00, 'Fixed', 2, 'Created'),
	(5, 'Open Bar Suprem', 'Jack Daniels\r\nChivas Regal\r\nBelvedere\r\nJagermeister\r\nTequila\r\nCourvoisier\r\nRom Havana\r\nAperol Spritz\r\nCremă de whisky\r\nCocktail-uri\r\nLimonadă\r\nFresh-uri', 2000.00, 'Fixed', 2, 'Created'),
	(6, 'Candy Bar', 'Cupcakes, jeleuri, bomboane gumate, lollypops, prăjiturele făcute în casă', 15.00, 'ByPersons', NULL, 'Created'),
	(7, 'Aranjamente florale', NULL, 200.00, 'Fixed', NULL, 'Created'),
	(8, 'Dj profesionist', NULL, 1300.00, 'Fixed', NULL, 'Created'),
	(9, 'Sala conferinte', NULL, 50.00, 'ByPersons', NULL, 'Created'),
	(10, 'Open mini bar', 'Apă minerală/plată ( Borsec la sticlă)\r\nCafea espresso\r\nCeai\r\nWhisky (J.Walker, Jim Beam)\r\nCampari', 30.00, 'ByPersons', 3, 'Created'),
	(11, 'Gustari', 'Saleuri, Pateuri', 10.00, 'ByPersons', NULL, 'Created'),
	(12, 'Fructe', NULL, 10.00, 'ByPersons', NULL, 'Created'),
	(13, 'Nonalcoolic', NULL, 20.00, 'ByPersons', 3, 'Created'),
	(14, 'Alcoolic', NULL, 25.00, 'ByPersons', 3, 'Created'),
	(15, 'Alcoolic Plus', NULL, 30.00, 'ByPersons', 3, 'Created'),
	(16, 'Dans popular', NULL, 500.00, 'Fixed', 4, 'Created'),
	(17, 'Dans modern', NULL, 600.00, 'Fixed', 4, 'Created'),
	(18, 'Dans abstract', NULL, 650.00, 'Fixed', 4, 'Created'),
	(19, 'Dans clasic', NULL, 550.00, 'Fixed', 4, 'Created'),
	(20, 'Breakdance', NULL, 600.00, 'Fixed', 4, 'Created');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Dumping data for table eveniment.program: ~0 rows (approximately)
DELETE FROM `program`;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` (`id`, `name`, `price`, `row_state`) VALUES
	(1, 'Nunta', 2000.00, 'Created'),
	(2, 'Intalnire Corporate', 100.00, 'Created'),
	(3, 'Dansuri', 500.00, 'Created');
/*!40000 ALTER TABLE `program` ENABLE KEYS */;

-- Dumping data for table eveniment.program_categories: ~0 rows (approximately)
DELETE FROM `program_categories`;
/*!40000 ALTER TABLE `program_categories` DISABLE KEYS */;
INSERT INTO `program_categories` (`program_id`, `category_id`, `name`, `add_multiple`, `row_state`) VALUES
	(1, 1, 'Mancare', 0, 'Created'),
	(1, 2, 'Bar', 0, 'Created'),
	(2, 3, 'Minibar', 0, 'Created'),
	(3, 4, 'Dans', 0, 'Created');
/*!40000 ALTER TABLE `program_categories` ENABLE KEYS */;

-- Dumping data for table eveniment.program_products: ~0 rows (approximately)
DELETE FROM `program_products`;
/*!40000 ALTER TABLE `program_products` DISABLE KEYS */;
INSERT INTO `program_products` (`program_id`, `product_id`, `name`, `price`, `add_multiple`, `row_state`) VALUES
	(1, 6, 'Candy Bar', NULL, 0, 'Created'),
	(1, 7, 'Flori', NULL, 0, 'Created'),
	(1, 8, 'DJ', NULL, 0, 'Created'),
	(2, 9, 'Sala', NULL, 0, 'Created'),
	(2, 11, NULL, NULL, 0, 'Created'),
	(2, 12, NULL, NULL, 0, 'Created');
/*!40000 ALTER TABLE `program_products` ENABLE KEYS */;

-- Dumping data for table eveniment.users: ~0 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `email`, `fullName`, `phone`, `password`, `access_level`, `row_state`) VALUES
	(2, '11', '1112', '112', 'd41d8cd98f00b204e9800998ecf8427e', 0, 'Created');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
