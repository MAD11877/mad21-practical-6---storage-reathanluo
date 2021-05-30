package sg.edu.np.s10205205;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder>{
    Context context;
    ArrayList<User> data;
    public UsersAdapter(Context c , ArrayList<User> d){
        context = c;
        data = d;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        if(viewType == 0){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_user2,parent,false);
        }

        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_user,parent,false);
        }
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u = data.get(position);
        holder.name.setText(u.getName());
        holder.des.setText((u.getDescription()));
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(holder.icon.getContext());
                alert.setTitle("Profile");
                alert.setMessage(u.getName());
                alert.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(holder.icon.getContext(),MainActivity.class);
                        intent1.putExtra("Name",u.getName());
                        intent1.putExtra("ID",position);
                        intent1.putExtra("Des",u.getDescription());
                        holder.icon.getContext().startActivity(intent1);
                    }
                });
                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.create().show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position).getName().charAt(data.get(position).getName().length() - 1)=='7'){
            return 0;
        }
        else{
            return 1;
        }
    }
}
