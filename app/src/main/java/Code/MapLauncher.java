package Code;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MapLauncher {

    public static void launchMapsWithStartLocation(Context context, String from, String to) {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + Uri.encode(from) + "&destination=" +
                        Uri.encode(to) + "&travelmode=driving"));
        intent.setComponent(new ComponentName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"));
        context.startActivity(intent);

    }

    public static void launchMapsFromCurrentLocation( Context context, String destination) {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/dir/?api=1" + "&destination=" +
                        Uri.encode(destination) + "&travelmode=driving"));
        intent.setComponent(new ComponentName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"));
        context.startActivity(intent);

    }
}
