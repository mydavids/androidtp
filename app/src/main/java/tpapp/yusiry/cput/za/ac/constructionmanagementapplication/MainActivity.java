package tpapp.yusiry.cput.za.ac.constructionmanagementapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.services.impl.SiteServiceImpl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private SiteServiceImpl siteService = new SiteServiceImpl();

    //Buttons
    private Button btnViewSites;
    private Button btnAddSite;
    private Button btnAddStaff;
    private Button btnViewStaff;
    private Button btnAddContractor;
    private Button btnViewContractors;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Android to Java
        btnViewSites = (Button)findViewById(R.id.btnViewSites);
        btnAddSite = (Button)findViewById(R.id.btnAddSite);
        btnViewStaff = (Button)findViewById(R.id.btnViewStaff);
        btnAddStaff = (Button)findViewById(R.id.btnAddStaff);
        btnViewContractors = (Button)findViewById(R.id.btnViewContractors);
        btnAddContractor = (Button) findViewById(R.id.btnAddContractor);


        //OnClickListeners ---------------------------------------------------------------
        btnViewSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivitySiteList.class);
                startActivity(i);
            }
        });

        btnAddSite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityAddSite.class);
                startActivity(i);
            }
        });

        btnViewStaff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityStaffList.class);
                startActivity(i);
            }
        });

        btnAddStaff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityAddStaff2.class);
                startActivity(i);
            }
        });

        btnViewContractors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityContractorList.class);
                startActivity(i);
            }
        });

        btnAddContractor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityAddContractor.class);
                startActivity(i);
            }
        });

        //------------------------------------------------------------------------------------
        //new HttpAsyncTask().execute("http://app-constructionma.rhcloud.com/api/sites");

    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";

        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(new HttpGet(url));
            inputStream =httpResponse.getEntity().getContent();
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "NOT WORK";
        } catch (Exception e){

        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls){
            return GET(urls[0]);
        }

        protected void onPostExecute(String result){
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
         //   mTextView.setText(result);
        }
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
}
