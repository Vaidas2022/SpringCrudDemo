package lt.codeacademy.javau5.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lt.codeacademy.javau5.crud.entities.User;
import lt.codeacademy.javau5.crud.repositories.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/index")
	public String showUserList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "index";
	}
	
	
	@GetMapping("/signup")
    public String showSignUpForm(User user,Model model) {
		model.addAttribute("user", new User());
        return "add-user";
    }
	
	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result, Model model  ) {
		if (result.hasErrors()) {
            return "add-user";
        }
		 userRepository.save(user);
	        return "redirect:/index";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm( @PathVariable("id") Long id, Model model ) {
		User user = userRepository
				.findById(id)
				.orElseThrow( () -> new IllegalArgumentException("Invalid user Id:" + id) );
		model.addAttribute("user", user);
		return "update-user";		
	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") Long id, User user, Model model) {
		userRepository.save(user);
		return "redirect:/index";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		User user = userRepository.findById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		return "redirect:/index";
	}
}