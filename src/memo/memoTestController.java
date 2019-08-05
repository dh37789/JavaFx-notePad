package memo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class memoTestController {
	@FXML
	private BorderPane paneId;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu menu;

    @FXML
    private MenuItem newMenu;

    @FXML
    private MenuItem openMenu;

    @FXML
    private MenuItem newNameMenu;

    @FXML
    private MenuItem closeMenu;

    @FXML
    private TextArea taMemo;
    
    // 기존의 Stage의 객체를 가져오기 위한 변수 설정
    private Stage stage;
    
    private String tempStr="";

    @FXML
    void closeFile(ActionEvent event) throws IOException {
    	stage = (Stage) paneId.getScene().getWindow();

    	Stage alert = new Stage(StageStyle.DECORATED);
    	
    	alert.initModality(Modality.WINDOW_MODAL);
    	
    	alert.initOwner(stage);
    	
    	alert.setTitle("저장 안내");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("memoalert.fxml"));
    	Parent child = loader.load();
    	
    	memoalertController memoAlert = loader.getController();
    	
    	memoAlert.setMyta(taMemo);
    	
    	
    	//Parent child = FXMLLoader.load(getClass().getResource("memoalert.fxml"));
    	
    	Button btnOk = (Button) child.lookup("#btnSave");
    	btnOk.setOnAction(e -> {
    		FileOutputStream fout = null;
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("All File", "*.*"));
			File selectedFile = fileChooser.showSaveDialog(stage);
			// 가져온 File이 없을 경우에 null
			if (selectedFile==null) {
				return;
			}
			String temp = selectedFile.getPath();
			// 파일을 저장할 시 끝에 확장자를 주지 않을경우 자동으로 붙혀준다.
			if (!temp.endsWith(".txt")) {
				temp = temp + ".txt";
			}else if (!temp.endsWith(".TXT")) {
				temp = temp + ".txt";
			}
			
			try{
				fout = new FileOutputStream(temp);
				OutputStreamWriter osw = new OutputStreamWriter(fout, "euc-kr");
				osw.write(taMemo.getText());
				osw.flush();
				osw.close();
				fout.close(); // 스트림 닫기
				
				Platform.exit();
			} catch (IOException e2){
				e2.printStackTrace();
			}
    	});
    	
    	Scene scene = new Scene(child);
    	
    	alert.setScene(scene);
    	
    	if (taMemo.getText().equals(tempStr)) {
			stage.close();
		}else {
			alert.show();
			
//			
		}
    }

    @FXML
    void newFile(ActionEvent event) {
    	// 기존의 Stage를 가져온다.
        stage = (Stage) paneId.getScene().getWindow();
    	stage.setTitle("NoName.txt");
    	taMemo.setText("");
    }

    @FXML
    void newNameFile(ActionEvent event) {
    	FileOutputStream fout = null;
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    			new ExtensionFilter("All File", "*.*"));
    	File selectedFile = fileChooser.showSaveDialog(stage);
    	// 가져온 File이 없을 경우에 null
    	if (selectedFile==null) {
			return;
		}
    	String temp = selectedFile.getPath();
    	// 파일을 저장할 시 끝에 확장자를 주지 않을경우 자동으로 붙혀준다.
    	if (!temp.endsWith(".txt")) {
			temp = temp + ".txt";
		}else if (!temp.endsWith(".TXT")) {
			temp = temp + ".txt";
		}
		
		try{
			fout = new FileOutputStream(temp);
			OutputStreamWriter osw = new OutputStreamWriter(fout, "euc-kr");
			osw.write(taMemo.getText());
			osw.flush();
			osw.close();
			fout.close(); // 스트림 닫기
		} catch (IOException e){
			e.printStackTrace();
		}
    }

    @FXML
    void openFile(ActionEvent event) throws IOException {
    	// 기존의 Stage를 가져온다.
        stage = (Stage) paneId.getScene().getWindow();
    	// File가져오기
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().add(
    		new ExtensionFilter("Text File", "*.txt")
    	);
    	// 가져온 File이 없을 경우에 null
    	File selectFile = fileChooser.showOpenDialog(stage);
    	if (selectFile==null) {
			return;
		}
    	// 제목 변경
		stage.setTitle(selectFile.getPath().substring(selectFile.getPath().lastIndexOf("\\")+1, selectFile.getPath().length()));
    	try {
    		// 메모장 데이터를 가져온다.
    		FileInputStream fin = new FileInputStream(selectFile.getPath());
    		InputStreamReader isr = new InputStreamReader(fin,"euc-kr");
    		taMemo.setText("");
    		tempStr = "";
    		int c;
    		while((c=isr.read()) != -1){
    			String temp = Character.toString(((char)c));
    			taMemo.appendText(temp);
			}
    		tempStr = taMemo.getText();
    		
    		isr.close();
    		fin.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    ButtonType confirm(String header, String msg){
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("안내");
    	alert.setHeaderText(header);
    	alert.setContentText(msg);
    	
    	ButtonType comfirmResult = alert.showAndWait().get();
    	return comfirmResult;
    }
    @FXML
    void initialize() {
    	assert paneId != null : "fx:id=\"paneId\" was not injected: check your FXML file 'memoTest.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'memoTest.fxml'.";
        assert newMenu != null : "fx:id=\"newMenu\" was not injected: check your FXML file 'memoTest.fxml'.";
        assert openMenu != null : "fx:id=\"openMenu\" was not injected: check your FXML file 'memoTest.fxml'.";
        assert newNameMenu != null : "fx:id=\"newNameMenu\" was not injected: check your FXML file 'memoTest.fxml'.";
        assert closeMenu != null : "fx:id=\"closeMenu\" was not injected: check your FXML file 'memoTest.fxml'.";
        assert taMemo != null : "fx:id=\"taMemo\" was not injected: check your FXML file 'memoTest.fxml'.";
    }
}
