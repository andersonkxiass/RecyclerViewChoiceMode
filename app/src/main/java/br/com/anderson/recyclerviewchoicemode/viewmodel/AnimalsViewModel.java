package br.com.anderson.recyclerviewchoicemode.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;

import java.util.List;

import br.com.anderson.recyclerviewchoicemode.model.Animals;
import br.com.anderson.recyclerviewchoicemode.model.RecyclerChoiceMode;
import br.com.anderson.recyclerviewchoicemode.view.AnimalsAdapter;
import br.com.anderson.recyclerviewchoicemode.view.ToolbarActionModeCallback;

public class AnimalsViewModel extends BaseObservable {

    private ObservableArrayList<Animals> animalsList = new ObservableArrayList<>();
    private AnimalsAdapter adapter;

    public AnimalsViewModel(List<Animals> animalsList) {
        adapter = new AnimalsAdapter();
        adapter.setChoiceMode(RecyclerChoiceMode.CHOICE_MODE_MULTIPLE);
        this.animalsList.addAll(animalsList);
    }

    public ObservableArrayList<Animals> getAnimalsList() {
        return animalsList;
    }

    public void setAnimalsList(ObservableArrayList<Animals> animalsList) {
        this.animalsList = animalsList;
    }

    public AnimalsAdapter getAdapter() {
        return adapter;
    }

    public ToolbarActionModeCallback getActionModeCallback(AnimalsAdapter animalsAdapter) {
        return new ToolbarActionModeCallback(animalsAdapter);
    }

    public void actionModeTo(int choiceMode) {
        adapter.setChoiceMode(choiceMode);
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
    }
}