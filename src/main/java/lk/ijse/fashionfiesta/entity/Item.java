package lk.ijse.fashionfiesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Item {

    private String item_id;
    private String item_name;
    private int quantity;
    private double price;
    private String model_color;
    private String categary;


}
