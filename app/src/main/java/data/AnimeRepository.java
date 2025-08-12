package com.example.animedxdgacor;

import java.util.ArrayList;

import model.Anime;

/**
 * Kelas ini berfungsi sebagai penyedia data dummy.
 * Dalam aplikasi nyata, kelas ini bisa mengambil data dari database lokal atau dari internet (API).
 */
public class AnimeRepository {

    public static ArrayList<Anime> getDummyData() {
        ArrayList<Anime> listAnime = new ArrayList<>();

        // Data 1: ID: INVADED
        listAnime.add(new Anime(
                R.drawable.idinvaded, // Gambar diisi 0 sebagai placeholder
                "ID: INVADED",
                2020,
                "Sci-Fi & Fantasy, Action & Adventure, Mystery",
                "Sakaido is a genius detective who can track down any criminal. But when his daughter is murdered, revenge lands him on the other side of the law. Now in prison, he helps the police solve mysteries using a system that invades a person’s identity. Little by little, a trail of blood forms, and it all leads back to his daughter’s...",
                8.02, // Rating dummy
                13    // Jumlah episode dummy
        ));

        // Data 2: Kaiju No. 8
        listAnime.add(new Anime(
                R.drawable.kaijuno8, // Gambar placeholder
                "Kaiju No. 8",
                2024,
                "Action & Adventure, Sci-Fi & Fantasy",
                "In a world plagued by creatures known as Kaiju, Kafka Hibino aspired to enlist in The Defense Force. He makes a promise to enlist with his childhood friend, Mina Ashiro. Soon, life takes them in separate ways. While employed cleaning up after Kaiju battles, Kafka meets Reno Ichikawa. Reno's determination to join...",
                8.35,
                12
        ));

        // Data 3: Bungou Stray Dogs
        listAnime.add(new Anime(
                R.drawable.bsd, // Gambar placeholder
                "Bungou Stray Dogs",
                2016,
                "Comedy, Crime, Mystery, Action & Adventure, Sci-Fi & Fantasy, Drama",
                "Unconfident loner Atsushi Nakajima joins the Armed Detective Agency, a group of operatives who fight Port Mafia with their supernatural abilities.",
                8.48,
                61 // Total episode dari beberapa season
        ));

        // Data 4: Gintama
        listAnime.add(new Anime(
                R.drawable.gintama, // Gambar placeholder
                "Gintama",
                2006,
                "Action & Adventure, Comedy, Sci-Fi & Fantasy",
                "Life isn't easy in feudal Japan... especially since the aliens landed and conquered everything! Oh sure, the new health care is great, but the public ban on the use of swords has left a lot of defeated samurai with a difficult decision to make concerning their future career paths! This is especially true if, as in the case of Gintoki...",
                9.10,
                367
        ));

        // Data 5: Steins;Gate
        listAnime.add(new Anime(
                R.drawable.steinsgate, // Gambar placeholder
                "Steins;Gate",
                2011,
                "Sci-Fi & Fantasy, Mystery, Comedy",
                "A group of friends have customized their microwave so that it can send text messages to the past. As they perform different experiments, an organization named SERN who has been doing their own research on time travel tracks them down and now the characters have to find a way to avoid being captured by them.",
                9.09,
                24
        ));

        return listAnime;
    }
}