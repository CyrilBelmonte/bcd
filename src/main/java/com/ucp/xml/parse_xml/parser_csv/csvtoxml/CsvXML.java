package com.ucp.xml.parse_xml.parser_csv.csvtoxml;

import com.ucp.xml.parse_xml.parser_csv.dao.category.Category;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;


public class CsvXML {

    public List<Category> categories;

    public CsvXML(List<Category> categories){
        this.categories = categories;
    }

    public void creatDocumentXML() {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //racine
            Document document = documentBuilder.newDocument();
            Element racine = document.createElement("categories");
            document.appendChild(racine);

            for(int index = 0 ; index < categories.size();index++){
                Category category = categories.get(index);

                Element categoryElement = document.createElement("category");
                racine.appendChild(categoryElement);

                Attr attr_id = document.createAttribute("id");
                attr_id.setValue(category.getIdOfCat());

                Attr attr_type = document.createAttribute("type");
                attr_type.setValue(category.getIdOfCat());

                categoryElement.setAttributeNode(attr_id);
                categoryElement.setAttributeNode(attr_type);

                Element near_categoriesElement = document.createElement("near_categories");
                categoryElement.appendChild(near_categoriesElement);

                for (Map.Entry<Integer,Float> e : category.getCatDist().entrySet()) {

                    Element near_categoryElement = document.createElement("near_category");
                    near_categoriesElement.appendChild(near_categoryElement);

                    Attr attr_id_c = document.createAttribute("id_c");
                    attr_id_c.setValue(""+e.getKey());

                    Attr attr_dist_c = document.createAttribute("id_c");
                    attr_dist_c.setValue(""+e.getValue());

                    near_categoryElement.setAttributeNode(attr_id_c);
                    near_categoryElement.setAttributeNode(attr_dist_c);
                }

                Element recipesElement = document.createElement("recipes");
                categoryElement.appendChild(recipesElement);

                for (Map.Entry<Integer,Float> e : category.getRecDist().entrySet()) {

                    Element recipeElement = document.createElement("recipe");
                    recipesElement.appendChild(recipeElement);

                    Attr attr_id_r = document.createAttribute("id_r");
                    attr_id_r.setValue(""+e.getKey());

                    Attr attr_dist_r = document.createAttribute("id_r");
                    attr_dist_r.setValue(""+e.getValue());

                    recipeElement.setAttributeNode(attr_id_r);
                    recipeElement.setAttributeNode(attr_dist_r);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("src/main/resources/test.xml"));

            transformer.transform(source,result);


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
