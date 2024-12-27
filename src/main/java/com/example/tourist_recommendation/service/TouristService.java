package com.example.tourist_recommendation.service;

import com.example.tourist_recommendation.model.TouristSpot;
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
 * TouristService는 관광명소 데이터를 처리하고 제공하는 역할을 한다.
 * 데이터는 CSV 파일에서 읽어온다.
 */
@Service
public class TouristService {

  /**
   * 관광명소 데이터를 CSV 파일에서 읽어와 TouristSpot 객체로 반환합니다.
   *
   * @return 관광명소 목록 (`List<TouristSpot>`)
   */
  public List<TouristSpot> getTouristSpots() {
    List<TouristSpot> spots = new ArrayList<>();
    try {
      // CSV 파일 읽기
      List<String[]> csvData = readCsvFile("classpath:Tourist_spot.csv");

      // CSV 데이터 파싱 및 객체 생성
      for (String[] row : csvData) {
        parseTouristSpot(row).ifPresent(spots::add);
      }

      System.out.println("로드된 관광명소 수: " + spots.size());
    } catch (IOException | CsvValidationException e) {
      System.err.println("CSV 파일 읽기 실패: " + e.getMessage());
    }

    return spots;
  }


  /**
   * TouristService는 관광명소 데이터를 처리합니다.
   */
  public Optional<TouristSpot> findSpotByName(String name) {
    return getTouristSpots().stream()
            .filter(spot -> spot.getName().equalsIgnoreCase(name))
            .findFirst(); // 이름으로 일치하는 관광명소 검색
  }

  /**
   * CSV 파일을 읽어 각 줄 데이터를 반환합니다.
   *
   * @param filePath CSV 파일 경로
   * @return CSV 데이터 목록
   * @throws IOException 파일 읽기 예외
   * @throws CsvValidationException CSV 형식 예외
   */
  private List<String[]> readCsvFile(String filePath) throws IOException, CsvValidationException {
    File file = ResourceUtils.getFile(filePath);
    CSVReader reader = new CSVReader(new FileReader(file));
    List<String[]> csvData = new ArrayList<>();

    // 첫 번째 줄(헤더) 건너뜀
    reader.readNext();

    String[] nextLine;
    while ((nextLine = reader.readNext()) != null) {
      csvData.add(nextLine);
    }
    return csvData;
  }

  /**
   * CSV 데이터 한 줄을 TouristSpot 객체로 변환합니다.
   *
   * @param csvRow CSV 데이터 한 줄
   * @return TouristSpot 객체
   */
  private Optional<TouristSpot> parseTouristSpot(String[] csvRow) {
    if (csvRow.length < 10) {
      System.out.println("행에 예상보다 적은 데이터가 있습니다: " + String.join(",", csvRow));
      return Optional.empty();
    }

    try {
      String address = csvRow[0].trim();
      String name = csvRow[1].trim();
      String url = csvRow[2].trim();
      double rating = Double.parseDouble(csvRow[3].trim());
      String tags = csvRow[4].trim().replace("|", ", ");
      String closedDay = csvRow[5].trim();
      List<String> images = List.of(
              csvRow[6].trim(),
              csvRow[7].trim(),
              csvRow[8].trim(),
              csvRow[9].trim()
      );

      return Optional.of(new TouristSpot(address, name, url, rating, closedDay, tags, images));
    } catch (Exception e) {
      System.out.println("데이터 파싱 실패: " + String.join(",", csvRow));
      return Optional.empty();
    }
  }

}
