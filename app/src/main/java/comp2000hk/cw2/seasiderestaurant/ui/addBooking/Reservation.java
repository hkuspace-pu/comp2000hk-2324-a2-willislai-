package comp2000hk.cw2.seasiderestaurant.ui.addBooking;

import androidx.lifecycle.LiveData;

public class Reservation {

    private String customerName;
    private String customerPhoneNumber;
    private String mealPeriod;
    private String seatingArea;
    private int tableSize;
    private String reserveDate;

    public Reservation(String customerName, String customerPhoneNumber, String mealPeriod, String seatingArea, int tableSize, String reserveDate) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.mealPeriod = mealPeriod;
        this.seatingArea = seatingArea;
        this.tableSize = tableSize;
        this.reserveDate = reserveDate;
    }

    // Getter methods
    public String getName() {
        return customerName;
    }
    public String getPhoneNo() {
        return customerPhoneNumber;
    }
    public String getMeal() {
        return mealPeriod;
    }
    public String getArea() {
        return seatingArea;
    }
    public int getSize() {
        return tableSize;
    }
    public String getDate() {
        return reserveDate;
    }

    // Setter methods
    public void setName(String customerName) {
        this.customerName = customerName;
    }
    public void setPhoneNo(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    public void setMeal(String mealPeriod) {
        this.mealPeriod = mealPeriod;
    }
    public void setArea(String seatingArea) {
        this.seatingArea = seatingArea;
    }
    public void setSize(int tableSize) {
        this.tableSize = tableSize;
    }
    public void setDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

}

