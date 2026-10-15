module com.example.projetoversaofinal {
    requires javafx.controls;
    requires javafx.fxml;

   
    opens com.example.projetoversaofinal to javafx.fxml, javafx.base;
    exports com.example.projetoversaofinal;
}
