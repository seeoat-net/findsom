package model.dto;

public class ResponseDto {
    private int status;
    private Object data;

    public ResponseDto(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}