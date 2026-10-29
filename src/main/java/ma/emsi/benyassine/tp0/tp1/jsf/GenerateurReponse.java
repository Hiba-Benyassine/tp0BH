package ma.emsi.benyassine.tp0.tp1.jsf;

import jakarta.enterprise.context.Dependent;
import java.io.Serializable;

/** Produit la réponse simulée de l'API, comme demandé dans le TP 0. */
@Dependent
public class GenerateurReponse implements Serializable {
    private static final long serialVersionUID = 1L;

    public String generer(String question, String role, boolean premierEchange) {
        String texte = premierEchange ? role.toUpperCase() + " : " + question : question;
        return "||" + texte.toUpperCase() + "||\n||" + texte.toLowerCase() + "||";
    }
}
