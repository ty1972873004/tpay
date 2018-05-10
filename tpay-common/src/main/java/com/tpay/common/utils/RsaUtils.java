package com.tpay.common.utils;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.component.xslt.XsltOutput.string;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-18 14:19
 **/
public class RsaUtils {

    private static Logger logger = LoggerFactory.getLogger(RsaUtils.class);

    private static final String SIGN_TYPE_RSA = "RSA";

    private static final String SIGN_TYPE_RSA2 = "RSA2";

    private static final String CHARSET = "UTF-8";

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * RSA/RSA2 生成签名
     *
     * @param map
     *            包含 sign_type、privateKey、charset
     * @return
     * @throws Exception
     */
    public static String rsaSign(Map map) throws Exception {
        PrivateKey priKey = null;
        java.security.Signature signature = null;
        String signType = map.get("sign_type").toString();
        String privateKey = map.get("privateKey").toString();
        String content = getSignContent(map);
        map.put("content", content);
        System.out.println("=============>请求参数生成的字符串为:" + content);
        if (SIGN_TYPE_RSA.equals(signType)) {
            priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
        } else if (SIGN_TYPE_RSA2.equals(signType)) {
            priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        } else {
            throw new Exception("不是支持的签名类型 : : signType=" + signType);
        }
        signature.initSign(priKey);

        if (StringUtil.isEmpty(CHARSET)) {
            signature.update(content.getBytes());
        } else {
            signature.update(content.getBytes(CHARSET));
        }

        byte[] signed = signature.sign();

        return new String(Base64.encodeBase64(signed));

    }


    /**
     * 验签方法
     * @param map
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean rsaCheck(Map map, String sign) throws Exception {
        java.security.Signature signature = null;
        String signType = map.get("sign_type").toString();
        String content = map.get("content").toString();
        String publicKey = map.get("publicKey").toString();
        System.out.println(">>验证的签名为:" + sign);
        System.out.println(">>生成签名的参数为:" + content);
        PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
        if (SIGN_TYPE_RSA.equals(signType)) {
            signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
        } else if (SIGN_TYPE_RSA2.equals(signType)) {
            signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        } else {
            throw new Exception("不是支持的签名类型 : signType=" + signType);
        }
        signature.initVerify(pubKey);

        if (StringUtil.isEmpty(CHARSET)) {
            signature.update(content.getBytes());
        } else {
            signature.update(content.getBytes(CHARSET));
        }

        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins == null || StringUtil.isEmpty(algorithm)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] encodedKey = readText(ins).getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        StringWriter writer = new StringWriter();
        io(new InputStreamReader(ins), writer, -1);

        byte[] encodedKey = writer.toString().getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    /**
     * 把参数合成成字符串
     *
     * @param sortedParams
     * @return
     */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        String[] sign_param = sortedParams.get("sign_param").split(",");// 生成签名所需的参数
        List<String> keys = new ArrayList<String>();
        for (int i = 0; i < sign_param.length; i++) {
            keys.add(sign_param[i]);
        }
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    private static String readText(InputStream ins) throws IOException {
        Reader reader = new InputStreamReader(ins);
        StringWriter writer = new StringWriter();
        io(reader, writer, -1);
        return writer.toString();
    }

    private static void io(Reader in, Writer out, int bufferSize) throws IOException {
        if (bufferSize == -1) {
            bufferSize = DEFAULT_BUFFER_SIZE >> 1;
        }
        char[] buffer = new char[bufferSize];
        int amount;

        while ((amount = in.read(buffer)) >= 0) {
            out.write(buffer, 0, amount);
        }
    }

