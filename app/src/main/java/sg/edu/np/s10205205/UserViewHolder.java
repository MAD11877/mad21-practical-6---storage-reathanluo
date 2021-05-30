package sg.edu.np.s10205205;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class UserViewHolder extends RecyclerView.ViewHolder{
    public TextView name;
    public TextView des;
    public ImageView icon;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textName);
        des = itemView.findViewById(R.id.textDes);
        icon = itemView.findViewById(R.id.icon);

    }
}
