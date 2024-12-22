package com.example.tourist_recommendation;

import com.example.tourist_recommendation.model.TouristSpot;
import com.example.tourist_recommendation.service.TouristService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TouristServiceTest {

    @Autowired
    private TouristService touristService;

    @Test
    public void testGetTouristSpots() {
        // TouristService를 통해 관광명소 목록을 가져옴
        List<TouristSpot> spots = touristService.getTouristSpots();

        // 관광명소 목록이 비어 있지 않은지 확인
        assertNotNull(spots, "관광명소 목록이 null입니다.");
        assertFalse(spots.isEmpty(), "관광명소 목록이 비어 있습니다.");

        // 특정 관광명소에 대한 속성 확인 (예시)
        TouristSpot spot = spots.get(0);
        assertNotNull(spot.getName(), "관광명소 이름이 null입니다.");
        assertNotNull(spot.getAddress(), "관광명소 주소가 null입니다.");
    }
}
