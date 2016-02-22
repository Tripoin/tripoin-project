package com.tripoin.tsm_mobile.fragment.quotation;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_component.ui.fragment.impl.ANavigationDrawerFragment;
import com.tripoin.tsm_mobile.R;

import java.util.ArrayList;

import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentQuotation extends ANavigationDrawerFragment {

    @InjectView(R.id.panelDetail)
    CardListView panelDetail;

    @InjectView(R.id.panelProduct)
    CardListView panelProduct;

    @Override
    public String getFragmentTitle() {
        return ApplicationConstant.FragmentInfo.Title.QUOTATION;
    }

    @Override
    public void initWidget() {
        initPanels();
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_quotation;
    }

    private void initPanels(){
        Card card = new PanelQuotationDetails(getActivity(), R.layout.card_quotation_detail);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        CardArrayAdapter detailCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);
        if (panelDetail != null){
            panelDetail.setAdapter(detailCardArrayAdapter);
        }

        Card cardProduct = new PanelQuotationProduct(getActivity(), R.layout.card_quotation_product);
        cards = new ArrayList<>();
        cards.add(cardProduct);
        CardArrayAdapter productCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);
        if (panelProduct != null){
            panelProduct.setAdapter(productCardArrayAdapter);
        }
    }
}
