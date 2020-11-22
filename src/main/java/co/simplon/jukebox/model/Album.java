package co.simplon.jukebox.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Name can't be empty")
	private String title;

	private LocalDate releaseDate;
	
	@ManyToOne @JoinColumn(name = "artistId")
	@JsonBackReference
	private Artist artist;
	
	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<Track> tracks;

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

}
