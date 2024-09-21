package com.ghtak.hellospring.question;

import com.ghtak.hellospring.answer.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    Question addQuestion(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreatedAt(LocalDateTime.now());
        this.questionRepository.save(q);
        return q;
    }

    public List<Question> getList(){
        if (this.questionRepository.count() == 0){
            this.addQuestion("Sjb1", "Ctnt1");
            this.addQuestion("Sjb2", "Ctnt2");
        }
        return this.questionRepository.findAll();
    }
}
