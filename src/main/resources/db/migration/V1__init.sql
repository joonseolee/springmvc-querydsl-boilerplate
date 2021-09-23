CREATE SCHEMA IF NOT EXISTS `demo`;
CREATE TABLE IF NOT EXISTS `demo`.`school` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `isActive` tinyint NOT NULL DEFAULT '1',
                                 `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `location` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;