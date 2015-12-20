package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.ui.SettingForm;

/**
 * Created by Kondratyev Sergey on 20.12.15.
 */
public interface ConfigurationService {

    <T> T getProperty(String name, Class<T> clazz);

    <T> void setProperty(String name, T value);

    SettingForm getSettings();

    void saveSettings(SettingForm settings);
}
