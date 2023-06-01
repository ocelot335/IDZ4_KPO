/**
 * Класс HwwwApplication представляет собой основной класс приложения.
 * Он используется для запуска приложения Spring Boot.
 */
package com.example.hwww;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
/**
 * Класс HwwwApplication используется для запуска приложения Spring Boot.
 * Он содержит метод main, который запускает приложение.
 */
@SpringBootApplication
public class HwwwApplication {
	/**
	 * Метод main используется для запуска приложения Spring Boot.
	 * Он вызывает метод SpringApplication.run, который запускает приложение.
	 * @param args аргументы командной строки
	 */
	public static void main(String[] args) {
		SpringApplication.run(HwwwApplication.class, args);
	}
}