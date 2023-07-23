package lk.ijse.fashionfiesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class User {

    private String employee_id;
    private String user_name;
    private String password;
    private String role;
}
