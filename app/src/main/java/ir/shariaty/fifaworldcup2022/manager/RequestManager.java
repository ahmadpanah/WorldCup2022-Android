package ir.shariaty.fifaworldcup2022.manager;

import android.content.Context;

import ir.shariaty.fifaworldcup2022.ResponseListener;
import ir.shariaty.fifaworldcup2022.models.FixtureResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.cup2022.ir/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getFixture(ResponseListener listener) {
        CallFixture callFixture = retrofit.create(CallFixture.class);
        Call<FixtureResponse> call = callFixture.callFixture();
        call.enqueue(new Callback<FixtureResponse>() {
            @Override
            public void onResponse(Call<FixtureResponse> call, Response<FixtureResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<FixtureResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallFixture {
        @GET("match")
        @Headers(
                "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzhiMmYyY2YyODA5NGFhM2FjMDRiM2YiLCJpYXQiOjE2NzIzMDE5MzcsImV4cCI6MTY3MjM4ODMzN30.vZq_pDEIkJ-iFz3ywVySD3HdzILwwTqB0bzO6-EQZ74"
        )
        retrofit2.Call<FixtureResponse> callFixture(

        );
    }
}
