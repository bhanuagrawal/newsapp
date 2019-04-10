package com.ultimatix.cashrich.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ultimatix.cashrich.MainViewModel;
import com.ultimatix.cashrich.R;
import com.ultimatix.cashrich.data.entity.Article;
import com.ultimatix.cashrich.ui.adapters.ItemAdater;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment implements ItemAdater.ItemAdaterListner {


    private ItemAdater itemApdater;
    private MainViewModel mainViewModel;
    private Observer<List<Article>> newsObserver;
    private Unbinder unbinder;

    public ItemListFragment() {
        // Required empty public constructor
    }


    @BindView(R.id.itemlist)
    RecyclerView itemsRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        newsObserver = new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> items) {
                itemApdater.onDataChange((ArrayList) items);
            }
        };
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.fetchHeadlines();
        itemApdater = new ItemAdater(getContext(), this, ItemAdater.ARTICLES);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        itemsRV.setAdapter(itemApdater);
        itemsRV.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel.getArticlesLiveData().observe(this, newsObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainViewModel.getArticlesLiveData().removeObserver(newsObserver);
        unbinder.unbind();
    }

    @Override
    public void onArticleSelected(Article article) {

        NavDirections action = ItemListFragmentDirections.actionItemListFragmentToItemFragment(article.getUrl());
        ((FragmentInteractionListner)getActivity()).openPage(action);
    }

}
