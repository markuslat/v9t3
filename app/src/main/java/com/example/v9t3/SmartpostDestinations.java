package com.example.v9t3;

import com.example.v9t3.SmartpostObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SmartpostDestinations {

    public String place_id;
    public String name;
    public String city;
    public String address;
    public String country;
    public String postalcode;
    public String availability;

    ArrayList<SmartpostObject> objectList = new ArrayList<>();

    private static SmartpostDestinations spd = new SmartpostDestinations();
    public static SmartpostDestinations getInstance() {
        return spd;
    }

    public SmartpostDestinations() {
    }
    public void XMLdestinations() {

        String urlString = "";

        for (int y = 1; y < 2 ;y++) {

            //Jätin viron tiedot tarkoituksella tästä pois, sillä niissä oli niin paljon epäsäännöllisyyttä
            if (y == 0) {
                urlString = "https://iseteenindus.smartpost.ee/api/?request=destinations&country=EE&type=APT";
            } else {
                urlString = "https://iseteenindus.smartpost.ee/api/?request=destinations&country=FI&type=APT";
            }
            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                Document doc = builder.parse(urlString);
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getDocumentElement().getElementsByTagName("item");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    //System.out.println("Element is this:" + node.getNodeName());

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        //System.out.println("Area: ");
                        //System.out.println(element.getElementsByTagName("name").item(0).getTextContent());

                        place_id = element.getElementsByTagName("place_id").item(0).getTextContent();
                        name = element.getElementsByTagName("name").item(0).getTextContent();
                        city = element.getElementsByTagName("city").item(0).getTextContent();
                        address = element.getElementsByTagName("address").item(0).getTextContent();
                        country = element.getElementsByTagName("country").item(0).getTextContent();
                        postalcode = element.getElementsByTagName("postalcode").item(0).getTextContent();
                        availability = element.getElementsByTagName("availability").item(0).getTextContent();

                        SmartpostObject spo = new SmartpostObject(place_id, name, city, address, country, postalcode, availability);
                        objectList.add(spo);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } finally {
                System.out.println("#######DONE########");
            }
        }
    }
    public List <String> spinnerList() {

        List<String> location = new ArrayList<>();

        for(SmartpostObject x : objectList) {
            location.add(x.getCity() + " , " + x.getName());
        }
        return location;
    }

    public ArrayList <SmartpostObject> getList() {
        return objectList;
    }
}

