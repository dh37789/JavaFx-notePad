package memo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class memoalertController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane border;

    @FXML
    private Label lblText;

    @FXML
    private HBox hbox;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNonSave;

    @FXML
    private Button btnClose;

    private Stage stage;
    
    private TextArea myta;
    
  	public void setMyta(TextArea myta) {
		this.myta = myta;
	}

	@FXML
    void Cancel(ActionEvent event) {
    	stage = (Stage) border.getScene().getWindow();
    	stage.close();
    	
    }

    @FXML
    void NonSave(ActionEvent event) {
    	Platform.exit();
    	
    }


    @FXML
    void initialize() {
        assert border != null : "fx:id=\"border\" was not injected: check your FXML file 'memoalert.fxml'.";
        assert lblText != null : "fx:id=\"lblText\" was not injected: check your FXML file 'memoalert.fxml'.";
        assert hbox != null : "fx:id=\"hbox\" was not injected: check your FXML file 'memoalert.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'memoalert.fxml'.";
        assert btnNonSave != null : "fx:id=\"btnNonSave\" was not injected: check your FXML file 'memoalert.fxml'.";
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'memoalert.fxml'.";

    }
}
