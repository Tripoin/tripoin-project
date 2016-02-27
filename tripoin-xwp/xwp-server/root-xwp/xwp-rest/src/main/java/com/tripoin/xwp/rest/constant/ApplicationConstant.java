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
    }

    interface SPRING_COMPONENT {
        interface SERVICE{
            String SERVICE_BANK                     = "serviceBank";
        }
        interface PROCESS{
        }
    }


    interface PARAM_TABLE {    }
}
