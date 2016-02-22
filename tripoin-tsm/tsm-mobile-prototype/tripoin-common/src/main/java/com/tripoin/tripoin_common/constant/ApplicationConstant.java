package com.tripoin.tripoin_common.constant;

/**
 * Created on 9/22/2015 : 4:58 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface ApplicationConstant {

    interface Log {
        String TRIPOIN_INFO = "TRIPOIN INFO";
        String TRIPOIN_ERROR = "TRIPOIN ERROR";
        String TRIPOIN_WARNING = "TRIPOIN WARNING";
        String TRIPOIN_DEBUG = "TRIPOIN DEBUG";
    }

    interface FragmentInfo{
        interface Title{
            String PERSONAL_INFO = "Personal Info";
            String CUSTOMER_DATA = "Customer Data";
            String CALL_PLAN = "Call Plan";
            String CALL_REPORT = "Call Report";
            String QUOTATION = "Quotation";
            String ABOUT = "About";
            String CUSTOMER_LIST = "Customer List";
            String INPUT_CUSTOMER = "Input Customer";
        }
    }

    interface Rest {
        String INIT_HOST = "webservice-tripoin.rhcloud.com";
        int INIT_PORT = 80;

        /*String INIT_HOST = "127.0.0.1";
        int INIT_PORT = 8081;*/

        String HOST_PROD = "180.250.115.49";

        String BASE_URL_DEV = "http://10.210.9.84:8080";
        String BASE_URL_PROD = "http://180.250.115.49:8080";

        String AUTHORIZATION = "Authorization";
        String BASIC = "Basic";
        String ACCEPT = "Accept";
        String CONTENT_TYPE = "Content-Type";
        String APPLICATION_JSON = "application/json";
        String APPLICATION_XML = "application/xml";
        String WS_CONTEXT = "tripoin/wscontext/";
        String MULTIPART = "multipart/form-data";

        interface Status{
            int SUCCESS = 200;
        }

        interface EndPoints{
            String LOGIN = "/login/";
            String LOAD_USER = "/user/load";
            String LOGOUT = "/logout/";
            String OADV = "/validation/val001";
            String UPLOAD = "/upload";
        }

        interface DTO{
            interface Request{
                String USER_CODE = "user_code";
                String POI_CODE = "poi_code";
                interface Login{
                    String SAMPLE_USER = "SAMPLE USER";
                    String SAMPLE_ROLE = "SAMPLE ROLE";
                }
            }
            interface Response{
                interface Base{
                    String RESPONSE_CODE = "responseCode";
                    String RESPONSE_MESSAGE = "responseMsg";
                    String RESPONSE_DESCRIPTION = "responseDesc";
                }
                interface Login{
                    String USER_DATAS = "userDatas";
                    interface UserData{
                        String ID = "id";
                        String USER_NAME = "username";
                        String ENABLED = "enabled";
                        String EXPIRED_DATE = "expiredDate";
                        String NON_LOCKED = "nonLocked";
                        String AUTH = "auth";
                        String STATUS = "status";
                        String REMARKS = "remarks";
                        String ROLE_DATA = "roleData";
                        interface RoleData{
                            String CODE = "code";
                        }
                    }
                }

                String VALIDATION_RESULT = "validation_result";
            }
        }
    }


    interface TransferKeys{
        String TRIPOIN_PREFERENCE = "tripoin_preference";
        String USER_LOGIN = "user_login";
    }

    interface Database {
        String TENANT_NAME = "CHAKRA_JAWARA";
        String DATABASE_NAME = "tripoin_".concat(TENANT_NAME);
        int DB_VERSION = 1;
        String ID = "id";
        String INIT_USER = "UNREGISTERED_USER";

        interface TableUser {
            String TABLE_NAME = "tsm_user";
            String USER_NAME = "user_name";
            String USER_CODE = "user_code";
            String LOGIN_STATUS = "login_status";
            String IS_ACTIVE = "is_active";
            String CHIPPER_AUTH = "chipper_auth";
        }

        interface TableCustomer {
            String TABLE_NAME = "tsm_customer";
            String CUSTOMER_NAME = "customer_name";
            String CUSTOMER_ADDRESS = "customer_address";
        }

        interface TableTestConfiguration {
            String TABLE_NAME = "dynamic_configuration";
            String SCHEDULER_INTERVAL = "scheduler_interval";
            String HOST = "host";
            String PORT = "port";
            String START_WORKING_HOUR = "start_working_hour";
            String STOP_WORKING_HOUR = "stop_working_hour";
        }
    }

    interface Status {
        interface Process {
            String PASS = "P";
            String FAILED = "F";
        }

        interface Config {
            String ACTIVE = "A";
            String DEACTIVE = "D";
        }
    }

    interface ThreadName {
        String BGP_LOGIN = "bgp login";
    }

}
