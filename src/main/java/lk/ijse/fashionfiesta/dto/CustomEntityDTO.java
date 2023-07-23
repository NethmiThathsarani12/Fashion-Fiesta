package lk.ijse.fashionfiesta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomEntityDTO {
    private String item_id;
    private String order_id;
    private String date;
    private double payment;
    private double price;
    private int quantity;
    private String time;

    private String employee_id;
    private String first_name;
    private String last_name;

}
