package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao.BoardDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import vo.BoardVO;

public class MainController implements Initializable{

	//Main.fxml�� ���ǵǾ� �ִ� ���̺�� 
	@FXML private TableView<BoardVO> tableView_boardList;
	//�˻����� �޺��ڽ�
	@FXML private ComboBox comboBox_search;
	//�˻����� �Է� �ؽ�Ʈ �ʵ�
	@FXML private TextField textField_search;
	//�˻� ��ư
	@FXML private Button button_search;
	
	//�����͸� �ҷ����� ���� DAO
	private BoardDAO dao = new BoardDAO();
	
	//������ ��Ʈ�ѷ��� �Ѱ��� �ڽ� ��Ʈ�ѷ�
	private MainController mainController = this;
	
	//�ʱ�ȭ 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//���̺�� ����
		setTableView();
		
		//�˻����� �޺��ڽ� ����
		setComboBox();
		
		//���̺�� ������ ����
		setTableData(null,null);
	}
	
	//���̺�� ����
	public void setTableView(){
		//���̺�信 ���� �Է� �� ��� true
//		tableView_boardList.setEditable(true);
		
		//���̺�信 �߰��� ���̺��÷�
		TableColumn<BoardVO, String> tableCol_boardId = new TableColumn<>("�۹�ȣ");
		TableColumn<BoardVO, String> tableCol_boardTitle = new TableColumn<>("����");
		TableColumn<BoardVO, String> tableCol_boardWriter = new TableColumn<>("�ۼ���");
		TableColumn<BoardVO, String> tableCol_boardDate = new TableColumn<>("�ۼ�����");
		
		//���̺��÷��� �����Ϳ� ���ε� - ������Ƽ ��� ���丮�� ���� BoardVO�� String���� boardId�� �� �÷��� ��Ī���� ���ε� �Ѵ�.
		tableCol_boardId.setCellValueFactory(new PropertyValueFactory<BoardVO,String>("boardId"));
		tableCol_boardTitle.setCellValueFactory(new PropertyValueFactory<BoardVO,String>("boardTitle"));
		tableCol_boardWriter.setCellValueFactory(new PropertyValueFactory<BoardVO,String>("boardWriter"));
		tableCol_boardDate.setCellValueFactory(new PropertyValueFactory<BoardVO,String>("boardDate"));
		
		//���̺� ���� �߰��� �� ȣ��Ǵ� �ݹ� - �ݹ��� � ������ �����Ǹ� �ڵ����� ȣ��Ǵ� ���� �ǹ�
		Callback<TableColumn<BoardVO,String>, TableCell<BoardVO,String>> callback = new Callback<TableColumn<BoardVO,String>, TableCell<BoardVO,String>>() {
			
			//�ݹ鿡�� ������ �����ϰ� �Ǵ� call �޼���
			@Override
			public TableCell<BoardVO, String> call(TableColumn<BoardVO, String> param) {
				TableCell<BoardVO, String> tc = new TableCell<BoardVO, String>(){
					@Override
                    public void updateItem(String item, boolean empty) {
                        if (item != null){
                            setText(item);
                        }
                    }
				};
			    tc.setAlignment(Pos.CENTER);
			    return tc;
			}
		};
		
		//���̺��� �߰��� �� �ش� �ݹ��� �����ϵ��� ����
		tableCol_boardId.setCellFactory(callback);
		
		tableCol_boardId.setMinWidth(100);
		tableCol_boardTitle.setMinWidth(300);
		tableCol_boardWriter.setMinWidth(100);
		tableCol_boardDate.setMinWidth(100);
		
		
		//������ ���̺� �÷����� ���̺�信 �߰��Ѵ�.
		tableView_boardList.getColumns().addAll(tableCol_boardId, tableCol_boardTitle, tableCol_boardWriter, tableCol_boardDate);
		
		//���̺�信 ���콺 Ŭ�� �̺�Ʈ�� �߰��Ѵ� - ����Ŭ���ϸ� �Խ��� �󼼳��� �˾��� �ߵ��� �� ����
		tableView_boardList.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		    	//�̺�Ʈ�� ���콺 �ֹ�ư Ŭ���̰�, ���콺 Ŭ�� ���� �ι��̸� ����Ŭ���̶�� �Ǵ��ϵ��� ����
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	//���̺�信�� ���õ� (����Ŭ�� ��) �������� �����´�.
		        	BoardVO vo = tableView_boardList.getSelectionModel().getSelectedItem();
		        	
					try {
						//�˾�â�� �ҷ����� ���� ���ο� ��������(������)
						Stage stage = new Stage();
//						Stage stage = (Stage)tableView_boardList.getScene().getWindow();
						
						//Detail.fxml�� ���� ������Ʈ�� �ҷ��ͼ� Scene�� ��ġ�Ѵ�.
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
						FlowPane pane = (FlowPane)loader.load();
						
						//������ �˾�â�� ��Ʈ�ѷ��� ������
						DetailController detailController = (DetailController)loader.getController();
						//������ ��Ʈ�ѷ��� �Խ��� ���̵� �Ű������� �Ͽ� �Խ��� �������� ����
						detailController.setBoardData(vo.getBoardId());
						//������ �˾�â���� ������ ������ �� �θ�â������ �ٲ� �����Ͱ� ���ŵ� �� �ֵ��� �ϱ�����
						//���� ��Ʈ�ѷ��� �˷���
						detailController.setParentController(mainController);
						
						//fxml���� �ҷ��� �г��� ���� ������
						Scene scene = new Scene(pane, 600, 400);
						
						//���������� ���� ����
						stage.setScene(scene);
						stage.initModality(Modality.WINDOW_MODAL);
						
						//���������� ������ - ������ ������ â�� ��Ÿ���� �κ�
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        	
		        }
		    }
		});
		
	}
	
	//���̺�信 �����͸� �����ϴ� �޼���
	public void setTableData(String boardTitle, String boardWriter){
		try {
			//dao�� ���� �����͸� �ҷ���
			List<BoardVO> list = dao.selectBoardList(boardTitle, boardWriter);
			//���̺�信 ���ε� �� ����������Ʈ
			ObservableList<BoardVO> boardList;
			//�ҷ��� �����͸� FXCollections�� ���� ����������Ʈ�� ��ȯ
			boardList = FXCollections.observableArrayList(list);
			//���̺�信 �������� ����Ʈ�� ���ε�
			tableView_boardList.setItems(boardList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�޺��ڽ� ���� �޼���
	public void setComboBox(){
		//�޺��ڽ��� ���� "����","�ۼ���"�� �߰�
		comboBox_search.getItems().addAll("����","�ۼ���");
		//�ʱⰪ�� "����"�� �ǵ��� ����
		comboBox_search.setValue("����");
	}

	//�˻���ư Ŭ��
	@FXML
	public void click_search(ActionEvent event){
		
		String boardTitle = null;
		String boardWriter = null;
		
		//�޺��ڽ��� ���� "����"�̸� �˻����ǰ��� �������� "�ۼ���"�̸� �˻����ǰ��� �ۼ��ڷ� �Ű������� ����
		if(comboBox_search.getValue().toString().equals("����")){
			boardTitle = textField_search.getText();
		}else if(comboBox_search.getValue().toString().equals("�ۼ���")){
			boardWriter = textField_search.getText();
		}
		
		//���̺� ������ ���� �޼��忡 �� �Ű������� ����, ������ �Ű����� �� �ϳ��� ���� ���� �������
		this.setTableData(boardTitle, boardWriter);
	}
	
	//������ư Ŭ��
	@FXML
	public void click_delete(ActionEvent event){
		//���̺�信�� ���õ� ���� �Խ��������� ������
		BoardVO vo = tableView_boardList.getSelectionModel().getSelectedItem();
		try {
			//dao�� ���� db���� �ش� �����͸� ����
			dao.deleteBoard(vo.getBoardId());
			//db���� ���� �� �Խ��� ����Ʈ������ ����
			tableView_boardList.getItems().remove(vo);
//			boardList.remove(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�Խ��� ������ ���� �Է� Ŭ�� �̺�Ʈ
	@FXML
	public void click_insert(ActionEvent event){
		//�˾�â�� �ҷ����� ���� ����
		Stage stage = new Stage();
		
		//Detail.fxml�� ���� ������Ʈ�� �ҷ��ͼ� Scene�� ��ġ�Ѵ�.
		FlowPane pane = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
			pane = (FlowPane)loader.load();
			
			DetailController detailController = (DetailController)loader.getController();
			detailController.setParentController(mainController);
			
			Scene scene = new Scene(pane, 600, 400);
			stage.setScene(scene);
			stage.initModality(Modality.WINDOW_MODAL);
			
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
