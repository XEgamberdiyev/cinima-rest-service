package uz.pdp.cinemarestservice.poyload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private String massage;
    private boolean status;
    private Object data;

    public ApiResponse(String massage, boolean status) {
        this.massage = massage;
        this.status = status;
    }
}
