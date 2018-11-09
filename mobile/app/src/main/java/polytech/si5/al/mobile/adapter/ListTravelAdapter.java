package polytech.si5.al.mobile.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.business.Travel;
import polytech.si5.al.mobile.holder.TravelHolder;

public class ListTravelAdapter extends RecyclerView.Adapter<TravelHolder> implements SettableAdapter<Travel> {

    private List<Travel> travelList;

    public ListTravelAdapter(){
        travelList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TravelHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_list_relocation,viewGroup,false);
        return new TravelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelHolder travelHolder, int position) {
        Travel travel = travelList.get(position);
        travelHolder.bind(travel);
    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }

    public void clear() {
        travelList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Travel> list) {
        travelList.addAll(list);
        notifyDataSetChanged();
    }
}
