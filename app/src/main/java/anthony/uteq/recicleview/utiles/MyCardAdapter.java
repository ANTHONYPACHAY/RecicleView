package anthony.uteq.recicleview.utiles;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import anthony.uteq.recicleview.R;

public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.RecicleHolder> {

    private List<SuperItem> elements;
    private Context context;

    public MyCardAdapter(Context myContext, List<SuperItem> elements) {
        this.context = myContext;
        this.elements = elements;
    }

    @NonNull
    @Override
    public RecicleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //se crea un view y se usa LayoutInflater enviandole como resource el view que creamos
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.micard, parent, false);
        return new RecicleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCardAdapter.RecicleHolder holder, int position) {
        //se crean los elementos
        SuperItem card = elements.get(position);
        //holder.imgCard.setImageURI(Uri.parse(card.getCover()));
        holder.titlecard.setText(card.getTitle());
        holder.datecard.setText(card.getDate_published());
        //renderiza img
        Glide.with(context).load(card.getCover()).into(holder.imgCard);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    /*clase vie holder requerida para enlazar la vista de los items*/
    public static class RecicleHolder extends RecyclerView.ViewHolder{
        private ImageView imgCard;
        private TextView titlecard;
        private TextView datecard;

        public RecicleHolder(@NonNull View itemView) {
            super(itemView);
            imgCard = itemView.findViewById(R.id.imgcard);
            titlecard = itemView.findViewById(R.id.titlecard);
            datecard = itemView.findViewById(R.id.datecard);
        }
    }
}
