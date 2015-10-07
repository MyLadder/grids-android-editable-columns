package com.shinobicontrols.grids.sample.editable_grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.shinobicontrols.grids.core.Column;
import com.shinobicontrols.grids.core.ColumnSpec;
import com.shinobicontrols.grids.supplement.HeaderTextViewHolder;

public class EditTextColumnSpec implements ColumnSpec {

    private final CharSequence columnTitle;
    private WritablePropertyBinder<CharSequence> propertyBinder;
    protected Column.Callback callback;

    public EditTextColumnSpec(WritablePropertyBinder<CharSequence> propertyBinder) {
        this(null, propertyBinder);
    }

    public EditTextColumnSpec(CharSequence columnTitle, WritablePropertyBinder<CharSequence> propertyBinder) {

        if (propertyBinder == null) {
            throw new IllegalArgumentException("The propertyBinder parameter cannot be null");
        }

        this.columnTitle = columnTitle;
        this.propertyBinder = propertyBinder;
    }

    @Override
    public void initialize(Column.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onColumnAdded(Context context) {

    }

    @Override
    public int getItemViewType(int rowIndex) {
        return R.id.edit_text_view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int rowIndex) {
        EditTextViewHolder editTextViewHolder = (EditTextViewHolder) holder;
        CharSequence text = propertyBinder.bind(rowIndex);

        //Prevent old watcher firing upon initial load
        editTextViewHolder.setWatcher(null);
        editTextViewHolder.editText.setText(text);

        //save back edited data
        editTextViewHolder.setWatcher(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                propertyBinder.write(s, rowIndex);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getHeaderItemViewType() {
        return com.shinobicontrols.grids.R.id.sg_header_text_view;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderTextViewHolder headerTextViewHolder = (HeaderTextViewHolder) holder;
        headerTextViewHolder.headerTextView.setText(columnTitle);
    }

    @Override
    public boolean hasHeader() {
        return columnTitle != null;
    }

}
