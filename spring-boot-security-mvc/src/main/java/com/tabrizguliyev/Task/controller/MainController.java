/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabrizguliyev.Task.controller;

import com.tabrizguliyev.Task.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tabrizguliyev
 */
@Controller
public class MainController {

    @RequestMapping("/main")
    public String page(Model model) {
        Test.test();
        System.out.println("salam kkkk  a heyo alma");
        System.out.println("ldjkn sss  a heyo alma");
        System.out.println("te   st    jn");
        System.out.println("ooooooo");
        System.out.println("hhhhhhh");
        model.addAttribute("test","rrr");
        return "main";
    }

}
