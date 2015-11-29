package com.tripoin.tsm_mobile.fragment.callreport;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripoin.tripoin_common.constant.GeneralConstant;
import com.tripoin.tripoin_component.ui.card.ABaseCustomCard;
import com.tripoin.tripoin_component.ui.dialog.option.TRIPOINOptionDialog;
import com.tripoin.tripoin_component.ui.fragment.impl.NavigatorFragment;
import com.tripoin.tripoin_model.ModelCustomer;
import com.tripoin.tsm_mobile.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created on 10/11/2015 : 3:57 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class CardListCallReport extends ABaseCustomCard {

    private FragmentActivity activity;
    private TextView txtCustomerName;
    private TextView txtCustomerAddress;
    private ModelCustomer modelCustomer;

    public CardListCallReport(Context context) {
        super(context);
    }

    public CardListCallReport(FragmentActivity context, int innerLayout, ModelCustomer modelCustomer) {
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
                final NavigatorFragment navigatorFragment = new NavigatorFragment() {
                    @Override
                    public FragmentActivity getFragmentActivity() {
                        return activity;
                    }
                };
                final FragmentCallReportDeal fragmentCallReportDeal = new FragmentCallReportDeal();
                final FragmentCallReportLost fragmentCallReportLost = new FragmentCallReportLost();

                TRIPOINOptionDialog tripoinOptionDialog = new TRIPOINOptionDialog() {
                    @Override
                    public void onClickPositive() {
                        navigatorFragment.gotoNextFragment(R.id.container, fragmentCallReportDeal);
                    }

                    @Override
                    public void onClickNegative() {
                        navigatorFragment.gotoNextFragment(R.id.container, fragmentCallReportLost);
                    }
                };
                tripoinOptionDialog.setParameter(activity);
                tripoinOptionDialog.buildAndShow(
                        GeneralConstant.Punctuation.EMPTY,
                        activity.getResources().getString(R.string.options),
                        activity.getResources().getString(R.string.deal),
                        activity.getResources().getString(R.string.lost)
                );
            }
        });
    }
}
