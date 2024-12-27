package com.example.tourist_recommendation.model;

import java.util.List;

/**
 * TouristSpot 클래스는 관광 명소 정보를 나타내는 모델 클래스입니다.
 * 관광 명소 주소, 이름, URL, 평점, 휴관일, 태그, 이미지 목록 등을 포함합니다.
 */
public class TouristSpot {

  // 관광 명소 주소 (필수)
  private String address;

  // 관광 명소 이름 (필수)
  private String name;

  // 관광 명소 관련 URL (옵션)
  private String url;

  // 관광 명소 평점 (0.0 ~ 5.0)
  private double rating;

  // 관광 명소의 휴관일 정보
  private String closedDay;

  // 관광 명소 관련 태그 (쉼표로 구분된 키워드)
  private String tags;

  // 관광 명소 이미지 목록
  private List<String> images;

  /**
   * 기본 생성자.
   */
  public TouristSpot() {}

  /**
   * TouristSpot 생성자.
   * 관광 명소 정보를 초기화합니다.
   *
   * @param address 관광 명소 주소
   * @param name 관광 명소 이름
   * @param url 관광 명소 관련 URL
   * @param rating 관광 명소 평점
   * @param closedDay 관광 명소의 휴관일 정보
   * @param tags 관광 명소 관련 태그
   * @param images 관광 명소 이미지 목록
   */
  public TouristSpot(String address, String name, String url, double rating, String closedDay, String tags, List<String> images) {
    this.address = address;
    this.name = name;
    this.url = url;
    this.rating = rating;
    this.closedDay = closedDay;
    this.tags = tags;
    this.images = images;
  }

  // Getter 및 Setter 메서드

  /**
   * @return 관광 명소 주소
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address 관광 명소 주소를 설정
   * @throws IllegalArgumentException 관광 명소 주소가 null이거나 비어있는 경우
   */
  public void setAddress(String address) {
    if (address == null || address.trim().isEmpty()) {
      throw new IllegalArgumentException("관광 명소 주소는 필수 항목입니다.");
    }
    this.address = address;
  }

  /**
   * @return 관광 명소 이름
   */
  public String getName() {
    return name;
  }

  /**
   * @param name 관광 명소 이름을 설정
   * @throws IllegalArgumentException 관광 명소 이름이 null이거나 비어있는 경우
   */
  public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("관광 명소 이름은 필수 항목입니다.");
    }
    this.name = name;
  }

  /**
   * @return 관광 명소 관련 URL
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url 관광 명소 관련 URL을 설정
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return 관광 명소 평점
   */
  public double getRating() {
    return rating;
  }

  /**
   * @param rating 관광 명소 평점을 설정
   * @throws IllegalArgumentException 평점이 0.0에서 5.0 사이가 아닌 경우
   */
  public void setRating(double rating) {
    if (rating < 0.0 || rating > 5.0) {
      throw new IllegalArgumentException("평점은 0.0에서 5.0 사이여야 합니다.");
    }
    this.rating = rating;
  }

  /**
   * @return 관광 명소의 휴관일 정보
   */
  public String getClosedDay() {
    return closedDay;
  }

  /**
   * @param closedDay 관광 명소의 휴관일 정보를 설정
   */
  public void setClosedDay(String closedDay) {
    this.closedDay = closedDay;
  }

  /**
   * @return 관광 명소 관련 태그
   */
  public String getTags() {
    return tags;
  }

  /**
   * @param tags 관광 명소 관련 태그를 설정
   */
  public void setTags(String tags) {
    this.tags = tags;
  }

  /**
   * @return 관광 명소 이미지 목록
   */
  public List<String> getImages() {
    return images;
  }

  /**
   * @param images 관광 명소 이미지 목록을 설정
   */
  public void setImages(List<String> images) {
    this.images = images;
  }

  /**
   * TouristSpot 객체의 정보를 문자열로 반환합니다.
   *
   * @return 객체 정보
   */
  @Override
  public String toString() {
    return "TouristSpot{" +
            "address='" + address + '\'' +
            ", name='" + name + '\'' +
            ", url='" + url + '\'' +
            ", rating=" + rating +
            ", closedDay='" + closedDay + '\'' +
            ", tags='" + tags + '\'' +
            ", images=" + images +
            '}';
  }
}
