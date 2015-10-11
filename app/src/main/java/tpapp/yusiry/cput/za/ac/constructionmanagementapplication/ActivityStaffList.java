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
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Staff;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest.RestStaffAPI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActivityStaffList extends AppCompatActivity {

    GridView gridView;
    ArrayList<String> gridItems = new ArrayList<String>();

    //STAFF
    RestStaffAPI restStaffAPI = new RestStaffAPI();
    List<Staff> staff = new LinkedList<Staff>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_staff);

        gridView = (GridView) findViewById(R.id.gridViewStaff);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityAddStaff2.class);
                intent.putExtra("id", staff.get(position).getId());
                intent.putExtra("name", staff.get(position).getName());
                intent.putExtra("surname", staff.get(position).getSurname());
                intent.putExtra("phone", staff.get(position).getPhone());
                intent.putExtra("address", staff.get(position).getAddress());
                intent.putExtra("idnumber", staff.get(position).getIDnumber());
                startActivity(intent);
            }
        });
        new HttpAsyncTask().execute();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        gridView = (GridView) findViewById(R.id.gridViewStaff);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityAddStaff2.class);
                intent.putExtra("id", staff.get(position).getId());
                intent.putExtra("name", staff.get(position).getName());
                intent.putExtra("surname", staff.get(position).getSurname());
                intent.putExtra("phone", staff.get(position).getPhone());
                intent.putExtra("address", staff.get(position).getAddress());
                intent.putExtra("idnumber", staff.get(position).getIDnumber());
                startActivity(intent);
            }
        });
        new HttpAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_staff_list, menu);
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

        ProgressDialog pdLoading = new ProgressDialog(ActivityStaffList.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdLoading.setMessage("Loading...");
            pdLoading.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                staff = restStaffAPI.getAll();
            }catch (Exception e){
                Log.e("ActivityStaffList", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(staff.size() != 0){
                String[] temp = new String[staff.size()];
                for(int i = 0; i < staff.size(); i++){
                    temp[i] = staff.get(i).getId() + " " + staff.get(i).getName() + " " + staff.get(i).getSurname();
                }
                populateGridView(temp);
            }
            else{
                gridView.setAdapter(null);
            }
            pdLoading.dismiss();
        }
    }

    public void populateGridView(String[] arr2){
        gridItems.clear();

        for(int i=0; i< arr2.length; i++){
            gridItems.add(arr2[i]);
        }

        ArrayAdapter<String> gridAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gridItems);
        gridView.setAdapter(gridAdapter);
    }

}
