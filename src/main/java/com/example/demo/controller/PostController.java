package com.example.demo.controller;

import com.example.demo.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String allPosts(Model model){
        List<Post> posts =  new ArrayList<>();
        posts.add(new Post(1, "title1", "body1"));
        posts.add(new Post(2, "title2", "body2"));
        posts.add(new Post(3, "title3", "body3"));
        model.addAttribute("allPosts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model) {
        Post post = new Post(id, "test-title", "test-body");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String create(){
        return "view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPost(){
        return "create a new post";
    }
}
