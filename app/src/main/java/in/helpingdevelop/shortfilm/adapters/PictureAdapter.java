package in.helpingdevelop.shortfilm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.helpingdevelop.shortfilm.R;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHolder> {
    private ArrayList<Integer> mDataset;
    private Context mCtx;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView screen;
        public MyViewHolder(LinearLayout v) {
            super(v);
            screen = v.findViewById(R.id.screen);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public PictureAdapter(ArrayList<Integer> myDataset, Context mCtx) {
        mDataset = myDataset;
        this.mCtx = mCtx;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public PictureAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_screen, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Glide
                .with(mCtx)
                .asBitmap()
                .load(mDataset.get(position))
                .into(holder.screen);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
