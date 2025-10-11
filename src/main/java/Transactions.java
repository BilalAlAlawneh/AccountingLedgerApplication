
import java.time.*;

public class Transactions {
    private double amount;
    private LocalDate Date;
    private LocalTime Time;
    private String description;
    private String vendor;


    public Transactions(double amount, LocalDate Date, LocalTime Time, String description, String vendor) {
        this.amount = amount;
        this.Date = Date;
        this.Time = Time;
        this.description = description;
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return Date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String toString(){
        return this.Date + "|" + this.Time + "|" + this.description + "|" + this.vendor + "|" + this.amount;
    }
}

