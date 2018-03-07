/**
 * *	This file is part of the project https://github.com/toss-dev/VoxelEngine
 * *
 * *	License is available here: https://raw.githubusercontent.com/toss-dev/VoxelEngine/master/LICENSE.md
 * *
 * *	PEREIRA Romain
 * *                                       4-----7
 * *                                      /|    /|
 * *                                     0-----3 |
 * *                                     | 5___|_6
 * *                                     |/    | /
 * *                                     1-----2
 */

package net.thegaminghuskymc.sandboxgame.game.client.resources;

import net.thegaminghuskymc.sandboxgame.engine.managers.GenericManager;
import net.thegaminghuskymc.sandboxgame.engine.managers.ResourceManager;
import net.thegaminghuskymc.sandboxgame.engine.util.math.Vector3f;
import net.thegaminghuskymc.sandboxgame.game.client.openal.ALH;
import net.thegaminghuskymc.sandboxgame.game.client.openal.ALSound;
import net.thegaminghuskymc.sandboxgame.game.client.openal.ALSourcePool;
import net.thegaminghuskymc.sandboxgame.game.client.renderer.camera.CameraView;

/** sound manager register only file sound name */
public class SoundManager extends GenericManager<String> {
    private static SoundManager _instance = null;
    /** sources pool */
    private ALSourcePool soundPool;

    public SoundManager(ResourceManager manager) {
        super(manager);
        _instance = this;
    }

    public static SoundManager instance() {
        return (_instance);
    }

    @Override
    public void onInitialized() {
        ALH.alhInit();
    }

    @Override
    public void onDeinitialized() {
        ALH.alhStop();
    }

    @Override
    public void onLoaded() {
        this.soundPool = ALH.alhGenSourcePool(32);
    }

    @Override
    public void onUnloaded() {
        this.soundPool.stopAll();
        this.soundPool.destroy();
    }

    /**
     * register a new sound
     *
     * @param sound
     *            : the filename (it has to be in folder './assets/sounds/')
     * @return
     */
    public int registerSound(String sound) {
        return (super.registerObject(sound));
    }

    /**
     * play a sound relative to source, meaning you will hear it wherever you
     * are
     */
    public void playSound(ALSound sound) {
        this.soundPool.play(sound);
    }

    /** play a sound relative to the listener */
    public void playSoundAt(ALSound sound, Vector3f pos) {
        this.soundPool.playAt(sound, pos);
    }

    /** play a sound relative to the listener */
    public void playSoundAt(ALSound sound, Vector3f pos, Vector3f velocity) {
        this.soundPool.playAt(sound, pos, velocity);
    }

    public void update(CameraView camera) {
        ALH.alhGetListener().setPosition(camera.getPosition());
        ALH.alhGetListener().setOrientation(camera.getViewVector());
    }

    @Override
    protected void onObjectRegistered(String object) {
    }
}
