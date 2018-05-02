package com.tpay.payment.utils.xml;

import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-12 15:58
 **/
public class XStreamCDataConverter extends StringConverter {
    public XStreamCDataConverter() {
    }

    @Override
    public String toString(Object obj) {
        return "<![CDATA[" + super.toString(obj) + "]]>";
    }
}
