package POJOs;

public class Trip {
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

    public Trip(String id, String tripName, String startLocationString, Double startLocLat, Double startLocLong, String destinationString, Double destinationLat, Double getDestinationLong, String startDate, String startTime, String description, String repeat, String round, String status, String history) {
        this.id = id;
        this.tripName = tripName;
        this.startLocationString = startLocationString;
        this.startLocLat = startLocLat;
        this.startLocLong = startLocLong;
        this.destinationString = destinationString;
        this.destinationLat = destinationLat;
        this.getDestinationLong = getDestinationLong;
        this.startDate = startDate;
        this.startTime = startTime;
        this.description = description;
        this.repeat = repeat;
        this.round = round;
        this.status = status;
        this.history = history;
    }


    public String getHistory() {
        return history;
    }

    private String history ;



    public Trip() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Trip(String tripName, String startLocationString, String destinationString, String startDate, String startTime, String description) {

        this.tripName = tripName;
        this.startLocationString = startLocationString;
        this.destinationString = destinationString;
        this.startDate = startDate;
        this.startTime = startTime;
        this.description = description;

    }

    public Trip(String id, String tripName, String startLocationString, Double startLocLat, Double startLocLong, String destinationString, Double destinationLat, Double getDestinationLong, String startDate, String startTime, String description, String repeat, String round) {
        this.id = id;
        this.tripName = tripName;
        this.startLocationString = startLocationString;
        this.startLocLat = startLocLat;
        this.startLocLong = startLocLong;
        this.destinationString = destinationString;
        this.destinationLat = destinationLat;
        this.getDestinationLong = getDestinationLong;
        this.startDate = startDate;
        this.startTime = startTime;
        this.description = description;
        this.repeat = repeat;
        this.round = round;
    }

    public String getId() {
        return id;
    }

    public String getTripName() {
        return tripName;
    }



    public String getStartLocationString() {
        return startLocationString;
    }

    public Double getStartLocLat() {
        return startLocLat;
    }

    public Double getStartLocLong() {
        return startLocLong;
    }

    public String getDestinationString() {
        return destinationString;
    }

    public Double getDestinationLat() {
        return destinationLat;
    }

    public Double getGetDestinationLong() {
        return getDestinationLong;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getDescription() {
        return description;
    }

    public String getRepeat() {
        return repeat;
    }

    public String getRound() {
        return round;
    }

    public void setId(String id) {
        this.id = id;
    }
}
