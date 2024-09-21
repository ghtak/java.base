package com.ghtak.hellospring;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Slf4j
@SpringBootTest
class HellospringApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private EntityManager entityManager;

    Question addQuestion(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreatedAt(LocalDateTime.now());
        this.questionRepository.save(q);
        return q;
    }

    Answer addAnswer(String content, Question q){
        Answer a = new Answer();
        a.setContent(content);
        a.setQuestion(q);
        a.setCreatedAt(LocalDateTime.now());
        this.answerRepository.save(a);
        return a;
    }

    @Transactional
    @Test
    void testJpa() {
        var q1 = this.addQuestion("Sjb1", "Ctnt1");
        var q2 = this.addQuestion("Sjb2", "Ctnt2");

        Assertions.assertEquals(this.questionRepository.findAll().stream().count(), 2);

        Assertions.assertEquals(this.questionRepository.findById(q1.getId()).get().getContent(), q1.getContent());
        Assertions.assertEquals(this.questionRepository.findById(q2.getId()).get().getContent(), q2.getContent());

        Assertions.assertEquals(this.questionRepository.findBySubject("Sjb1").getContent(), q1.getContent());
        Assertions.assertEquals(this.questionRepository.findBySubjectLike("%b1").get(0).getContent(), q1.getContent());

        var a1 = this.addAnswer("Ans1", q1);
        Assertions.assertTrue(this.answerRepository.findById(a1.getId()).isPresent());
        Assertions.assertEquals(this.answerRepository.findById(a1.getId()).get().getContent(), a1.getContent());
        Assertions.assertEquals(this.answerRepository.findById(a1.getId()).get().getQuestion().getId(), q1.getId());

        entityManager.clear();

        var q = this.questionRepository.findById(q1.getId()).get();
        Assertions.assertEquals(q.getAnswers().size(), 1);
    }

}
