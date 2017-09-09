package com.github.andrejnazarov.itunesfinder.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Nazarov on 09.09.17.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

}