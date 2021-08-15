package basic2;

public enum HttpStatusInt {
  OK(200),
  NOT_FOUND(404),
  INTERNAL_SERVER_ERROR(500);
  
  private final int value;
  
  private HttpStatusInt(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return value;
  }
}
