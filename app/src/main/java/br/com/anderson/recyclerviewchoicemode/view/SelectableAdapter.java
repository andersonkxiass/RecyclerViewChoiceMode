package br.com.anderson.recyclerviewchoicemode.view;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.List;

import br.com.anderson.recyclerviewchoicemode.model.RecyclerChoiceMode;

public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private SparseBooleanArray selectedItems;
    boolean modeState = false;
    int mChoiceMode = RecyclerChoiceMode.CHOICE_MODE_NONE;
    private int lastPositionSelected = -1;

    public SelectableAdapter() {
        selectedItems = new SparseBooleanArray();
    }

    public SparseBooleanArray getSelected() {
        return selectedItems;
    }

    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    public void toggleSelection(int position) {

        if (mChoiceMode == RecyclerChoiceMode.CHOICE_MODE_SINGLE) {
            uncheckLast();
        }

        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }

        notifyItemChanged(position);

        lastPositionSelected = position;
    }

    private void uncheckLast() {

        if (lastPositionSelected != -1) {

            if (selectedItems.get(lastPositionSelected, false)) {
                selectedItems.delete(lastPositionSelected);
            }

            notifyItemChanged(lastPositionSelected);
        }
    }

    public void clearSelection() {

        List<Integer> selection = getSelectedItems();
        selectedItems.clear();

        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    public void toggleSelectionAll() {

        int count = getItemCount();

        for (int position = 0; position < count; ++position) {
            selectedItems.put(position, true);
        }

        notifyItemRangeChanged(0, getItemCount());
    }

    public void startActionMode(int position) {

        modeState = true;
        selectedItems.put(position, true);

        notifyItemRangeChanged(0, getItemCount());

        if (mChoiceMode == RecyclerChoiceMode.CHOICE_MODE_SINGLE) {
            lastPositionSelected = position;
        }
    }

    public void stopActionMode() {
        disableActionViewMode();
    }

    private void disableActionViewMode() {
        modeState = false;
        lastPositionSelected = -1;
        selectedItems.clear();
        notifyItemRangeChanged(0, getItemCount());
    }

    public int getChoiceMode() {
        return mChoiceMode;
    }

    public void setChoiceMode(int mChoiceMode) {
        this.mChoiceMode = mChoiceMode;
    }
}