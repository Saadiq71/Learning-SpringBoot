package com.app.notesandtodo;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class NotesandtodoApplication{
	public static void main(String[] args){
		SpringApplication.run(NotesandtodoApplication.class,args);
	}
}