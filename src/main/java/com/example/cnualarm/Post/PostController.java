package com.example.cnualarm.Post;

import com.example.cnualarm.Dto.PostDto;
import com.example.cnualarm.Utils.Tag;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PostService service;

    @GetMapping("posts")
    public JsonObject getPostList(@RequestParam("user_id") String userId, @RequestParam("category_num") int categoryNum, @RequestParam("tag") Tag tag){
        return service.getPostList(categoryNum, tag);
    }

    @GetMapping("contents")
    public PostDto getPostContents(@RequestParam("article_no") int articleNo){
        return service.getPostContents(articleNo);
    }
}
