module xyz.xyz.xyz.workdarnit {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.bouncycastle.pkix;
    requires org.bouncycastle.provider;


    opens xyz.xyz.xyz.workdarnit to javafx.fxml;
    exports xyz.xyz.xyz.workdarnit;
}