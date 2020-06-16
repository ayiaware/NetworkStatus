package com.example.networkstatus;

import android.content.Context;
import android.os.AsyncTask;



import java.io.IOException;


public class ConnectionTask extends AsyncTask<String, Void, Boolean> {


    private OnConnectionListener connectionListener;


    public ConnectionTask(Context context) {

        if (context instanceof OnConnectionListener) {
            connectionListener = (OnConnectionListener) context;
        }
    }

    @Override
    protected Boolean doInBackground(String... params) {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();

            return (exitValue == 0);
            //
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        //     try {
//            URL url = new URL("http://www.google.com/");
//            HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
//            urlc.setRequestProperty("User-Agent", "test");
//            urlc.setRequestProperty("Connection", "close");
//            urlc.setConnectTimeout(1000); // mTimeout is in seconds
//            urlc.connect();
//            if (urlc.getResponseCode() == 200) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (IOException e) {
//            Log.i("warning", "Error checking internet connection", e);
//            return false;
//        }


        //Method two

//        InetAddress inetAddress = null;
//        try {
//            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
//                @Override
//                public InetAddress call() {
//                    try {
//                        return InetAddress.getByName("google.com");
//                    } catch (UnknownHostException e) {
//                        return null;
//                    }
//                }
//            });
//            inetAddress = future.get(50, TimeUnit.MILLISECONDS);
//            future.cancel(true);
//        } catch (InterruptedException e) {
//        } catch (ExecutionException e) {
//        } catch (TimeoutException e) {
//        }
//        if(inetAddress!=null && !inetAddress.equals(""))
//        {
//            return true;
//        }
//        else {
//            return false;
//        }



        //Method 3
        /*Just to check Time delay*/


//        long t = Calendar.getInstance().getTimeInMillis();
//
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//            int exitValue = ipProcess.waitFor();
//            return (exitValue == 0);
//            //  return "connected";
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            long t2 = Calendar.getInstance().getTimeInMillis();
//            Log.i("NetWork check Time", (t2 - t) + "");
//        }
//
//        return false;

        return false;
    }

    @Override
    protected void onPreExecute() {

        this.connectionListener.notifyApplication("starting","Enabled",false);

    }

    @Override
    protected void onPostExecute(Boolean result) {

        this.connectionListener.notifyApplication("done","Enabled",result);

    }


}
