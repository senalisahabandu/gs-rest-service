package com.example.restservice.controller;

import com.example.restservice.resource.Answer;
import com.example.restservice.resource.Onregelmatig;
import com.example.restservice.service.OnregelmatigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Scope("session")
public class OnregelmatigController {

    @Autowired
    OnregelmatigService onregelmatigService;


    @GetMapping("/view/{startNum}/{endNum}")
    public String load(@PathVariable("startNum") Integer startNum, @PathVariable("endNum") Integer endNum, Model model,
                       HttpServletRequest request) throws IOException {
        String sessionId = request.getSession().getId();
        onregelmatigService.resetScores(sessionId);
        onregelmatigService.readFile(startNum, endNum, sessionId);

        List<Onregelmatig> onregelmatigs = onregelmatigService.getKeys(sessionId).stream()
                .map(key -> onregelmatigService.getSessionMap().get(sessionId).getWordMap().get(key))
                .collect(Collectors.toList());

        model.addAttribute("onregelmatigs", onregelmatigs);

        return "practice";
    }

    @GetMapping("/view")
    public String view(Model model, HttpServletRequest request) {
        Onregelmatig onregelmatig = new Onregelmatig();
        onregelmatig.setInfinitief(onregelmatigService.getInfinitief(request.getSession().getId()));
        model.addAttribute("onregelmatig", onregelmatig);
        return "view2";
    }

    @PostMapping(value = "/view")
    public String submit(@ModelAttribute Onregelmatig onregelmatig, Model model, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        Answer answer = onregelmatigService.processAnswer(onregelmatig, sessionId);
        model.addAttribute("onregelmatig", onregelmatig);
        model.addAttribute("answer", answer);

        if (onregelmatigService.getKeys(sessionId).size() == 0) {
            model.addAttribute("start", onregelmatigService.getEnd(sessionId));
            model.addAttribute("end", onregelmatigService.getEnd(sessionId) +
                    onregelmatigService.getEnd(sessionId) - onregelmatigService.getStart(sessionId));
            return "final";
        }

        return "answer";
    }
}
