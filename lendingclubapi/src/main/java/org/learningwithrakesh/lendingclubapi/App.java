package org.learningwithrakesh.lendingclubapi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.learningwithrakesh.lendingclubapi.entity.Member;
import org.learningwithrakesh.lendingclubapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = { "org.learningwithrakesh.lendingclubapi" })
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}
}
