package lk.ijse.fashionfiesta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Salary {

    private String employee_id;
    private String salary_id;
    private String date;
    private double salary;
    private int employee_attendance_count;

}
