-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-07-2024 a las 20:20:08
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `puntoventa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `ID` int(255) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`ID`, `NOMBRE`, `EMAIL`) VALUES
(1, 'Juan Perez', 'jperez@gmail.com'),
(12, 'Mario Asad', 'masad@yahoo.com'),
(13, 'Lucia H', 'luh10@yahoo.com'),
(14, 'Rita Perez', 'rita@gmail.com'),
(15, 'Juan Cruz F', 'jfc@gmail.com'),
(16, 'Santiago MM', 'smm@hotmail.com'),
(17, 'Fernanda Campos', 'fcampos@hotmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `ID` int(11) NOT NULL,
  `ALIAS` varchar(20) NOT NULL,
  `DESCRIPCION` varchar(200) NOT NULL,
  `PRECIO` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`ID`, `ALIAS`, `DESCRIPCION`, `PRECIO`) VALUES
(1, 'NIKESB', 'Zapatillas Nike SB 2', 55000),
(2, 'NIKEAIR2', 'Zapatillas Nike Air', 33001),
(34, 'ADIDAS', 'Adidas basicas', 40000),
(36, 'NIKEAIR', 'Zapatillas Nike Air', 78999),
(48, 'ADIDAS', 'adidas blancas', 40000),
(49, 'NSHOES N13', 'NSHOES NEGRAS', 35000),
(50, 'TKG', 'zapatillas trekking', 62999),
(51, 'NBWHITE', 'Zapatillas New Balance Blancas', 62999);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedor`
--

CREATE TABLE `vendedor` (
  `ID` int(255) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `SUELDO` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vendedor`
--

INSERT INTO `vendedor` (`ID`, `NOMBRE`, `SUELDO`) VALUES
(1, 'TOMAS', 500000),
(2, 'MARIO LOPEZ', 50000),
(3, 'MARIANO', 750000),
(4, 'ANA', 350000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `ID` int(255) NOT NULL,
  `FECHA` date NOT NULL,
  `VENDEDORID` int(255) NOT NULL,
  `CLIENTEID` int(255) NOT NULL,
  `DESCUENTO` decimal(10,0) NOT NULL,
  `TOTAL` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`ID`, `FECHA`, `VENDEDORID`, `CLIENTEID`, `DESCUENTO`, `TOTAL`) VALUES
(16, '2024-06-03', 3, 1, 500, 615996),
(17, '2024-06-28', 1, 1, 600, 184042),
(51, '2024-06-30', 1, 1, 0, 110000),
(52, '2024-06-29', 3, 1, 0, 99999),
(54, '2024-06-05', 3, 1, 5252, -5227),
(59, '2024-06-30', 3, 12, 234, 269429),
(63, '2024-07-01', 2, 1, 0, 165000),
(64, '2024-07-01', 1, 16, 10000, 85000),
(65, '2024-07-01', 2, 15, 15000, 221997),
(67, '2024-07-01', 2, 12, 10000, 110000),
(70, '2024-07-01', 3, 1, 15000, 82999),
(71, '2024-07-02', 4, 17, 1526, 108474),
(72, '2024-07-02', 2, 17, 0, 55000),
(73, '2024-07-03', 2, 16, 86996, 200000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventaproducto`
--

CREATE TABLE `ventaproducto` (
  `ID` int(11) NOT NULL,
  `VENTAID` int(11) NOT NULL,
  `PRODUCTOID` int(11) NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `PRECIOVENTA` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventaproducto`
--

INSERT INTO `ventaproducto` (`ID`, `VENTAID`, `PRODUCTOID`, `CANTIDAD`, `PRECIOVENTA`) VALUES
(116, 17, 1, 1, 43),
(117, 17, 34, 1, 40000),
(118, 17, 1, 1, 65000),
(119, 17, 36, 1, 78999),
(127, 16, 1, 1, 55000),
(128, 16, 48, 1, 40000),
(129, 16, 36, 4, 78999),
(130, 16, 50, 1, 40000),
(131, 16, 1, 1, 55000),
(132, 16, 1, 1, 55000),
(133, 16, 1, 1, 55000),
(138, 51, 1, 1, 55000),
(139, 51, 1, 1, 55000),
(142, 59, 1, 1, 32432),
(143, 59, 36, 3, 78999),
(145, 64, 1, 1, 55000),
(146, 64, 34, 1, 40000),
(154, 67, 1, 1, 60000),
(155, 67, 1, 1, 60000),
(158, 52, 1, 1, 99999),
(159, 65, 36, 1, 78999),
(160, 65, 36, 1, 78999),
(161, 65, 36, 1, 78999),
(162, 54, 1, 5, 5),
(163, 63, 1, 1, 55000),
(164, 63, 1, 1, 55000),
(165, 63, 1, 1, 55000),
(167, 70, 51, 1, 62999),
(168, 70, 49, 1, 35000),
(169, 71, 1, 1, 55000),
(170, 71, 1, 1, 55000),
(171, 72, 1, 1, 55000),
(175, 73, 49, 1, 35000),
(176, 73, 51, 1, 62999),
(177, 73, 51, 3, 62999);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VENDEDOR` (`VENDEDORID`),
  ADD KEY `FK_CLIENTE` (`CLIENTEID`);

--
-- Indices de la tabla `ventaproducto`
--
ALTER TABLE `ventaproducto`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PRODUCTO` (`PRODUCTOID`),
  ADD KEY `FK_VENTA` (`VENTAID`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT de la tabla `ventaproducto`
--
ALTER TABLE `ventaproducto`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=178;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `FK_CLIENTE` FOREIGN KEY (`CLIENTEID`) REFERENCES `cliente` (`ID`),
  ADD CONSTRAINT `FK_VENDEDOR` FOREIGN KEY (`VENDEDORID`) REFERENCES `vendedor` (`ID`);

--
-- Filtros para la tabla `ventaproducto`
--
ALTER TABLE `ventaproducto`
  ADD CONSTRAINT `FK_PRODUCTO` FOREIGN KEY (`PRODUCTOID`) REFERENCES `producto` (`ID`),
  ADD CONSTRAINT `FK_VENTA` FOREIGN KEY (`VENTAID`) REFERENCES `venta` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
