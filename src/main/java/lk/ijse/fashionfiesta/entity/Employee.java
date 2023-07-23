package lk.ijse.fashionfiesta.entity;

public class Employee {

    private String employee_id;
    private String street;
    private String city;
    private String lane;
    private String role;
    private String firsrt_name;
    private String last_name;
    private String contact_number;

    public Employee() {
    }

    public Employee(String employee_id, String street, String city, String lane, String role, String firsrt_name, String last_name, String contact_number) {
        this.employee_id = employee_id;
        this.street = street;
        this.city = city;
        this.lane = lane;
        this.role = role;
        this.firsrt_name = firsrt_name;
        this.last_name = last_name;
        this.contact_number = contact_number;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id='" + employee_id + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", lane='" + lane + '\'' +
                ", role='" + role + '\'' +
                ", firsrt_name='" + firsrt_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", contact_number='" + contact_number + '\'' +
                '}';
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirsrt_name() {
        return firsrt_name;
    }

    public void setFirsrt_name(String firsrt_name) {
        this.firsrt_name = firsrt_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
}
