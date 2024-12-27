package com.example.tourist_recommendation.controller;

import com.example.tourist_recommendation.model.TouristSpot;
import com.example.tourist_recommendation.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * TouristController는 관광명소 관련 요청을 처리합니다.
 */
@org.springframework.stereotype.Controller
public class TouristController {

    private final TouristService touristService;

    @Autowired
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    /**
     * 관광명소 목록 페이지를 반환합니다.
     * @param model 뷰로 데이터를 전달하기 위한 Spring Model 객체
     * @return tourist_spot_index.html 페이지
     */
    @GetMapping("/spots")
    public String showTouristSpots(Model model) {
        List<TouristSpot> spots = touristService.getTouristSpots();
        model.addAttribute("spots", spots);
        return "tourist_spot_index";
    }

    /**
     * 특정 관광명소의 상세 정보를 반환합니다.
     * @param name 관광명소 이름
     * @param model 뷰로 데이터를 전달하기 위한 Spring Model 객체
     * @return tourist_spot_detail.html 페이지
     */
    @GetMapping("/spot/{name}")
    public String spotDetails(@PathVariable String name, Model model) {
        TouristSpot spot = touristService.findSpotByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tourist spot not found: " + name));
        model.addAttribute("spot", spot);
        return "tourist_spot_detail";
    }


}
