-- V1__create_user_form_table.sql
CREATE TABLE user_form (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    agreed BOOLEAN NOT NULL
);