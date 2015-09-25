package com.um.ehrprivacy.utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;

public class UtilTest {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, IOException, JWTVerifyException {
		// TODO Auto-generated method stub
		String CLIENT_SECRET = "PGD-EHR";
		String tokens = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImRvY3RvcmdvdiIsImhvc3BpdGFsIjoiR09WIiwiaWF0IjoxNDQzMTA2MTY0LCJleHAiOjE0NDMxMDk3NjQsImF1ZCI6ImRvY3RvciJ9.mv-viZtVbM6TvLKmow9S5UoOcVRa4To8rStXI-6LBC0";
		try {
            Map<String,Object> decodedPayload =
                new JWTVerifier(CLIENT_SECRET,"doctor").verify(tokens.trim());

            // Get custom fields from decoded Payload
            System.out.println(decodedPayload);
        } catch (SignatureException signatureException) {
            System.err.println("Invalid signature!");
        } catch (IllegalStateException illegalStateException) {
            System.err.println("Invalid Token! " + illegalStateException);
        }
	}

}
