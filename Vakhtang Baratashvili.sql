CREATE TABLE teacher
  (
     teacher_id INT,
     first_name VARCHAR,
     last_name  VARCHAR,
     gender     VARCHAR,
     subject    VARCHAR
  );

CREATE TABLE pupils
  (
     pupil_id   INT,
     first_name VARCHAR,
     last_name  VARCHAR,
     gender     VARCHAR,
     class      VARCHAR
  );

CREATE TABLE teacher_pupil
  (
     teacher_id INT,
     pupil_id   INT
  );

INSERT INTO teacher
            (teacher_id,
             first_name,
             last_name,
             gender,
             subject)
VALUES      (1,
             'Vakhtang',
             'Baratashvili',
             'Male',
             'Math'),
            (2,
             'Saba',
             'Baratashvili',
             'Male',
             'Literature'),
            (3,
             'Bidzina',
             'Tabagari',
             'Male',
             'Physics'),
            (4,
             'Gvantsa',
             'Andriadze',
             'Female',
             'Chemistry');

INSERT INTO pupils
            (pupil_id,
             first_name,
             last_name,
             gender,
             class)
VALUES      (1,
             'Lekso',
             'Nizharadze',
             'Male',
             '5a'),
            (2,
             'Giorgi',
             'Nizharadze',
             'Male',
             '5a'),
            (3,
             'Monica',
             'Silagadze',
             'Female',
             '5b'),
            (4,
             'Giorgi',
             'Andriadze',
             'Male',
             '6c'),
            (5,
             'Tornike',
             'Nizharadze',
             'Male',
             '6c');

INSERT INTO teacher_pupil
            (teacher_id,
             pupil_id)
VALUES      (1, 1),
            (1, 3),
            (1, 4),
            (2, 1),
            (2, 4),
            (2, 2),
            (3, 1),
            (3, 2),
            (3, 3),
            (3, 4),
            (4, 2),
            (4, 4);

SELECT t.first_name,
       t.last_name,
       s.first_name
FROM   teacher t
       INNER JOIN teacher_pupil ts
               ON t.teacher_id = ts.teacher_id
       INNER JOIN pupils s
               ON ts.pupil_id = s.pupil_id
WHERE  s.first_name = 'Giorgi' 