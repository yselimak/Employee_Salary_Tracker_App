module Proje_son{
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires org.kordamp.ikonli.fontawesome5;
	requires org.kordamp.ikonli.javafx;
	requires java.desktop;
	requires java.base;
	opens controller to javafx.graphics, javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
	opens personel to javafx.base;
}
