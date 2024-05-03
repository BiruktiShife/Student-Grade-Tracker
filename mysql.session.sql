CREATE TABLE student(
    userName VARCHAR(255) NOT NULL,
    id INT PRIMARY KEY,
    grade FLOAT NOT NULL
);

CREATE TABLE courses(
    courseId INT NOT NULL,
    courseName VARCHAR(255) NOT NULL,
    Grade VARCHAR(255) NOT NULL,
    creditHr INT NOT NULL
);
delete FROM courses;
delete  FROM student;
DROP TABLE courses;

ALTER TABLE courses
DROP COLUMN courseName;

SELECT * FROM student;
SELECT * FROM courses;

