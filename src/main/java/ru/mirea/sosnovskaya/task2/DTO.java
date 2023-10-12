package ru.mirea.sosnovskaya.task2;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DTO {
    @JsonProperty(value = "title")
    String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty(value = "year")
    int year;
    @JsonProperty(value = "cast")
    List<String> cast;
    @JsonProperty(value = "genres")
    List<String> genres;
    @JsonProperty(value = "href")
    String href;


    public DTO(String title, int year, List<String> cast, List<String> genres, String href) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.href = href;
    }
    public DTO() {}


    public String getTitle() { return title; }

    public int getYear() { return year; }

    public List<String> getCast() { return cast; }


    public void getDescriptionFilm() {
        String description = String.format("Описание фильма %s: \nГод выпуска: %s" +
                "\nСписок актёров: %s \nЖанры: %s \nСсылка на фильм: %s",
                this.title, this.year, this.cast, genres, this.href);
        System.out.println(description);
    }
}
