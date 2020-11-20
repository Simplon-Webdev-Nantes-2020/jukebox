package co.simplon.jukebox.album.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Name can't be empty")
	private String title;

	private LocalDate releaseDate;

	private Integer fanNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getReleasedate() {
		return releaseDate;
	}

	public void setReleasedate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getFanNumber() {
		return fanNumber;
	}

	public void setFanNumber(Integer fanNumber) {
		this.fanNumber = fanNumber;
	}


}
