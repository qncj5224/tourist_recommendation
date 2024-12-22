package com.example.tourist_recommendation;

import com.example.tourist_recommendation.service.FoodService;
import com.example.tourist_recommendation.model.FoodSpot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Test
    public void testGetFoodSpots() {
        // 실행: 음식점 목록 가져오기
        List<FoodSpot> foodSpots = foodService.getFoodSpots();

        // 검증: 음식점 데이터가 제대로 로드되었는지 확인
        Assertions.assertNotNull(foodSpots, "음식점 목록이 null입니다.");
        Assertions.assertFalse(foodSpots.isEmpty(), "음식점 목록이 비어 있습니다.");
        System.out.println("로드된 음식점 수: " + foodSpots.size());
    }
}
