package com.sf.stylefusion.controller;


import com.sf.stylefusion.dto.PortfolioDto;
import com.sf.stylefusion.service.PortfolioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PortfolioController {
    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/add_portfolio")
    public  String getPortfolioForm(Model model){
        PortfolioDto portfolioDto = new PortfolioDto();
        model.addAttribute("portfolio", portfolioDto);
        return "add_portfolio";
    }


    @PostMapping("/add_portfolio")
    public String addPortfolio(@ModelAttribute("portfolio")PortfolioDto portfolioDto){
        portfolioService.savePortfolio(portfolioDto);
        return "redirect:/admin";
    }

    @GetMapping("/delete_profile/{id}")
    public String deletePortfolio(@PathVariable("id")int id ){
         portfolioService.deletePortfolioById(id);
        return  "redirect:/admin";
    }

@GetMapping("/update/{id}")
        public  String updatePortfolio(@PathVariable("id")int id, Model model){
            System.out.println("ID from URL: " + id);
            model.addAttribute("portfolio", portfolioService.getPortfolioById(id).orElse(null));
            return "update_portfolio";

      }
    @PostMapping("/update")
    public String updatePortfolioForm(@ModelAttribute PortfolioDto portfolioDto){
        portfolioService.updatePortfolio(portfolioDto);
        return "redirect:/admin";
    }
    @GetMapping("/update-model/{id}")
    public  String updateModel(@PathVariable("id")int id, Model model){
        System.out.println("ID from URL: " + id);
        model.addAttribute("portfolio", portfolioService.getPortfolioById(id).orElse(null));
        return "update_model";

    }
    @PostMapping("/update_model")
    public String updateModelForm(@ModelAttribute PortfolioDto portfolioDto){
        portfolioService.updatePortfolio(portfolioDto);
        return "redirect:/model";
    }
    @GetMapping("/add_model")
    public  String getModelForm(Model model){
        PortfolioDto portfolioDto = new PortfolioDto();
        model.addAttribute("portfolio", portfolioDto);
        return "add_model";
    }
    @PostMapping("/add_model")
    public String addModel(@ModelAttribute("portfolio")PortfolioDto portfolioDto){
        portfolioService.savePortfolio(portfolioDto);
        return "redirect:/model";
    }
    @GetMapping("/delete_model/{id}")
    public String deleteModelPortfolio(@PathVariable("id")int id ){
        portfolioService.deletePortfolioById(id);
        return  "redirect:/model";
    }
}
