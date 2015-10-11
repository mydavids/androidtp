package tpapp.yusiry.cput.za.ac.constructionmanagementapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;

import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Site;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest.RestSiteAPI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActivitySiteList extends Activity {

    GridView gridView;
    ArrayList<String> gridItems = new ArrayList<String>();
    String[] spinnerItems = new String[]{"- SELECT -", "Sites", "Staff"};
    //SITES
    RestSiteAPI restSiteAPI = new RestSiteAPI();
    List<Site> sites = new LinkedList<>();
    //STAFF
    //RestStaffAPI restStaffAPI = new RestStaffAPI();
    //List<Staff> staff = new LinkedList<>();

    ImageView imageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sites);

        /*SPINNER---------------------------------------------------------------
        spinner = (Spinner) findViewById(R.id.spinnerSites);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());*/

        gridView = (GridView) findViewById(R.id.gridViewSites);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityAddSite.class);
                intent.putExtra("id", sites.get(position).getId());
                intent.putExtra("name", sites.get(position).getName() + "");
                intent.putExtra("size", sites.get(position).getSize());
                intent.putExtra("address", sites.get(position).getAddress() + "");
                startActivity(intent);
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int idDelete = sites.get(position).getId();
                restSiteAPI.deleteSite(idDelete);
                return true;
            }
        });

        new HttpAsyncTask().execute();
        //SiteAdapter adapter = new SiteAdapter(sites);
    }

    private void doPopup(final int idDelete){
        PopupMenu popupMenu = new PopupMenu(this, gridView);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                    restSiteAPI.deleteSite(idDelete);
                    return true;

            }
        });
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        gridView = (GridView) findViewById(R.id.gridViewSites);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityAddSite.class);
                intent.putExtra("id", sites.get(position).getId());
                intent.putExtra("name", sites.get(position).getName() + "");
                intent.putExtra("size", sites.get(position).getSize());
                intent.putExtra("address", sites.get(position).getAddress() + "");
                startActivity(intent);

            }
        });

        new HttpAsyncTask().execute();
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

    private class HttpAsyncTask extends AsyncTask<Void, Void, Void>{

        ProgressDialog pdLoading = new ProgressDialog(ActivitySiteList.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdLoading.setMessage("Loading...");
            pdLoading.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                sites = restSiteAPI.getAll();
            }catch(Exception e){
                Log.e("ActivitySiteList", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(sites.size() != 0){
                String[] temp = new String[sites.size()];
                for(int i = 0; i < sites.size(); i++){
                   // sites.get(i).setId(j);
                    temp[i] = sites.get(i).getId() + " " + sites.get(i).getName();
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

        for(int i=0; i < arr2.length; i++){
            gridItems.add(arr2[i]);
        }

        ArrayAdapter<String> gridAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gridItems);
        gridView.setAdapter(gridAdapter);
    }

}
