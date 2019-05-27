package com.ucp.xml.parse_xml.user_xml.bdtoxml;

import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;

public class DbXML {
    public List<User> users;

    public DbXML(List<User> users) {
        this.users = users;
    }

    public void createDocumentXML() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // Racine <users>
            Document document = documentBuilder.newDocument();
            Element racine = document.createElement("users");
            document.appendChild(racine);

            // Boucle sur les utilisateurs
            for (int index = 0; index < users.size(); index++) {
                User user = users.get(index);
                int entreeCount = user.getEntreeCategories().size();
                int platCount = user.getPlatCategories().size();
                int dessertCount = user.getDessertCategories().size();

                // <user>
                Element userElement = document.createElement("user");

                Attr attributeId = document.createAttribute("id_u");
                attributeId.setValue(String.valueOf(user.getIdUser()));

                userElement.setAttributeNode(attributeId);

                racine.appendChild(userElement);

                Element categoriesElement = document.createElement("categories");

                // Premier type
                Element entreeTypeElement = document.createElement("type");
                Attr attributeEntreeValue = document.createAttribute("value");
                attributeEntreeValue.setValue("entree");

                Attr attributeEntreeNumber = document.createAttribute("number");
                attributeEntreeNumber.setValue(String.valueOf(entreeCount));

                entreeTypeElement.setAttributeNode(attributeEntreeValue);
                entreeTypeElement.setAttributeNode(attributeEntreeNumber);

                // Boucle sur les catégorie du premier type
                for(Map.Entry<Integer, String> entryEntree : user.getEntreeCategories().entrySet()) {
                    Element categoryElement = document.createElement("category");
                    Attr attributeIdC = document.createAttribute("id_c");
                    attributeIdC.setValue(""+entryEntree.getValue());

                    Attr attributeProba = document.createAttribute("proba");
                    attributeProba.setValue(String.valueOf(1f/entreeCount));

                    categoryElement.setAttributeNode(attributeIdC);
                    categoryElement.setAttributeNode(attributeProba);

                    entreeTypeElement.appendChild(categoryElement);
                }
                categoriesElement.appendChild(entreeTypeElement);

                // Deuxième type
                Element platTypeElement = document.createElement("type");
                Attr attributePlatValue = document.createAttribute("value");
                attributePlatValue.setValue("plat");

                Attr attributePlatNumber = document.createAttribute("number");
                attributePlatNumber.setValue(String.valueOf(platCount));

                platTypeElement.setAttributeNode(attributePlatValue);
                platTypeElement.setAttributeNode(attributePlatNumber);

                // Boucle sur les catégorie du deuxième type
                for (Map.Entry<Integer, String> entryPlat : user.getPlatCategories().entrySet()) {
                    Element categoryElement = document.createElement("category");
                    Attr attributeIdC = document.createAttribute("id_c");
                    attributeIdC.setValue(""+entryPlat.getValue());

                    Attr attributeProba = document.createAttribute("proba");
                    attributeProba.setValue(String.valueOf(1f/platCount));

                    categoryElement.setAttributeNode(attributeIdC);
                    categoryElement.setAttributeNode(attributeProba);

                    platTypeElement.appendChild(categoryElement);
                }
                categoriesElement.appendChild(platTypeElement);

                // Troisième type
                Element dessertTypeElement = document.createElement("type");
                Attr attributeDessertValue = document.createAttribute("value");
                attributeDessertValue.setValue("dessert");

                Attr attributeDessertNumber = document.createAttribute("number");
                attributeDessertNumber.setValue(String.valueOf(dessertCount)); // A CHANGER !!! (categories.size() des plats

                dessertTypeElement.setAttributeNode(attributeDessertValue);
                dessertTypeElement.setAttributeNode(attributeDessertNumber);
                // Boucle sur les catégorie du troisième type
                for (Map.Entry<Integer, String> entryDessert : user.getDessertCategories().entrySet()) {
                    Element categoryElement = document.createElement("category");
                    Attr attributeIdC = document.createAttribute("id_c");
                    attributeIdC.setValue(""+entryDessert.getValue());

                    Attr attributeProba = document.createAttribute("proba");
                    attributeProba.setValue(String.valueOf(1f/dessertCount));

                    categoryElement.setAttributeNode(attributeIdC);
                    categoryElement.setAttributeNode(attributeProba);

                    dessertTypeElement.appendChild(categoryElement);

                }
                categoriesElement.appendChild(dessertTypeElement);
                userElement.appendChild(categoriesElement);
                Element friendsElement = document.createElement("friends");

                // Boucle sur les amis
                for (Map.Entry<Integer, String> entryFriend : user.getFriends().entrySet()) {
                    Element friendElement = document.createElement("user");
                    Attr attributeIdRef = document.createAttribute("id_ref");
                    attributeIdRef.setValue(""+entryFriend.getValue());

                    friendElement.setAttributeNode(attributeIdRef);

                    friendsElement.appendChild(friendElement);

                }
                userElement.appendChild(friendsElement);

                Element bookmarksElement = document.createElement("bookmarks");

                // Boucle sur les favoris
                for (Map.Entry<Integer, String> entryBookmarks : user.getBookmarks().entrySet()) {
                    Element recipeElement = document.createElement("recipe");
                    Attr attributeBookmarks = document.createAttribute("id_r");
                    attributeBookmarks.setValue(""+entryBookmarks.getValue());

                    recipeElement.setAttributeNode(attributeBookmarks);

                    bookmarksElement.appendChild(recipeElement);

                }
                userElement.appendChild(bookmarksElement);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("src/main/resources/testsusers.xml"));

            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
