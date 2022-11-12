USE mobapp;
DROP DATABASE IF EXISTS mobapp;
CREATE DATABASE mobapp;
USE mobapp;
CREATE TABLE Roles (
    role_id CHAR(10) NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (role_id)
);
CREATE TABLE Status (
    status_id CHAR(10) NOT NULL,
    status_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (status_id)
);
CREATE TABLE PaymentCategory (
    category_id CHAR(10) NOT NULL,
    payment_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (category_id)
);
CREATE TABLE Users (
    user_id CHAR(10) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role_id CHAR(10) NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (role_id) REFERENCES Roles(role_id),
    UNIQUE(email)
);
CREATE TABLE Food (
    food_id CHAR(10) NOT NULL,
    food_name VARCHAR(50) NOT NULL,
    food_price INT NOT NULL,
    food_image VARCHAR(300) NOT NULL,
    merchant_id CHAR(10) NOT NULL,
    PRIMARY KEY (food_id),
    FOREIGN KEY (merchant_id) REFERENCES Users(user_id)
);
CREATE TABLE OrderDetail (
    order_id CHAR(10) NOT NULL,
    food_id CHAR(10) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (food_id) REFERENCES Food(food_id)
);
CREATE TABLE Payment (
    payment_id CHAR(10) NOT NULL,
    category_id CHAR(10) NOT NULL,
    totalPayment INT NOT NULL,
    PRIMARY KEY (payment_id),
    FOREIGN KEY (category_id) REFERENCES PaymentCategory(category_id)
);
CREATE TABLE Orders (
    order_id CHAR(10) NOT NULL,
    user_id CHAR(10) NOT NULL,
    status_id CHAR(10) NOT NULL,
    payment_id CHAR(10) NOT NULL,
    orderDate DATETIME NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (order_id) REFERENCES OrderDetail(order_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (status_id) REFERENCES Status(status_id),
    FOREIGN KEY (payment_id) REFERENCES Payment(payment_id),
    UNIQUE(order_id)
);
INSERT INTO Roles
VALUES ("ROLES00001", "Customer");
INSERT INTO Roles
VALUES ("ROLES00002", "Merchant");
INSERT INTO Roles
VALUES ("ROLES00003", "Admin");
INSERT INTO Users
VALUES (
        "C000000001",
        "Customer1",
        "pass",
        "email@a.com",
        "ROLES00001"
    );
INSERT INTO Users
VALUES (
        "C000000002",
        "Customer2",
        "pass",
        "email@a1.com",
        "ROLES00001"
    );
INSERT INTO Users
VALUES (
        "C000000003",
        "Customer3",
        "pass",
        "email@a2.com",
        "ROLES00001"
    );
INSERT INTO Users
VALUES (
        "M000000001",
        "Merchant1",
        "pass",
        "email@merchant.com",
        "ROLES00002"
    );
INSERT INTO Users
VALUES (
        "M000000002",
        "Merchant2",
        "pass",
        "email@merchant2.com",
        "ROLES00002"
    );
INSERT INTO Users
VALUES (
        "A000000001",
        "Admin1",
        "pass",
        "email@admin.com",
        "ROLES00003"
    );
INSERT INTO Status
VALUES ("Status0001", "Pending");
INSERT INTO Status
VALUES ("Status0002", "Ready");
INSERT INTO Status
VALUES ("Status0003", "Finished");
INSERT INTO PaymentCategory
VALUES ("Payment001", "Gopay");
INSERT INTO PaymentCategory
VALUES ("Payment002", "Ovo");
INSERT INTO PaymentCategory
VALUES ("Payment003", "Dana");
INSERT INTO PaymentCategory
VALUES ("Payment004", "M-BCA");
INSERT INTO Food
VALUES (
        "F000000001",
        "Tempe Mendoan",
        "2000",
        "images/1.jpg",
        "M000000001"
    );
INSERT INTO Food
VALUES (
        "F000000002",
        "Bakso kiloan",
        "9000",
        "images/2.jpg",
        "M000000002"
    );
INSERT INTO Food
VALUES (
        "F000000003",
        "Nasi Rebus",
        "15000",
        "images/3.jpg",
        "M000000002"
    );
