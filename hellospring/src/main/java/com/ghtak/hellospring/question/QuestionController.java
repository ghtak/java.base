package com.ghtak.hellospring.question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model){
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id){
        Question q = this.questionService.findById(id);
        model.addAttribute("question", q);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(){
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@RequestParam(value = "subject") String subject,
                                 @RequestParam(value = "content") String content)
    {
        this.questionService.create(subject, content);
        return "redirect:/question/list";
    }
}
