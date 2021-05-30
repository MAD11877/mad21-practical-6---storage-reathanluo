package sg.edu.np.s10205205;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DBHandler dB = new DBHandler(this);
        dB.deleteDatabase(this);
        Random myRandom = new Random();
        for(int i=0;i<20;i++){
            String uName = "Name" + String.valueOf(myRandom.nextInt());
            String uDes = "Description " + String.valueOf(myRandom.nextInt());
            int id = i;
            boolean followed = myRandom.nextBoolean();
            dB.addUser(new User(uName,uDes,id,followed));
        }
        ArrayList<User> userList = dB.getUsers();
        RecyclerView rv = findViewById(R.id.rv);
        UsersAdapter adapter = new UsersAdapter(this, userList);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

    }

}
