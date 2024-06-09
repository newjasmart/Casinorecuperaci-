module cat.boscdelacoma.casinoreptefinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javax.persistence;
    requires java.base;
    
    opens cat.boscdelacoma.casinoreptefinal to javafx.fxml;
    exports cat.boscdelacoma.casinoreptefinal;
}

