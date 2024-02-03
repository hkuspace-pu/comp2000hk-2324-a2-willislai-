package comp2000hk.cw2.seasiderestaurant.ui.booking;

public class ReservationHist {

    private int reserveId;
    private String customerName;
    private String customerPhoneNumber;
    private String mealPeriod;
    private String seatingArea;
    private int tableSize;
    private String reserveDate;

    public ReservationHist() {

    }

    public ReservationHist(int reserveId, String customerName, String customerPhoneNumber,
                           String mealPeriod, String seatingArea, int tableSize, String reserveDate) {
        this.reserveId = reserveId;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.mealPeriod = mealPeriod;
        this.seatingArea = seatingArea;
        this.tableSize = tableSize;
        this.reserveDate = reserveDate;
    }

    public int getId() {
        return reserveId;
    }
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

    public void setId(int id) {
        this.reserveId = id;
    }
    public void setName(String name) {
        this.customerName = name;
    }
    public void setPhoneNo(String phoneNo) {
        this.customerPhoneNumber = phoneNo;
    }
    public void setMeal(String meal) {
        this.mealPeriod = meal;
    }
    public void setArea(String area) {
        this.seatingArea = area;
    }
    public void setTableSize(int size) {
        this.tableSize = size;
    }
    public void setDate(String date) {
        this.reserveDate = date;
    }

}