package ma.enset.sma.xmlapp;

import jade.content.lang.Codec;
import jade.content.lang.xml.XMLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import ma.enset.sma.CatalogOntology;
import ma.enset.sma.Disponible;

public class XmlApp {
    public static void main(String[] args) throws OntologyException, Codec.CodecException {
        XMLCodec xmlCodec=new XMLCodec();
        Ontology cataOntology= CatalogOntology.getCatalogOntology();
        String xmlDoc="<Disponible>\n" +
                "<Usb name='SAMSUNG A30' price='2500.0F' capacity='39999.0F'/>\n" +
                "<agent-identifier name='buyer@192.168.0.146:1099/JADE'></agent-identifier>\n" +
                "</Disponible>";
        Disponible disponible = (Disponible) xmlCodec.decodeObject(cataOntology, xmlDoc);
        System.out.println("Seller >> "+disponible.getSeller().getName()+"\nproduct >> "
                +disponible.getProduct().getName()+" Price : "+disponible.getProduct().getPrice());
        String encodeObject = xmlCodec.encodeObject(cataOntology, disponible, true);
        System.out.println(encodeObject);
    }
}
