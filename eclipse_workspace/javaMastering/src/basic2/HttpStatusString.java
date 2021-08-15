package basic2;

public enum HttpStatusString {
  OK("200"),
  NOT_FOUND("404"),
  INTERNAL_SERVER_ERROR("500");
  
  private final String value;
  
  private HttpStatusString(String value) {
    this.value = value;
  }
  
  public String getValue() {
    return value;
  }
}
