package lk.ijse.fashionfiesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class CustomerOrder {

    private String customer_id;
    private String customer_order_id;
    private String customer_order_date;
    private String customer_order_time;
    private String payment;

}
