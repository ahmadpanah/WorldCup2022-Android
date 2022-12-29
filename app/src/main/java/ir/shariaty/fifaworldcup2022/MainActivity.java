package ir.shariaty.fifaworldcup2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import ir.shariaty.fifaworldcup2022.manager.RequestManager;
import ir.shariaty.fifaworldcup2022.models.FixtureResponse;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerview;
    ProgressDialog dialog;
    RequestManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recycler_fixure);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        /*TODO I'll Fix it Later*/
        manager.getFixture(listener);
        dialog.show();
    }

    private final ResponseListener listener = new ResponseListener(){

        @Override
        public void didFetch(FixtureResponse response, String message) {
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}