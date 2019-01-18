package polytech.si5.al.mobile.adapter;

import java.util.List;

public interface SettableAdapter<T> {

    void clear();

    void addAll(List<T> list);
}
