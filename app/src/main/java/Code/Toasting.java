package Code;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class Toasting {



    public static void toastAnywhere(final Context context, final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(context, text,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
