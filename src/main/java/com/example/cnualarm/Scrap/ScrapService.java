package com.example.cnualarm.Scrap;

import com.example.cnualarm.Dto.ScrapDto;
import com.example.cnualarm.Entity.ScrapEntity;
import com.example.cnualarm.Utils.EntityConverter;
import com.example.cnualarm.repository.ScrapRepository;
import com.example.cnualarm.security.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ScrapService {

    @Autowired
    Jwt jwt;

    @Autowired
    ScrapRepository scrapRepository;

    public List<ScrapDto> getScraps(String token) {
        List<ScrapEntity> scrapEntities = scrapRepository.findByUser_UserId(jwt.verify(token).getUsername());
        return scrapEntities.stream().map(EntityConverter::scrapToDto).toList();
    }

    public boolean addScrap(String token, int articleNo) {
        String userId = jwt.verify(token).getUsername();
        ScrapEntity newScrap = new ScrapEntity(userId, articleNo);
        scrapRepository.save(newScrap);
        return true;
    }

    public List<ScrapDto> deleteScraps(String token, List<ScrapDto> scrapDtos) {
        String userId = jwt.verify(token).getUsername();
        scrapDtos.forEach(scrapDto -> {
                if (Objects.equals(scrapRepository.findById(scrapDto.getScrapNo()).orElseThrow(() -> new RuntimeException("해당 스크랩이 존재하지 않습니다."))
                        .getUser().getUserId(), userId)) {
                    scrapRepository.deleteById(scrapDto.getScrapNo());
                } else throw new RuntimeException("스크랩의 소유자가 아닙니다.");
        });
        return scrapRepository.findByUser_UserId(userId).stream().map(EntityConverter::scrapToDto).toList();
    }
}
