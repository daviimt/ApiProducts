-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-02-2023 a las 12:28:12
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `api_products`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `description`, `name`) VALUES
(1, '12345678', 'Category B'),
(2, '12345678', 'Category A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category_seq`
--

CREATE TABLE `category_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `category_seq`
--

INSERT INTO `category_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `id_category` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`id`, `description`, `name`, `price`, `id_category`) VALUES
(102, 'muñeca barbie', 'barbie', 3, 2),
(103, 'juego puzzle', 'puzzle', 8, 2),
(104, 'juego lego', 'lego', 8, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_seq`
--

CREATE TABLE `product_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `product_seq`
--

INSERT INTO `product_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `enabled`, `password`, `role`, `token`, `username`) VALUES
(1, b'1', '$2a$10$575tmRV1WZ9qCb2hhVKEM.bs.uFQJ2CIQ6LPMsibX32MYO9SOvEsa', 'ROLE_USER', NULL, 'marta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(51);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5cxv31vuhc7v32omftlxa8k3c` (`id_category`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`) USING HASH;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
