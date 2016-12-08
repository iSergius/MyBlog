package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;

/**
 * Created by Kondratyev Sergey on 23.12.15.
 */
public class Setting extends Model {

    private String name;
    private String title;
    private String description;
    private String value;
    private String type;

    public Setting() {
    }

    public Setting(String key, String value) {
        this.name = key;
        this.value = value;
        String[] split = key.split("[.]");
        this.type = split[split.length - 1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public <T> void importValue(T value) {
        this.value = value.toString();
        this.type = value.getClass().getName();
    }

    public <T> T exportValue() {
        switch (type) {
            case "Integer" :
                return (T) Integer.valueOf(value);
            case "Long" :
                return (T) Long.valueOf(value);
            case "Boolean" :
                return (T) Boolean.valueOf(value);
            case "String" :
                return (T) String.valueOf(value);
            default :
                return (T) value;
        }
    }

    public boolean isSet() {
        if (value != null)
            return !value.isEmpty();
        else
            return false;
    }
}
