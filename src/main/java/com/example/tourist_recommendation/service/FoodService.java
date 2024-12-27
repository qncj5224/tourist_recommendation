package com.example.tourist_recommendation.service;

import com.example.tourist_recommendation.model.FoodSpot;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * FoodService는 음식점 데이터를 처리하고 제공하는 역할을 담당한다.
 * 데이터는 CSV 파일에서 로드된다.
 */
@Service
public class FoodService {

  /**
   * 음식점 데이터를 CSV 파일에서 로드하여 반환한다.
   *
   * @return 음식점 목록 (`List<FoodSpot>`)
   */
  public List<FoodSpot> getFoodSpots() {
    List<FoodSpot> spots = new ArrayList<>();  // 음식점 데이터를 저장할 리스트
    try {
      // classpath 경로에서 food_spot.csv 파일을 가져옴
      File file = ResourceUtils.getFile("classpath:food_spot.csv");
      CSVReader reader = new CSVReader(new FileReader(file)); // CSV 파일을 읽는 Reader 객체 생성
      String[] nextLine;

      // 첫 번째 줄(헤더)은 건너뜀
      reader.readNext();

      // 파일을 한 줄씩 읽으면서 처리
      while ((nextLine = reader.readNext()) != null) {
        // 필드 개수 부족 시 로그 출력 및 건너뜀
        if (nextLine.length < 9) {  // 9개 필드 확인 (이미지 포함)
          System.out.println("행에 예상보다 적은 데이터가 있습니다: " + String.join(",", nextLine));
          continue;
        }

        // 데이터 파싱
        String name = nextLine[0].trim();         // 음식점 이름
        String address = nextLine[1].trim();      // 주소
        String url = nextLine[2].trim();          // 웹사이트 URL
        double rating = 0.0;
        try {
          rating = Double.parseDouble(nextLine[3].trim());  // 평점
        } catch (NumberFormatException e) {
          System.out.println("잘못된 평점 형식: " + nextLine[3]); // 평점 형식 오류 처리
        }
        String tags = nextLine[4].trim();         // 태그
        String image1 = nextLine[5].trim();       // 이미지1
        String image2 = nextLine[6].trim();       // 이미지2
        String image3 = nextLine[7].trim();       // 이미지3
        String image4 = nextLine[8].trim();       // 이미지4

        // FoodSpot 객체를 생성하여 리스트에 추가
        spots.add(new FoodSpot(name, address, url, rating, tags, image1, image2, image3, image4));
      }
    } catch (IOException | CsvValidationException e) {
      // 파일 읽기 또는 CSV 파싱 중 예외 발생 시 로그 출력
      e.printStackTrace();
    }

    // 로드된 음식점 개수 출력
    System.out.println("로드된 음식점 수: " + spots.size());
    return spots;
  }

  /**
   * FoodService는 음식점 데이터를 처리합니다.
   */
  public Optional<FoodSpot> findSpotByName(String name) {
    return getFoodSpots().stream()
            .filter(spot -> spot.getName().equalsIgnoreCase(name))
            .findFirst(); // 이름으로 일치하는 음식점 검색
  }

}
