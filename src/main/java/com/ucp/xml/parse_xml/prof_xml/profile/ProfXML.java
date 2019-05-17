package com.ucp.xml.parse_xml.prof_xml.profile;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

public class ProfXML {

    public List<Profile> profiles;

    public ProfXML(List<Profile> profiles){
        this.profiles=profiles;
    }

    public void creadDocumentXML(){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //racine
            Document document = documentBuilder.newDocument();
            Element racine = document.createElement("profiles");
            document.appendChild(racine);

            for (int index = 0; index < profiles.size(); index++) {
                Profile profile = profiles.get(index);

                Element profileElement = document.createElement("profile");
                racine.appendChild(profileElement);

                Attr attr_id = document.createAttribute("id_p");
                attr_id.setValue(""+index);

                profileElement.setAttributeNode(attr_id);

                for(int indexUser = 0;indexUser<profile.getUsers().size();indexUser++){

                    User user = profile.getUsers().get(indexUser);

                    Element userElement = document.createElement("user");
                    profileElement.appendChild(userElement);

                    Attr attr_id_u = document.createAttribute("id_u");
                    attr_id_u.setValue(user.getIdUser());

                    profileElement.setAttributeNode(attr_id_u);
                }
            }
        }catch (Exception ex){
            System.err.println(ex);
        }
    }
}
