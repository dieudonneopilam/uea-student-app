
-- REQUÊTES SQL DE TEST
-- UEA Student Services - Mini Système d'Inscription aux Cours

-- 1. Lister tous les étudiants inscrits dans un cours donné
-- Remplacez 'COURSE_CODE' par le code du cours souhaité

SELECT s.id, s.nom, s.post_nom, s.matricule, s.faculte, s.email
FROM students s
INNER JOIN student_course sc ON s.id = sc.student_id
INNER JOIN courses c ON sc.course_id = c.id
WHERE c.code = 'COURSE_CODE'
ORDER BY s.nom, s.post_nom;


-- 2. Trouver les cours sans étudiants

SELECT c.id, c.code, c.nom, c.credits, c.enseignant
FROM courses c
LEFT JOIN student_course sc ON c.id = sc.course_id
WHERE sc.course_id IS NULL
ORDER BY c.code;



-- 3. Supprimer toutes les inscriptions d'un étudiant sans supprimer l'étudiant lui-même
-- Remplacez STUDENT_ID par l'ID de l'étudiant souhaité

DELETE FROM student_course
WHERE student_id = STUDENT_ID;

-- Exemple avec un ID spécifique (remplacez par un ID réel)
-- DELETE FROM student_course
-- WHERE student_id = 1;

-- Pour vérifier que l'étudiant existe toujours après la suppression
-- SELECT * FROM students WHERE id = STUDENT_ID;


