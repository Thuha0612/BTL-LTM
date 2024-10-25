package org.example.demo1.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.demo1.MockWebSocketClient;
import org.example.demo1.entity.MatchHistory;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class MatchHistoryController implements Initializable {
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
    private ComboBox<Integer> rowsPerPageComboBox; // ComboBox chọn số dòng trên mỗi trang
    @FXML
    private Button previousPageButton; // Nút quay lại
    @FXML
    private Button nextPageButton; // Nút tiếp theo

    private ObservableList<MatchHistory> data;
    private MockWebSocketClient webSocketClient;

    // Định dạng cho LocalDateTime
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Thêm thuộc tính để quản lý phân trang
    private int currentPage = 0;
    private int rowsPerPage = 10; // Giá trị mặc định

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webSocketClient = new MockWebSocketClient();
        List<MatchHistory> matchHistoryList = webSocketClient.fetchMatchHistory();
        data = FXCollections.observableArrayList(matchHistoryList);

        // Gán dữ liệu cho các cột
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        matchIdColumn.setCellValueFactory(new PropertyValueFactory<>("matchId")); // Gán cột Match ID
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        pointsEarnedColumn.setCellValueFactory(new PropertyValueFactory<>("pointsEarned"));

        // Thiết lập CellValueFactory cho cột "Created At" với định dạng ngày giờ
        createdAtColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCreatedAt()
                        .format(dateTimeFormatter)));

        // Đặt chiều rộng cho cột "Created At"
        createdAtColumn.setPrefWidth(200); // Hoặc giá trị phù hợp khác
        resultColumn.setPrefWidth(85);
        idColumn.setPrefWidth(40);
        // Gán dữ liệu cho bảng
        matchHistoryTable.setItems(data);

        // Tùy chỉnh màu sắc cho các dòng trong bảng
        matchHistoryTable.setRowFactory(tv -> new TableRow<MatchHistory>() {
            @Override
            protected void updateItem(MatchHistory item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle(""); // Đặt lại kiểu nếu dòng trống
                } else {
                    // Dựa vào kết quả để xác định màu sắc
                    switch (item.getResult()) {
                        case "Win":
                            setStyle("-fx-background-color: #CCFFCC;"); // Màu nền cho Win
                            break;
                        case "Lose":
                            setStyle("-fx-background-color: #D3D3D3;"); // Màu nền cho Lose
                            break;
                        case "Draw":
                            setStyle("-fx-background-color: #FFFFCC;"); // Màu nền cho Draw
                            break;
                        default:
                            setStyle(""); // Đặt lại kiểu nếu không có kết quả xác định
                            break;
                    }
                }
            }
        });

        // Thiết lập ComboBox cho bộ lọc
        filterByComboBox.getItems().addAll("ID", "Time", "Player", "Result");

        // Gán listener cho trường tìm kiếm để tự động tìm kiếm
        searchField.textProperty().addListener((observable, oldValue, newValue) -> performSearch());

        // Đặt giá trị mặc định cho ComboBox số dòng
        rowsPerPageComboBox.getItems().addAll(5, 10, 15, 20);
        rowsPerPageComboBox.setValue(rowsPerPage);

        // Gán listener cho ComboBox số dòng trên mỗi trang
        rowsPerPageComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                rowsPerPage = newValue;
                currentPage = Math.min(currentPage, data.size() / rowsPerPage); // Đảm bảo currentPage không vượt quá số trang
                updateTableRows();
            }
        });

        // Gán hành động cho các nút điều khiển trang
        previousPageButton.setOnAction(event -> {
            if (currentPage > 0) {
                currentPage--;
                updateTableRows();
            }
        });

        nextPageButton.setOnAction(event -> {
            if ((currentPage + 1) * rowsPerPage < data.size()) {
                currentPage++;
                updateTableRows();
            }
        });

        // Hiển thị dữ liệu ban đầu
        updateTableRows();
    }

    // Phương thức thực hiện tìm kiếm
    private void performSearch() {
        String query = searchField.getText().toLowerCase();
        String filterBy = filterByComboBox.getValue();

        ObservableList<MatchHistory> filteredData = FXCollections.observableArrayList();

        for (MatchHistory match : data) {
            boolean matches = false;
            if (filterBy != null) { // Kiểm tra xem filterBy có null không
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
        }

        data = filteredData; // Cập nhật dữ liệu
        currentPage = 0; // Reset trang về 0 khi tìm kiếm
        updateTableRows(); // Cập nhật bảng
    }

    // Cập nhật hàng trong bảng dựa trên trang và số dòng
    private void updateTableRows() {
        int fromIndex = currentPage * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size());

        // Lấy danh sách con theo chỉ số
        ObservableList<MatchHistory> pagedData = FXCollections.observableArrayList(data.subList(fromIndex, toIndex));
        matchHistoryTable.setItems(pagedData);
    }
}
