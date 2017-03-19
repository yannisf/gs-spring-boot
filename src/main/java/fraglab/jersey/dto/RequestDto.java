package fraglab.jersey.dto;

public class RequestDto {

    String value;

    public RequestDto() {
    }

    public RequestDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
