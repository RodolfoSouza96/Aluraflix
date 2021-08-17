package br.com.aluraflix.aluraflix.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
class VideoRepositoryTest {

	@Autowired
	private VideoRepository videoRepository;
	
	@Test
	public void deveriaRetornarUmVideo() {
		String titulo = "As Branquelas";
		
		
	}

}
