package Model;

import java.time.LocalDate;

public class ExchangeRate {
    private double rate;
    private Currency from;
    private Currency to;
    private LocalDate date;

    public ExchangeRate(double rate, Currency from, Currency to, LocalDate date) {
        this.rate = rate;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public double getRate() {
        return rate;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public LocalDate getLocalDate() {
        return date;
    }
    
}
