CREATE USER 'studentdb_user'@'localhost' IDENTIFIED BY 'st_account';

GRANT ALL PRIVILEGES ON simple_student_account.* TO 'studentdb_user'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'studentdb_user'@'localhost';