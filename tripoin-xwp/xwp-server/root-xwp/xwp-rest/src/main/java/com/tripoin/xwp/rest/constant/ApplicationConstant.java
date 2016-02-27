package com.tripoin.xwp.rest.constant;

/**
 * Created on 11/9/15 9:55 AM.
 *
 * @author <a href="mailto:achmad.fauzi@sigma.co.id">Achmad Fauzi</a>
 */
public interface ApplicationConstant {

    final String COMPONENT_SCAN   = "com.tripoin.xwp.rest";
    final String BASE_PACKAGES    = "com.tripoin.xwp";
    final long DAY_IN_MILLIS      = 1000 * 60 * 60 * 24;

    interface LOG{
        final String INFO           = "XWP_INFO : {} ";
        final String DEBUG          = "XWP_DEBUG : {} ";
        final String ERROR          = "XWP_ERROR : {} ";
        final String WARN           = "XWP_WARNING : {} ";
    }

    interface MIKROTIK{
        String IP                   = "192.168.110.1";

        interface COMMAND{
            String GET_ALL_PROPERTIES = "/interface/print";
        }

        interface INTERFACES{
            String MAC_ADDRESS = "mac-address";
            String RX_ERROR = "rx-error";
            String RX_DROP = "rx-drop";
            String ID = ".id";
            String TYPE = "type";
            String RX_PACKET = "rx-packet";
            String DEFAULT_NAME = "default-name";
            String MTU = "mtu";
            String LAST_LINK_UP_TIME = "last-link-up-time";
            String RUNNING = "running";
            String TX_BYTE = "tx-byte";
            String ACTUAL_MTU = "actual-mtu";
            String FAST_PATH = "fast-path";
            String LINK_DOWNS = "link-downs";
            String NAME = "name";
            String TX_DROP = "tx-drop";
            String DISABLED = "disabled";
            String RX_BYTE = "rx-byte";
            String TX_ERROR = "tx-error";
            String TX_PACKET = "tx-packet";
        }
    }

    interface AUDITRAIL{
        String CREATED_BY           = "SYSTEM";
    }


    interface REST{
        final String REST_PATH    = "/service";

        interface CHANGE_DATE{
            final String CHANGE_SYSTEM_DATE         = "/changeSystemDate";
            final String CHANGE_REPORT_DATE         = "/changeReportDate";
        }

        interface MANAGE_BANK{
            final String SELECT_BY_CODE             = "/bank/select/code";
        }

        interface MANAGE_MIKROTIK{
            final String GET_ALL_INTERFACES         = "/getAllInterfaces";
        }
    }

    interface SPRING_COMPONENT {
        interface SERVICE{
            String SERVICE_BANK                     = "serviceBank";
            String SERVICE_MIKROTIK                 = "serviceMikrotik";
        }
        interface PROCESS{
        }
    }


    interface PARAM_TABLE {    }
}
