package Code;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import POJOs.Trip;

public class Distance {

    public static String getDistance(String origin, String destination) {

        String finalDistance = "";
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();

        try {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?units=metrics");
            sb.append("&origins=" + Uri.decode(origin));
            sb.append("&destinations=" + Uri.encode(destination));
            sb.append("&key=AIzaSyCudlTIHtQyuZ7-6l7Gz9-Nb_0P8Ehyjdc");
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];

            while ((read = inputStreamReader.read(buff)) != -1) {
                jsonResult.append(buff, 0, read);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                ;
            }
        }

        try {

            JSONObject jsonRespRouteDistance = new JSONObject(jsonResult.toString())
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("distance");

            finalDistance = jsonRespRouteDistance.get("text").toString();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return finalDistance;
    }

    public static String getDistance(double lat, double lon, String destination) {

        String finalDistance = "";
        String finalDuration = "";
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();

        try {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?units=metrics");
            sb.append("&origins=" + lat + "," + lon);
            sb.append("&destinations=" + Uri.encode(destination));
            sb.append("&key=AIzaSyCudlTIHtQyuZ7-6l7Gz9-Nb_0P8Ehyjdc");
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];

            while ((read = inputStreamReader.read(buff)) != -1) {
                jsonResult.append(buff, 0, read);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                ;
            }
        }

        try {

            JSONObject jsonRespRouteDistance = new JSONObject(jsonResult.toString())
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("distance");


            finalDistance = jsonRespRouteDistance.get("text").toString();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return finalDistance;
    }

    public static ArrayList<String> getSpaceTime(String origin, String destination) {

         ArrayList<String > list= new ArrayList<>();


        String finalDistance = "";
        String finalDuration = "";
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();

        try {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?units=metrics");
            sb.append("&origins=" + Uri.decode(origin));
            sb.append("&destinations=" + Uri.encode(destination));
            sb.append("&key=AIzaSyCudlTIHtQyuZ7-6l7Gz9-Nb_0P8Ehyjdc");
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];

            while ((read = inputStreamReader.read(buff)) != -1) {
                jsonResult.append(buff, 0, read);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                ;
            }
        }

        try {

            JSONObject jsonRespRouteDistance = new JSONObject(jsonResult.toString())
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("distance");


            JSONObject jsonRespRouteDuration = new JSONObject(jsonResult.toString())
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("duration");


            finalDistance = jsonRespRouteDistance.get("text").toString();
            list.add(finalDistance);
            finalDuration = jsonRespRouteDuration.get("text").toString();
            list.add(finalDuration);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }


        return list;
    }

    public String getTime(String origin, String destination) {

        String time = "";
        ArrayList<String> spaceTime = new ArrayList();

        String finalDistance = "";
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();

        try {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?units=metrics");
            sb.append("&origins=" + Uri.decode(origin));
            sb.append("&destinations=" + Uri.encode(destination));
            sb.append("&key=AIzaSyCudlTIHtQyuZ7-6l7Gz9-Nb_0P8Ehyjdc");
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];

            while ((read = inputStreamReader.read(buff)) != -1) {
                jsonResult.append(buff, 0, read);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                ;
            }
        }

        try {

            /*
            JSONObject jsonRespRouteDistance = new JSONObject(jsonResult.toString())
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("duration");

            finalDistance = jsonRespRouteDistance.get("text").toString();

             */

            JSONObject jsonRespRouteDistance = new JSONObject(jsonResult.toString())
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0);


            finalDistance = jsonRespRouteDistance.get("text").toString();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return time;
    }

}

