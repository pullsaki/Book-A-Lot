
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `admin` (
  `adminid` int(11) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `bookings` (
  `bookingid` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `vehicle_type` varchar(50) NOT NULL,
  `vehicle_no` varchar(50) NOT NULL,
  `locationid` int(8) DEFAULT NULL,
  `slotid` varchar(20) NOT NULL,
  `date` varchar(30) NOT NULL,
  `time` varchar(20) NOT NULL,
  `duration` varchar(10) NOT NULL,
  `cost` varchar(100) NOT NULL,
  `paid` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `locations` (
  `locationid` int(8) UNSIGNED NOT NULL,
  `location_name` varchar(80) NOT NULL,
  `area` varchar(100) NOT NULL,
  `slots` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `slots` (
  `slotid` varchar(20) NOT NULL,
  `locationid` int(8) NOT NULL,
  `slotno` varchar(20) NOT NULL,
  `status` int(8) NOT NULL,
  `time` varchar(20) NOT NULL,
  `duration` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `users` (
  `email` varchar(50) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `mobno` varchar(15) NOT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `vehicle` (
  `vehicleid` int(8) NOT NULL,
  `vehicle_type` varchar(50) NOT NULL,
  `cost` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminid`);


ALTER TABLE `bookings`
  ADD PRIMARY KEY (`bookingid`),
  ADD KEY `fk_email` (`email`);


ALTER TABLE `locations`
  ADD PRIMARY KEY (`locationid`);


ALTER TABLE `slots`
  ADD PRIMARY KEY (`slotid`);


ALTER TABLE `users`
  ADD PRIMARY KEY (`email`);

ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`vehicleid`);


ALTER TABLE `bookings`
  MODIFY `bookingid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;


ALTER TABLE `locations`
  MODIFY `locationid` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;


ALTER TABLE `vehicle`
  MODIFY `vehicleid` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;


ALTER TABLE `bookings`
  ADD CONSTRAINT `fk_email` FOREIGN KEY (`email`) REFERENCES `users` (`email`);
COMMIT;

