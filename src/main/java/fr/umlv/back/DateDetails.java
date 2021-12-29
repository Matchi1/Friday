package fr.umlv.back;

public record DateDetails(int year, int month, int day, int hour, int minute) {
    public DateDetails {
        if (year < 0) {
            throw new IllegalArgumentException("Year should be positive");
        }
        if (month < 0) {
            throw new IllegalArgumentException("Month should be positive");
        }
        if (day < 0) {
            throw new IllegalArgumentException("Day should be positive");
        }
        if (hour < 0) {
            throw new IllegalArgumentException("Hour should be positive");
        }
        if(minute < 0) {
            throw new IllegalArgumentException("Minute should be positive");
        }
    }
}
