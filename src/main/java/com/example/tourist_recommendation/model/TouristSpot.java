package com.example.tourist_recommendation.model;

/**
 * TouristSpot 클래스는 관광 명소 정보를 나타내는 모델 클래스이다.
 * 주소, 이름, URL, 평점, 휴관일, 태그, 이미지 경로 등을 포함한다.
 */
public class TouristSpot {

  // 관광 명소 주소
  private String address;

  // 관광 명소 이름
  private String name;

  // 관광 명소 관련 URL (예: 공식 웹사이트, 리뷰 페이지 등)
  private String url;

  // 관광 명소 평점 (0.0 ~ 5.0)
  private double rating;

  // 휴관일 (예: 매주 월요일, 공휴일 등)
  private String closedDay;

  // 관광 명소 관련 태그 (쉼표로 구분된 키워드)
  private String tags;

  // 관광 명소 이미지를 저장하는 필드
  private String image1;
  private String image2;
  private String image3;
  private String image4;

  /**
   * TouristSpot 생성자.
   * 관광 명소 정보를 초기화한다.
   *
   * @param address 관광 명소 주소
   * @param name 관광 명소 이름
   * @param url 관광 명소 관련 URL
   * @param rating 관광 명소 평점
   * @param closedDay 관광 명소의 휴관일
   * @param tags 관광 명소 관련 태그
   * @param image1 첫 번째 이미지 경로
   * @param image2 두 번째 이미지 경로
   * @param image3 세 번째 이미지 경로
   * @param image4 네 번째 이미지 경로
   */
  public TouristSpot(String address, String name, String url, double rating, String closedDay, String tags, String image1, String image2, String image3, String image4) {
    this.address = address;
    this.name = name;
    this.url = url;
    this.rating = rating;
    this.closedDay = closedDay;
    this.tags = tags;
    this.image1 = image1;
    this.image2 = image2;
    this.image3 = image3;
    this.image4 = image4;
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
   */
  public void setAddress(String address) {
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
   */
  public void setName(String name) {
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
   */
  public void setRating(double rating) {
    this.rating = rating;
  }

  /**
   * @return 관광 명소의 휴관일
   */
  public String getClosedDay() {
    return closedDay;
  }

  /**
   * @param closedDay 관광 명소의 휴관일을 설정
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
}
