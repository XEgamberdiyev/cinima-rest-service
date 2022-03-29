package uz.pdp.cinemarestservice.payLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    String message;
    boolean isSuccess;
    Object data;

    public ApiResponse(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }
}
