package com.example.nhankhaumanager;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeAnimationMode {
    public FadeAnimationMode(Node t){
        FadeTransition fade = new FadeTransition();
        fade.setNode(t);
        fade.setDuration(Duration.millis(200));
        //fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}
