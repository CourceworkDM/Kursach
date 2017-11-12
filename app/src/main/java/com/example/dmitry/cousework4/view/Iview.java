package com.example.dmitry.cousework4.view;

import java.util.List;

/**
 * Created by dmitry on 12.11.17.
 */

public interface Iview<T> {
    void onReseived(List<T> list);
}
