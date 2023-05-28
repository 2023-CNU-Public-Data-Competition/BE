package com.example.cnualarm.Post;

import com.example.cnualarm.Dto.PostDto;
import com.example.cnualarm.Utils.EntityConverter;
import com.example.cnualarm.Utils.Tag;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository repository;

    @Autowired
    EntityConverter converter;

    public JsonObject getPostList(int categoryNum, Tag tag) {
        List<PostDto> postDtos;
        if (tag.equals(Tag.ALL)) {
            postDtos = repository.findByCategoryNo(categoryNum)
                    .stream().map(converter::postToDto).toList();
        }
        else {
            postDtos = repository.findByCategoryNoAndTag(categoryNum, tag)
                    .stream().map(converter::postToDto).toList();
        }
        JsonObject result = new JsonObject();
        JsonArray postList = new JsonArray();
        for (PostDto postDto: postDtos) {
            JsonObject postInfo = new JsonObject();
            postInfo.addProperty("article_no", postDto.getArticleNo());
            postInfo.addProperty("category_no", postDto.getCategoryDto().getCategoryNo());
            postInfo.addProperty("category_name", postDto.getCategoryDto().getCategoryName());
            postInfo.addProperty("article_title", postDto.getArticleTitle());
            postInfo.addProperty("update_dt", postDto.getUpdateDate().toString());
            postInfo.addProperty("tag", postDto.getTag().toString());
            postList.add(postInfo);
        }
        result.add("post_list", postList);
        return result;
    }
}