    public static void main(String[] args) throws Exception{
        String TprivateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDHojxgxIzTDVgpiitiF+MpYqA+zvu+3GsFNqt1kCqBq9JaqISPmTinvzblEcvh+4n+95UvRNeN+Ecc5tT4HM4lACsFWa6VKkJuTcayL0hCQSeayDvjsBYeIMu4nPAuLkxNQNS8Q5wu/S2vjmLhByE+pc7iOegPOYnVJ4qu3gYHDFJBYBcmt3c6z1+a1QYEXsxkeK2LTYYCDygG0hUipbjCWiD//RTRwMEAfkXCZUrWdee+bXaQs7Vj20LZiTY+UlxWMzbmqDVMu4aE9Sb8rWQlrLMylEuxmntQ2BlI49E+1ljEJ/VwD8RjHOAYeYVxXz9iVIQcf1Dj7TGvtdgTCjUlAgMBAAECggEABP7iU+mXPJDQ6/kxLLs8TATTzb6QPi513yOHFuIMrkIGeY5OAunqqQf377OKTpVk1qSLbyth2YOlA+wi2DKgVfyzFucvnzv3fZskpcDZJg9LVmw8U25Mn2RitLP4rq8p6aT9rdhjSCm9wu7lOoxbIJ41lyOr0vW1pUfgCJA1xmdVNgun910Ib8Fv1zjPPzDbwKxpU2Xa2gdT4BpS8GIaJy3ipuEoZVCJM3xVmYYzoOXulFMBA4No0dhw7Po+7Qfq4F7oAYSTLcT4mWH9dctmmjqTb3VwIq9m/txsYGHKU5BFlFMpTH4UU+37VGgTvasaOWMudWNXrRSoCZK0RAT64QKBgQDoWV35DiSVnFVt8LOgO1qOPl7SpNQyEjj/ouog+IbYpIQG6qViRX7pKTix+W04xwcRe4JjyNFYt/ZUBUJtB3YJoXGJIf3l0KVPN12sq8lC8qnXECofTmDneM2c8d5UKEbM5k4pve4uuY35NsgJNO8kLI+UmTDc2ZXN2kb8dnB5uwKBgQDb9FxXEYyrLIt9bNa1tQUINk89lW5csk5eLT2lEZtbi7kYFz7VwI9/zGrfC89OJGssEzeqHVwQ7KJQoxQ5t6pi5S5DjkCuBq2SuJUpLcp3vDFt7JSx/OWlmRF5F56qdQ/+CXFE4a6RmjZapThUoIVaMm2LhYXbn4TLwkX8R30unwKBgQC+JUybYnCcI2liZIaLzacJG4+8MPoa1ADja+7Q9rJcHx6RDnw0FQ2MQJjORYKV4YMuUnZdtN1mDn6/OeKnmWCG9yEUCcZfycVPM88sXRWC/VXy0561Kg5xv9H86494LdHrI1q6HF4MzPucBLHlnvVKFFGYWVm8kzBeW5CWUVMwuwKBgQCXz3ugOKOfTVplUiaLOYpklZ9xgN6wKfz+8Ti34oQY1kIFR539yC8C5OLqWWtWoro5rHXh+uudXsXU5AnEoGiAY2/f/qHTuxY3yyBgUJOKuqBfH0et5T/XYON3cX74ZV2JV5kPErj7E7XqMFjFyBjiHSYQgzLVV68/NzI4olrbPwKBgQDLLsqN3qqhnESy07l7yfb9uIWebNHlREcV00loEvL3LJMWGacHvY824yrx6iAI920ZWVCbqFOyZIPSG1XngKUdarh9AV5uIdSX5rueRQCaHbtv30vhF9gcTNYSCxKj6XnvzQuxIYSLCroEPpeG7Q6SU+qv+X34yAV+5RNFhFHE5A==";
        String TpublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx6I8YMSM0w1YKYorYhfjKWKgPs77vtxrBTardZAqgavSWqiEj5k4p7825RHL4fuJ/veVL0TXjfhHHObU+BzOJQArBVmulSpCbk3Gsi9IQkEnmsg747AWHiDLuJzwLi5MTUDUvEOcLv0tr45i4QchPqXO4jnoDzmJ1SeKrt4GBwxSQWAXJrd3Os9fmtUGBF7MZHiti02GAg8oBtIVIqW4wlog//0U0cDBAH5FwmVK1nXnvm12kLO1Y9tC2Yk2PlJcVjM25qg1TLuGhPUm/K1kJayzMpRLsZp7UNgZSOPRPtZYxCf1cA/EYxzgGHmFcV8/YlSEHH9Q4+0xr7XYEwo1JQIDAQAB";

        String BprivateKey ="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC6nUK7p5+SoM0bgZh83qwBlanEFI26jhnDCCIEEVoo5G3H91T0YdRnAon5hgBDy+V61RRN9mSLqCjPyRE9nRbAWuw2I2YcTLrQinuzsOtTT88cBija79U+uPnuJWjJyQlK48hBpvsRu2PXP58Noug23BHBtC4Asjlho40HIfE4TqwHyoJWfuTmc3/p7gQqZwknA0JG2LWFmy14kM0A+3PWdkEY/ypbwXYioJ5t+hd8r68Fip8GjZdsetUMTWtKOKDa47MZoLX0YdnMojSxD5zuoc7amLzak0Miocc6og6bynAF2ZfQQMcjxFyRKJHbqvROMYk3A34m2ALX3s2+oPb7AgMBAAECggEAcmH2B2OlEZDyZ0u2FcO+lnIPzpnyjUiQTdTsVKX2J15sU8csEWbtc/5AZ+tUujl9/R4iBe9ijZ+S9Fl/8c4ZpemVI1HrQqldUHmxfOCSPUyL29eCrz+V644h03CmnBhXU8nucx9QgPvlJIhgi1ExGH8nex1fnRmgxlIjHR4W5rV17aiVvyuTxMhB0giB7EFwvirrQhQOaRa3IQ6N28E7n58lEKsnqCpRM0S3WIFCPcICvA3b4v5QctltREWTJPYAeeOdqB37vij7rq5AoSFF1OGauCp8kwIKSe87lbXQodsQkY3Ois85uQQtUGp4z/lEWi5rqvmHkt1lblKRtx9DsQKBgQDiRoHy20E7HWVpK8sQIvUzJag2Z/UiSnIfIakcNqfhCqjEkO5ro5esV6a8HoLf2rrefy+um+ifMPjr04e611lz9D6r0ZQ61Ev09zjEtN+Xvu2RnQgrBdhHhfCIR5+7+ec1xwyrYrV3YGERM1hG0HwzoNvltClyyLQ+vpqmD2kvxQKBgQDTIPnSDe/bUDlyQYS6DmHo+T0cLdDFm6ok9vtghyLgJSVj21Uf0krXnKvJUiAvHKinsxOZ4TRwthxDJUDERx7rvrYcWhykwJbRpz8zwJNxwRyxn8bCnMw7gdCAca5TWSYqeYsqzVSg6hW4nrQ7U0KBsNJr3d/0eUT3ji8I8Yc3vwKBgQDNLSX0MwAJPHSSUxWEgk5YnJLVEprjByJIPFt9q8m6c9Hou4qVq/eCXNBh0EDX/xxnWGjCKblbcCqmnF58+3yveg/B+P4yAgMGE440P9ZnYMdGvF+Fs30UDc23pUqgRtByoRVJ6u2lW41o7WfkfnPA1OHQffb/kCJwqqDMZzj4CQKBgQCwHgjnVWLpMqHJEqhyP/8ixY5ZjEpkHPcwgKqvGetYyQPIqbT3p4dxFqsidBSO2DxEMBjAu2DwSKmIxXGiiaVciCkHu4+7S0BEQVxygkk8kheqgBrgSsX/OcnA65O8yVgSBHO3z7KxzLMz34d/GXQYFXViL6JbIDIMw5muvzPJpwKBgQCvarqEy0jJA29SMvPIMLEsjm98AzkGHU0J0YG7OaPwnuE1pPKInYksquwGJkEUhfrtxns+7bYuO+9bIH2AJ+LMKx96RyWjVqLCWVSZg/E7A9z7yVjlvzJGiukw2YEmPASiJLYyTeYnHKO47bgUnc/4JXumaPSZ9WcS7dFl51Tulw==";
        String BpublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAup1Cu6efkqDNG4GYfN6sAZWpxBSNuo4ZwwgiBBFaKORtx/dU9GHUZwKJ+YYAQ8vletUUTfZki6goz8kRPZ0WwFrsNiNmHEy60Ip7s7DrU0/PHAYo2u/VPrj57iVoyckJSuPIQab7Ebtj1z+fDaLoNtwRwbQuALI5YaONByHxOE6sB8qCVn7k5nN/6e4EKmcJJwNCRti1hZsteJDNAPtz1nZBGP8qW8F2IqCebfoXfK+vBYqfBo2XbHrVDE1rSjig2uOzGaC19GHZzKI0sQ+c7qHO2pi82pNDIqHHOqIOm8pwBdmX0EDHI8RckSiR26r0TjGJNwN+JtgC197NvqD2+wIDAQAB";


        Map<String, String> map = InstanceUtil.newHashMap();
        map.put("sign_type","RSA2");
        map.put("privateKey",BprivateKey);
        map.put("charset","UTF-8");
//        map.put("sign_param","mchId,payOrderNo");
//        map.put("mchId","8000000000");
//        map.put("payOrderNo","20180410171600098");

        map.put("sign_param","mchId,mchOrderNo,channelId,amount,currency,clientIp,device,notifyUrl,subject,body");
        map.put("mchId","8000000000");
        map.put("mchOrderNo","20180410171600106");
        map.put("channelId","ALIPAY_MOBILE");
        map.put("amount","1");
        map.put("currency","CNY");
        map.put("clientIp","127.0.0.1");
        map.put("device","PC");
        map.put("notifyUrl","http://sys.javaxxw.cn/test/notify");
        map.put("subject","测试商品标题");
        map.put("body","测试商品描述");

        System.out.println(rsaSign(map));



//
//        String sign = "aMySYtjh7ynCyWZoaB/PDyxI1P7idkmL9bVBwFkR3c2f4euCE3Do0dLMIzsr9mdJCKvd4CD4535oTUM4APJ9XzFB9r9Rd//uqBAmYyUqLYyCQoNUTZYJBJByalCTS7LfOkk3LC3sJREWvM1IwJTkF4qLiXdCB2d7tiZ1Bgohf4rqMhx5E+3nWOO7NVA/5nHtSrUTbPUbos5gZOSM7ljEQmbYs7by8b9PuZAdr5WWDH6suke1fnV9OzyMUoYr91gGqM8IlR2AMP1hG3g1HTRaO6mM44db0dkEdpWYA1U20caPwege5VMDdvw7PkaA9q7n0oCo85yUOo8XqD3ChANcFQ==";
//
//        Map<String, String> map1 = InstanceUtil.newHashMap();
//        map.put("sign_type","RSA2");
//        map.put("privateKey",TprivateKey);
//        map.put("charset","UTF-8");
//        map.put("content","name=U232132132&sex=1");
//        map.put("publicKey",TpublicKey);
//        System.out.println(rsaCheck(map,sign));
//         System.out.println(TpublicKey.length());
    }
}
