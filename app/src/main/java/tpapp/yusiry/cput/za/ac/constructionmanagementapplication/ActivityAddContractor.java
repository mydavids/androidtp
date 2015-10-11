package tpapp.yusiry.cput.za.ac.constructionmanagementapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Subcontractor;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest.RestSubcontractorAPI;

import java.util.LinkedList;
import java.util.List;

public class ActivityAddContractor extends AppCompatActivity {

    Button btnSave;
    EditText txtName, txtSpeciality, txtAddress;
    int id = 0;

    //CONTRACTORS
    static Subcontractor subcontractor = new Subcontractor();
    List<Subcontractor> subcontractors = new LinkedList<Subcontractor>();
    RestSubcontractorAPI restSubcontractorAPI = new RestSubcontractorAPI();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contractor);

        txtName = (EditText) findViewById(R.id.txtContractorName);
        txtSpeciality = (EditText) findViewById(R.id.txtSpeciality);
        txtAddress = (EditText) findViewById(R.id.txtContractorAddress);

        btnSave = (Button) findViewById(R.id.btnSaveContractor);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id == 0){
                    subcontractor.setName(txtName.getText().toString());
                    subcontractor.setSpeciality(txtSpeciality.getText().toString());
                    subcontractor.setAddress(txtAddress.getText().toString());
                }else{
                    subcontractor.setId(id);
                    subcontractor.setName(txtName.getText().toString());
                    subcontractor.setSpeciality(txtSpeciality.getText().toString());
                    subcontractor.setAddress(txtAddress.getText().toString());
                }
                new HttpRequestTask().execute();
            }
        });

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            subcontractor.setId(extras.getInt("id"));
            subcontractor.setName(extras.getString("name"));
            subcontractor.setSpeciality(extras.getString("speciality"));
            subcontractor.setAddress(extras.getString("address"));

            id = subcontractor.getId();
            txtName.setText(subcontractor.getName()+"");
            txtSpeciality.setText(subcontractor.getSpeciality()+"");
            txtAddress.setText(subcontractor.getAddress()+"");
        }
    }

    private class HttpRequestTask extends AsyncTask<String, Void, String>{

        ProgressDialog pd = new ProgressDialog(ActivityAddContractor.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("Loading...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            if(id != 0){
                try {
                    restSubcontractorAPI.post(subcontractor);
                }catch (Exception e){

                }
                return "Successfully Update SubContractor";
            }else{
                try{
                    restSubcontractorAPI.post(subcontractor);
                }catch (Exception ex){

                }
                return "Sucessfully Created New SubContractor";
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
        getMenuInflater().inflate(R.menu.menu_activity_add_contractor, menu);
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
