package com.tripoin.core.rest.security.bca;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class SignatureBCA {

	public static String hexSha256(String data){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        md.update(data.getBytes());        
        byte byteData[] = md.digest(); 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
	}

    public static String hmacSha256(String KEY, String VALUE) {
        return hmacSha(KEY, VALUE, "HmacSHA256");
    }

    private static String hmacSha(String KEY, String VALUE, String SHA_TYPE) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(KEY.getBytes("UTF-8"), SHA_TYPE);
            Mac mac = Mac.getInstance(SHA_TYPE);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(VALUE.getBytes("UTF-8"));

            byte[] hexArray = {
                    (byte)'0', (byte)'1', (byte)'2', (byte)'3',
                    (byte)'4', (byte)'5', (byte)'6', (byte)'7',
                    (byte)'8', (byte)'9', (byte)'a', (byte)'b',
                    (byte)'c', (byte)'d', (byte)'e', (byte)'f'
            };
            byte[] hexChars = new byte[rawHmac.length * 2];
            for ( int j = 0; j < rawHmac.length; j++ ) {
                int v = rawHmac[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
	
	public static void main(String[] args) {
		//SAMPLE SIGNATURE BCA
		String data = "{\"CompanyCode\":\"80173\","+
					"\"PrimaryID\":\"1111222233334445\","+
					"\"TransactionID\":\"TRX9876543210\","+
					"\"ReferenceID\":\"111222333444\","+
					"\"RequestDate\":\"2016-02-03T10:00:00.000+07:00\","+
					"\"Amount\":\"10000.00\","+
					"\"CurrencyCode\":\"IDR\"}";
		String signature = "POST"+":"+"/ewallet/payments"
				+":"+"lIWOt2p29grUo59bedBUrBY3pnzqQX544LzYPohcGHOuwn8AUEdUKS"
				+":"+SignatureBCA.hexSha256(data)+":"+"2016-02-03T10:00:00.000+07:00";
        System.out.println(SignatureBCA.hmacSha256("22a2d25e-765d-41e1-8d29-da68dcb5698b", signature));
    }
    
}
