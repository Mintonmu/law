package com.web.law.controller;


import com.web.law.domain.Lawyer;
import com.web.law.domain.Vip;
import com.web.law.service.InfoService;
import com.web.law.service.LawyerService;
import com.web.law.service.UserService;
import com.web.law.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

/**
 * 前台所有请求controller
 */
@Controller
@RequestMapping("/fore")
public class ForeController {

    @Autowired
    private UserService userService;

    @Autowired
    private LawyerService lawyerService;

    @Autowired
    private InfoService infoService;

    @Autowired
    private VipService vipService;

    @RequestMapping("/index")
    public String index(Model model){
        List<Lawyer> lawyerList = lawyerService.findAll();
        Collections.shuffle(lawyerList);
        int size = lawyerList.size() / 2;
        if(size > 7){
            size = 7;
        }
//        lawyerList = lawyerList.subList(0,size);
        model.addAttribute("lawyerList",lawyerList);
        return "forepage/index";
    }


}
