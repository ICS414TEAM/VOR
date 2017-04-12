/**
 * Created by ryanbarsatan on 3/21/17.
 * App and GUI for the VOR implementation
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    public static void main(String[] args){
        final VOR vor = new VOR(5, -7, 45);
        JFrame frame = new JFrame("VOR");
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //set lables for x and y cordinate
        JLabel xl = new JLabel("x coordinate");
        JLabel yl = new JLabel("y coodinate");
        JLabel headerl = new JLabel("OBS heading");
        final JLabel direction = new JLabel("Direction: ");
        final JLabel signal = new JLabel("Signal: ");
        final JTextField xcord = new JTextField(10);
        final JTextField ycord = new JTextField(10);
        final JTextField header = new JTextField(10);
        panel.add(xl);
        panel.add(xcord);
        panel.add(yl);
        panel.add(ycord);
        panel.add(headerl);
        panel.add(header);

        //add obs
        final JPanel ff = new JPanel(){
            public void paintComponent(Graphics g){
                // Draw what you want to appear on your JPanel here
                g.setColor(Color.white);
                g.fillOval(0,0,400,400);
                g.setColor(Color.black);
                g.drawLine(100, 200, 300, 200);
                g.setColor(Color.red);
                g.drawLine(vor.xline,vor.yline,vor.x2line.intValue(),vor.y2line.intValue());
            }
        };
        ff.setPreferredSize(new Dimension(400,400));
        final Graphics g = ff.getGraphics();
        final JLabel inflection = new JLabel("Degree Inflection: ");
        ff.add(inflection);

        //obs frame
        final JFrame frameobs = new JFrame("OBS");
        frameobs.add(ff);
        frameobs.setVisible(true);
        frameobs.setSize(new Dimension(400, 430));



        //add buttons
        JButton button = new JButton("GO");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vor.resetCoordinates(Integer.parseInt(xcord.getText()), Integer.parseInt(ycord.getText()), Integer.parseInt(header.getText()));
                direction.setText("Direction: " + vor.getDirection());
                signal.setText("Signal: " + vor.getSignal());

                inflection.setText("Degree Inflection: " + vor.trueinflection);
                frameobs.repaint();

            }
        });

        panel.add(button);
        panel.add(direction);
        panel.add(signal);


        container.add(panel);

        frame.add(container);
        frame.setSize(new Dimension(700, 300));
        frame.setVisible(true);

    }

}
