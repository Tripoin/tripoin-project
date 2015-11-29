package com.tripoin.tripoin_component.ui.fragment;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Achmad Fauzi on 11/24/2014.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 *
 * Interface ini digunakan sebagai fungsi- fungsi common sebuah fragment
 */
public interface IFragment extends INavigationFragment {

    /**
     * Method ini digunakan untuk inisiasi widget- widget activity yang sedang aktif termasuk variabel
     */
    void initWidget();

    int getViewLayoutId();

    /**
     * Mengkoleksi TextView dalam grup content dalam sebuah Activity
     * @return List<TextView>
     */
    List<TextView> getTextViews();

    /**
     * Mengkoleksi EditText dalam sebuah Activity
     * @return List<EditText>
     */
    List<EditText> getEditTexts();

    /**
     * Mengkoleksi Button dalam sebuah Fragment
     * @return List<Button>
     */
    List<Button> getButtons();

    String[] initAssetName();

}
