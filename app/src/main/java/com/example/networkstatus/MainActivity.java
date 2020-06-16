package com.example.networkstatus;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnConnectionListener{

    private TextView tvStat;
    FloatingActionButton fab;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvStat = findViewById(R.id.tvStat);
        fab = findViewById(R.id.fab);
        fab.setClickable(false);
        fab.setEnabled(false);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    showDialog();
                new NetworkConnectivity(MainActivity.this).requestDataCallback();


            }
        });
      ///  showDialog();
        new NetworkConnectivity(this).requestDataCallback();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void notifyApplication(String state, String status, boolean response) {
        if(state.equals("starting"))
        {
            showDialog();
            String stat = "Status: "+ status + "\nConnection: "+response;
            tvStat.setText(stat);
            fab.setClickable(false);
            fab.setEnabled(false);
        }
        else if(state.equals("done"))
        {

            String stat = "Status: "+ status + "\nConnection: "+response;
            tvStat.setText(stat);
            fab.setClickable(true);
            fab.setEnabled(true);
            dismissDialog(dialog);
        }


    }


    public void showDialog()
    {
        dialog = new ProgressDialog(MainActivity.this) {
            @Override
            public void onBackPressed() {

                finish();
            }
        };
        dialog.setTitle("Checking your network");
        dialog.setMessage("Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        // dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();

    }


    public void dismissDialog(final ProgressDialog pd){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                    Handler mHandler = new Handler();
//                    mHandler.post(new Runnable() {
//                        public void run() {
//                            pd.dismiss();
//                        }
//                    });
//                }catch(Exception e){
//                    pd.dismiss();
//                }
//
//            }
//        }).start();

        pd.dismiss();
    }


}
