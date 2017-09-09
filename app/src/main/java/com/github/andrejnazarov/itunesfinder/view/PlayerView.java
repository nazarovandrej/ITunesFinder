package com.github.andrejnazarov.itunesfinder.view;

/**
 * @author Nazarov on 09.09.17.
 */

public interface PlayerView {

    void setSeekBarMax(int max);

    void setSeekBarProgress(int progress);

    void setPlayButtonEnabled(boolean enabled);

    void setPauseButtonEnabled(boolean enabled);
}