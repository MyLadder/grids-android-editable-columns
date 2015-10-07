package com.shinobicontrols.grids.sample.editable_grid;

import com.shinobicontrols.grids.supplement.PropertyBinder;

public interface WritablePropertyBinder<T> extends PropertyBinder<T>{
    void write(T data, int rowIndex);
}
