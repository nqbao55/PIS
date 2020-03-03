package bao.nguyen.PIS

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class PisApplication: SpringBootServletInitializer(){
	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(PisApplication::class.java)
	}
}

fun main(args: Array<String>) {
//	runApplication<PisApplication>(*args)
	SpringApplication.run(PisApplication::class.java, *args)
}

