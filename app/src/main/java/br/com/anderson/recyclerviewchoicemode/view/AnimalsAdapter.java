package br.com.anderson.recyclerviewchoicemode.view;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.anderson.recyclerviewchoicemode.R;
import br.com.anderson.recyclerviewchoicemode.databinding.AdapterChoiceRowBinding;
import br.com.anderson.recyclerviewchoicemode.model.Animals;

public class AnimalsAdapter extends SelectableAdapter<AnimalsViewHolder> {

    private List<Animals> animalsList = new ArrayList<>();

    public AnimalsAdapter() {
    }

    @Override
    public AnimalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        AdapterChoiceRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_choice_row, parent, false);

        return new AnimalsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AnimalsViewHolder holder, int position) {

        Animals animals = animalsList.get(position);
        holder.bindingDataModel(animals, isSelected(position), mChoiceMode, modeState);
    }

    @Override
    public int getItemCount() {
        return animalsList.size();
    }

    public void setItems(List<Animals> items) {
        this.animalsList = items;
    }
}