package com.web.law.controller;

import com.web.law.domain.Lawyer;
import com.web.law.domain.Question;
import com.web.law.domain.User;
import com.web.law.enums.LawyerTypeEnum;
import com.web.law.enums.QuestionStatusEnum;
import com.web.law.service.LawyerService;
import com.web.law.service.QuestionService;
import com.web.law.service.UserService;
import com.web.law.utils.FileUploadAndDowloadUtils;
import com.web.law.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : QuestionController
 * @Description :
 * @Author : lixin
 * @Date: 2020-08-25 22:56
 */
@RequestMapping("/question")
@Controller
public class QuestionController extends BaseController<Question> {

    private UserService userService;

    private QuestionService questionService;

    private LawyerService lawyerService;

    @Autowired
    public QuestionController(QuestionService questionService,LawyerService lawyerService,UserService userService){
        this.questionService = questionService;
        this.lawyerService = lawyerService;
        this.userService = userService;
        super.init(questionService);
    }

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5")int size, @RequestParam(defaultValue = "0")int status,Model model){
        Page<Question> pageInfo = questionService.getPageByStatus(page,size,status);
        List<Lawyer> lawyerList = lawyerService.findAll();
        List<User> userList = userService.findAll();
        model.addAttribute("lawyerList",lawyerList);
        model.addAttribute("userList",userList);
        model.addAttribute("pageInfo",pageInfo);
        return "question/list";
    }

    @RequestMapping("/toadd")
    public String toadd(Model model){
        List<Lawyer> lawyerList = lawyerService.findAll();
        List<User> userList = userService.findAll();
        model.addAttribute("lawyerList",lawyerList);
        model.addAttribute("userList",userList);
        return "question/add";
    }
    @RequestMapping("/toedit")
    public String toedit(String id,Model model){
        List<Lawyer> lawyerList = lawyerService.findAll();
        List<User> userList = userService.findAll();
        model.addAttribute("lawyerList",lawyerList);
        model.addAttribute("userList",userList);

        Question question = questionService.findById(id);
        model.addAttribute("question",question);
        return "question/edit";
    }

    @RequestMapping("/add")
    public String add(Question question, MultipartFile file, HttpServletRequest request){
        question.setQuestionId(KeyUtils.genItemId());
        String url = FileUploadAndDowloadUtils.upload(file,request);
        question.setQuestion_appendix(url);
        question.setCreatetime(new Date());
        Lawyer lawyer = lawyerService.findById(question.getLawyerId());
        if(lawyer.getLawyerType() == LawyerTypeEnum.FREE.getCode()){
            question.setPayment(new BigDecimal(0.0));
        }else{
            question.setPayment(new BigDecimal(lawyer.getLawerExpense()));
        }

        question.setStatus(QuestionStatusEnum.PAYING.getCode());

        insert(question);
        return "redirect:list";
    }

    @RequestMapping("/edit")
    public String edit(Question question){
        update(question);
        return "redirect:list";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        remove(id);
        return "redirect:list";
    }
}