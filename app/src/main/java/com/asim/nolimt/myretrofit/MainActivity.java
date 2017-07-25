package com.asim.nolimt.myretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layManager;
    MyAdapter myAdapter;
    List<MyFlawers> list;
    APIClint apiClint;

    public static final String ENDPOINT= "http://services.hanselandpetal.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilaize();
    }

    private void initilaize() {
        recyclerView= (RecyclerView) findViewById(R.id.recycleVeiw);
        layManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layManager);

        OkHttpClient cleint= new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request =chain.request().newBuilder()
                                .addHeader("Accept","Application/Json")
                                .build();

                        return chain.proceed(request);
                    }
                }).build();

        Retrofit   retrofit = new Retrofit.Builder().baseUrl(ENDPOINT)
                .client(cleint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClint = retrofit.create(APIClint.class);

         Call<List<MyFlawers>>  call=apiClint.getFlowers("flowers");

        call.enqueue(new Callback<List<MyFlawers>>() {
            @Override
            public void onResponse(Call<List<MyFlawers>> call, retrofit2.Response<List<MyFlawers>> response) {

                list = response.body();

                myAdapter= new MyAdapter(list);
                recyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onFailure(Call<List<MyFlawers>> call, Throwable t) {

            }
        });
    }
}
