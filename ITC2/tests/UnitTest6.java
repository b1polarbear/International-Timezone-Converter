package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class UnitTest6 {
    //Unit-Test 006 bei Amarah

    @Test
    void testUT006ConversionOverDifferentDates() {
        // Testschritt 1: Datum und Zeit "2024-12-31 23:00 UTC" eingeben
        ZonedDateTime utcTime = ZonedDateTime.parse("2024-12-31T23:00:00Z");

        // Testschritt 2: Zeitzone "Asia/Tokyo" auswählen
        ZoneId targetZone = ZoneId.of("Asia/Tokyo");

        // Umwandlung in die Zielzeitzone
        ZonedDateTime tokyoTime = utcTime.withZoneSameInstant(targetZone);

        // Erwartetes Ergebnis: Ausgabe zeigt "2025-01-01 08:00 JST"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        String expected = "2025-01-01 08:00 JST";
        String actual = tokyoTime.format(formatter);

        // Überprüfung des Ergebnisses
        assertEquals(expected, actual);
    }
}
