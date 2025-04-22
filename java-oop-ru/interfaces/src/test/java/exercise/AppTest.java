package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;


class AppTest {

    @Test
    void testBuildApartmentsList1() {
        List<Home> apartments = new ArrayList<>(List.of(
            new Flat(41, 3, 10),
            new Cottage(125.5, 2),
            new Flat(80, 10, 2),
            new Cottage(150, 3)
        ));

        List<String> expected = new ArrayList<>(List.of(
            "Квартира площадью 44.0 метров на 10 этаже",
            "Квартира площадью 90.0 метров на 2 этаже",
            "2 этажный коттедж площадью 125.5 метров"
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testBuildApartmentsList2() {
        List<Home> apartments = new ArrayList<>(List.of(
            new Cottage(100, 1),
            new Flat(190, 10, 2),
            new Flat(180, 30, 5),
            new Cottage(250, 3)
        ));

        List<String> expected = new ArrayList<>(List.of(
            "1 этажный коттедж площадью 100.0 метров",
            "Квартира площадью 200.0 метров на 2 этаже",
            "Квартира площадью 210.0 метров на 5 этаже",
            "3 этажный коттедж площадью 250.0 метров"
        ));

        List<String> result = App.buildApartmentsList(apartments, 4);
        assertThat(result).isEqualTo(expected);

    }

    @Test
    void testBuildApartmentsList3() {
        List<Home> apartments = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        List<String> result = App.buildApartmentsList(apartments, 10);
        assertThat(result).isEqualTo(expected);
    }

    // BEGIN
    @Test
    void testLength() {
        CharSequence reversed = new ReversedSequence("abcdef");
        assertEquals(6, reversed.length());
        assertEquals(0, new ReversedSequence("").length());
    }

    @Test
    void testCharAtValidIndex() {
        CharSequence reversed = new ReversedSequence("abcdef");
        assertEquals('f', reversed.charAt(0));
        assertEquals('e', reversed.charAt(1));
        assertEquals('d', reversed.charAt(2));
        assertEquals('c', reversed.charAt(3));
        assertEquals('b', reversed.charAt(4));
        assertEquals('a', reversed.charAt(5));
    }

    @Test
    void testCharAtInvalidIndex() {
        CharSequence reversed = new ReversedSequence("abc");
        assertThrows(IndexOutOfBoundsException.class, () -> reversed.charAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> reversed.charAt(3));
    }

    @Test
    void testSubSequenceValidRange() {
        CharSequence reversed = new ReversedSequence("abcdef");
        assertEquals("edc", reversed.subSequence(1, 4).toString());
        assertEquals("f", reversed.subSequence(0, 1).toString());
        assertEquals("ba", reversed.subSequence(4, 6).toString());
        assertEquals("", reversed.subSequence(2, 2).toString());
    }

    @Test
    void testSubSequenceEmptyString() {
        CharSequence reversed = new ReversedSequence("");
        assertEquals("", reversed.subSequence(0, 0).toString());
        assertThrows(IndexOutOfBoundsException.class, () -> reversed.subSequence(0, 1));
    }

    @Test
    void testSubSequenceInvalidRange() {
        CharSequence reversed = new ReversedSequence("abc");
        assertThrows(IndexOutOfBoundsException.class, () -> reversed.subSequence(-1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> reversed.subSequence(0, 4));
        assertThrows(IllegalArgumentException.class, () -> reversed.subSequence(2, 1));
    }

    @Test
    void testToString() {
        CharSequence reversed = new ReversedSequence("abcdef");
        assertEquals("fedcba", reversed.toString());
        assertEquals("", new ReversedSequence("").toString());
        assertEquals("racecar", new ReversedSequence("racecar").toString());
    }
    // END
}
