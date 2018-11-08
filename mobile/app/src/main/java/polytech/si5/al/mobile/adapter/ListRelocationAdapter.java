package polytech.si5.al.mobile.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.business.Relocation;
import polytech.si5.al.mobile.holder.RelocationHolder;

public class ListRelocationAdapter extends RecyclerView.Adapter<RelocationHolder> {

    private List<Relocation> relocationList;

    public ListRelocationAdapter(){
        relocationList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RelocationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_list_relocation,viewGroup,false);
        return new RelocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelocationHolder relocationHolder, int position) {
        Relocation relocation = relocationList.get(position);
        relocationHolder.bind(relocation);
    }

    @Override
    public int getItemCount() {
        return relocationList.size();
    }

    public void clear() {
        relocationList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Relocation> list) {
        relocationList.addAll(list);
        notifyDataSetChanged();
    }
}
