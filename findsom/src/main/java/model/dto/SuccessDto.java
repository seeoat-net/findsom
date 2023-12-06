package model.dto;

public class SuccessDto {
    private boolean success;

    public SuccessDto(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}