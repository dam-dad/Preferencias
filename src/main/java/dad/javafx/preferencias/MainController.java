package dad.javafx.preferencias;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Setting;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {
		
	// view

	@FXML
	private VBox view;

	@FXML
	private Button preferencesButton;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		preferencesButton.setOnAction(e -> onEditPreferences(e));

	}

	private void onEditPreferences(ActionEvent e) {

		ListProperty<Theme> themes = new SimpleListProperty<>(FXCollections.observableArrayList(Theme.values()));
		ListProperty<Locale> languages = new SimpleListProperty<>(FXCollections.observableArrayList(Locale.ENGLISH, new Locale("es"), Locale.FRENCH));

		PreferencesFx preferencesFx = PreferencesFx.of(
				App.class,
				Category.of("Configuración",
						Setting.of("Theme", themes, App.preferences.themeProperty()),
						Setting.of("Locale", languages, App.preferences.localeProperty())
				)
			);
		preferencesFx.saveSettings(false);
		preferencesFx.dialogTitle("Configuración");
		preferencesFx.show(true);
		
	}

	public Parent getView() {
		return view;
	}

}
