package com.example.cnualarm.Post;

import com.example.cnualarm.Dto.PostDto;
import com.example.cnualarm.Utils.EntityConverter;
import com.example.cnualarm.Entity.Tag;
import com.example.cnualarm.repository.PostRepository;
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
            postInfo.addProperty("articleNo", postDto.getArticleNo());
            postInfo.addProperty("categoryNo", postDto.getCategoryDto().getCategoryNo());
            postInfo.addProperty("categoryName", postDto.getCategoryDto().getCategoryName());
            postInfo.addProperty("articleTitle", postDto.getArticleTitle());
            postInfo.addProperty("updateDt", postDto.getUpdateDate().toString());
            postInfo.addProperty("tag", postDto.getTag().toString());
            postList.add(postInfo);
        }
        result.add("postList", postList);
        return result;
    }

    public PostDto getPostContents(int articleNo) {
        return repository.findById(articleNo)
                .map(converter::postToDto)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
    }
}
