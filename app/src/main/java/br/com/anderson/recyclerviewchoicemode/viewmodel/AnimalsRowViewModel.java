package br.com.anderson.recyclerviewchoicemode.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import br.com.anderson.recyclerviewchoicemode.model.Animals;

public class AnimalsRowViewModel extends BaseObservable {

    private ObservableField<Animals> animals = new ObservableField<>();
    private int choiceType = -1;
    private ObservableBoolean selected = new ObservableBoolean(false);
    private ObservableBoolean modeState = new ObservableBoolean(false);

    public AnimalsRowViewModel(Animals animals, boolean selected, int choiceType, boolean modeState) {
        this.animals.set(animals);
        this.selected.set(selected);
        this.choiceType = choiceType;
        this.modeState.set(modeState);
    }

    public ObservableField<Animals> getAnimals() {
        return animals;
    }

    public void setAnimals(Animals animals) {
        this.animals.set(animals);
    }

    public int getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(int choiceType) {
        this.choiceType = choiceType;
    }

    public ObservableBoolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public ObservableBoolean getModeState() {
        return modeState;
    }

    public void setModeState(boolean modeState) {
        this.modeState.set(modeState);
    }
}