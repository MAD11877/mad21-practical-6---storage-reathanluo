package sg.edu.np.s10205205;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User uSelected;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler db = new DBHandler(this);
        Intent receivedIntent = getIntent();
        uSelected = db.getUsers().get(receivedIntent.getIntExtra("ID", 0));
        Button follow_button = findViewById(R.id.follow_button);
        Context context = getApplicationContext();
        if (uSelected.isFollowed()){
            follow_button.setText("UNFOLLOW");
        }
        else{
            follow_button.setText("FOLLOW");
        }
        follow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("debug", "Follow Button Pressed");
                if (uSelected.isFollowed()){
                    follow_button.setText("FOLLOW");
                    uSelected.setFollowed(false);
                    Toast setunfollow = Toast.makeText(context, "Unfollowed!", Toast.LENGTH_SHORT);
                    setunfollow.show();
                    db.updateUser(uSelected);
                }
                else{
                    follow_button.setText("UNFOLLOW");
                    uSelected.setFollowed(true);
                    Toast setFollow = Toast.makeText(context, "Followed!", Toast.LENGTH_SHORT);
                    setFollow.show();
                    db.updateUser(uSelected);
                }
            }
        });

        Intent receiver = getIntent();
        Intent rc = getIntent();
        String name = rc.getStringExtra("Name");
        String des = rc.getStringExtra("Des");

        TextView textView1 = findViewById(R.id.textView);
        textView1.setText(name);
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(des);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug","start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug","stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug","destory");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug","pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug","resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug","restart");

    }
}