package dad.javafx.preferencias;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Preferences {

	private static Gson GSON = FxGson
			.fullBuilder()
			.setPrettyPrinting()
			.create();
	
	private static File PREFS_FILE = new File(System.getProperty("user.home"), "." + App.APP_NAME + "/preferences.json");

	public static Preferences load() throws IOException {
		try {
			return GSON.fromJson(new FileReader(PREFS_FILE, StandardCharsets.UTF_8), Preferences.class);
		} catch (FileNotFoundException e) {
			return new Preferences();
		}
	}

	public void save() throws IOException {
		String jsonString = GSON.toJson(this, Preferences.class);
		FileUtils.writeStringToFile(PREFS_FILE, jsonString, StandardCharsets.UTF_8);
	}
	
	// aquí pones las propiedades que quieres persistir

	// en el constructor ponemos la configuración por defecto
	public Preferences() {
		setTheme(Theme.DARK);
		setLocale(Locale.getDefault());
	}
		
	private ObjectProperty<Theme> theme = new SimpleObjectProperty<>();
	private ObjectProperty<Locale> locale = new SimpleObjectProperty<>();

	public final ObjectProperty<Theme> themeProperty() {
		return this.theme;
	}

	public final Theme getTheme() {
		return this.themeProperty().get();
	}

	public final void setTheme(final Theme theme) {
		this.themeProperty().set(theme);
	}

	public final ObjectProperty<Locale> localeProperty() {
		return this.locale;
	}

	public final Locale getLocale() {
		return this.localeProperty().get();
	}

	public final void setLocale(final Locale locale) {
		this.localeProperty().set(locale);
	}


}
