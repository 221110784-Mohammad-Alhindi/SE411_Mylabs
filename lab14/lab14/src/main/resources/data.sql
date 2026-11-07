INSERT INTO course (id, code, name, description, credits)
VALUES (1, 'SE411', 'Software Engineering', 'Introduction to software engineering', 3);

INSERT INTO course (id, code, name, description, credits)
VALUES (2, 'CS330', 'Operating Systems', 'Introduction to operating systems', 3);

INSERT INTO course (id, code, name, description, credits)
VALUES (3, 'CS340', 'Database Systems', 'Introduction to database systems', 3);

INSERT INTO course (id, code, name, description, credits)
VALUES (4, 'CYS401', 'Cybersecurity', 'Fundamentals of cybersecurity', 3);

INSERT INTO course (id, code, name, description, credits)
VALUES (5, 'SE322', 'Software Design', 'Software design and architecture', 3);
ALTER TABLE course ALTER COLUMN id RESTART WITH 6;