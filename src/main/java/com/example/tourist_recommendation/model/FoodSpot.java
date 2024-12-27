package com.example.tourist_recommendation.model;

import java.util.List;

/**
 * FoodSpot 클래스는 음식점 정보를 나타내는 모델 클래스이다.
 * 이름, 주소, URL, 평점, 태그, 이미지 경로 등을 포함한다.
 */
public class FoodSpot {

  // 음식점 이름
  private String name;

  // 음식점 주소
  private String address;

  // 음식점 관련 URL (예: 웹사이트, 리뷰 페이지 등)
  private String url;

  // 음식점 평점 (0.0 ~ 5.0)
  private double rating;

  // 음식점 관련 태그 (쉼표로 구분된 키워드)
  private String tags;

  // 이미지 필드를 List<String>으로 통합
  private List<String> images;

  // 수정된 생성자
  public FoodSpot(String name, String address, String url, double rating, String tags, List<String> images) {
    this.name = name;
    this.address = address;
    this.url = url;
    this.rating = rating;
    this.tags = tags;
    this.images = images;
  }

  // 추가된 Getter 및 Setter
  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> images) {
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
   * @return 첫 번째 이미지 경로
   */
  public String getImage1() {
    return image1;
  }

  /**
   * @param image1 첫 번째 이미지 경로를 설정
   */
  public void setImage1(String image1) {
    this.image1 = image1;
  }

  /**
   * @return 두 번째 이미지 경로
   */
  public String getImage2() {
    return image2;
  }

  /**
   * @param image2 두 번째 이미지 경로를 설정
   */
  public void setImage2(String image2) {
    this.image2 = image2;
  }

  /**
   * @return 세 번째 이미지 경로
   */
  public String getImage3() {
    return image3;
  }

  /**
   * @param image3 세 번째 이미지 경로를 설정
   */
  public void setImage3(String image3) {
    this.image3 = image3;
  }

  /**
   * @return 네 번째 이미지 경로
   */
  public String getImage4() {
    return image4;
  }

  /**
   * @param image4 네 번째 이미지 경로를 설정
   */
  public void setImage4(String image4) {
    this.image4 = image4;
  }

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
