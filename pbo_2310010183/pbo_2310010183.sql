-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 11, 2025 at 02:13 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo_2310010183`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_183`
--

CREATE TABLE `admin_183` (
  `id_admin` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama_admin` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin_183`
--

INSERT INTO `admin_183` (`id_admin`, `username`, `password`, `nama_admin`) VALUES
(1, 'admin', '12345', 'Administrator');

-- --------------------------------------------------------

--
-- Table structure for table `kasus`
--

CREATE TABLE `kasus` (
  `id_kasus` int NOT NULL,
  `nama_kasus` varchar(100) DEFAULT NULL,
  `jenis_kasus` varchar(100) DEFAULT NULL,
  `tanggal_mulai` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pelapor`
--

CREATE TABLE `pelapor` (
  `id_pelapor` int NOT NULL,
  `nama_pelapor` varchar(100) DEFAULT NULL,
  `alamat` varchar(200) DEFAULT NULL,
  `no_telp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pengacara`
--

CREATE TABLE `pengacara` (
  `id_pengacara` int NOT NULL,
  `nama_pengacara` varchar(100) DEFAULT NULL,
  `spesialisasi` varchar(100) DEFAULT NULL,
  `no_telp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `terlapor`
--

CREATE TABLE `terlapor` (
  `id_terlapor` int NOT NULL,
  `nama_terlapor` varchar(100) DEFAULT NULL,
  `alamat` varchar(200) DEFAULT NULL,
  `no_telp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_183`
--

CREATE TABLE `user_183` (
  `id_user` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama_user` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_183`
--

INSERT INTO `user_183` (`id_user`, `username`, `password`, `nama_user`) VALUES
(1, 'user1', 'user123', 'Pengguna Umum');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_183`
--
ALTER TABLE `admin_183`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `kasus`
--
ALTER TABLE `kasus`
  ADD PRIMARY KEY (`id_kasus`);

--
-- Indexes for table `pelapor`
--
ALTER TABLE `pelapor`
  ADD PRIMARY KEY (`id_pelapor`);

--
-- Indexes for table `pengacara`
--
ALTER TABLE `pengacara`
  ADD PRIMARY KEY (`id_pengacara`);

--
-- Indexes for table `terlapor`
--
ALTER TABLE `terlapor`
  ADD PRIMARY KEY (`id_terlapor`);

--
-- Indexes for table `user_183`
--
ALTER TABLE `user_183`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_183`
--
ALTER TABLE `admin_183`
  MODIFY `id_admin` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `kasus`
--
ALTER TABLE `kasus`
  MODIFY `id_kasus` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pelapor`
--
ALTER TABLE `pelapor`
  MODIFY `id_pelapor` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pengacara`
--
ALTER TABLE `pengacara`
  MODIFY `id_pengacara` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `terlapor`
--
ALTER TABLE `terlapor`
  MODIFY `id_terlapor` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_183`
--
ALTER TABLE `user_183`
  MODIFY `id_user` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
