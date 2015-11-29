package com.tripoin.tsm_mobile.fragment.customerdata;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripoin.tripoin_component.ui.card.ABaseCustomCard;
import com.tripoin.tripoin_component.ui.fragment.impl.NavigatorFragment;
import com.tripoin.tripoin_model.ModelCustomer;
import com.tripoin.tsm_mobile.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created on 10/11/2015 : 3:57 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class CardListCustomer extends ABaseCustomCard {

    private FragmentActivity activity;
    private TextView txtCustomerName;
    private TextView txtCustomerAddress;
    private ModelCustomer modelCustomer;

    public CardListCustomer(Context context) {
        super(context);
    }

    public CardListCustomer(FragmentActivity context, int innerLayout, ModelCustomer modelCustomer) {
        super(context, innerLayout);
        this.activity = context;
        this.modelCustomer = modelCustomer;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        txtCustomerName = (TextView) view.findViewById(R.id.txtCustomerName);
        txtCustomerAddress = (TextView) view.findViewById(R.id.txtCustomerAddress);

        if(txtCustomerName != null){
            txtCustomerName.setText(modelCustomer.getCustomerName());
        }

        if(txtCustomerAddress != null){
            txtCustomerAddress.setText(modelCustomer.getCustomerAddress());
        }
    }

    @Override
    public void initActions() {
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                NavigatorFragment navigatorFragment = new NavigatorFragment() {
                    @Override
                    public FragmentActivity getFragmentActivity() {
                        return activity;
                    }
                };
                FragmentInputCustomer1 fragment = new FragmentInputCustomer1();
                navigatorFragment.gotoNextFragment(R.id.container, fragment);
            }
        });
    }
}
