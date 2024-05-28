package org.example;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeZoneConverter {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        displayMainMenu();
    }

    private static void displayMainMenu() {
        System.out.println("****************************************");
        System.out.println("*          TIME ZONE CONVERTER         *");
        System.out.println("****************************************");
        System.out.println("Klicke (X) für Alle Commands");

        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "X":
                displayCommands();
                break;
            case "O":
                displayInstructions();
                break;
            case "L":
                displayTimeZones();
                break;
            case "F":
                addToFavorites();
                break;
            case "S":
                displaySettingsMenu();
                break;
            case "B":
                displayMainMenu(); // Zurück zum Hauptmenü
                break;
            case "Q":
                System.out.println("Auf Wiedersehen!");
                System.exit(0);
            default:
                System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
                displayMainMenu();
        }
    }

    private static void displayCommands() {
        System.out.println("COMMANDS:");
        System.out.println("(O) Anleitung");
        System.out.println("(L) Alle Zeitzonen Auflisten");
        System.out.println("(F) Zu Favoriten Hinzufügen");
        System.out.println("(S) Einstellungen");
        System.out.println("(B) Zurück ");
        System.out.println("(Q) Quit");
        System.out.println("\n----------------------------------------------------------------------\n");

        displayMainMenu();
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
            case "C":
                convertTime();
                break;
            case "B":
                displayMainMenu();
                break;
            case "Q":
                System.out.println("Auf Wiedersehen!");
                System.exit(0);
            default:
                System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
                displayInstructions();
        }
    }

    private static void convertTime() {
        System.out.println("Gebe deine Zeitzone ein: ");
        String sourceZoneId = scanner.nextLine();
        ZoneId sourceZone = ZoneId.of(sourceZoneId);

        System.out.println("Gebe die Zeit zum Konvertieren ein (Format: yyyy-MM-dd HH:mm): ");
        String inputTime = scanner.nextLine();
        LocalDateTime timeToConvert = LocalDateTime.parse(inputTime, formatter);

        System.out.println("Gebe deine Zielzeitzone ein: ");
        String targetZoneId = scanner.nextLine();
        ZoneId targetZone = ZoneId.of(targetZoneId);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(timeToConvert, sourceZone);
        ZonedDateTime convertedZonedDateTime = zonedDateTime.withZoneSameInstant(targetZone);

        System.out.println("Konvertierte Zeit: " + convertedZonedDateTime.format(formatter) + " " + targetZoneId);
        System.out.println("--------------------------------------------\n");
        displayMainMenu();
    }

    private static void displayTimeZones() {
        System.out.println("Liste aller Zeitzonen:");
        System.out.println("--------------------------------------------");
        String[] zoneIds = ZoneId.getAvailableZoneIds().toArray(new String[0]);
        for (String zoneId : zoneIds) {
            System.out.println(zoneId);
        }
        System.out.println("--------------------------------------------\n");
        displayMainMenu();
    }

    private static void addToFavorites() {
        // Zu Favoriten hinzufügen
        displayMainMenu();
    }

    private static void displaySettingsMenu() {
        System.out.println("****************************************");
        System.out.println("*             Einstellungen            *");
        System.out.println("****************************************");
        System.out.println("COMMANDS:");
        System.out.println("(Z) Zeitausgabe");
        System.out.println("\t> (M) Militärzeit (24H Uhr)");
        System.out.println("\t> (T) Standartzeit (12H Uhr)");
        System.out.println("(B) Zurück ");
        System.out.println("(Q) Quit");
        System.out.println();
        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "Z":
                displayTimeOutputSettings();
                break;
            case "B":
                displayMainMenu();
                break;
            case "Q":
                System.out.println("Auf Wiedersehen!");
                System.exit(0);
            default:
                System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
                displaySettingsMenu();
        }
    }

    private static void displayTimeOutputSettings() {
        // Zeitausgabe-Einstellungen anzeigen
        displayMainMenu();
    }
}
