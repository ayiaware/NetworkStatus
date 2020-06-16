package com.example.networkstatus;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class NetworkConnectivity {

private Context context;


    private OnConnectionListener mainActivityInterface;

    public NetworkConnectivity(Context context) {
        this.mainActivityInterface = (OnConnectionListener) context;
        this.context = context;
    }

    public void requestDataCallback() {

        try {

            if (isNetworkConnected(context)) {
                new ConnectionTask(context).execute();

            } else {
                Toast.makeText(context, "Network Disabled",Toast.LENGTH_LONG).show();

                mainActivityInterface.notifyApplication("done","Disabled", false);
            }


        } catch (Exception e) {
            mainActivityInterface.notifyApplication("done","Error "+e.getMessage(), false);
        }
    }


    private static boolean isNetworkConnected(Context context) {
        boolean result = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = true;
                    }
                }
            }
        } else {
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        result = true;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}
