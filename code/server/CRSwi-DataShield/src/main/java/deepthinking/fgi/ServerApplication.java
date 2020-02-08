/********************************************
 * 服务端启动主类
 *
 * @author zwq
 * @create 2018-06-02 22:23
 *********************************************/

package deepthinking.fgi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.event.EventListener;

import deepthinking.fgi.task.TaskTimer;


@MapperScan("deepthinking.fgi.dao.mapper")
@SpringBootApplication
@ServletComponentScan
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void whenReady(){
		TaskTimer.startTask();
	}

}
