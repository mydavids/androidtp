package tpapp.yusiry.cput.za.ac.constructionmanagementapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Subcontractor;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest.RestSubcontractorAPI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActivityContractorList extends AppCompatActivity {

    GridView gridView;
    ArrayList<String> gridItems = new ArrayList<String>();

    //SUBCONTRACTORS
    RestSubcontractorAPI restSubcontractorAPI = new RestSubcontractorAPI();
    List<Subcontractor> subcontractors = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subcontractors);

        gridView = (GridView) findViewById(R.id.gridviewSubcontractors);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityAddContractor.class);
                intent.putExtra("id", subcontractors.get(position).getId());
                intent.putExtra("name", subcontractors.get(position).getName());
                intent.putExtra("speciality", subcontractors.get(position).getSpeciality());
                intent.putExtra("address", subcontractors.get(position).getAddress());
                startActivity(intent);
            }
        });
        new HttpAsyncTask().execute();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        gridView = (GridView) findViewById(R.id.gridviewSubcontractors);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityAddContractor.class);
                intent.putExtra("id", subcontractors.get(position).getId());
                intent.putExtra("name", subcontractors.get(position).getName());
                intent.putExtra("speciality", subcontractors.get(position).getSpeciality());
                intent.putExtra("address", subcontractors.get(position).getAddress());
                startActivity(intent);
            }
        });
        new HttpAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_contractor_list, menu);
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

    private class HttpAsyncTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog pdLoading = new ProgressDialog(ActivityContractorList.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("Loading...");
            pdLoading.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(subcontractors.size() != 0){
                String[] temp = new String[subcontractors.size()];
                for(int i = 0; i < subcontractors.size(); i++){
                   // subcontractors.get(i).setId(j);
                    temp[i] = subcontractors.get(i).getId() + " " + subcontractors.get(i).getName();
                }
                populateGridView(temp);
            }
            else{
                gridView.setAdapter(null);
            }
            pdLoading.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                subcontractors = restSubcontractorAPI.getAll();
            }catch(Exception e){
                Log.e("ActivitySiteList", e.getMessage(), e);
            }
            return null;
        }
    }

    public void populateGridView(String[] arr2){
        gridItems.clear();

        for(int i=0; i < arr2.length; i++){
            gridItems.add(arr2[i]);
        }

        ArrayAdapter<String> gridAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gridItems);
        gridView.setAdapter(gridAdapter);
    }
}
