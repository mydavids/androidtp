package tpapp.yusiry.cput.za.ac.constructionmanagementapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Site;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest.RestSiteAPI;

import java.util.LinkedList;
import java.util.List;

public class ActivityAddSite extends AppCompatActivity {

    Button btnBack, btnSave;
    EditText txtSiteName, txtSiteSize, txtSiteAddress;
    int id = 0;

    //SITES
    static Site site = new Site();
    List<Site> sites = new LinkedList<Site>();
    RestSiteAPI restSiteAPI = new RestSiteAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);

        txtSiteName = (EditText) findViewById(R.id.txtSiteName);
        txtSiteSize = (EditText) findViewById(R.id.txtSiteSize);
        txtSiteAddress = (EditText) findViewById(R.id.txtSiteAddress);

        /*btnBack = (Button)findViewById(R.id.btnSiteBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        btnSave = (Button) findViewById(R.id.btnSiteSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id == 0){
                    site.setName(txtSiteName.getText().toString());
                    site.setSize(Integer.parseInt(txtSiteSize.getText().toString()));
                    site.setAddress(txtSiteAddress.getText().toString());
                }
                else{
                    site.setId(id);
                    site.setName(txtSiteName.getText().toString());
                    site.setSize(Integer.parseInt(txtSiteSize.getText().toString()));
                    site.setAddress(txtSiteAddress.getText().toString());
                }
                new HttpRequestTask().execute();
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null)
        {
            site.setId(extras.getInt("id"));
            site.setName(extras.getString("name"));
            site.setSize(extras.getInt("size"));
            site.setAddress(extras.getString("address"));

            id = site.getId();
            txtSiteName.setText(site.getName()+"");
            txtSiteSize.setText(site.getSize()+"");
            txtSiteAddress.setText(site.getAddress()+"");
        }
    }

    private class HttpRequestTask extends AsyncTask<String, Void, String>{

        ProgressDialog pd = new ProgressDialog(ActivityAddSite.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("Loading...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            if (id != 0) {
                try {
                    restSiteAPI.post(site);
                } catch (Exception e) {
                    Log.e("ActivityAddSite", e.getMessage(), e);
                }
                return "Successfully Updated Site";
            }
            else{
                try{
                    restSiteAPI.post(site);
                }catch (Exception ex){
                    Log.e("ActivityAddSite", ex.getMessage(), ex);
                }
                return "Successfully Created Site";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_add_site, menu);
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
