package com.tripoin.tsm_mobile.fragment.quotation;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripoin.tripoin_component.ui.card.ABaseCustomCard;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created on 10/11/2015 : 3:57 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class PanelQuotationDetails extends ABaseCustomCard {

    private FragmentActivity activity;

    public PanelQuotationDetails(Context context) {
        super(context);
    }

    public PanelQuotationDetails(FragmentActivity context, int innerLayout) {
        super(context, innerLayout);
        this.activity = context;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

    }

    @Override
    public void initActions() {
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
            }
        });
    }
}
