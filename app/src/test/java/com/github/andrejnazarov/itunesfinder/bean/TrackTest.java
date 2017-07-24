package com.github.andrejnazarov.itunesfinder.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test file for {@link Track}
 *
 * @author Nazarov on 24.07.17.
 */
public class TrackTest {

    private static final String TEST_FILE = "track_test_file.json";
    private Track mExpectedTrack;
    private ObjectMapper mObjectMapper;

    @Before
    public void setUp() throws Exception {
        mExpectedTrack = getTrack();
        mObjectMapper = new ObjectMapper();
    }

    @Test
    public void testParseJson() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(TEST_FILE);
        Track actualTrack = mObjectMapper.readValue(stream, Track.class);
        assertThat(mExpectedTrack, is(actualTrack));
    }

    private Track getTrack() {
        Track track = new Track();
        track.setArtistName("Eminem");
        track.setArtistViewUrl("https://itunes.apple.com/us/artist/eminem/id111051?uo=4");
        track.setCoverUrl("http://is5.mzstatic.com/image/thumb/Music/v4/54/4b/00/544b0075-6ef9-5fb6-8040-0bcd9f0d5766/source/100x100bb.jpg");
        track.setTrackName("Lose Yourself");
        track.setTrackPreviewUrl("http://a1693.phobos.apple.com/us/r30/Music/v4/f1/7b/d6/f17bd6e3-55c0-b7e0-9863-bc522900e950/mzaf_5153970109972844579.aac.m4a");
        track.setTrackPrice(1.29);
        return track;
    }
}