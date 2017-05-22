package br.com.anderson.recyclerviewchoicemode.viewmodel;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import br.com.anderson.recyclerviewchoicemode.model.Animals;
import br.com.anderson.recyclerviewchoicemode.view.AnimalsAdapter;
import br.com.anderson.recyclerviewchoicemode.view.RecyclerViewWithClick;

public class GroupChoiceBindAdapter {

    @BindingAdapter({"adapter", "data"})
    public static void bindRecycler(RecyclerViewWithClick recyclerView, AnimalsAdapter animalsAdapter, List<Animals> animalsList) {

        animalsAdapter.setItems(animalsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(animalsAdapter);
    }
}
