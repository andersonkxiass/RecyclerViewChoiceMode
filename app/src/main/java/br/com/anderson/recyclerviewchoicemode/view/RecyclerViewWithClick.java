package br.com.anderson.recyclerviewchoicemode.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import br.com.anderson.recyclerviewchoicemode.R;
import br.com.anderson.recyclerviewchoicemode.model.RecyclerChoiceMode;

public class RecyclerViewWithClick extends RecyclerView {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private int mChoiceMode = RecyclerChoiceMode.CHOICE_MODE_NONE;

    private ActionMode.Callback actionMode;

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                ViewHolder holder = getChildViewHolder(v);
                mOnItemClickListener.onItemClicked(RecyclerViewWithClick.this, holder.getAdapterPosition(), v);
            }
        }
    };

    private OnLongClickListener mOnLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                ViewHolder holder = getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClicked(RecyclerViewWithClick.this, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };

    private OnChildAttachStateChangeListener mAttachListener
            = new OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(mOnClickListener);
            }
            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(mOnLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
        }
    };

    public RecyclerViewWithClick(Context context) {
        super(context);
        init();
    }

    public RecyclerViewWithClick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setTag(R.id.item_click_support, this);
        addOnChildAttachStateChangeListener(mAttachListener);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(RecyclerViewWithClick recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerViewWithClick recyclerView, int position, View v);
    }

    public int getChoiceMode() {
        return mChoiceMode;
    }

    public void setChoiceMode(int mChoiceMode) {
        this.mChoiceMode = mChoiceMode;
    }

    public void setOnChoiceModeListener(ActionMode.Callback actionMode){
        this.actionMode = actionMode;
    }

    public ActionMode.Callback getChoiceModeListener(){
        return this.actionMode;
    }
}