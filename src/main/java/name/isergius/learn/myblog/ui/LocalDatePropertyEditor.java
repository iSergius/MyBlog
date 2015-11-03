package name.isergius.learn.myblog.ui;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Kondratyev Sergey on 01.11.15.
 */
public class LocalDatePropertyEditor extends PropertyEditorSupport {

    private String datePattern;

    public LocalDatePropertyEditor(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public void setAsText(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        LocalDate parsedDate = LocalDate.parse(value, formatter);
        setValue(parsedDate);
    }
    @Override
    public String getAsText() {
        LocalDate localDate = (LocalDate) getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return localDate.format(formatter);
    }
}
