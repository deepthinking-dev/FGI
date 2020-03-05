/********************************************
 * Druid连接池配置类
 *
 * @author zwq
 * @create 2018-06-02
 *********************************************/

package deepthinking.fgi.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.alibaba.druid.pool.DruidDataSource;

@Component
@ConfigurationProperties(prefix="spring.datasource")
@EnableTransactionManagement
public class DruidConfiguration{

    private String url;
    private String username;
    private String password;
    private String driver_class_namel;
    private String type;
    private int max_active;
    private int initial_size;
    private int min_idle;
    private int max_wait;
    private int time_between_eviction_runs_millis;
    private int min_evictable_idle_time_millis;
    private boolean test_while_idle;
    private boolean test_on_borrow;
    private boolean test_on_return;
    private boolean poolPreparedStatements;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver_class_namel() {
        return driver_class_namel;
    }

    public void setDriver_class_namel(String driver_class_namel) {
        this.driver_class_namel = driver_class_namel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMax_active() {
        return max_active;
    }

    public void setMax_active(int max_active) {
        this.max_active = max_active;
    }

    public int getInitial_size() {
        return initial_size;
    }

    public void setInitial_size(int initial_size) {
        this.initial_size = initial_size;
    }

    public int getMin_idle() {
        return min_idle;
    }

    public void setMin_idle(int min_idle) {
        this.min_idle = min_idle;
    }

    public int getMax_wait() {
        return max_wait;
    }

    public void setMax_wait(int max_wait) {
        this.max_wait = max_wait;
    }

    public int getTime_between_eviction_runs_millis() {
        return time_between_eviction_runs_millis;
    }

    public void setTime_between_eviction_runs_millis(int time_between_eviction_runs_millis) {
        this.time_between_eviction_runs_millis = time_between_eviction_runs_millis;
    }

    public int getMin_evictable_idle_time_millis() {
        return min_evictable_idle_time_millis;
    }

    public void setMin_evictable_idle_time_millis(int min_evictable_idle_time_millis) {
        this.min_evictable_idle_time_millis = min_evictable_idle_time_millis;
    }

    public boolean isTest_while_idle() {
        return test_while_idle;
    }

    public void setTest_while_idle(boolean test_while_idle) {
        this.test_while_idle = test_while_idle;
    }

    public boolean isTest_on_borrow() {
        return test_on_borrow;
    }

    public void setTest_on_borrow(boolean test_on_borrow) {
        this.test_on_borrow = test_on_borrow;
    }

    public boolean isTest_on_return() {
        return test_on_return;
    }

    public void setTest_on_return(boolean test_on_return) {
        this.test_on_return = test_on_return;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.getUrl());
        datasource.setDriverClassName(this.getDriver_class_namel());
        datasource.setUsername(this.getUsername());
        datasource.setPassword(this.getPassword());
        datasource.setInitialSize(Integer.valueOf(this.getInitial_size()));
        datasource.setMinIdle(Integer.valueOf(this.getMin_idle()));
        datasource.setMaxWait(Long.valueOf(this.getMax_wait()));
        datasource.setMaxActive(Integer.valueOf(this.getMax_active()));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(this.getMin_evictable_idle_time_millis()));
        try {
			datasource.setFilters("stat,wall,slf4j");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return datasource;
    }
}
