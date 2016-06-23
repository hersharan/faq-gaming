package com.faqgaming.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.faqgaming.domain.enums.Role;
import com.faqgaming.dto.TokenDto;
import com.faqgaming.exception.NotAuthorisedException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class AuthUtils {
  
  private static final Logger LOGGER = Logger.getLogger("AuthUtils");


  private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);

  private static final String TOKEN_SECRET = "TgY_Kx=Khcwv/79j";

  private static final String ISSUER = "http://www.prontoitlabs.com";

  private static final String ROLE = "userRole";

  public static TokenDto decodeToken(String authHeader) throws NotAuthorisedException {
    ReadOnlyJWTClaimsSet decode = decode(authHeader);
    String token = xor(decode.getSubject().getBytes());
    Role role = Role.valueOf(decode.getCustomClaim(ROLE).toString());

    return new TokenDto(token, role);
  }

  public static ReadOnlyJWTClaimsSet decode(String authHeader) throws NotAuthorisedException {

    ReadOnlyJWTClaimsSet jwtClaimsSet = null;
    try {
      jwtClaimsSet = SignedJWT.parse(getSerializedToken(authHeader)).getJWTClaimsSet();
    } catch (ParseException e) {
      throw new NotAuthorisedException("Invalid Token provided!");
    }
    Date expiryDate = jwtClaimsSet.getExpirationTime();
    Date now = DateTime.now().toDate();

    if (now.after(expiryDate)) {
      throw new NotAuthorisedException("Token expired");
    }
    return jwtClaimsSet;
  }

  public static String createToken(TokenDto tokenDto) {
    String subject = tokenDto.getPayload();
    JWTClaimsSet claim = new JWTClaimsSet();
    claim.setSubject(xor(subject.getBytes()));
    claim.setCustomClaim(ROLE, tokenDto.getRole());
    claim.setIssuer(ISSUER);
    claim.setIssueTime(DateTime.now().toDate());
    claim.setExpirationTime(DateTime.now().plusDays(30).toDate());

    JWSSigner signer = new MACSigner(TOKEN_SECRET);
    SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
    try {
      jwt.sign(signer);
    } catch (JOSEException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return jwt.serialize();
  }

  private static String getSerializedToken(String authHeader) {
    return authHeader;
  }

  private static String xor(final byte[] input) {
    final byte[] output = new byte[input.length];
    final byte[] secret = TOKEN_SECRET.getBytes();
    int spos = 0;
    for (int pos = 0; pos < input.length; ++pos) {
      output[pos] = (byte) (input[pos] ^ secret[spos]);
      spos += 1;
      if (spos >= secret.length) {
        spos = 0;
      }
    }
    return new String(output);
  }

  public static String createTokenForForgetPassword(String subject) {

    JWTClaimsSet claim = new JWTClaimsSet();
    claim.setSubject(xor(subject.getBytes()));
    claim.setIssuer(ISSUER);
    claim.setIssueTime(DateTime.now().toDate());
    claim.setExpirationTime(DateTime.now().plusMillis(1000 * 60).toDate());
    JWSSigner signer = new MACSigner(TOKEN_SECRET);
    SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
    try {
      jwt.sign(signer);
    } catch (JOSEException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return jwt.serialize();
  }

  public static int createRandomToken(int min, int max) {
    return (int) Math.floor(Math.random() * (max - min + 1)) + min;
  }


}
