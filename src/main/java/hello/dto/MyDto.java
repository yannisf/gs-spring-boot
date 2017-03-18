package hello.dto;

public class MyDto {

    String value;

    public MyDto() {
    }

    public MyDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
