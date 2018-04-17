package chen.spider.spiderservice.webConf;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@PropertySource("classpath:/application.properties")
@ConfigurationProperties(prefix = "chen")
public class ThreadConfigSetting {
    private int corepool;
    private int maxpool;
    private int queuesize;
    private int alives;

    public int getCorepool() {
        return corepool;
    }

    public int getMaxpool() {
        return maxpool;
    }

    public int getQueuesize() {
        return queuesize;
    }

    public int getAlives() {
        return alives;
    }

    public void setCorepool(int corepool) {
        this.corepool = corepool;
    }

    public void setMaxpool(int maxpool) {
        this.maxpool = maxpool;
    }

    public void setQueuesize(int queuesize) {
        this.queuesize = queuesize;
    }

    public void setAlives(int alives) {
        this.alives = alives;
    }
}
