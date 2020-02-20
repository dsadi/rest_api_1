package com.example.rest_api_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rest_api_1.Adapter.KontakAdapter;
import com.example.rest_api_1.Model.GetKontak;
import com.example.rest_api_1.Model.Kontak;
import com.example.rest_api_1.Rest.ApiClient;
import com.example.rest_api_1.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btIns;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    private List<Kontak> userList =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btIns = (Button) findViewById(R.id.btIns);
        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;
        refresh();
    }

    public void refresh() {
//        Call<GetKontak> kontakCall = mApiInterface.getKontak();
        Call<List<Kontak>> kontakCall = mApiInterface.getKontak();
//        kontakCall.enqueue(new Callback<GetKontak>() {
//            @Override
//            public void onResponse(Call<GetKontak> call, Response<GetKontak>
//                    response) {
//                List<Kontak> KontakList = response.body().getListDataKontak();
//                Log.e("response", call.toString());
//                Log.d("Retrofit Get", "Jumlah data Kontak: " +
//                        String.valueOf(KontakList.size()));
//                mAdapter = new KontakAdapter(KontakList);
//                mRecyclerView.setAdapter(mAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<GetKontak> call, Throwable t) {
//                Log.e("Retrofit Get", t.toString());
//            }
//        });
        kontakCall.enqueue(new Callback<List<Kontak>>() {
            @Override
            public void onResponse(Call<List<Kontak>> call, Response<List<Kontak>>
                    response) {
                userList = response.body();
                Log.e("response", call.toString());
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        String.valueOf(userList.size()));
                mAdapter = new KontakAdapter(userList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Kontak>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
