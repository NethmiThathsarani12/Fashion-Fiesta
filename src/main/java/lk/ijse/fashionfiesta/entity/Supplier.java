package lk.ijse.fashionfiesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Supplier {

    private String supplier_id;
    private String first_name;
    private String last_name;
    private String street;
    private String city;
    private String lane;
    private String contact_number;
}
