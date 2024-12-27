package com.example.tourist_recommendation.model;

import java.util.List;

/**
 * FoodSpot 클래스는 음식점 정보를 나타내는 모델 클래스입니다.
 * 음식점 이름, 주소, 평점, 태그, 이미지 목록 등을 포함합니다.
 */
public class FoodSpot {

  // 음식점 이름 (필수)
  private String name;

  // 음식점 주소 (필수)
  private String address;

  // 음식점 관련 URL (옵션)
  private String url;

  // 음식점 평점 (0.0 ~ 5.0)
  private double rating;

  // 음식점 관련 태그 (쉼표로 구분된 키워드)
  private String tags;

  // 음식점 이미지 목록
  private List<String> images;

  /**
   * 기본 생성자.
   */
  public FoodSpot() {}

  /**
   * FoodSpot 생성자.
   * 음식점 정보를 초기화합니다.
   *
   * @param name 음식점 이름
   * @param address 음식점 주소
   * @param url 음식점 관련 URL
   * @param rating 음식점 평점
   * @param tags 음식점 관련 태그
   * @param images 음식점 이미지 목록
   */
  public FoodSpot(String name, String address, String url, double rating, String tags, List<String> images) {
    this.name = name;
    this.address = address;
    this.url = url;
    this.rating = rating;
    this.tags = tags;
    this.images = images;
  }

  // Getter 및 Setter 메서드

  /**
   * @return 음식점 이름
   */
  public String getName() {
    return name;
  }

  /**
   * @param name 음식점 이름을 설정
   * @throws IllegalArgumentException 음식점 이름이 비어있거나 null인 경우
   */
  public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("음식점 이름은 필수 항목입니다.");
    }
    this.name = name;
  }

  /**
   * @return 음식점 주소
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address 음식점 주소를 설정
   * @throws IllegalArgumentException 음식점 주소가 비어있거나 null인 경우
   */
  public void setAddress(String address) {
    if (address == null || address.trim().isEmpty()) {
      throw new IllegalArgumentException("음식점 주소는 필수 항목입니다.");
    }
    this.address = address;
  }

  /**
   * @return 음식점 관련 URL
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url 음식점 관련 URL을 설정
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return 음식점 평점
   */
  public double getRating() {
    return rating;
  }

  /**
   * @param rating 음식점 평점을 설정
   * @throws IllegalArgumentException 평점이 0.0에서 5.0 사이가 아닌 경우
   */
  public void setRating(double rating) {
    if (rating < 0.0 || rating > 5.0) {
      throw new IllegalArgumentException("평점은 0.0에서 5.0 사이여야 합니다.");
    }
    this.rating = rating;
  }

  /**
   * @return 음식점 관련 태그
   */
  public String getTags() {
    return tags;
  }

  /**
   * @param tags 음식점 관련 태그를 설정
   */
  public void setTags(String tags) {
    this.tags = tags;
  }

  /**
   * @return 음식점 이미지 목록
   */
  public List<String> getImages() {
    return images;
  }

  /**
   * @param images 음식점 이미지 목록을 설정
   */
  public void setImages(List<String> images) {
    this.images = images;
  }

  /**
   * FoodSpot 객체의 정보를 문자열로 반환합니다.
   *
   * @return 객체 정보
   */
  @Override
  public String toString() {
    return "FoodSpot{" +
            "name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", url='" + url + '\'' +
            ", rating=" + rating +
            ", tags='" + tags + '\'' +
            ", images=" + images +
            '}';
  }
}
