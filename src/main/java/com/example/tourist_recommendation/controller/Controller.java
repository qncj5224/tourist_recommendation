package com.example.tourist_recommendation.controller;

import com.example.tourist_recommendation.model.FoodSpot;
import com.example.tourist_recommendation.model.TouristSpot;
import com.example.tourist_recommendation.service.FoodService;
import com.example.tourist_recommendation.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Controller 클래스는 요청된 URL에 따라 알맞은 HTML 페이지와 데이터를 반환하는 역할을 함.
 */
@org.springframework.stereotype.Controller
public class Controller {

  @Autowired
  private TouristService touristService; // 관광지 관련 데이터를 제공하는 서비스

  @Autowired
  private FoodService foodService; // 음식점 관련 데이터를 제공하는 서비스

  /**
   * 메인 페이지를 반환.
   * @return main.html 페이지
   */
  @GetMapping("/")
  public String mainPage() {
    return "main";  // main.html로 이동
  }

  /**
   * 관광지 목록을 표시.
   * @param model 뷰로 데이터를 전달하기 위한 Spring Model 객체
   * @return tourist_spot_index.html 페이지로 이동
   */
  @GetMapping("/spots")
  public String showTouristSpots(Model model) {
    // 모든 관광지 데이터를 가져와 모델에 추가
    List<TouristSpot> spots = touristService.getTouristSpots();
    model.addAttribute("spots", spots);
    return "tourist_spot_index";  // 관광지 목록 페이지로 이동
  }

  /**
   * 특정 관광지의 상세 정보를 표시.
   * @param name 관광지 이름
   * @param model 뷰로 데이터를 전달하기 위한 Spring Model 객체
   * @return tourist_spot_detail.html 페이지로 이동
   */
  @GetMapping("/spot/{name}")
  public String spotDetails(@PathVariable String name, Model model) {
    // 관광지 데이터를 가져와 이름으로 필터링
    List<TouristSpot> spots = touristService.getTouristSpots();
    TouristSpot spot = spots.stream()
            .filter(s -> s.getName().equals(name)) // 이름이 일치하는 관광지를 찾음
            .findFirst()
            .orElse(null);
    model.addAttribute("spot", spot);

    // 관광지 이미지를 모델에 추가
    model.addAttribute("image1", spot.getImage1());
    model.addAttribute("image2", spot.getImage2());
    model.addAttribute("image3", spot.getImage3());
    model.addAttribute("image4", spot.getImage4());

    return "tourist_spot_detail";  // 관광지 상세정보 페이지로 이동
  }

  /**
   * 음식점 목록을 표시.
   * @param model 뷰로 데이터를 전달하기 위한 Spring Model 객체
   * @return food_spot_index.html 페이지로 이동
   */
  @GetMapping("/foodspots")
  public String showFoodSpots(Model model) {
    // 모든 음식점 데이터를 가져와 모델에 추가
    List<FoodSpot> spots = foodService.getFoodSpots();
    model.addAttribute("spots", spots);
    return "food_spot_index";  // 음식점 목록 페이지로 이동
  }

  /**
   * 특정 음식점의 상세 정보를 표시.
   * @param name 음식점 이름
   * @param model 뷰로 데이터를 전달하기 위한 Spring Model 객체
   * @return food_spot_detail.html 페이지로 이동
   */
  @GetMapping("/food/spot/{name}")
  public String foodSpotDetails(@PathVariable("name") String name, Model model) {
    // 음식점 데이터를 가져와 이름으로 필터링
    List<FoodSpot> spots = foodService.getFoodSpots();
    FoodSpot spot = spots.stream()
            .filter(s -> s.getName().equals(name)) // 이름이 일치하는 음식점을 찾음
            .findFirst()
            .orElse(null);
    model.addAttribute("spot", spot);
    return "food_spot_detail";  // 음식점 상세정보 페이지로 이동
  }
}
