package org.example.demo1.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.demo1.MockWebSocketClient;
import org.example.demo1.entity.MatchHistory;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class MatchHistoryController implements Initializable {

    @FXML
    private AnchorPane rootPane;  // Để đặt kích thước màn hình
    @FXML
    private TableView<MatchHistory> matchHistoryTable;
    @FXML
    private TableColumn<MatchHistory, Integer> idColumn;
    @FXML
    private TableColumn<MatchHistory, Long> userIdColumn;
    @FXML
    private TableColumn<MatchHistory, Long> matchIdColumn; // Cột Match ID
    @FXML
    private TableColumn<MatchHistory, String> resultColumn; // Cột Kết quả
    @FXML
    private TableColumn<MatchHistory, Integer> pointsEarnedColumn; // Cột Điểm kiếm được
    @FXML
    private TableColumn<MatchHistory, String> createdAtColumn; // Cột Thời gian tạo
    @FXML
    private TextField searchField;  // Trường tìm kiếm
    @FXML
    private ComboBox<String> filterByComboBox;  // ComboBox để chọn bộ lọc
    @FXML
    private Button previousPageButton; // Nút quay lại
    @FXML
    private Button nextPageButton; // Nút tiếp theo
    @FXML
    private Label userNameLabel, userStarsLabel; // Thông tin người dùng
    @FXML
    private Button homeButton, logoutButton; // Nút điều hướng
    @FXML
    private HBox paginationBox;


    @FXML
    private void onPageChange() {
        matchHistoryTable.scrollTo(0); // Đưa bảng về dòng đầu tiên
        matchHistoryTable.layout();    // Cập nhật lại giao diện của bảng
    }


    private ObservableList<MatchHistory> data;
    private ObservableList<MatchHistory> originalData; // Biến lưu trữ dữ liệu gốc
    private MockWebSocketClient webSocketClient;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int currentPage = 0;
    private final int rowsPerPage = 20; // Giá trị mặc định
//    private void updatePagination() {
//        paginationBox.getChildren().clear(); // Xóa các nút cũ
//        int totalPages = (int) Math.ceil((double) data.size() / rowsPerPage);
//
//        for (int i = 0; i < totalPages; i++) {
//            Button pageButton = new Button(String.valueOf(i + 1));
//            final int pageIndex = i; // Biến cục bộ để sử dụng trong lambda
//            pageButton.setOnAction(event -> {
//                currentPage = pageIndex;
//                updateTableRows();
//            });
//            paginationBox.getChildren().add(pageButton);
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webSocketClient = new MockWebSocketClient();
        List<MatchHistory> matchHistoryList = webSocketClient.fetchMatchHistory();
        originalData = FXCollections.observableArrayList(matchHistoryList); // Lưu trữ dữ liệu gốc
        data = FXCollections.observableArrayList(matchHistoryList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        matchIdColumn.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        pointsEarnedColumn.setCellValueFactory(new PropertyValueFactory<>("pointsEarned"));
        createdAtColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getCreatedAt().format(dateTimeFormatter)));
        updateTableRows();
        matchHistoryTable.setRowFactory(tv -> new TableRow<MatchHistory>() {
            @Override
            protected void updateItem(MatchHistory item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("");
                } else {
                    switch (item.getResult()) {
                        case "Win":
                            setStyle("-fx-background-color: #CCFFCC;");
                            break;
                        case "Lose":
                            setStyle("-fx-background-color: #D3D3D3;");
                            break;
                        case "Draw":
                            setStyle("-fx-background-color: #FFFFCC;");
                            break;
                        default:
                            setStyle("");
                            break;
                    }
                }
            }
        });

        filterByComboBox.getItems().addAll("All", "ID", "Time", "Player", "Result");

        searchField.textProperty().addListener((observable, oldValue, newValue) -> performSearch());



        updateTableRows();
    }

    private void performSearch() {
        String query = searchField.getText().toLowerCase();
        String filterBy = filterByComboBox.getValue();
        ObservableList<MatchHistory> filteredData = FXCollections.observableArrayList();

        for (MatchHistory match : originalData) { // Sử dụng originalData để tìm kiếm
            boolean matches = false;
            if (filterBy == null || filterBy.equals("All")) {
                // Tìm kiếm trên tất cả các cột
                matches = String.valueOf(match.getId()).toLowerCase().contains(query) ||
                        String.valueOf(match.getUserId()).toLowerCase().contains(query) ||
                        String.valueOf(match.getMatchId()).toString().toLowerCase().contains(query) ||
                        match.getResult().toLowerCase().contains(query) ||
                        match.getCreatedAt().format(dateTimeFormatter).toLowerCase().contains(query);
            } else if (filterBy != null) {
                switch (filterBy) {
                    case "ID":
                        matches = String.valueOf(match.getId()).toLowerCase().contains(query);
                        break;
                    case "Time":
                        matches = match.getCreatedAt().format(dateTimeFormatter).toLowerCase().contains(query);
                        break;
                    case "Player":
                        matches = String.valueOf(match.getUserId()).toLowerCase().contains(query);
                        break;
                    case "Result":
                        matches = match.getResult().toLowerCase().contains(query);
                        break;
                }
            }
            if (matches) {
                filteredData.add(match);
            }
            updatePagination();
        }

        data = filteredData; // Cập nhật data với filteredData
        currentPage = 0; // Đặt lại trang hiện tại về 0
        updateTableRows(); // Cập nhật hàng trong bảng để phản ánh dữ liệu tìm kiếm
    }

    private static final int ROW_HEIGHT = 30; // Chiều cao của mỗi hàng
    private static final int MAX_ROWS = 20; // Số dòng tối đa

    private void updateTableRows() {
        int totalDataSize = data.size();
        int fromIndex = currentPage * rowsPerPage;

        // Kiểm tra trường hợp không có dữ liệu để hiển thị
        if (fromIndex >= totalDataSize) {
            matchHistoryTable.setItems(FXCollections.observableArrayList());
            matchHistoryTable.setPrefHeight(MAX_ROWS * ROW_HEIGHT + 25); // Đặt chiều cao cố định khi không có dữ liệu
            return;
        }

        // Tính toIndex để lấy dữ liệu trang hiện tại
        int toIndex = Math.min(fromIndex + rowsPerPage, totalDataSize);
        ObservableList<MatchHistory> pagedData = FXCollections.observableArrayList(data.subList(fromIndex, toIndex));

        // Đặt dữ liệu cho bảng
        matchHistoryTable.setItems(pagedData);

        // Cập nhật chiều cao bảng dựa trên số dòng hiển thị thực tế
        int numberOfRowsToDisplay = Math.min(pagedData.size(), rowsPerPage);
        matchHistoryTable.setPrefHeight(numberOfRowsToDisplay * ROW_HEIGHT + 25);

        // Đặt bảng về dòng đầu tiên và cập nhật layout để tránh thanh cuộn
        matchHistoryTable.scrollTo(0);
        matchHistoryTable.layout();

        // Cập nhật phân trang
        updatePagination();
    }

    private void updatePagination() {
        paginationBox.getChildren().clear();
        int totalPages = (int) Math.ceil((double) data.size() / rowsPerPage);

        for (int i = 0; i < totalPages; i++) {
            Button pageButton = new Button(String.valueOf(i + 1));
            final int pageIndex = i;
            pageButton.setOnAction(event -> {
                currentPage = pageIndex;
                updateTableRows(); // Gọi lại updateTableRows khi chuyển trang
            });
            paginationBox.getChildren().add(pageButton);
        }
    }







    //popup
    @FXML
    private void showPlayerInfoPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo1/player_info_popup.fxml"));
            Parent root = loader.load();

            // Lấy dữ liệu người chơi từ server và truyền vào Controller của popup
            PlayerInfoController controller = loader.getController();
            controller.setPlayerInfo("PlayerName", 120, 5, 50, 30, 15, 5);

            // Tạo và hiển thị popup
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Player Information");

            Scene scene = new Scene(root, 300, 200); // Set kích thước popup
            popupStage.setScene(scene);
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
