package controller.produit;


import entities.Categorie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.ServiceCategorie;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;







public class addProduitController implements Initializable {

    @FXML
    private Button btnAddProd;

    @FXML
    private Button btnClearProd;

    @FXML
    private Button btnImportProd;

    @FXML
    private Button btnReturnProd;

    @FXML
    private TextArea textDescriptionProd;

    @FXML
    private TextField textNomProd;

    @FXML
    private TextField textPrixProd;

    @FXML
    private TextField textQuantiteProd;

    @FXML
    private ComboBox<String> txtCategorieProd;

    @FXML
    private ComboBox<String> txtOffreProd;

    @FXML
    private ImageView imgProduitInput;
    @FXML
    private TextArea chatOutput;

    @FXML
    private TextField chatInput;

    private static final String OPENAI_API_KEY = "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";
    private static final String MODEL_NAME = "gpt-3.5-turbo-0125";
    @FXML
    private AnchorPane addProduitPane;


    private File selectedImageFile;
    private String imageName = null;


    ServiceCategorie cs = new ServiceCategorie();
    List<Categorie> categs = cs.Show();
    private int categId = -1;

    private int offreId = 1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for (Categorie c : categs) {
            txtCategorieProd.getItems().add(c.getNom_categorie());
            valuesMap.put(c.getNom_categorie(), c.getId_categorie());
        }

        txtCategorieProd.setOnAction(event -> {
            String SelectedOption = null;
            SelectedOption = txtCategorieProd.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            categId = SelectedValue;
        });
    }

    @FXML
    void Submit() {
       /* String userInput = chatInput.getText();
        System.out.printf(userInput);
        try {
            String botResponse = sendChatRequest(userInput);
            System.out.println(botResponse);
            JSONObject jsonResponse = new JSONObject(botResponse);
            JSONArray choices = jsonResponse.getJSONArray("choices");
            if (choices.length() > 0) {
                JSONObject message = choices.getJSONObject(0).getJSONObject("message");
                String botMessage = message.getString("content");
                chatOutput.appendText("You: " + userInput + "\n");
                chatOutput.appendText("SmartStepsBot: " + botMessage + "\n");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No response from the chatbot.");
            }
        } catch (IOException | InterruptedException | URISyntaxException | JSONException e) {
            e.printStackTrace();
           showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while communicating with the chatbot.");
        }

        chatInput.clear();
    }
    private void showAlert(Alert.AlertType type, String title, String contentText) {
        showAlert(type, title, null, contentText);
    }

    private void showAlert(Alert.AlertType type, String title, String headerText, String contentText) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static String sendChatRequest(String userInput) throws IOException, InterruptedException, URISyntaxException {
        String url = "https://api.openai.com/v1/chat/completions";

        Map<Object, Object> data = new HashMap<>();
        data.put("model", MODEL_NAME);
        data.put("prompt", userInput);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(new JSONObject(data).toString()))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }




//    public static String sendChatRequest(String userInput) throws IOException, InterruptedException, URISyntaxException {
//        String url = "https://api.openai.com/v1/completions";
//
//        Map<Object, Object> data = new HashMap<>();
//        data.put("model", MODEL_NAME);
//        data.put("messages", new ArrayList<Map<String, String>>() {{
//            add(new HashMap<String, String>() {{
//                put("role", "user");
//                put("content", userInput);
//            }});
//        }});
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI(url))
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer " + OPENAI_API_KEY)
//                .POST(HttpRequest.BodyPublishers.ofString(new JSONObject(data).toString()))
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        return response.body();
//    }

    @FXML
    void AjoutProduit(ActionEvent event) {
        check if not empty
        if(event.getSource() == btnAddProd){
            if (textQuantiteProd.getText().isEmpty() || textNomProd.getText().isEmpty() || textDescriptionProd.getText().isEmpty() ||
                    textPrixProd.getText().isEmpty() || imageName==null || categId==-1 || txtCategorieProd.getItems().isEmpty())*/
          /*  {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre produit.");
                Optional<ButtonType> option = alert.showAndWait();

            } else {
                ajouterProduit();
                send_SMS();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre produit a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();

                clearFieldsProduit();
            }
        }
        if(event.getSource() == btnClearProd){
            clearFieldsProduit();
        }
    }

    @FXML
    void ajouterImage() throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imgProduitInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imgProduitInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src","main","java", "uploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        }
    }

    @FXML
    void clearFieldsProduit() {
        textDescriptionProd.clear();
        textNomProd.clear();
        textPrixProd.clear();
        textQuantiteProd.clear();
        imgProduitInput.setImage(null);
    }

    @FXML
    void return_ListProduit() throws IOException{
        Parent fxml= FXMLLoader.load(getClass().getResource("listProduit.fxml"));
        addProduitPane.getChildren().removeAll();
        addProduitPane.getChildren().setAll(fxml);
    }

    private void ajouterProduit() {

        // From Formulaire
        String nomProd = textNomProd.getText();
        int prixProd = Integer.parseInt(textPrixProd.getText());
        int quantiteProd = Integer.parseInt(textQuantiteProd.getText());
        String descriptionProd = textDescriptionProd.getText();
        String imgProd = imageName;
        int categProd = categId;
        int offreProd = offreId;


        MyDB db = MyDB.getInstance();
        Produit prod = new Produit(
                nomProd, prixProd, quantiteProd, descriptionProd, imgProd, categId,1);
        ServiceProduit ps = new ServiceProduit();
        ps.ajouter(prod);
    }

    void send_SMS (){
        // Initialisation de la bibliothèque Twilio avec les informations de votre compte
        String ACCOUNT_SID = "AC24ccf0796502905b1dc946992f7164e9";
        String AUTH_TOKEN = "2dc6c72bd177b64cd3423c3319875734";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String recipientNumber = "+21629601848";
        String message = "Bonjour Mr(s) ,\n"
                + "Nous sommes ravis de vous informer qu'un produit a été ajouté.\n "
                + "Veuillez contactez l'administration pour plus de details.\n "
                + "Merci de votre fidélité(e) et à bientôt chez Tab&Eat.\n"
                + "Cordialement,\n"
                + "Tab&Eat";

        Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("+12139842320"),message).create();

        System.out.println("SMS envoyé : " + twilioMessage.getSid());
    }
*/


    }
}