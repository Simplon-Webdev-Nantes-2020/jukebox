package co.simplon.jukebox.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	@NotBlank(message = "Name can't be empty")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	private String bio;

	@PositiveOrZero(message = "fan number must be positive")
	private Integer fanNumber;
	
	@OneToMany(mappedBy = "artist")
	@JsonBackReference
	private List<Album> albums;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Integer getFanNumber() {
		return fanNumber;
	}

	public void setFanNumber(Integer fanNumber) {
		this.fanNumber = fanNumber;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	
}
