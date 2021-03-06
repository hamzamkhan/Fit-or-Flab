package com.example.hp.project;

import android.support.v4.app.DialogFragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;


public class CookBOOK extends AppCompatActivity {


    //private String API_KEY = "3e9166ad629eca6587a5e501e4e30961";
    private final String API_KEY = "473dd92f0fbc20142cca69d26013bc65";   // my key
    private final String BUNDLE_RECIPE_ARRAY = "BundleRecipeArray";


    private final String SERVER_SERCH_URL = "http://food2fork.com/api/search";
    private final String SERVER_GET_URL = "http://food2fork.com/api/get";
    public static final String TEST = "Cook it up";


    private RecipeAdapter mRecipeAdapter;
    private Downloader mImageDownloader;
    private EditText editText;
    private SharedPreferences prefs;
    private ArrayList<Recipe> mRecipeList;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private DialogFragment mFragment;
    private boolean sort;
    private int column;
    private String query;
    private Integer page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cook_book);
        if (BuildConfig.DEBUG) Log.d(TEST, " Thread num = " + Thread.currentThread().hashCode());

        //restore preferences
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        column = prefs.getInt(getResources().getString(R.string.column_one), 1);


        mRecipeAdapter = new RecipeAdapter(this);
        mImageDownloader = (Downloader) getLastCustomNonConfigurationInstance();
        if (mImageDownloader == null) {
            mImageDownloader = new Downloader(this);
            mImageDownloader.setWeakRecipeAdapter(mRecipeAdapter);
            mImageDownloader.start();
        } else {
            mImageDownloader.setLink(this);
            mImageDownloader.setWeakRecipeAdapter(mRecipeAdapter);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mGridLayoutManager = new PreCachingLayoutManager(this, column);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mGridLayoutManager);


        Button buttonSerch = (Button) findViewById(R.id.buttonserch);
        editText = (EditText) findViewById(R.id.editText);


        mRecipeAdapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                get(RecipeAdapter.linkedList.get(position).getRecipeID(), position);
            }
        });
        mRecyclerView.setAdapter(mRecipeAdapter);


        buttonSerch.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Downloader.taskDeque.clear();
                        RecipeAdapter.linkedList.clear();
                        mImageDownloader.stopPool(true);
                        mRecipeAdapter.notifyDataSetChanged();
                        page = 1;
                        Editable edit = editText.getText();
                        query = edit.toString();
                        sort = prefs.getBoolean("TOP Rated", true);
                        String s = sort ? "r" : "t";
                        search(query, s, page.toString());
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mImageDownloader;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //TODO:Change RecipeAdapter.likedList to mLinkedList
        mRecipeList = savedInstanceState.getParcelableArrayList(BUNDLE_RECIPE_ARRAY);
        query = savedInstanceState.getString("query");
        if (mRecipeList != null) {
            RecipeAdapter.linkedList.clear();
            RecipeAdapter.linkedList.addAll(mRecipeList);
        }
        mRecyclerView.setAdapter(mRecipeAdapter);
        mImageDownloader.setWeakRecipeAdapter(mRecipeAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("query", query);
        if (mRecipeAdapter.getItemCount() != 0) {
            mRecipeList = mRecipeAdapter.getData();
            outState.putParcelableArrayList(BUNDLE_RECIPE_ARRAY, mRecipeList);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cook_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_serch) {
            page++;
            sort = prefs.getBoolean("TOP Rated", true);
            String s = sort ? "r" : "t";
            search(query, s, page.toString());
            return true;
        }
        if (id == R.id.action_settings) {
            mFragment = new SettingsFragment().newInstance(mGridLayoutManager);
            mFragment.show(getSupportFragmentManager(), "MySF");
            getSupportFragmentManager().executePendingTransactions();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void search(String q, String s, String p) {
        String data = "&key=" + API_KEY;
        data += "&q=" + q;
        if (s != null) {
            data += "&sort=" + s;
        }
        if (p != null) {
            data += "&page=" + p;
        }

        Toast.makeText(this, " Loading data please wait", Toast.LENGTH_SHORT).show();
        //send REST query using AsyncTask
        new RestAPI(this, mImageDownloader).execute(SERVER_SERCH_URL, data);
    }

    private void get(String id, Integer position) {
        String data = "&key=" + API_KEY;
        data += "&rId=" + id;
        Toast.makeText(this, " Loading data", Toast.LENGTH_SHORT).show();

        if (RecipeAdapter.linkedList.get(position).getIngredients() != null) {
            mFragment = new RecipeFragment().newInstance(RecipeAdapter.linkedList.get(position));
            mFragment.show(getSupportFragmentManager(), "MyRecipeFragment");
        } else {
            new RestAPI(this, position, mImageDownloader).execute(SERVER_GET_URL, data);
        }
    }

    public void notifyDataSetChanged() {
        mRecipeAdapter.notifyDataSetChanged();
    }

    public synchronized void showDialog(Recipe r) {

        mFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag("MyRecipeFragment");
        if (mFragment != null) {
            return;
        }
        mFragment = new RecipeFragment().newInstance(r);
        mFragment.show(getSupportFragmentManager(), "MyRecipeFragment");
        getSupportFragmentManager().executePendingTransactions();
    }

}
