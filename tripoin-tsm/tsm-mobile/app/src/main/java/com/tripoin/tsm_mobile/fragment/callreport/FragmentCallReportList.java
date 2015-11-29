package com.tripoin.tsm_mobile.fragment.callreport;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_component.ui.fragment.impl.ANavigationDrawerFragment;
import com.tripoin.tripoin_dao.impl.DAOCustomer;
import com.tripoin.tripoin_model.ModelCustomer;
import com.tripoin.tsm_mobile.R;
import com.tripoin.tsm_mobile.fragment.customerdata.CardListCustomer;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentCallReportList extends ANavigationDrawerFragment {

    @InjectView(R.id.listCallReport)
    CardListView listCallReport;

    private List<ModelCustomer> modelCustomers;
    private DAOCustomer daoCustomer;

    @Override
    public String getFragmentTitle() {
        return ApplicationConstant.FragmentInfo.Title.CALL_REPORT;
    }

    @Override
    public void initWidget() {
        daoCustomer = new DAOCustomer(getActivity());
        initCustomerList();
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_call_report_list;
    }

    private void initCustomerList(){
        ArrayList<Card> cards = new ArrayList<Card>();
        modelCustomers = daoCustomer.getAllData();
        if(modelCustomers.size() <= 1){
            ModelCustomer modelCustomer = new ModelCustomer();
            modelCustomer.setCustomerName("PT. BESMINDO");
            modelCustomer.setCustomerAddress("Tangerang Selatan");
            modelCustomers.add(modelCustomer);

            ModelCustomer modelCustomer2 = new ModelCustomer();
            modelCustomer2.setCustomerName("PT. MAJU JAYA");
            modelCustomer2.setCustomerAddress("Jakarta Timur");
            modelCustomers.add(modelCustomer2);
        }
        try{
            if(modelCustomers != null){
                for (int i = 0; i<modelCustomers.size() ; i++) {
                    Card card = new CardListCallReport(getActivity(), R.layout.card_call_report_list, modelCustomers.get(i));
                    cards.add(card);
                }
                CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);
                if (listCallReport != null){
                    listCallReport.setAdapter(mCardArrayAdapter);
                }
            }else{
                listCallReport.setAdapter(new CardArrayAdapter(getActivity(), new ArrayList<Card>()));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
