package com.example.demo.controller;

import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepo userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepo userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }


    // displaying all posts
    @GetMapping("/posts")
    public String allPosts(Model vModel){
        vModel.addAttribute("allPosts", postDao.findAll());
        return "posts/index";
    }

    // displaying create form to user
    @GetMapping("/posts/create")
    public String showCreateForm(Model vModel){
        vModel.addAttribute("post", new Post());
        return "posts/create";
    }

    // receiving post request from user and saving data into a new post then using postDao to save then at the end redirect back to "/posts"
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getOne(principal.getId());  // getting currently signed in user; our Dao gets all info needed
        post.setUser(user); // assigning currently sing user to newly created post
        postDao.save(post); // save to DB
        emailService.prepareAndSend(post, "New post!", "You have successfully created a new post!");
        return "redirect:/posts";
    }

    // displaying the edit form and using the postDao and Model to show existing data of post on edit form
    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model vModel){
        Post postToEdit = postDao.getOne(id);
        vModel.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    //
    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post postToEdit) {
        postDao.save(postToEdit);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/show")
    public String showDetails(@PathVariable long id, Model vModel) {
        Post postToView = postDao.getOne(id);
//        PostDetails postDetails = postToView.getPostDetails();
        vModel.addAttribute("post", postToView);
//        vModel.addAttribute("posts", postToView);
        return "posts/show";
    }



}
