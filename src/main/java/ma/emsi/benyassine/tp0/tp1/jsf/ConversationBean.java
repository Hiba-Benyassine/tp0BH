package ma.emsi.benyassine.tp0.tp1.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/** Etat conservé pendant les échanges d'une vue JSF. */
@Named
@ViewScoped
public class ConversationBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String role;
    private String question;
    private String reponse;
    private StringBuilder conversation = new StringBuilder();

    @Inject
    private GenerateurReponse generateurReponse;

    public void envoyer() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (question == null || question.isBlank()) {
            context.addMessage("form:question", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Question obligatoire", "Veuillez saisir une question."));
            return;
        }
        if (role == null || role.isBlank()) {
            context.addMessage("form:role", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Rôle obligatoire", "Choisissez un rôle avant d'envoyer votre question."));
            return;
        }

        boolean premierEchange = conversation.length() == 0;
        reponse = generateurReponse.generer(question.trim(), role, premierEchange);
        conversation.append("Vous (").append(role).append(") : ").append(question.trim())
                .append("\nAPI : ").append(reponse).append("\n\n");
        question = null;
    }

    /** Une navigation vers la même page ouvre une nouvelle vue et réinitialise le chat. */
    public String nouveauChat() {
        return "index.xhtml?faces-redirect=true";
    }

    public void effacerQuestionEtReponse() {
        question = null;
        reponse = null;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getReponse() { return reponse; }
    public String getConversation() { return conversation.toString(); }
}
