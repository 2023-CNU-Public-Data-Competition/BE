package com.example.cnualarm.category;

import com.example.cnualarm.Dto.CategoryDto;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService service;

    @GetMapping("/category")
    public JsonObject getCategorys(){
        return service.getCategorys();
    }

//    @GetMapping("/liked_category")
//    public JsonObject getLikedCategory(@RequestHeader("token") String token){
//        return service.getLikedCategory(token);
//    }
//
    @PutMapping("/liked_category")
    public JsonObject updateLikedCategory(@RequestHeader("Authorization") String Authorization, @RequestBody List<CategoryDto> categoryDtos) throws Exception {
        for(CategoryDto i : categoryDtos) {
            System.out.println(i.getCategoryName());
        }
        service.updateLikedCategory(Authorization, categoryDtos);
        return service.getLikedCategory(Authorization);
    }

    @GetMapping("/liked_category")
    public JsonObject getLikedCategory(@RequestHeader("Authorization") String Authorization){
        return service.getLikedCategory(Authorization);
    }
}
