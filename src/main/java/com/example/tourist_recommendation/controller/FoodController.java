package com.example.tourist_recommendation.controller;

import com.example.tourist_recommendation.model.FoodSpot;
import com.example.tourist_recommendation.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * FoodController는 음식점 관련 요청을 처리합니다.
 */
@org.springframework.stereotype.Controller
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * 음식점 목록 페이지를 반환합니다.
     * @param model 뷰로 데이터를 전달하기 위한 Spring Model 객체
     * @return food_spot_index.html 페이지
     */
    @GetMapping("/foodspots")
    public String showFoodSpots(Model model) {
        List<FoodSpot> spots = foodService.getFoodSpots();
        model.addAttribute("spots", spots);
        return "food_spot_index";
    }

    /**
     * 특정 음식점의 상세 정보를 반환합니다.
     * @param name 음식점 이름
     * @param model 뷰로 데이터를 전달하기 위한 Spring Model 객체
     * @return food_spot_detail.html 페이지
     */
    @GetMapping("/food/spot/{name}")
    public String foodSpotDetails(@PathVariable String name, Model model) {
        FoodSpot spot = foodService.getFoodSpots().stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null); // 음식점이 없으면 null 반환
        model.addAttribute("spot", spot);
        return "food_spot_detail";
    }
}
