package com.example.demo.controller;

import com.example.demo.models.Post;
import com.example.demo.models.PostDetails;
import com.example.demo.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
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
        postDao.save(newPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model vModel){
        Post postToEdit = postDao.getOne(id);
        vModel.addAttribute("post", postToEdit);
        return "posts/edit";
    }

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
        PostDetails postDetails = postToView.getPostDetails();
        vModel.addAttribute("post", postDetails);
        vModel.addAttribute("posts", postToView);
        return "posts/show";
    }



}
