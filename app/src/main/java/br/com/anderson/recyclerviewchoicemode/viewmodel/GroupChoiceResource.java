package br.com.anderson.recyclerviewchoicemode.viewmodel;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.CheckedTextView;

import br.com.anderson.recyclerviewchoicemode.model.RecyclerChoiceMode;
import br.com.anderson.recyclerviewchoicemode.view.RecyclerViewWithClick;

public class GroupChoiceResource {

    private static final int checkMaterial = android.support.v7.appcompat.R.drawable.abc_btn_check_material;
    private static final int radioMaterial = android.support.v7.appcompat.R.drawable.abc_btn_radio_material;

    @BindingAdapter({"choiceType", "state"})
    public static void choiceModeStyle(CheckedTextView view, int choiceMode, boolean modeState) {

        Drawable drawable = null;

        if (modeState) {

            if (choiceMode == RecyclerChoiceMode.CHOICE_MODE_MULTIPLE) {
                drawable = ContextCompat.getDrawable(view.getContext(), checkMaterial);
            } else if (choiceMode == RecyclerChoiceMode.CHOICE_MODE_SINGLE) {
                drawable = ContextCompat.getDrawable(view.getContext(), radioMaterial);
            }
        }

        view.setCheckMarkDrawable(drawable);
    }
}