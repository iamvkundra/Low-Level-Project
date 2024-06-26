package model;

public class Location {

    private int longitude;

    private int latitude;

    public Location(int longitude, int latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}
