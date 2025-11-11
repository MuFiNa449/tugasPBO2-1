-- SQL script for database pbo_2310010183
CREATE DATABASE IF NOT EXISTS pbo_2310010183;
USE pbo_2310010183;

CREATE TABLE IF NOT EXISTS kasus (
    id_kasus INT AUTO_INCREMENT PRIMARY KEY,
    nama_kasus VARCHAR(100),
    jenis_kasus VARCHAR(100),
    tanggal_mulai DATE,
    status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS pelapor (
    id_pelapor INT AUTO_INCREMENT PRIMARY KEY,
    nama_pelapor VARCHAR(100),
    alamat VARCHAR(200),
    no_telp VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS terlapor (
    id_terlapor INT AUTO_INCREMENT PRIMARY KEY,
    nama_terlapor VARCHAR(100),
    alamat VARCHAR(200),
    no_telp VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS pengacara (
    id_pengacara INT AUTO_INCREMENT PRIMARY KEY,
    nama_pengacara VARCHAR(100),
    spesialisasi VARCHAR(100),
    no_telp VARCHAR(50)
);
