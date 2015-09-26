package com.tripoin.tsm_mobile;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;

import com.tripoin.tripoin_component.ui.activity.base.ABaseActivity;
import com.tripoin.tripoin_dao.DAOHelper;
import com.tripoin.tripoin_dao.impl.DAOUser;
import com.tripoin.tripoin_model.ModelDynamicConfiguration;
import com.tripoin.tripoin_model.ModelUser;

import java.util.List;

import butterknife.InjectView;
import retrofit.RetrofitError;

public class ActivityMain extends ABaseActivity implements IPostOnceADayValidation {

    @InjectView(R.id.btSend)
    Button bt;



    @Override
    public void onPostSuccessOnceADayValidation(DTOOnceADayValidation dtoOnceADayValidation) {
        Log.d("oadv", dtoOnceADayValidation.toString());
    }

    @Override
    public void onPostFailureOnceADayValidation(RetrofitError retrofitError) {
        Log.d("oadv", retrofitError.getLocalizedMessage());
    }

    @Override
    public void initWidget() {
        new DAOHelper(this).getWritableDatabase();
        DAOUser daoUser = new DAOUser(this);
        ModelUser modelUser = new ModelUser();
        modelUser.setIsActive(1);
        modelUser.setLoginStatus(1);
        modelUser.setUserName("abc");
        daoUser.insertEntity(modelUser);

        modelUser.setIsActive(2);
        modelUser.setLoginStatus(2);
        modelUser.setUserName("def");
        daoUser.insertEntity(modelUser);

        List<ModelUser> modelUsers = daoUser.getAllData();
        for(ModelUser user: modelUsers){
            Log.d("user", user.toString());
        }
        modelUser = (ModelUser) daoUser.getAllData().get(0);
        Log.d("modeluser", modelUser.toString());

        DAODynamicConfiguration daoDynamicConfiguration = new DAODynamicConfiguration(this);
        ModelDynamicConfiguration modelDynamicConfiguration = new ModelDynamicConfiguration();
        modelDynamicConfiguration.setHost("10.210.9.84");
        modelDynamicConfiguration.setPort(8080);
        daoDynamicConfiguration.insertEntity(modelDynamicConfiguration);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void goToMainView(String extraKey, String extraContent) {
    }

    @OnClick(R.id.btSend)
    public void klik(){
        Log.d("click", "klik");
        new OADV() {
            @Override
            public DTOOnceADayValidationRequest dtoOneADayValidationRequest() {
                DTOOnceADayValidationRequest dtoOnceADayValidation = new DTOOnceADayValidationRequest();
                dtoOnceADayValidation.setPoi_code("201707");
                dtoOnceADayValidation.setUser_code("510102125987162");
                return dtoOnceADayValidation;
            }

            @Override
            public IPostOnceADayValidation iPostOnceADayValidation() {
                return MainActivity.this;
            }
        }.execute();
    }

    private abstract class OADV extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            RestParameter restParameter = new RestParameter();
            restParameter.setUserName("");
            restParameter.setPassword("");
            restParameter.setContext(MainActivity.this);

            RestGenerator restGenerator = new RestGenerator();
            restGenerator.setParameter(restParameter);

            EPOnceADayValidation epOnceADayValidation = (EPOnceADayValidation) restGenerator.createService(EPOnceADayValidation.class);
            epOnceADayValidation.validateOnceADay(dtoOneADayValidationRequest(), new CallBackOnceADayValidation(iPostOnceADayValidation()));
            return null;
        }

        public abstract DTOOnceADayValidationRequest dtoOneADayValidationRequest();
        public abstract IPostOnceADayValidation iPostOnceADayValidation();
    }


}
