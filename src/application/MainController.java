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

	//Main.fxml에 정의되어 있는 테이블뷰 
	@FXML private TableView<BoardVO> tableView_boardList;
	//검색조건 콤보박스
	@FXML private ComboBox comboBox_search;
	//검색조건 입력 텍스트 필드
	@FXML private TextField textField_search;
	//검색 버튼
	@FXML private Button button_search;
	
	//데이터를 불러오기 위한 DAO
	private BoardDAO dao = new BoardDAO();
	
	//디테일 컨트롤러로 넘겨줄 자신 컨트롤러
	private MainController mainController = this;
	
	//초기화 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//테이블뷰 설정
		setTableView();
		
		//검색조건 콤보박스 설정
		setComboBox();
		
		//테이블뷰 데이터 설정
		setTableData(null,null);
	}
	
	//테이블뷰 설정
	public void setTableView(){
		//테이블뷰에 직접 입력 할 경우 true
//		tableView_boardList.setEditable(true);
		
		//테이블뷰에 추가할 테이블컬럼
		TableColumn<BoardVO, String> tableCol_boardId = new TableColumn<>("글번호");
		TableColumn<BoardVO, String> tableCol_boardTitle = new TableColumn<>("제목");
		TableColumn<BoardVO, String> tableCol_boardWriter = new TableColumn<>("작성자");
		TableColumn<BoardVO, String> tableCol_boardDate = new TableColumn<>("작성일자");
		
		//테이블컬럼과 데이터와 바인드 - 프로퍼티 밸류 팩토리를 통해 BoardVO의 String값인 boardId를 이 컬럼과 매칭시켜 바인드 한다.
		tableCol_boardId.setCellValueFactory(new PropertyValueFactory<BoardVO,String>("boardId"));
		tableCol_boardTitle.setCellValueFactory(new PropertyValueFactory<BoardVO,String>("boardTitle"));
		tableCol_boardWriter.setCellValueFactory(new PropertyValueFactory<BoardVO,String>("boardWriter"));
		tableCol_boardDate.setCellValueFactory(new PropertyValueFactory<BoardVO,String>("boardDate"));
		
		//테이블 셀이 추가될 때 호출되는 콜백 - 콜백은 어떤 조건이 충족되면 자동으로 호출되는 것을 의미
		Callback<TableColumn<BoardVO,String>, TableCell<BoardVO,String>> callback = new Callback<TableColumn<BoardVO,String>, TableCell<BoardVO,String>>() {
			
			//콜백에서 실제로 동작하게 되는 call 메서드
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
		
		//테이블셀이 추가될 때 해당 콜백을 실행하도록 설정
		tableCol_boardId.setCellFactory(callback);
		
		tableCol_boardId.setMinWidth(100);
		tableCol_boardTitle.setMinWidth(300);
		tableCol_boardWriter.setMinWidth(100);
		tableCol_boardDate.setMinWidth(100);
		
		
		//설정한 테이블 컬럼들을 테이블뷰에 추가한다.
		tableView_boardList.getColumns().addAll(tableCol_boardId, tableCol_boardTitle, tableCol_boardWriter, tableCol_boardDate);
		
		//테이블뷰에 마우스 클릭 이벤트를 추가한다 - 더블클릭하면 게시판 상세내용 팝업이 뜨도록 할 예정
		tableView_boardList.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		    	//이벤트가 마우스 주버튼 클릭이고, 마우스 클릭 수가 두번이면 더블클릭이라고 판단하도록 설정
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	//테이블뷰에서 선택된 (더블클릭 한) 아이템을 가져온다.
		        	BoardVO vo = tableView_boardList.getSelectionModel().getSelectedItem();
		        	
					try {
						//팝업창을 불러오기 위한 새로운 스테이지(윈도우)
						Stage stage = new Stage();
//						Stage stage = (Stage)tableView_boardList.getScene().getWindow();
						
						//Detail.fxml로 부터 컴포넌트를 불러와서 Scene에 배치한다.
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
						FlowPane pane = (FlowPane)loader.load();
						
						//디테일 팝업창의 컨트롤러를 가져옴
						DetailController detailController = (DetailController)loader.getController();
						//디테일 컨트롤러에 게시판 아이디를 매개변수로 하여 게시판 상세정보를 셋팅
						detailController.setBoardData(vo.getBoardId());
						//디테일 팝업창에서 정보를 수정한 뒤 부모창에서도 바뀐 데이터가 갱신될 수 있도록 하기위해
						//메인 컨트롤러를 알려줌
						detailController.setParentController(mainController);
						
						//fxml에서 불러온 패널을 씬에 설정함
						Scene scene = new Scene(pane, 600, 400);
						
						//스테이지에 씬을 붙임
						stage.setScene(scene);
						stage.initModality(Modality.WINDOW_MODAL);
						
						//스테이지를 보여줌 - 실제로 윈도우 창이 나타나는 부분
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        	
		        }
		    }
		});
		
	}
	
	//테이블뷰에 데이터를 설정하는 메서드
	public void setTableData(String boardTitle, String boardWriter){
		try {
			//dao를 통해 데이터를 불러옴
			List<BoardVO> list = dao.selectBoardList(boardTitle, boardWriter);
			//테이블뷰에 바인드 될 옵저버블리스트
			ObservableList<BoardVO> boardList;
			//불러온 데이터를 FXCollections를 통해 옵저버블리스트로 변환
			boardList = FXCollections.observableArrayList(list);
			//테이블뷰에 옵저버블 리스트를 바인드
			tableView_boardList.setItems(boardList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//콤보박스 설정 메서드
	public void setComboBox(){
		//콤보박스에 값을 "제목","작성자"로 추가
		comboBox_search.getItems().addAll("제목","작성자");
		//초기값은 "제목"이 되도록 설정
		comboBox_search.setValue("제목");
	}

	//검색버튼 클릭
	@FXML
	public void click_search(ActionEvent event){
		
		String boardTitle = null;
		String boardWriter = null;
		
		//콤보박스의 값이 "제목"이면 검색조건값이 제목으로 "작성자"이면 검색조건값이 작성자로 매개변수를 설정
		if(comboBox_search.getValue().toString().equals("제목")){
			boardTitle = textField_search.getText();
		}else if(comboBox_search.getValue().toString().equals("작성자")){
			boardWriter = textField_search.getText();
		}
		
		//테이블 데이터 설정 메서드에 두 매개변수를 보냄, 하지만 매개변수 중 하나만 조건 값이 들어있음
		this.setTableData(boardTitle, boardWriter);
	}
	
	//삭제버튼 클릭
	@FXML
	public void click_delete(ActionEvent event){
		//테이블뷰에서 선택된 행의 게시판정보를 가져옴
		BoardVO vo = tableView_boardList.getSelectionModel().getSelectedItem();
		try {
			//dao를 통해 db에서 해당 데이터를 삭제
			dao.deleteBoard(vo.getBoardId());
			//db에서 삭제 후 게시판 리스트에서도 삭제
			tableView_boardList.getItems().remove(vo);
//			boardList.remove(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//게시판 내용을 새로 입력 클릭 이벤트
	@FXML
	public void click_insert(ActionEvent event){
		//팝업창을 불러오기 위한 설정
		Stage stage = new Stage();
		
		//Detail.fxml로 부터 컴포넌트를 불러와서 Scene에 배치한다.
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
