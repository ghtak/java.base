package com.ghtak.hellospring.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository  extends JpaRepository<Question, Long> {
    Question findBySubject(String subject);
    List<Question> findBySubjectLike(String subject);
}
