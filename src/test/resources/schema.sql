CREATE TABLE `book` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `author` VARCHAR(255),
    `publication_date` DATE,
    `title` VARCHAR(255)
);

CREATE TABLE `book_copy` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `status` VARCHAR(255),
    `book_id` INT NOT NULL,
    CONSTRAINT `fk_book` FOREIGN KEY (`book_id`) REFERENCES `book`(`id`)
);

CREATE TABLE `users` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `account_created` DATE,
    `name` VARCHAR(255),
    `surname` VARCHAR(255)
);

CREATE TABLE `rent` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `rent_date` DATE,
    `return_date` DATE,
    `book_copy_id` INT,
    `user_id` INT,
    CONSTRAINT `fk_book_copy_id` FOREIGN KEY (`book_copy_id`) REFERENCES `book_copy`(`id`),
    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);