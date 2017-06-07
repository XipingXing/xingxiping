package com.xxp.learn.springboot.controller;

import com.xxp.learn.springboot.vo.Person;
import com.xxp.learn.springboot.vo.WebStocketMessage;
import com.xxp.learn.springboot.vo.WebStocketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制器
 * Created by xingxiping on 2017/6/7.
 */
@Controller
public class DemoController {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @RequestMapping(value = "/")
    public String hell(Model model) {
        Person single = new Person("aa", 11);
        List<Person> person = new ArrayList<>();

        Person p1 = new Person("xx", 11);
        Person p2 = new Person("yy", 22);
        Person p3 = new Person("zz", 33);

        person.add(p1);
        person.add(p2);
        person.add(p3);

        model.addAttribute("singlePerson", single);
        model.addAttribute("people", person);

        return "index";
    }

    @RequestMapping(value = "/json", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String json(Model model) {
        Person sigle = new Person("aa", 11);
        model.addAttribute("single", sigle);
        return "jsonView";
    }

    @MessageMapping("/welcome")
    public void sya(WebStocketMessage message) throws InterruptedException {
        while (true) {
            Thread.sleep(3000);
            messagingTemplate.convertAndSend("/topic/getResponse", "Welcome, " + message.getName() + "-" + System.currentTimeMillis() + "!");
        }
    }

}
