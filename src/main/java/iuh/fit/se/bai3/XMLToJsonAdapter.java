package iuh.fit.se.bai3;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class XMLToJsonAdapter implements XMLDataSystemNew{
    private XMLDataSystemOld xmlDataSystemOld;
    public XMLToJsonAdapter(XMLDataSystemOld xmlDataSystemOld){
        this.xmlDataSystemOld = xmlDataSystemOld;
    }
    @Override
    public String getJsonData() throws IOException {
        String xmlString = xmlDataSystemOld.getXMLData();

        XmlMapper xmlMapper = new XmlMapper();
        Data dataIntances = xmlMapper.readValue(xmlString.getBytes(), Data.class);
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.writeValueAsString(dataIntances);

    }
}
