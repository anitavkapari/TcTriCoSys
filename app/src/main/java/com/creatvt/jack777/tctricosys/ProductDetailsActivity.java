package com.creatvt.jack777.tctricosys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_product_details );
        final ListView listView =findViewById( R.id.list_item );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Api.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class );
        Call<List<Product>> call = api.getProducts();
        call.enqueue( new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> product = response.body();
               String[] products = new String[product.size()];
                for (int i= 0; i<product.size();i++)
                {
                    products[i] = product.get(i).getUserId();
                    products[i] = product.get(i).getId();
                    products[i] = product.get(i).getTitle();
                    products[i] = product.get(i).getBody();

                }
                    listView.setAdapter( new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            products
                            )
                    );
                }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText( getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
