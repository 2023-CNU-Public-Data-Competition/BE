package com.example.cnualarm.Post;

import com.example.cnualarm.Dto.PostDto;
import com.example.cnualarm.Utils.EntityConverter;
import com.example.cnualarm.Entity.Tag;
import com.example.cnualarm.repository.LikedCategoryRepository;
import com.example.cnualarm.repository.PostRepository;
import com.example.cnualarm.security.jwt.Jwt;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    LikedCategoryRepository likedCategoryRepository;
    @Autowired
    Jwt jwt;
    @Autowired
    EntityConverter converter;

    public JsonObject getPostList(String token, int categoryNum, Tag tag) {
        List<PostDto> postDtos;
        if (categoryNum == 0) {
            List<Integer> likedNoList = likedCategoryRepository.findAllByUser_UserId(jwt.verify(token).getUsername())
                    .stream().map(likedCategoryEntity -> likedCategoryEntity.getCategory().getCategoryNo()).toList();
            if (tag.equals(Tag.ALL)) {
                postDtos = postRepository.findByCategoryNoIn(likedNoList)
                        .stream().map(converter::postToDto).toList();
            } else {
                postDtos = postRepository.findByCategoryNoInAndTag(likedNoList, tag)
                        .stream().map(converter::postToDto).toList();
            }
        }
        else {
            if (tag.equals(Tag.ALL)) {
                postDtos = postRepository.findByCategoryNo(categoryNum)
                        .stream().map(converter::postToDto).toList();
            }
            else {
                postDtos = postRepository.findByCategoryNoAndTag(categoryNum, tag)
                        .stream().map(converter::postToDto).toList();
            }
        }
        JsonObject result = new JsonObject();
        JsonArray postList = new JsonArray();
        for (PostDto postDto: postDtos) {
            JsonObject postInfo = new JsonObject();
            postInfo.addProperty("articleNo", postDto.getArticleNo());
            postInfo.addProperty("categoryNo", postDto.getCategoryDto().getCategoryNo());
            postInfo.addProperty("categoryName", postDto.getCategoryDto().getCategoryName());
            postInfo.addProperty("articleTitle", postDto.getArticleTitle());
            postInfo.addProperty("updateDate", Optional.ofNullable(postDto.getUpdateDate()).map(Objects::toString).orElse(""));
            postInfo.addProperty("tag", postDto.getTag());
            postList.add(postInfo);
        }
        result.add("postList", postList);
        return result;
    }

    public PostDto getPostContents(int articleNo) {
        return postRepository.findById(articleNo)
                .map(converter::postToDto)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
    }
}
