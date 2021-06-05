package com.miaowmere.final_h071191035.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import com.miaowmere.final_h071191035.R;
import com.miaowmere.final_h071191035.data.api.repository.TvShowRepository;
import com.miaowmere.final_h071191035.data.api.repository.callback.OnSearchCallback;
import com.miaowmere.final_h071191035.data.api.repository.callback.OnTvShowCallback;
import com.miaowmere.final_h071191035.data.models.TvShow;
import com.miaowmere.final_h071191035.ui.activities.DetailActivity;
import com.miaowmere.final_h071191035.ui.adapters.TvShowAdapter;
import com.miaowmere.final_h071191035.ui.adapters.clicklistener.OnItemClickListener;

public class TvShowFragment extends Fragment implements OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private TvShowAdapter adapter;
    private TvShowRepository repository;
    private TextView tvError;
    private boolean isFetching;
    private int currentPage = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_tv_show_fragment, menu);
        MenuItem item = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) item.getActionView();
        setSearchView(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setSearchView(SearchView searchView) {
        searchView.setQueryHint(getString(R.string.language));
        searchView.setOnQueryTextListener(this);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.item_language_setting) {
//            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        refreshLayout = view.findViewById(R.id.swl_tv_show);
        recyclerView = view.findViewById(R.id.rv_tv_show);
        tvError = view.findViewById(R.id.tv_error);
        repository = TvShowRepository.getInstance();
        getRepositoryData("", currentPage);
        onScrollListener();
        refreshLayout.setOnRefreshListener(this);
        return view;
    }

    private void onScrollListener() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int totalItem = layoutManager.getItemCount();
                int visibleItem = layoutManager.getChildCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem + visibleItem >= totalItem / 2) {
                    if (!isFetching) {
                      // TODO: call repository with incremented page
                    }
                }
            }
        });
    }


    private void getRepositoryData(String query, int page) {
        isFetching = true;
        if (query.equals("")) {
            repository.getTvShow(getBundle(), page, new OnTvShowCallback() {
                @Override
                public void onSuccess(int page, List<TvShow> tvShowList) {
                    // TODO: hide error text
                    if (adapter == null) {
                        adapter = new TvShowAdapter(tvShowList);
                        adapter.setClickListener(TvShowFragment.this);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    } else {
                        adapter.appendList(tvShowList);
                    }
                    currentPage = page;
                    isFetching = false;
                    refreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(String message) {
                    // TODO: show error text
                }
            });
        } else {
            repository.search(query, page, new OnSearchCallback() {
                @Override
                public void onSuccess(List<TvShow> tvShowList, String msg, int page) {
                    // TODO: hide error text
                    if (adapter == null) {
                        adapter = new TvShowAdapter(tvShowList);
                        adapter.setClickListener(TvShowFragment.this);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    } else {
                        adapter.appendList(tvShowList);
                    }
                    currentPage = page;
                    isFetching = false;
                    refreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(String msg) {
                    // TODO: show error text
                }
            });
        }
    }

    private String getBundle() {
        if (getArguments() != null) {
            return getArguments().getString("SORT_BY");
        }
        return "airing_today";
    }

    @Override
    public void onClick(TvShow tvShow) {
        Intent detailActivity = new Intent(getContext(), DetailActivity.class);
        detailActivity.putExtra("ID", tvShow.getId());
        detailActivity.putExtra("SELECTED_FRAGMENT", getBundle());
        startActivity(detailActivity);
    }

    @Override
    public void onRefresh() {
        adapter = null;
        getRepositoryData("", currentPage);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (s.length() > 0) {
            adapter = null;
            getRepositoryData(s, currentPage);
        } else {
            adapter = null;
            getRepositoryData("", currentPage);
        }
        return true;
    }
}
