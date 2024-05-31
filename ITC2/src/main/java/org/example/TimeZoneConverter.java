package org.example;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TimeZoneConverter {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isMilitaryTime = false;  // default to standard time (12-hour format)
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final FavoriteZones favoriteZones = new FavoriteZones();
    private static boolean showMainMenu = true;

    public static void main(String[] args) {
        while (true) {
            if (showMainMenu) {
                displayMainMenu();
                showMainMenu = false;
            }
            handleUserInput();
        }
    }

    private static void displayMainMenu() {
        System.out.println("****************************************");
        System.out.println("*          TIME ZONE CONVERTER         *");
        System.out.println("****************************************");
        System.out.println("Klicke (X) für Alle Commands");
    }

    private static void handleUserInput() {
        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "X" -> displayCommands();
            case "O" -> displayInstructions();
            case "L" -> displayTimeZones();
            case "F" -> addToFavorites();
            case "D" -> removeFromFavorites();
            case "V" -> favoriteZones.listFavorites();
            case "S" -> displaySettingsMenu();
            case "B" -> showMainMenu = true; // Set flag to true to show the main menu again
            case "Q" -> {
                System.out.println("Auf Wiedersehen!");
                System.exit(0);
            }
            default -> System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
        }
    }

    private static void removeFromFavorites() {
        System.out.println("Gebe die Zeitzone ein, die du aus den Favoriten entfernen möchtest:");
        String zoneId = scanner.nextLine();
        favoriteZones.removeFavorite(zoneId);
    }

    private static void displayCommands() {
        System.out.println("COMMANDS:");
        System.out.println("(O) Anleitung");
        System.out.println("(L) Alle Zeitzonen Auflisten");
        System.out.println("(F) Zu Favoriten Hinzufügen");
        System.out.println("(V) Favoriten anzeigen");
        System.out.println("(D) Favoriten löschen");
        System.out.println("(S) Einstellungen");
        System.out.println("(B) Zurück ");
        System.out.println("(Q) Quit");
        System.out.println("\n----------------------------------------------------------------------\n");
    }

    private static void displayInstructions() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("ANLEITUNG: ");
        System.out.println("1. Gebe deine Zeitzone ein: \t\t(Beispiel: Europe/Berlin)");
        System.out.println("2. Gebe die Zeit zum Konvertieren ein: \t(Beispiel: 2024-04-16 15:30)");
        System.out.println("3. Gebe deine Zielzeitzone ein: \t(Beispiel: America/Los_Angeles) ");
        System.out.println("Drücke Enter zum Konvertieren.\n");
        System.out.println("AUSGABE KANN SO AUSSEHEN");
        System.out.println("--------------------------------------------");
        System.out.println("Konvertierte Zeit: 2024-04-16 06:30 PST");
        System.out.println("--------------------------------------------\n");
        System.out.println("\n");
        System.out.println("ZEITZONECONVERTER COMMANDS:");
        System.out.println("(C) TimeZone Converter Starten");
        System.out.println("(B) Zurück");
        System.out.println("(Q) Quit");
        System.out.println("\n----------------------------------------------------------------------\n");
        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "C" -> convertTime();
            case "B" -> {
                showMainMenu = true;
            }
            case "Q" -> {
                System.out.println("Auf Wiedersehen!");
                System.exit(0);
            }
            default -> System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
        }
    }

    private static void convertTime() {
        System.out.println("Gebe deine Zeitzone ein: ");
        String sourceZoneId = scanner.nextLine();
        ZoneId sourceZone;
        try {
            sourceZone = ZoneId.of(sourceZoneId);
        } catch (DateTimeException e) {
            System.out.println("Ungültige Zeitzone. Bitte erneut eingeben.");
            return;
        }

        LocalDateTime timeToConvert = null;
        while (timeToConvert == null) {
            System.out.println("Gebe die Zeit zum Konvertieren ein (Format: yyyy-MM-dd HH:mm): ");
            String inputTime = scanner.nextLine();
            try {
                timeToConvert = LocalDateTime.parse(inputTime, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ungültiges Zeitformat. Bitte erneut eingeben.");
            }
        }

        System.out.println("Gebe deine Zielzeitzone ein: ");
        String targetZoneId = scanner.nextLine();
        ZoneId targetZone;
        try {
            targetZone = ZoneId.of(targetZoneId);
        } catch (DateTimeException e) {
            System.out.println("Ungültige Zeitzone. Bitte erneut eingeben.");
            return;
        }

        ZonedDateTime zonedDateTime = ZonedDateTime.of(timeToConvert, sourceZone);
        ZonedDateTime convertedZonedDateTime = zonedDateTime.withZoneSameInstant(targetZone);

        System.out.println("\nKonvertierte Zeit: " + formatTime(convertedZonedDateTime) + " " + targetZoneId);
        System.out.println("--------------------------------------------\n");
    }

    private static void displayTimeZones() {
        System.out.println("Liste aller Zeitzonen:");
        System.out.println("--------------------------------------------");
        System.out.println("(AF) Afrika");
        System.out.println("(AM) Amerika");
        System.out.println("(AA) Asien");
        System.out.println("(EU) Europa");
        System.out.println("(OZ) Ozeanien");
        System.out.println("(ALL) Alle");
        System.out.println("--------------------------------------------");
        String input = scanner.nextLine();
        String[] zoneIds;
        switch (input.toUpperCase()) {
            case "ALL" -> zoneIds = ZoneId.getAvailableZoneIds().toArray(new String[0]);
            case "AF" -> zoneIds = filterZonesByContinent("Africa");
            case "AM" -> zoneIds = filterZonesByContinent("America");
            case "AA" -> zoneIds = filterZonesByContinent("Asia");
            case "EU" -> zoneIds = filterZonesByContinent("Europe");
            case "OZ" -> zoneIds = filterZonesByContinent("Oceania");
            default -> {
                System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
                return;
            }
        }
        for (String zoneId : zoneIds) {
            System.out.println(zoneId);
        }
        System.out.println("--------------------------------------------\n");
    }

    private static String[] filterZonesByContinent(String continent) {
        return ZoneId.getAvailableZoneIds().stream()
                .filter(zoneId -> zoneId.startsWith(continent))
                .toArray(String[]::new);
    }

    private static void addToFavorites() {
        System.out.println("Gebe die Zeitzone ein, die du zu den Favoriten hinzufügen möchtest:");
        String zoneId = scanner.nextLine();
        favoriteZones.addFavorite(zoneId);
    }

    private static void displaySettingsMenu() {
        System.out.println("****************************************");
        System.out.println("*             Einstellungen            *");
        System.out.println("****************************************");
        System.out.println("COMMANDS:");
        System.out.println("(Z) Zeitausgabe");
        System.out.println("(B) Zurück ");
        System.out.println("(Q) Quit");
        System.out.println();

        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "Z" -> displayTimeOutputSettings();
            case "B" -> {
                return;
            }
            case "Q" -> {
                System.out.println("Auf Wiedersehen!");
                System.exit(0);
            }
            default -> System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
        }
    }

    private static void displayTimeOutputSettings() {
        System.out.println("****************************************");
        System.out.println("*         Zeitausgabe-Einstellungen    *");
        System.out.println("****************************************");
        System.out.println("Wähle das Zeitformat:");
        System.out.println("(M) Militärzeit (24H Uhr)");
        System.out.println("(T) Standardzeit (12H Uhr)");
        System.out.println("(B) Zurück zum Einstellungen-Menü");
        System.out.println();

        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "M" -> {
                isMilitaryTime = true;
                System.out.println("Militärzeit (24H Uhr) aktiviert.");
            }
            case "T" -> {
                isMilitaryTime = false;
                System.out.println("Standardzeit (12H Uhr) aktiviert.");
            }
            case "B" -> {
                return;
            }
            default -> System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
        }
    }

    private static String formatTime(ZonedDateTime time) {
        DateTimeFormatter formatter;
        if (isMilitaryTime) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        }
        return time.format(formatter);
    }
}