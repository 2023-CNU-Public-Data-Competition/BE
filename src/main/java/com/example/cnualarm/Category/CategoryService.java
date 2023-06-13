package com.example.cnualarm.Category;

import com.example.cnualarm.Dto.CategoryDto;
import com.example.cnualarm.Entity.LikedCategoryEntity;
import com.example.cnualarm.Entity.UserEntity;
import com.example.cnualarm.Utils.EntityConverter;
import com.example.cnualarm.repository.CategoryRepository;
import com.example.cnualarm.repository.LikedCategoryRepository;
import com.example.cnualarm.repository.UserRepository;
import com.example.cnualarm.security.jwt.Jwt;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LikedCategoryRepository likedCategoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Jwt jwt;

    public JsonObject getCategorys() {
        JsonObject result = new JsonObject();
        JsonArray array = new JsonArray();
        List<String> categoryTypes = categoryRepository.getCategoryTypes();
        for ( String categoryType : categoryTypes) {
            JsonObject categorysByType = new JsonObject();
            categorysByType.addProperty("categoryType", categoryType);
            JsonArray content = new JsonArray();
            for (CategoryDto categoryDto : categoryRepository.findByCategoryTypeOrderByCategoryName(categoryType).stream().map(EntityConverter::categoryToDto).toList()) {
                JsonObject category = new JsonObject();
                category.addProperty("categoryNo", categoryDto.getCategoryNo());
                category.addProperty("categoryName", categoryDto.getCategoryName());
                content.add(category);
            }
            categorysByType.add("content", content);
            array.add(categorysByType);
        }
        result.add("result", array);
        return result;
    }

    @Transactional
    public boolean updateLikedCategory(String token, List<CategoryDto> categoryDtos) throws Exception {
        if (categoryDtos.size() > 5) {
            throw new Exception("선택한 카테고리가 5개보다 많습니다.");
        } else try {
            String userId = jwt.verify(token).getUsername();
            likedCategoryRepository.deleteAllByUser_UserId(userId);
            for (CategoryDto categoryDto : categoryDtos) {
                if (!categoryRepository.existsById(categoryDto.getCategoryNo())) {
                    throw new Exception("존재하지 않는 카테고리입니다.");
                }
                LikedCategoryEntity likedCategory = new LikedCategoryEntity(new UserEntity(userId), EntityConverter.categoryFromDto(categoryDto));
                likedCategoryRepository.save(likedCategory);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("update fail.");
        }
    }

    public JsonObject getLikedCategory(String token) {
        String userId = jwt.verify(token).getUsername();
        return getLikedCategoryByUserId(userId);
    }

    public JsonObject getLikedCategoryByUserId(String userId) {
        List<LikedCategoryEntity> likedCategoryList = likedCategoryRepository.findAllByUser_UserId(userId);
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (LikedCategoryEntity lc : likedCategoryList) {
            JsonObject temp = new JsonObject();
            temp.addProperty("categoryNo", lc.getCategory().getCategoryNo());
            temp.addProperty("categoryName", lc.getCategory().getCategoryName());
            jsonArray.add(temp);
        }
        jsonObject.add("categoryList", jsonArray);
        return jsonObject;
    }

    public void setLikedCategory(String userId, List<CategoryDto> categoryDtos) throws Exception {
        if (categoryDtos.size() > 5) {
            throw new Exception("선택한 카테고리가 5개보다 많습니다.");
        } else {
            for (CategoryDto categoryDto : categoryDtos) {
                if (!categoryRepository.existsById(categoryDto.getCategoryNo())) {
                    throw new Exception("존재하지 않는 카테고리입니다.");
                }
                LikedCategoryEntity likedCategory = new LikedCategoryEntity(new UserEntity(userId), EntityConverter.categoryFromDto(categoryDto));
                likedCategoryRepository.save(likedCategory);
            }
        }
    }
}




