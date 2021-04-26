package com.hr.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class TestUtils {
    public String readFromGlobalPropertiesFile(String key) throws IOException {
        File globalPropFile = new File("src/main/resources/config.properties");
        FileInputStream stream = new FileInputStream(globalPropFile);
        Properties prop = new Properties();
        prop.load(stream);
        return prop.getProperty(key);
    }

    public HashMap<String, String> parseStringXML(InputStream is) throws Exception {
        HashMap<String, String> stringMap = new HashMap<String, String>();

        //Get document builder
        DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuild = docFac.newDocumentBuilder();

        //Build document
        Document document = docBuild.parse(is);

        //Normalize XML structure.. Very important step
        document.getDocumentElement().normalize();

        //Find root node
        Element root = document.getDocumentElement();

        //Get all elements
        NodeList nodeList = document.getElementsByTagName("string");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
            }
        }
        return stringMap;
    }

    public String getDateTime(int plusMinus) {

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, plusMinus);
        dt = c.getTime();
        return dateFormat.format(dt);
    }


}
