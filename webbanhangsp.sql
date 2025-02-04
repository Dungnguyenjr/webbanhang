-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 14, 2025 lúc 10:55 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `webbanhangsp`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `email`, `full_name`, `password`, `role`, `username`, `image`, `is_active`, `uuid`) VALUES
(7, 'kamehalv222@gmail.com.vn', 'nguyendung', '$2a$10$r4DQgB/amyZlND6CNKlpouVXSwrFpvd9D3Vk65tZVQYc4gbiTE6fy', 'EMP', 'dunggg', '2023_02_11_16_19_IMG_4503.jpg', b'1', '3c7b7249-99eb-4f36-8ba3-77d93df305ae'),
(10, 'supervitmomo@gmail.com', 'dungngnuyen', '$2a$10$w1ZrHYz/G5RRQllJ/.TmMeDHxSmxDrsrBqa8OnV3Wy84m6shqyL4y', 'ADMIN', 'dungad', '2023_02_11_16_19_IMG_4503.jpg', b'1', '530b1ad8-b712-4f85-80ef-6f3bc7e223b4'),
(12, 'kamehalv1@gmail.com', 'bacninh', '$2a$10$Q8zakOLWXFIICQbMYOc5iOvVj6wo46C9SX.l27kLQTsqKDN5DDBmK', 'EMP', 'bacninh', '2023_02_11_16_57_IMG_4507.jpg', b'1', '33edb314-e45e-4cb6-a74f-11be3556d4c1'),
(16, 'kamehalv1@gmail.com', 'dungaaa', '$2a$10$nNo.GMEUDG7dG5weuc7Td.NiJ9Bs7wicRIQSeNiRGmiDVtBQL1jV.', 'ADMIN', 'admin', '55668000-a9d9-41d1-93e1-f3fa2c631985.jpg', b'1', '1b5561cf-596a-4aad-984c-872813206ab5'),
(17, 'khanhlqd2003svm@gmail.com', 'khanh', '$2a$10$j4Q79cc8nLKk6AGE0qG4mux6zm4rxixbEfBibhGwnSb1.4JNeS.wG', 'EMP', 'khanh', '874e28b4-3225-4c87-98fc-92990ce8ab03.jpg', b'0', '085a4357-62b5-484a-b265-94e8f2373cbe');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `adproductype`
--

CREATE TABLE `adproductype` (
  `id` int(11) NOT NULL,
  `productcode` varchar(255) DEFAULT NULL,
  `productdescription` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `adproductype`
--

INSERT INTO `adproductype` (`id`, `productcode`, `productdescription`) VALUES
(17, 'a12312', 'aaaaaa'),
(20, 'aaaaabbbb', 'aaaaâbbbb');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'catg?y');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `department`
--

CREATE TABLE `department` (
  `id` int(11) NOT NULL,
  `department_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `department`
--

INSERT INTO `department` (`id`, `department_name`) VALUES
(1, 'ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `imported_date` datetime(6) DEFAULT NULL,
  `importprice` bigint(20) UNSIGNED DEFAULT NULL,
  `price` bigint(20) UNSIGNED DEFAULT NULL,
  `price_sale` smallint(5) UNSIGNED DEFAULT NULL,
  `product_code` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` smallint(5) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `description`, `image`, `imported_date`, `importprice`, `price`, `price_sale`, `product_code`, `product_name`, `quantity`) VALUES
(13, 'aaa', 'gà.jpg', '2024-11-18 00:00:00.000000', 200000, 100000, 12, 'a12312', 'aaaaa', 11),
(44, 'aaaaa', '874e28b4-3225-4c87-98fc-92990ce8ab03.jpg', '2024-11-19 00:00:00.000000', 1000, 2000, 5, 'á', 'bbb', 1),
(46, 'qqqqqqqqqqqq', 'z5574226147444_5de525ac6e11ff42bd93434d0d42f640.jpg', '2024-11-18 00:00:00.000000', 5, 5, 10, 'a12312', 'i', 10),
(47, 'aaaaaaaaaaaaaa', '383a5c253f0e8450dd1f.jpg', '2024-11-19 00:00:00.000000', 200000, 100000, 10, 'aaaaabbbb', 'n', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKgex1lmaqpg0ir5g1f5eftyaa1` (`username`);

--
-- Chỉ mục cho bảng `adproductype`
--
ALTER TABLE `adproductype`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK383i0awxqlq7pc33hil7afbgo` (`product_name`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `adproductype`
--
ALTER TABLE `adproductype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `department`
--
ALTER TABLE `department`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
