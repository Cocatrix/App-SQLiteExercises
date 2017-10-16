package fr.dta.formtion.app_mysqlite;

import android.content.Context;
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
 * With almost no coffee
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<Users> mDataset;
    Context context;

    public MyAdapter(List<Users> myDataset,Context context) { // The context is needed for the actions of the buttons
        this.mDataset = myDataset;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Declaration of the components of the ViewHolder
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
        //Creation of the viewHolder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Assignation of the elements of the list

        holder.nameTV.setText(mDataset.get(position).getFirstName()+" "+mDataset.get(position).getLastName());
        holder.jobTV.setText(mDataset.get(position).getJob());
        holder.ageTV.setText(Integer.toString(mDataset.get(position).getAge()) + " ans");
        holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mecanism to build a bridge between the adapter and the database methods
                UserDataSource usrDataSource = UserDataSource.getInstance(v.getContext());
                Log.d("connexion","connexion etablie");
                UtilisateurDAO myManager = usrDataSource.newUtilisateurDAO();
                myManager.delete(mDataset.get(position));
                mDataset=myManager.readAll();
                notifyDataSetChanged();
            }
        });

        /*
            LongClickListener redirecting to the modify method
         */
       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               Intent intent = new Intent (context, ModifyUserActivity.class);
               intent.putExtra("lastName",mDataset.get(position).getLastName());
               intent.putExtra("firstName",mDataset.get(position).getFirstName());
               intent.putExtra("age",Integer.toString(mDataset.get(position).getAge()));
               intent.putExtra("job",mDataset.get(position).getJob());
               intent.putExtra("id",mDataset.get(position).getId());
               context.startActivity(intent);
               return false;
           }


        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
