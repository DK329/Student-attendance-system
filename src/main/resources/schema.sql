CREATE TABLE IF NOT EXISTS Student
(
    id      VARCHAR(20) PRIMARY KEY,
    name    VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS Profile_Picture(
    student_id VARCHAR(20) PRIMARY KEY,
    picture MEDIUMBLOB NOT NULL,
    CONSTRAINT fk_student_picture FOREIGN KEY (student_id) REFERENCES Student (id)
);
CREATE TABLE IF NOT EXISTS Attendance(
      student_id VARCHAR(20),
      id INT PRIMARY KEY AUTO_INCREMENT,
      status ENUM ('IN','OUT') NOT NULL,
      stamp DATETIME NOT NULL,
      CONSTRAINT fk_student_attendance FOREIGN KEY (student_id) REFERENCES Student (id)
);
CREATE TABLE IF NOT EXISTS User
(
    user_name VARCHAR(50) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);