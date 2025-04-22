package org.horizon.sdk.library.java.tool.lang.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.crypto.asymmetric.KeyType;
import org.dromara.hutool.crypto.asymmetric.RSA;

/**
 * @author wjm
 * @since 2025-04-15 18:34
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RSAs {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Singleton {

        private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwNzOx0DD52qDlpx62Py7oNrVNFsZbVfpIkoTBq0PL6xLlM4pjEABjJlbo73jqYSVSxtHYXkyLM436kOCzKaG7pPNFOiP9jyoJkxd6w/rFFgc3gthfifwQ/zgAbx6lamDiJgS8jtQv0RGU+GlcDzr0Qonovqj+FljZ8UQkvxX4NQIDAQAB";

        private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALA3M7HQMPnaoOWnHrY/Lug2tU0WxltV+kiShMGrQ8vrEuUzimMQAGMmVujveOphJVLG0dheTIszjfqQ4LMpobuk80U6I/2PKgmTF3rD+sUWBzeC2F+J/BD/OABvHqVqYOImBLyO1C/REZT4aVwPOvRCiei+qP4WWNnxRCS/Ffg1AgMBAAECgYAT+1Z90c1T78Ix+HbnlJeVlmqNoMkCX2f/pbmvGmQYMgJVAjitjgl1NMpDJLCR500rq0btYZgNat2tLZOFTXG83DBjvZCL93VPxM3ukpRah4mOtj/43cxDEOQ2lh9CgWeXtQLQEmAt7iohHqoEAlCSEofiXUZssKkiAe/d/qtXcQJBALW3W+tQaN306HubrkAFqLOYhzE1wRxidYQLsjmDHNwDksH4+K44pmrhPsmXXH4mK6q9/nU6SLSE7S2KDAwfAvkCQQD4QDPa2PiKGXoZ7Ce3TgAFNpTnqr6cRchuMZx7GClnukxsueWPnJx9HKPNHwkC5P2se+nVYnI9eV9jQn7abjIdAkBdywWr4PYv0nCJFKMNMp6QDKODN+60GvHzNSJJ4y3rNgDzE/Iv4terBRYmcXTxO/yABc8obOwIGTBeuAld3EfpAkA8mX+PBrP1Ei8KMOmpB3Nd0msa+kU5ZJd+vsGSEt3YolORnvd4zSQuLlWV9uN+P2PlPQJhwnxcoUo1sr/AlnHJAkAOn6nBiQiUezfWV/4RzpF5cQ7IoRjrHl61SkkaAs13GsDdQ96LlR6osrRodk6XgGWGQ1ln3uJwur3VhzkaOWpA";

        private static final RSA SINGLETON_RSA = new RSA(PRIVATE_KEY, PUBLIC_KEY);

        public static String getPublicKey() {
            return PUBLIC_KEY;
        }

        public static String encrypt(String content) {
            return SINGLETON_RSA.encryptBase64(content, KeyType.PublicKey);
        }

        public static String decrypt(String content) {
            return SINGLETON_RSA.decryptStr(content, KeyType.PrivateKey);
        }

    }

}