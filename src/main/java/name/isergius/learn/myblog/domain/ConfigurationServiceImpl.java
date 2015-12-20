package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.SettingDao;
import name.isergius.learn.myblog.ui.IndexController;
import name.isergius.learn.myblog.ui.SettingForm;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kondratyev Sergey on 21.12.15.
 */
public class ConfigurationServiceImpl implements ConfigurationService {

    private SettingDao settingDao;
    private Map<String, Setting> defaultSettings = new HashMap<>();

    public ConfigurationServiceImpl() {
    }

    @Override
    public<T> T getProperty(String name, Class<T> clazz) {
        T result = defaultSettings.get(name).exportValue();
        try {
            Setting setting = settingDao.readBy(name);
            if (setting.isSet()) {
                result = setting.exportValue();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public <T> void setProperty(String name, T value) {
        Setting setting = new Setting();
        setting.setName(name);
        setting.importValue(value);
        try {
            settingDao.update(setting);
        } catch (DaoException e) {
            throw new ConfigurationException();
        }
    }

    @Override
    public SettingForm getSettings() {
        SettingForm settingForm = new SettingForm();
        try {
            Setting articlePageLength = settingDao.readBy(IndexController.ARTICLE_PAGE_LENGTH);
            Setting blogTitle = settingDao.readBy(BlogService.BLOG_TITLE);
            settingForm.setPageLength(articlePageLength.getValue());
            settingForm.setTitle(blogTitle.getValue());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return settingForm;
    }

    @Override
    public void saveSettings(SettingForm settings) {
        try {
            Setting blogTitle = settingDao.readBy(BlogService.BLOG_TITLE);
            Setting articlePageLength = settingDao.readBy(IndexController.ARTICLE_PAGE_LENGTH);
            articlePageLength.setValue(settings.getPageLength());
            blogTitle.setValue(settings.getTitle());
            settingDao.update(articlePageLength);
            settingDao.update(blogTitle);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void setProperties(List<Properties> propertiesList) {
        propertiesList.forEach(properties -> {
            properties.forEach((key, value) -> {
                String resultValue = parseVariables((String) value);
                defaultSettings.put((String) key, new Setting((String) key, resultValue));
                System.out.println(key+resultValue);
            });
        });
    }

    public void setSettingDao(SettingDao settingDao) {
        this.settingDao = settingDao;
    }

    private String parseVariables(String value) {

        Pattern pattern = Pattern.compile("[${]+[A-Z,_]+[}]");
        Matcher matcher = pattern.matcher(value);

        while (matcher.find()) {
            String raw = matcher.group();
            String var = raw.substring("${".length(), raw.length() - "}".length());
            String getenv = System.getenv(var);
            value = matcher.replaceFirst(getenv);
            matcher = pattern.matcher(value);
        }
        return value;
    }
}
