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

public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.RecycleHolder> {

    private List<SuperItem> elements;
    private Context context;

    public MyCardAdapter(Context myContext, List<SuperItem> elements) {
        this.context = myContext;
        this.elements = elements;
    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //se crea un view y se usa LayoutInflater enviandole como resource el view que creamos
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.micard, parent, false);
        return new RecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCardAdapter.RecycleHolder holder, int position) {
        //se crean los elementos
        SuperItem card = elements.get(position);
        holder.titlecard.setText(card.getTitle());
        holder.numercard.setText("Num: "+card.getNumber()+ "\t");
        holder.volumencard.setText("Vol. "+card.getVolume()+ "\t");
        holder.yearcard.setText("año: " + card.getYear());
        holder.doicard.setText(card.getDoi());
        holder.datecard.setText(card.getDate_published());
        //renderiza img
        Glide.with(context).load(card.getCover()).into(holder.imgCard);

    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    /*clase vie holder requerida para enlazar la vista de los items*/
    public static class RecycleHolder extends RecyclerView.ViewHolder{
        private ImageView imgCard;
        private TextView titlecard;
        private TextView numercard;
        private TextView volumencard;
        private TextView yearcard;
        private TextView doicard;
        private TextView datecard;


        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
            //obtener los elementos del activity y ponerlo a disposición de la clase
            imgCard = itemView.findViewById(R.id.imgcard);
            titlecard = itemView.findViewById(R.id.titlecard);
            volumencard = itemView.findViewById(R.id.volumencard);
            numercard = itemView.findViewById(R.id.numercard);
            yearcard = itemView.findViewById(R.id.yearcard);
            doicard = itemView.findViewById(R.id.doicard);
            datecard = itemView.findViewById(R.id.datecard);
        }
    }

    public static class viewHeader extends RecyclerView.ViewHolder{
        TextView txtTitle;
        public viewHeader(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.articlesnumer);
        }
    }
}
