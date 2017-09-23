package com.github.andrejnazarov.itunesfinder.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link TracksResponse}
 *
 * @author Nazarov on 24.07.17.
 */
public class TracksResponseTest {

    private static final String TEST_FILE = "tracks_response_test_file.json";
    private TracksResponse mExpected;
    private ObjectMapper mObjectMapper;

    @Before
    public void setUp() {
        mObjectMapper = new ObjectMapper();
        mExpected = getExpectedObject();
    }

    @Test
    public void testParseObject() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(TEST_FILE);
        TracksResponse actual = mObjectMapper.readValue(stream, TracksResponse.class);
        assertThat(mExpected, is(actual));
    }

    private TracksResponse getExpectedObject() {
        return new TracksResponse(getTracks(), 2);
    }

    private List<Track> getTracks() {
        return Arrays.asList(getFirstTrack(), getSecondTrack());
    }

    private Track getFirstTrack() {
        return getTrack(
                "Eminem",
                "Lose Yourself",
                "https://itunes.apple.com/us/artist/eminem/id111051?uo=4",
                "http://a1693.phobos.apple.com/us/r30/Music/v4/f1/7b/d6/f17bd6e3-55c0-b7e0-9863-bc522900e950/mzaf_5153970109972844579.aac.m4a",
                "http://is5.mzstatic.com/image/thumb/Music/v4/54/4b/00/544b0075-6ef9-5fb6-8040-0bcd9f0d5766/source/100x100bb.jpg",
                1.29);
    }

    private Track getSecondTrack() {
        return getTrack(
                "Eminem",
                "Not Afraid",
                "https://itunes.apple.com/us/artist/eminem/id111051?uo=4",
                "http://a1416.phobos.apple.com/us/r30/Music/d9/f9/ba/mzm.ohfuogtw.aac.p.m4a",
                "http://is2.mzstatic.com/image/thumb/Music/v4/e1/48/37/e148379b-30a2-6d4f-a7a9-da5ae15b2b0a/source/100x100bb.jpg",
                1.29);
    }

    private Track getTrack(String artistName,
                           String trackName,
                           String artistViewUrl,
                           String previewUrl,
                           String coverUrl,
                           double price) {
        return new Track(artistName,
                trackName,
                artistViewUrl,
                previewUrl,
                coverUrl,
                price);
    }
}