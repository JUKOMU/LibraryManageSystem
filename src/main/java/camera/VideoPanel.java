package camera;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.embed.swing.SwingNode;

import javax.swing.*;

public class VideoPanel {


    public SwingNode getVideoPanel() {
        final SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        return swingNode;
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            /*Swing中的调用摄像头方法*/
            Webcam webcam = Webcam.getDefault();
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            WebcamPanel panel = new WebcamPanel(webcam);
            panel.setFPSDisplayed(true);
            panel.setDisplayDebugInfo(true);
            panel.setImageSizeDisplayed(true);
            panel.setMirrored(true);
            WebcamPicker picker = new WebcamPicker();

            swingNode.setContent(panel);
        });
    }

}