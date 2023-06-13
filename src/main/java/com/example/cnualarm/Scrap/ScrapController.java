package com.example.cnualarm.Scrap;

import com.example.cnualarm.Dto.ScrapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScrapController {

    @Autowired
    ScrapService scrapService;

    @GetMapping("scrap")
    public List<ScrapDto> getScraps(@RequestHeader("token") String token){
        return scrapService.getScraps(token);
    }

    @PostMapping("scrap")
    public String addScrap(@RequestHeader("token") String token, @RequestParam("articleNo") int articleNo) {
        scrapService.addScrap(token, articleNo);
        return "Success!";
    }

    @DeleteMapping("scrap")
    public List<ScrapDto> deleteScraps(@RequestHeader("token") String token, @RequestBody List<ScrapDto> scrapDtos) {
        return scrapService.deleteScraps(token, scrapDtos);
    }
}
