package com.creatvt.jack777.tctricosys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.creatvt.jack777.tctricosys.adapter.PostAdapter;
import com.creatvt.jack777.tctricosys.model.BaseResponse;
import com.creatvt.jack777.tctricosys.model.Post;
import com.creatvt.jack777.tctricosys.rest.Api;
import com.creatvt.jack777.tctricosys.rest.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rvpost;
    ProgressBar progress;
    List<Post> posts;
    Api api;//declare
    PostAdapter postAdapter;
    //Constens
    public static final String DATA = "data";
    public static final int REQUEST_UPDATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvpost = findViewById(R.id.rvpost);
        progress = findViewById(R.id.progress);
        api = ApiClient.getClient().create(Api.class);//
        fetchPost();
    }

    private void fetchPost() {
        Call<List<Post>> callPost = api.getPost();
        progress.setVisibility(View.VISIBLE);

        callPost.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                posts = response.body();
                progress.setVisibility(View.GONE);
                postAdapter = new PostAdapter(posts, R.layout.product_list, MainActivity.this);
                rvpost.setAdapter(postAdapter);
            }

            /*int statusCode = response.code();
            List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));*/
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progress.setVisibility(View.GONE);

            }
        });
    }

   /* @Override
    public void onDeleteClick(int i) {
        final Employee employee = employeeListAdapter.getItemAtPosion( i );
        int emloyeeId = employee.getId( );
        Call<BaseResponse> callDelete = apiInterface.deleteEmployee( emloyeeId );
        progress.setVisibility( View.VISIBLE );
        callDelete.enqueue( new Callback<BaseResponse>( ) {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                progress.setVisibility( View.GONE );
                BaseResponse baseResponse = response.body( );
                employees.remove( employee );
                employeeListAdapter.notifyDataSetChanged( );
                Toast.makeText( GetActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                progress.setVisibility( View.GONE );

            }
        } );
    }*/

  /*  @Override
    public void onItemClick(int i) {
        Post employee = PostAdapter.getItemAtPosion( i );
        Intent intent =new Intent( this,UpdateActivity.class );
        intent.putExtra( DATA,employee );
        startActivityForResult( intent,REQUEST_UPDATE );
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_UPDATE && requestCode == RESULT_OK) {
            fetchPost();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
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

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_product) {
            Intent intent = new Intent(MainActivity.this,ProductDetailsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_post) {
            Intent intent = new Intent(MainActivity.this,PostActivity.class);
            startActivity(intent);
        } /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
