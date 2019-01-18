package polytech.si5.al.mobile.fragments.fragmentseek;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.adapter.ListTravelAdapter;
import polytech.si5.al.mobile.requests.AsyncGatherHelper;
import polytech.si5.al.mobile.requests.JSONHelper;

public class ListTravelFragment extends Fragment implements CallableFragment {

    private ListTravelAdapter adapter;

    private SwipeRefreshLayout refreshLayout;

    public ListTravelFragment(){

    }

    public static ListTravelFragment newInstance(){
        ListTravelFragment fragment = new ListTravelFragment();
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

        adapter = new ListTravelAdapter();

        executeRequest();

        recyclerView.setAdapter(adapter);

        refreshLayout = rootView.findViewById(R.id.swipe_container);

        refreshLayout.setOnRefreshListener(this::executeRequest);

        return rootView;
    }

    private void executeRequest(){
        new AsyncGatherHelper(this).execute("routes");
    }

    @Override
    public void callbackSetter(String rawResult) {
        adapter.clear();

        adapter.addAll(new JSONHelper().convertTravelFromJSON(rawResult));

        refreshLayout.setRefreshing(false);
    }
}
