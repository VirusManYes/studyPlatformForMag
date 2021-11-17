package com.VKR.studyPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyPlatformApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(StudyPlatformApplication.class, args);
		}
		catch (Exception e){
			e.printStackTrace();
		} catch (Throwable e){
			e.printStackTrace();
		}
	}

}
