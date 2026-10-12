module com.example.projetoversaofinal {
    requires javafx.controls;
    requires javafx.fxml;

    // javafx.base precisa de acesso reflexivo para o PropertyValueFactory das tabelas
    opens com.example.projetoversaofinal to javafx.fxml, javafx.base;
    exports com.example.projetoversaofinal;
}
