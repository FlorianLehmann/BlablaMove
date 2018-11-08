package polytech.si5.al.mobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.adapter.ListRelocationAdapter;
import polytech.si5.al.mobile.business.Relocation;
import polytech.si5.al.mobile.requests.AsyncGatherHelper;

public class ListRelocationFragment extends Fragment implements CallableFragment{

    private ListRelocationAdapter adapter;

    private SwipeRefreshLayout refreshLayout;

    public ListRelocationFragment(){
    }

    public static ListRelocationFragment newInstance(){
        ListRelocationFragment fragment = new ListRelocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_generic_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.generic_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ListRelocationAdapter();

        new AsyncGatherHelper(this).execute("");

        recyclerView.setAdapter(adapter);

        refreshLayout = rootView.findViewById(R.id.swipe_container);

        refreshLayout.setOnRefreshListener(() -> {
            new AsyncGatherHelper(this).execute("");
        });

        return rootView;
    }

    @Override
    public void callbackSetter(List<Relocation> relocationList) {
        adapter.clear();

        adapter.addAll(relocationList);

        refreshLayout.setRefreshing(false);
    }
}
