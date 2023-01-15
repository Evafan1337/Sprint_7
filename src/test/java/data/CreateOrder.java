package data;
import java.util.ArrayList;

public class CreateOrder {
    public String firstName;
    public String lastName;
    public String address;
    public int metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public ArrayList<String> color;

    public CreateOrder(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, ArrayList color){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public CreateOrder(){

    }

}
