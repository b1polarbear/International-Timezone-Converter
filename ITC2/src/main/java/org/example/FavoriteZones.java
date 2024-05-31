package org.example;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class FavoriteZones {
    private Set<String> favorites = new HashSet<>();

    public void addFavorite(String zoneId) {
        if (ZoneId.getAvailableZoneIds().contains(zoneId)) {
            favorites.add(zoneId);
            System.out.println(zoneId + " wurde zu den Favoriten hinzugefügt.");
        } else {
            System.out.println("Ungültige Zeitzone.");
        }
    }

    public void removeFavorite(String zoneId) {
        if (favorites.remove(zoneId)) {
            System.out.println(zoneId + " wurde aus den Favoriten entfernt.");
        } else {
            System.out.println("Zeitzone nicht in Favoriten gefunden.");
        }
    }

    public void listFavorites() {
        if (favorites.isEmpty()) {
            System.out.println("Keine Favoriten gefunden.");
        } else {
            System.out.println("Favoriten Zeitzonen:");
            for (String zoneId : favorites) {
                System.out.println(zoneId);
            }
        }
    }
}
