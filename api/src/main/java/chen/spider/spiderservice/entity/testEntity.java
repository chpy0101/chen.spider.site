package chen.spider.spiderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Created by chpy on 18/5/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class testEntity {

    private String name;

    private LocalDate birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
