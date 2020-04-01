package POJOs;

import java.io.Serializable;

public class Trip implements Serializable {
    private String id;
    private String tripName;
    private String startLocationString;
    private Double startLocLat;
    private Double startLocLong;
    private String destinationString;
    private Double destinationLat;
    private Double getDestinationLong;
    private String startDate;
    private String startTime;
    private String description;
    private String repeat;
    private String round;
    private String status ;
    boolean isExpandable;
    private String history ;
    private String distance;

    public Trip() {

    }


    public Trip(String id, String tripName,
                String startLocationString, String destinationString,
                String startDate, String startTime, String description,
                String repeat, String round,
                String distance) {
        this.id = id;
        this.tripName = tripName;
        this.startLocationString = startLocationString;
        // this.startLocLat = startLocLat;
        // this.startLocLong = startLocLong;
        this.destinationString = destinationString;
        // this.destinationLat = destinationLat;
        // this.getDestinationLong = getDestinationLong;
        this.startDate = startDate;
        this.startTime = startTime;
        this.description = description;
        this.repeat = repeat;
        this.round = round;
        this.status = "upComing";
        this.history="false";
        this.distance = distance;
    }


/*
    public Trip(String id, String tripName,
                String startLocationString, Double startLocLat, Double startLocLong,
                String destinationString, Double destinationLat, Double getDestinationLong,
                String startDate, String startTime,
                String description, String repeat, String round,
                String status, String history) {



 */
            public Trip(String id, String tripName,
                String startLocationString, String destinationString,
                String startDate, String startTime,
                String description, String repeat, String round,
                String status, String history) {
        this.id = id;
        this.tripName = tripName;
        this.startLocationString = startLocationString;
        // this.startLocLat = startLocLat;
        // this.startLocLong = startLocLong;
        this.destinationString = destinationString;
        // this.destinationLat = destinationLat;
        // this.getDestinationLong = getDestinationLong;
        this.startDate = startDate;
        this.startTime = startTime;
        this.description = description;
        this.repeat = repeat;
        this.round = round;
        this.status = status;
        this.history = history;
        this.isExpandable= false;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStartLocationString() {
        return startLocationString;
    }

    public void setStartLocationString(String startLocationString) {
        this.startLocationString = startLocationString;
    }

    public Double getStartLocLat() {
        return startLocLat;
    }

    public void setStartLocLat(Double startLocLat) {
        this.startLocLat = startLocLat;
    }

    public Double getStartLocLong() {
        return startLocLong;
    }

    public void setStartLocLong(Double startLocLong) {
        this.startLocLong = startLocLong;
    }

    public String getDestinationString() {
        return destinationString;
    }

    public void setDestinationString(String destinationString) {
        this.destinationString = destinationString;
    }

    public Double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(Double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Double getGetDestinationLong() {
        return getDestinationLong;
    }

    public void setGetDestinationLong(Double getDestinationLong) {
        this.getDestinationLong = getDestinationLong;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

     boolean isExpandable() {
        return isExpandable;
    }

     void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getDistance() {
        return distance;
    }



    public void setDistance(String distance) {
        this.distance = distance;
    }
}
