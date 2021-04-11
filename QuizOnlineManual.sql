USE J3LP0014_QuizOnline

INSERT INTO TblUsers (email, password, name, role, status)
VALUES ('minhtn1709', 'minhtn1709', 'Nhat Minh', 'Student', 'New')

SELECT subId, subName, timeDoQuiz, numberQuestionOfQuiz
FROM TblSubject

INSERT INTO TblQuestionBank (questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, status, subId)
VALUES ('2', '1', '1', '1', '1', '1', 'A', 'Active', 'CEA201')

SELECT questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, qb.subId
FROM TblQuestionBank qb, TblSubject s 
WHERE qb.subId = s.subID AND questionContent LIKE N'%functionally%' AND status = 'active' AND qb.subId = 'PRJ321'
GROUP BY questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, qb.subId
ORDER BY qb.subId, questionContent DESC
OFFSET 0 ROWS
FETCH NEXT 20 ROWS ONLY

UPDATE TblQuestionBank SET status = 'Inactive' WHERE questionId = '20210307-051000-PRJ321'

SELECT questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, subId
FROM TblQuestionBank
WHERE questionId = '20210307-051300-PRJ321'

SELECT questionId
FROM TblQuizAnswer
WHERE questionId = '20210307-051300-PRJ321'

UPDATE TblQuestionBank SET questionContent = ?, optionA = ?, optionB = ?, optionC = ?, optionD = ?, answerCorrect = ?, createDate = ?, status = ?, subId = ? WHERE questionId = '20210307-051000-PRJ321'

SELECT COUNT(questionId) AS TotalQuestion
FROM TblQuestionBank
WHERE status = 'Active' 
GROUP BY subId
HAVING COUNT(questionId) <= ALL (SELECT COUNT(questionId)
								FROM TblQuestionBank
								WHERE status = 'Active' 
								GROUP BY subId)

UPDATE TblSubject
SET timeDoQuiz = '', numberQuestionOfQuiz =''
WHERE subId = ?

SELECT TOP(10) questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, s.subName 
FROM TblQuestionBank q, TblSubject s 
WHERE q.subId = s.subId AND status = 'active' AND s.subId = 'CEA201'
ORDER BY NEWID() 

SELECT questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, subId, answer
FROM TblQuestionBank 
WHERE questionId IN (SELECT questionId 
						FROM TblQuizAnswer 
						WHERE quizId = ?)

SELECT *
FROM TblQuestionBank qb, TblQuizAnswer qa
WHERE qb.questionId = qa.questionId AND
 qa.questionId IN (SELECT questionId 
						FROM TblQuizAnswer 
						WHERE quizId = '1')

SELECT  qa.questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, subId, quizId, answer
FROM TblQuestionBank q, TblQuizAnswer qa
WHERE  q.questionId = qa.questionId AND qa.questionId IN (SELECT questionId 
															FROM TblQuizAnswer 
															WHERE quizId = '20210315-104202-PRJ321-506')

SELECT quizId, dateDidQuiz, score, numberOfCorrectAnswer, timeDoQuiz, numberQuestionOfQuiz, subId, q.email, u.name
FROM TblQuiz q, TblUsers u
WHERE q.email = u.email AND quizId = '1'

INSERT INTO TblQuizAnswer 
(questionId, quizId, answer) 
VALUES ('20210307-051922-PRJ321', '1', 'C')

SELECT quizId, dateDidQuiz, score, numberOfCorrectAnswer, timeDoQuiz, numberQuestionOfQuiz, subId, u.email
FROM TblQuiz q, TblUsers u
WHERE q.email = u.email AND u.email = 'minhtn1709@gmail.com' AND subId = 'CEA201' AND timeDoQuiz = '10'