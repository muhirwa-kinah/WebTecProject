package com.sf.stylefusion.controller;

import com.sf.stylefusion.dto.PortfolioDto;
import com.sf.stylefusion.dto.UserDto;
import com.sf.stylefusion.model.Portfolio;
import com.sf.stylefusion.model.User;
import com.sf.stylefusion.service.PortfolioService;
import com.sf.stylefusion.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {

    private final UserService userService;
    private final PortfolioService portfolioService;

    public AuthController(UserService userService, PortfolioService portfolioService) {
        this.userService = userService;
        this.portfolioService = portfolioService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }
    @GetMapping("/login")
    public  String showLoginForm(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user",user);
        return "signup";
    }
    @GetMapping("/reset-password")
    public String showResetForm(Model model){
//        UserDto userDto = new UserDto();
        System.out.println("im being called atleast");
        model.addAttribute("email", "");
        return "reset-password";
    }

    @GetMapping("/admin")
        public String getDashboard(Model model){
            model.addAttribute("portfolios" , portfolioService.getAllPortfolios());
            return "dashboard";
    }

//    @PreAuthorize("hasAuthority('MODEL')")
    @GetMapping("/model")
    public String getModelDash( Model model){
        System.out.println("========================I'm getting called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();;
        String email = user.getUsername();
        Optional<Portfolio> portfolio = Optional.of(new Portfolio());
        portfolio = portfolioService.getPortfolioByEmail(email);
        if(!portfolio.isEmpty()){
            model.addAttribute("portfolio", portfolio);
        }else{
            model.addAttribute("notification", "notification");
        }

        return "model_dashboard";
    }


    @PostMapping("/register")
    public String registration(@ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null &&
                !existingUser.getEmail().isEmpty()
    ){
          result.reject("The email already exists");
        }
        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return"/signup";
        }
        userService.saveUser(userDto);
        return "redirect:/login";
    }
    @GetMapping("/users")
    public String users (Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "portfolios";
    }
}
