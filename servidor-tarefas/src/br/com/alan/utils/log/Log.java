package br.com.alan.utils.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextPane;

public class Log {

	private static JTextPane textPane;

	public static void setJTextPane(JTextPane textPane) {
		Log.textPane = textPane;
	}

	public static synchronized void gravarLog(String texto) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		String sep = Log.textPane.getText().isBlank() ? "" : "\n";
		Log.textPane.setText(Log.textPane.getText() + sep + dtf.format(LocalDateTime.now()) + " >> " + texto);

	}

}
