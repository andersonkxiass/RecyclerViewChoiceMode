package br.com.anderson.recyclerviewchoicemode.view;

import android.content.Context;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import br.com.anderson.recyclerviewchoicemode.R;
import br.com.anderson.recyclerviewchoicemode.model.RecyclerChoiceMode;

public class ToolbarActionModeCallback implements ActionMode.Callback {

    private boolean choiceModeActivated = false;
    private SelectableAdapter adapter;
    private  CheckBox checkBox;

    public ToolbarActionModeCallback(SelectableAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        choiceModeActivated = true;

        boolean multi = adapter.getChoiceMode() == RecyclerChoiceMode.CHOICE_MODE_MULTIPLE;

        mode.getMenuInflater().inflate(R.menu.action_mode_menu, menu);

        menu.getItem(0).setVisible(multi);

        return true;
    }

    @Override
    public boolean onPrepareActionMode(final ActionMode mode, Menu menu) {

        checkBox = (CheckBox) menu.getItem(0).getActionView();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Context v = buttonView.getContext();

                if (isChecked) {
                    adapter.toggleSelectionAll();
                    mode.setTitle(adapter.getSelectedItemCount()
                            + v.getResources().getString(R.string.selection_count));
                } else {
                    adapter.clearSelection();
                    mode.finish();
                }
            }
        });

        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        choiceModeActivated = false;
        adapter.stopActionMode();
    }

    public boolean isChoiceModeActivated() {
        return choiceModeActivated;
    }

    public void dismissCheckbox(){
        checkBox.setSelected(false);
    }
}