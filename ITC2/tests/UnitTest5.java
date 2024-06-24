package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UnitTest5 {
    //Unit-Test 005 bei Amarah

    @Test
    void testUT005HandlingTimeSwitching() {
        // Testschritt 1: Datum und Zeit "2016-12-31 23:59 UTC" eingeben
        ZonedDateTime utcTime = ZonedDateTime.parse("2016-12-31T23:59:00Z");

        // Testschritt 2: Zielzeitzone "CET" auswählen
        ZoneId targetZone = ZoneId.of("CET");

        // Umwandlung in die Zielzeitzone
        ZonedDateTime cetTime = utcTime.withZoneSameInstant(targetZone);

        // Erwartetes Ergebnis: Ausgabe zeigt "2017-01-01 00:59 CET"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        String expected = "2017-01-01 00:59 CET";
        String actual = cetTime.format(formatter);

        // Überprüfung des Ergebnisses
        assertEquals(expected, actual);
    }
}
