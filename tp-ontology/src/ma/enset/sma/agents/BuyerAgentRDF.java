package ma.enset.sma.agents;

import jade.content.lang.Codec;
import jade.content.lang.rdf.RDFCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ma.enset.sma.CatalogOntology;
import ma.enset.sma.Disponible;

public class BuyerAgentRDF extends Agent {
    private Ontology catalogOntology= CatalogOntology.getCatalogOntology();
    private Codec rdfCodec =new RDFCodec();

    @Override
    protected void setup() {
     getContentManager().registerOntology(catalogOntology);
     getContentManager().registerLanguage(rdfCodec);
        MessageTemplate messageTemplate=MessageTemplate.and
                (
                        MessageTemplate.MatchOntology(CatalogOntology.ONTOLOGY_NAME),
        MessageTemplate.MatchLanguage(rdfCodec.getName()));

        ACLMessage receivedMessage = blockingReceive(messageTemplate);
        System.out.println(receivedMessage);
        try {
            Disponible disponible = (Disponible)getContentManager().extractContent(receivedMessage);
            System.out.println(disponible.getProduct().getName()+" "+disponible.getProduct().getPrice());
        } catch (Codec.CodecException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        }

    }
}
