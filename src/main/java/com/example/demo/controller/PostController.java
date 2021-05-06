package com.example.demo.controller;

import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepo userDao;

    public PostController(PostRepository postDao, UserRepo userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }


    // displaying all posts
    @GetMapping("/posts")
    public String allPosts(Model vModel){
        vModel.addAttribute("allPosts", postDao.findAll());
        return "posts/index";
    }

    // displaying create form to user
    @GetMapping("/posts/create")
    public String showCreateForm(){
        return "posts/create";
    }

    // receiving post request from user and saving data into a new post then using postDao to save then at the end redirect back to "/posts"
    @PostMapping("/posts/create")
    public String createPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        Post newPost = new Post(title, body);
        User user = userDao.getOne(1L); // this code is getting the user with id = 1
        newPost.setUser(user); // here we are assigning the user to the newPost
        postDao.save(newPost); // save to DB
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
    public String update(@PathVariable long id,
                         @RequestParam String title,
                         @RequestParam String body) {
        Post postToUpdate = new Post(
                id,
                title,
                body
        );
        postDao.save(postToUpdate);
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
