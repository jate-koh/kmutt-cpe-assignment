package utils;

import javax.swing.*;

public enum FrameTypeCheck {
    FRAME_ACCEPTED,
    FRAME_NOT_ACCEPTED;

    public static FrameTypeCheck accept(Object frame) {
        if (frame instanceof JFrame) return FRAME_ACCEPTED;
        else if(frame instanceof JInternalFrame) return FRAME_ACCEPTED;
        else if(frame instanceof JPanel) return FRAME_ACCEPTED;
        return FRAME_NOT_ACCEPTED;
    }
}
