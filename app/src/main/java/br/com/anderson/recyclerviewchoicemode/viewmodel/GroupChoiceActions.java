package br.com.anderson.recyclerviewchoicemode.viewmodel;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.anderson.recyclerviewchoicemode.R;
import br.com.anderson.recyclerviewchoicemode.model.RecyclerChoiceMode;
import br.com.anderson.recyclerviewchoicemode.view.RecyclerViewWithClick;
import br.com.anderson.recyclerviewchoicemode.view.SelectableAdapter;
import br.com.anderson.recyclerviewchoicemode.view.ToolbarActionModeCallback;

public class GroupChoiceActions {

    private ToolbarActionModeCallback choiceMode;
    private ActionMode mActionMode;

    public GroupChoiceActions() {
    }

    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

        SelectableAdapter adapter = (SelectableAdapter) recyclerView.getAdapter();

        if (choiceMode != null && choiceMode.isChoiceModeActivated()) {

            adapter.toggleSelection(position);

            if (adapter.getSelectedItemCount() == 0) {
                mActionMode.finish();
            } else {
                mActionMode.setTitle(adapter.getSelectedItemCount()
                        + v.getContext().getResources().getString(R.string.selection_count));
            }

            if (adapter.getChoiceMode() == RecyclerChoiceMode.CHOICE_MODE_MULTIPLE) {
                if (adapter.getSelectedItemCount() != adapter.getItemCount()) {
                    choiceMode.dismissCheckbox();
                }
            }
        }
    }

    public boolean onItemLongClicked(RecyclerViewWithClick recyclerView, int position, View v) {

        SelectableAdapter adapter = (SelectableAdapter) recyclerView.getAdapter();
        AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();

        if (recyclerView.getChoiceMode() != RecyclerChoiceMode.CHOICE_MODE_NONE) {

            choiceMode = (ToolbarActionModeCallback) recyclerView.getChoiceModeListener();

            if (choiceMode.isChoiceModeActivated()) {

                adapter.toggleSelection(position);

                if (adapter.getSelectedItemCount() == 0) {
                    mActionMode.finish();
                } else {
                    mActionMode.setTitle(adapter.getSelectedItemCount()
                            + v.getContext().getResources().getString(R.string.selection_count));
                }

                if (adapter.getChoiceMode() == RecyclerChoiceMode.CHOICE_MODE_MULTIPLE) {
                    if (adapter.getSelectedItemCount() != adapter.getItemCount()) {
                        choiceMode.dismissCheckbox();
                    }
                }

            } else {
                mActionMode = appCompatActivity.startSupportActionMode(choiceMode);
                adapter.startActionMode(position);
                mActionMode.setTitle(adapter.getSelectedItemCount()
                        + v.getContext().getResources().getString(R.string.selection_count));
            }
        }

        return true;
    }
}