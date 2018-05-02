package com.tpay.payment.utils.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.Writer;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-12 15:57
 **/
public class XStreamInitializer {
    public XStreamInitializer() {
    }

    public static XStream getInstance() {
        XStream xstream = new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(final Writer out) {
                return new PrettyPrintWriter(out, this.getNameCoder()) {
                    protected String PREFIX_CDATA = "<![CDATA[";
                    protected String SUFFIX_CDATA = "]]>";
                    protected String PREFIX_MEDIA_ID = "<MediaId>";
                    protected String SUFFIX_MEDIA_ID = "</MediaId>";

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if(text.startsWith(this.PREFIX_CDATA) && text.endsWith(this.SUFFIX_CDATA)) {
                            writer.write(text);
                        } else if(text.startsWith(this.PREFIX_MEDIA_ID) && text.endsWith(this.SUFFIX_MEDIA_ID)) {
                            writer.write(text);
                        } else {
                            super.writeText(writer, text);
                        }

                    }

                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }
                };
            }
        });
        xstream.ignoreUnknownElements();
        xstream.setMode(1001);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        return xstream;
    }
}
