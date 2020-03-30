package POJOs;

public class HomeTripData {
    String tripName , Destination_path ,  source_path , date , time , description;
   boolean isExpandable;

    public HomeTripData() {
    }

    public HomeTripData(String tripName, String destination_path, String source_path, String date, String time, String description) {
        this.tripName = tripName;
        Destination_path = destination_path;
        this.source_path = source_path;
        this.date = date;
        this.time = time;
        this.description = description;
        isExpandable= false;
    }





    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public String getTripName() {
        return tripName;
    }

    public String getDestination_path() {
        return Destination_path;
    }

    public String getSource_path() {
        return source_path;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public boolean isExpandable() {
        return isExpandable;
    }
}
