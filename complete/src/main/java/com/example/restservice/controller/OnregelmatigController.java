package com.example.restservice.controller;

import com.example.restservice.resource.Onregelmatig;
import com.example.restservice.service.OnregelmatigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OnregelmatigController {

    @Autowired
    OnregelmatigService onregelmatigService;


    @GetMapping("/view/{startNum}/{endNum}")
    public String load(@PathVariable("startNum") Integer startNum, @PathVariable("endNum") Integer endNum, Model model) {
        onregelmatigService.resetScores();
        onregelmatigService.readFile(startNum, endNum);

        List<Onregelmatig> onregelmatigs = onregelmatigService.getKeys().stream()
                .map(key -> onregelmatigService.getWordMap().get(key)).collect(Collectors.toList());

        model.addAttribute("onregelmatigs", onregelmatigs);

        return "practice";
    }

    @GetMapping("/view")
    public String view(Model model) {
        Onregelmatig onregelmatig = new Onregelmatig();
        onregelmatig.setInfinitief(onregelmatigService.getInfinitief());
        model.addAttribute("onregelmatig", onregelmatig);
        return "view";
    }

    @PostMapping(value = "/view")
    public String submit(@ModelAttribute Onregelmatig onregelmatig, Model model) {

        model.addAttribute("onregelmatig", onregelmatig);
        model.addAttribute("score", onregelmatigService.processAnswer(onregelmatig));

        return "answer";
    }
}
