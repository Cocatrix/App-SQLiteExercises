package fr.dta.formtion.app_mysqlite;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thomas on 16/10/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<Users> mDataset;

    public MyAdapter(List<Users> myDataset) {
        this.mDataset = myDataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTV;
        public TextView jobTV;
        public TextView ageTV;

        public ImageButton mImageButton;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.name) ;
            jobTV = (TextView) itemView.findViewById(R.id.job) ;
            ageTV = (TextView) itemView.findViewById(R.id.age) ;
            mImageButton = (ImageButton) itemView.findViewById(R.id.imageButton2);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.nameTV.setText(mDataset.get(position).getFirstName()+" "+mDataset.get(position).getLastName());
        holder.jobTV.setText(mDataset.get(position).getJob());
        holder.ageTV.setText(Integer.toString(mDataset.get(position).getAge()) + " ans");
        holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataSource usrDataSource = UserDataSource.getInstance(v.getContext());
                Log.d("connexion","connexion etablie");
                UtilisateurDAO myManager = usrDataSource.newUtilisateurDAO();
                myManager.delete(mDataset.get(position));
                mDataset=myManager.readAll();
                notifyDataSetChanged();
            }
        });

      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (this, );
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
