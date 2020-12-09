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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Name can't be empty")
	private String title;

	@PastOrPresent(message = "release date must be past")
	private LocalDate releaseDate;
	
	@ManyToOne @JoinColumn(name = "artist_id")
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId = true)
	private Artist artist;
	
	@Valid
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

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	
}
