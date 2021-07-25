package de.maryfro.urlshortenerrestcontroller.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="statistics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int id;
    public String longUrl;
    public String shortUrl;
    public Integer counter;

}


