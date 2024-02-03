package comp2000hk.cw2.seasiderestaurant.ui.booking;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import comp2000hk.cw2.seasiderestaurant.R;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<ReservationHist> mData ;


    public RecycleViewAdapter(Context mContext, List lst) {
        this.mContext = mContext;
        this.mData = lst;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.booking_row_record,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.data.setText(mData.get(position).getDate());
        holder.meal.setText(mData.get(position).getMeal());
        holder.size.setText(String.valueOf(mData.get(position).getSize()));
        holder.area.setText(mData.get(position).getArea());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView data, meal, size, area;

        public MyViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.bkHistDate);
            meal = itemView.findViewById(R.id.bkHistMeal);
            size = itemView.findViewById(R.id.bkHistTableSize);
            area = itemView.findViewById(R.id.bkHistSeatArea);
        }
    }


}