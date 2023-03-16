package tfip.lovecalculator.day16homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import tfip.lovecalculator.day16homework.model.LoveCalculatorInput;
import tfip.lovecalculator.day16homework.model.LoveCalculatorResult;
import tfip.lovecalculator.day16homework.service.LoveService;

@Controller
public class LoveController {

    @Autowired
    LoveService svc;

    @GetMapping(path = "/")
    public String showIndex(@ModelAttribute LoveCalculatorInput input, Model model) {
        model.addAttribute("input", new LoveCalculatorInput());
        model.addAttribute("result", null);
        return "index";
    }

    @GetMapping(path="result")
    public String calcResult(@ModelAttribute LoveCalculatorInput input, Model model) {
        if(input == null || 
        (input.getFname() == null && input.getSname() == null) || 
        (input.getFname().isEmpty() && input.getSname().isEmpty())) {
            // no input = new page
            return "redirect:/";
        } else {
            // got input = calculate results
            LoveCalculatorResult result = svc.calculateLove(input);            
            model.addAttribute("input", input);
            model.addAttribute("result", result);
            
            System.out.println("hello" + result);
        }
        
        return "index";
    }
}
