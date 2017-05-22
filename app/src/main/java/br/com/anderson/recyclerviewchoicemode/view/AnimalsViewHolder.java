package br.com.anderson.recyclerviewchoicemode.view;

import android.support.v7.widget.RecyclerView;

import br.com.anderson.recyclerviewchoicemode.databinding.AdapterChoiceRowBinding;
import br.com.anderson.recyclerviewchoicemode.model.Animals;
import br.com.anderson.recyclerviewchoicemode.viewmodel.AnimalsRowViewModel;
import br.com.anderson.recyclerviewchoicemode.viewmodel.GroupChoiceResource;

public class AnimalsViewHolder extends RecyclerView.ViewHolder {

    private AdapterChoiceRowBinding binding;

    public AnimalsViewHolder(AdapterChoiceRowBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindingDataModel(Animals animals, boolean selected, int choiceType, boolean modeState) {

        if (binding.getModel() == null) {
            binding.setCustom(new GroupChoiceResource());
            binding.setModel(new AnimalsRowViewModel(animals, selected, choiceType, modeState));
        } else {

            binding.getModel().setAnimals(animals);
            binding.getModel().setSelected(selected);
            binding.getModel().setChoiceType(choiceType);
            binding.getModel().setModeState(modeState);
        }
    }
}