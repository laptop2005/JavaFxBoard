package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.BoardDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.BoardVO;

public class DetailController implements Initializable{

	@FXML private Label label_boardId;
	@FXML private TextField textField_boardTitle;
	@FXML private Label label_boardWriter;
	@FXML private Label label_boardDate;
	@FXML private TextArea textArea_boardContent;
	
	private BoardDAO dao = new BoardDAO();
	
	private MainController parentController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(">>Detail initialize");
	}
	
	public void setBoardData(String boardId){
		try {
			BoardVO vo = dao.selectBoard(boardId);
			label_boardId.setText("["+vo.getBoardId()+"]");
			textField_boardTitle.setText(vo.getBoardTitle());
			label_boardWriter.setText("작성자 : "+vo.getBoardWriter());
			label_boardDate.setText("작성일 : "+vo.getBoardDate());
			textArea_boardContent.setText(vo.getBoardContent());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void click_save(ActionEvent event){
		BoardVO vo = new BoardVO();
		
		vo.setBoardId(label_boardId.getText().replace("[", "").replace("]", ""));
		vo.setBoardTitle(textField_boardTitle.getText());
		vo.setBoardWriter(label_boardWriter.getText().replace("작성자 : ", ""));
		vo.setBoardContent(textArea_boardContent.getText());
		
			try {
				if(label_boardId.getText().equals("")){
					dao.insertBoard(vo);
				}else{
					dao.updateBoard(vo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		parentController.click_search(null);
		
		Stage stage = (Stage) textArea_boardContent.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void click_delete(ActionEvent event){
		try {
			dao.deleteBoard(label_boardId.getText().replace("[", "").replace("]", ""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		parentController.click_search(null);
		
		Stage stage = (Stage) textArea_boardContent.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void click_cancel(ActionEvent event){
		parentController.click_search(null);
		
		Stage stage = (Stage) textArea_boardContent.getScene().getWindow();
		stage.close();
	}
	
	public void setParentController(MainController parentController){
		this.parentController = parentController;
	}
}
